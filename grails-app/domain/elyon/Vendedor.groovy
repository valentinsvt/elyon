package elyon
class Vendedor implements Serializable {
    String codigo
    String descripcion
    static mapping = {
        table 'vend'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'vend__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'vend__id'
            codigo column: 'vendcdgo'
            descripcion column: 'venddscr'
        }
    }
    static constraints = {
        codigo(size: 1..5, blank: false, attributes: [title: 'codigo'])
        descripcion(size: 1..31, blank: false, attributes: [title: 'descripcion'])
    }

    String toString(){

        return this.descripcion
    }

}