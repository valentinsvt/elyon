package elyon
class TipoVivienda implements Serializable {
    String codigo
    String descripcion
    static mapping = {
        table 'tpvv'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'tpvv__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'tpvv__id'
            codigo column: 'tpvvcdgo'
            descripcion column: 'tpvvdscr'
        }
    }
    static constraints = {
        codigo(size: 1..2, blank: false, attributes: [title: 'codigo'])
        descripcion(size: 1..15, blank: false, attributes: [title: 'descripcion'])
    }


    String toString(){

        return this.descripcion
    }
}