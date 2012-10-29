package elyon

class Ciudad {

    String codigo
    String descripcion
    static mapping = {
        table 'cdad'
        cache usage:'read-write', include:'non-lazy'
        id column:'cdad__id'
        id generator:'identity'
        version false
        columns {
            id column:'cdad__id'
            codigo column: 'cdadcdgo'
            descripcion column: 'cdaddscr'
        }
    }
    static constraints = {
        codigo(size:1..2,blank:false,attributes:[title:'codigo'])
        descripcion(size:1..50,blank:false,attributes:[title:'descripcion'])
    }

    String toString() {

        return this.descripcion

    }
}
