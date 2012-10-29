package elyon
class Profesion implements Serializable {
    String codigo
    String descripcion
    static mapping = {
        table 'prof'
        cache usage:'read-write', include:'non-lazy'
        id column:'prof__id'
        id generator:'identity'
        version false
        columns {
            id column:'prof__id'
            codigo column: 'profcdgo'
            descripcion column: 'profdscr'
        }
    }
    static constraints = {
        codigo(size:1..3,blank:false,attributes:[title:'codigo'])
        descripcion(size:1..31,blank:false,attributes:[title:'descripcion'])
    }

    String toString(){

        return this.descripcion
    }
}