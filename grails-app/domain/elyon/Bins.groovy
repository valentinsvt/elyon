package elyon

class Bins {
    String codigo
    String descripcion

    static mapping = {
        table 'bins'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'bins__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'bins__id'
            codigo column: 'binscdgo'
            descripcion column: 'binsdscr'
        }
    }

    static constraints = {
        codigo(maxSize: 6, blank: false, nullable: false, attributes: [title: 'codigo'])
        descripcion(maxSize: 31, blank: false, nullable: false, attributes: [title: 'descripcion'])
    }

    String toString(){

        return this.descripcion

    }

}
