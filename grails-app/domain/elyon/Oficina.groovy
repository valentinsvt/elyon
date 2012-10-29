package elyon
class Oficina implements Serializable {
    Sucursal sucursal
    String codigo
    String descripcion
    static mapping = {
        table 'ofic'
        cache usage:'read-write', include:'non-lazy'
        id column:'ofic__id'
        id generator:'identity'
        version false
        columns {
            id column:'ofic__id'
            sucursal column: 'sucr__id'
            codigo column: 'oficcdgo'
            descripcion column: 'oficdscr'
        }
    }
    static constraints = {
        sucursal( blank:true, nullable:true ,attributes:[title:'sucursal'])
        codigo(size:1..3,blank:false,attributes:[title:'codigo'])
        descripcion(size:1..31, blank:true, nullable:true ,attributes:[title:'descripcion'])
    }

    String toString(){

        return this.descripcion

    }
}