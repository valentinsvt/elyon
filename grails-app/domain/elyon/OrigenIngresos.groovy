package elyon
class OrigenIngresos implements Serializable {
    String codigo
    String descripcion
    static mapping = {
        table 'orig'
        cache usage:'read-write', include:'non-lazy'
        id column:'orig__id'
        id generator:'identity'
        version false
        columns {
            id column:'orig__id'
            codigo column: 'origcdgo'
            descripcion column: 'origdscr'
        }
    }
    static constraints = {
        codigo(size:1..2,blank:false,attributes:[title:'codigo'])
        descripcion(size:1..63,blank:false,attributes:[title:'descripcion'])
    }


    String toString(){

           return this.descripcion
    }
}