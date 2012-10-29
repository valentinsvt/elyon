package elyon
class RangoIngresos implements Serializable {
    String codigo
    String descripcion
    String rango
    static mapping = {
        table 'rgig'
        cache usage:'read-write', include:'non-lazy'
        id column:'rgig__id'
        id generator:'identity'
        version false
        columns {
            id column:'rgig__id'
            codigo column: 'rgigcdgo'
            descripcion column: 'rgigdscr'
            rango column: 'rgigrngo'
        }
    }
    static constraints = {
        codigo(size:1..1,blank:false,attributes:[title:'codigo'])
        descripcion(size:1..15,blank:false,attributes:[title:'descripcion'])
        rango(size:1..15,blank:false,attributes:[title:'rango'])
    }

    String toString(){

        return this.descripcion

    }
}