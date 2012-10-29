package elyon
class EstadoCivil implements Serializable {
    String codigo
    String descripcion
    static mapping = {
        table 'edcv'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'edcv__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'edcv__id'
            codigo column: 'edcvcdgo'
            descripcion column: 'edcvdscr'
        }
    }
    static constraints = {
        codigo(size: 1..1, blank: false, attributes: [title: 'codigo'])
        descripcion(size: 1..15, blank: false, attributes: [title: 'descripcion'])
    }

    String toString(){

        return this.descripcion

    }
}