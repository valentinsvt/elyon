package elyon
class Ruta implements Serializable {
    String codigo
    String descripcion
    static mapping = {
        table 'ruta'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'ruta__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'ruta__id'
            codigo column: 'rutacdgo'
            descripcion column: 'rutadscr'
        }
    }
    static constraints = {
        codigo(size: 1..1, blank: false, attributes: [title: 'codigo'])
        descripcion(size: 1..31, blank: false, attributes: [title: 'descripcion'])
    }

   String toString(){

       return this.descripcion

   }
}