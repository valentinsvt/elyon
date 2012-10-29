package elyon
class Sexo implements Serializable {
    String codigo
    String descripcion
    static mapping = {
        table 'sexo'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'sexo__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'sexo__id'
            codigo column: 'sexocdgo'
            descripcion column: 'sexodscr'
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