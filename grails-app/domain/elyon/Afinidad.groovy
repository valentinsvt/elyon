package elyon

class Afinidad {
    Bins bins
    String codigo
    String descripcion

    static mapping = {
        table 'afnd'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'afnd__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'afnd__id'
            bins column: 'bins__id'
            codigo column: 'afndcdgo'
            descripcion column: 'afnddscr'
        }
    }

    static constraints = {
        bins(blank: true, nullable: true, attributes: [title: 'bins'])
        codigo(maxSize: 3, blank: false, nullable: false, attributes: [title: 'codigo'])
        descripcion(maxSize: 63, blank: false, nullable: false, attributes: [title: 'descripcion'])
    }

    String toString() {
        return this.descripcion
    }


}
