package elyon

class EstadoGestion {

    String codigo
    String descripcion
    String lista
    static mapping = {
        table 'edgs'
        cache usage:'read-write', include:'non-lazy'
        id column:'edgs__id'
        id generator:'identity'
        version false
        columns {
            id column:'edgs__id'
            codigo column: 'edgscdgo'
            descripcion column: 'edgsdscr'
            lista column: 'edgslsta'
        }
    }
    static constraints = {
        codigo(size:1..5,blank:false,attributes:[title:'codigo'])
        descripcion(size:1..31,blank:false,attributes:[title:'descripcion'])
        lista(size: 1..1, blank: false, attributes:[title: 'lista'])
    }

    String toString() {

        return this.descripcion

    }
}