package elyon

class ActividadEconomica {
    String codigo
    String descripcion

    static mapping = {
        table 'acec'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'acec__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'acec__id'
            codigo column: 'aceccdgo'
            descripcion column: 'acecdscr'
        }
    }

    static constraints = {
        codigo(maxSize: 7, blank: false, nullable: false, attributes: [title: 'codigo'])
        descripcion(maxSize: 150, blank: false, nullable: false, attributes: [title: 'descripcion'])
    }

    String toString(){

        return this.descripcion

    }

}
