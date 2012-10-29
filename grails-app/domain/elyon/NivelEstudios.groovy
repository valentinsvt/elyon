package elyon
class NivelEstudios implements Serializable {
    String codigo
    String descripcion
    static mapping = {
        table 'nves'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'nves__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'nves__id'
            codigo column: 'nvescdgo'
            descripcion column: 'nvesdscr'
        }
    }
    static constraints = {
        codigo(size: 1..2, blank: false, attributes: [title: 'codigo'])
        descripcion(size: 1..31, blank: false, attributes: [title: 'descripcion'])
    }

    String toString(){

        return this.descripcion

    }
}