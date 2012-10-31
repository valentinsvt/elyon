package elyon
class Parentesco implements Serializable {
    String codigo
    String descripcion
    static mapping = {
        table 'prnt'
        cache usage:'read-write', include:'non-lazy'
        id column:'prnt__id'
        id generator:'identity'
        version false
        columns {
            id column:'prnt__id'
            codigo column: 'prntcdgo'
            descripcion column: 'prntdscr'
        }
    }
    static constraints = {
        codigo(size:1..2,blank:false,attributes:[title:'codigo'])
        descripcion(size:1..31,blank:false,attributes:[title:'descripcion'])
    }

    String toString(){

        return this.descripcion

    }
}