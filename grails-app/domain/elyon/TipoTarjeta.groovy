package elyon
class TipoTarjeta implements Serializable {
    String codigo
    String descripcion
    static mapping = {
        table 'tptj'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'tptj__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'tptj__id'
            codigo column: 'tptjcdgo'
            descripcion column: 'tptjdscr'
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