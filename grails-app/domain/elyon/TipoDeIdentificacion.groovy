package elyon
class TipoDeIdentificacion implements Serializable {
    String codigo
    String descripcion
    static mapping = {
        table 'tpid'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'tpid__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'tpid__id'
            codigo column: 'tpidcdgo'
            descripcion column: 'tpiddscr'
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