package elyon

class Campana {


    String descripcion
    static mapping = {
        table 'camp'
        cache usage:'read-write', include:'non-lazy'
        id column:'camp__id'
        id generator:'identity'
        version false
        columns {
            id column:'camp__id'
            descripcion column: 'campdscr'
        }
    }
    static constraints = {
        descripcion(size:1..63,blank:false,attributes:[title:'descripcion'])
    }

    String toString() {

        return this.descripcion

    }
}