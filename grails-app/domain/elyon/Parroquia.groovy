package elyon
class Parroquia implements Serializable {
    Ciudad ciudad
    String codigo
    String descripcion
    static mapping = {
        table 'parr'
        cache usage:'read-write', include:'non-lazy'
        id column:'parr__id'
        id generator:'identity'
        version false
        columns {
            id column:'parr__id'
            ciudad column: 'cdad__id'
            codigo column: 'parrcdgo'
            descripcion column: 'parrdscr'
        }
    }
    static constraints = {
        ciudad( blank:true, nullable:true ,attributes:[title:'ciudad'])
        codigo(size:1..6,blank:false,attributes:[title:'codigo'])
        descripcion(size:1..63,blank:false,attributes:[title:'descripcion'])
    }

    String toString(){

        return this.descripcion

    }
}