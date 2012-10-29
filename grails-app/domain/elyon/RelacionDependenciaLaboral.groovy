package elyon
class RelacionDependenciaLaboral implements Serializable {
    String codigo
    String descripcion
    static mapping = {
        table 'rldp'
        cache usage:'read-write', include:'non-lazy'
        id column:'rldp__id'
        id generator:'identity'
        version false
        columns {
            id column:'rldp__id'
            codigo column: 'rldpcdgo'
            descripcion column: 'rldpdscr'
        }
    }
    static constraints = {
        codigo(size:1..2,blank:false,attributes:[title:'codigo'])
        descripcion(size:1..15,blank:false,attributes:[title:'descripcion'])
    }


    String toString(){

        return this.descripcion

    }
}