package elyon
class Sucursal implements Serializable {
    Ciudad ciudad
    String codigo
    String descripcion
    static mapping = {
        table 'sucr'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'sucr__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'sucr__id'
            ciudad column: 'cdad__id'
            codigo column: 'sucrcdgo'
            descripcion column: 'sucrdscr'
        }
    }
    static constraints = {
        ciudad(blank: true, nullable: true, attributes: [title: 'ciudad'])
        codigo(size: 1..3, blank: false, attributes: [title: 'codigo'])
        descripcion(size: 1..31, blank: true, nullable: true, attributes: [title: 'descripcion'])
    }

    String toString(){

        return this.descripcion
    }

}