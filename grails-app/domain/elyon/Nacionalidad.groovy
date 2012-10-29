package elyon
class Nacionalidad implements Serializable {
    String codigo
    String descripcion
    static mapping = {
        table 'ncnl'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'ncnl__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'ncnl__id'
            codigo column: 'ncnlcdgo'
            descripcion column: 'ncnldscr'
        }
    }
    static constraints = {
        codigo(size: 1..3, blank: false, attributes: [title: 'codigo'])
        descripcion(size: 1..31, blank: false, attributes: [title: 'descripcion'])
    }

    String toString(){

        return this.descripcion

    }
}