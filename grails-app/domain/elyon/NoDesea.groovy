package elyon

class NoDesea implements Serializable {


    String descripcion


    static mapping = {
        table 'nods'
        cache usage:'read-write', include:'non-lazy'
        id column:'nods__id'
        id generator:'identity'
        version false
        columns {
            id column:'nods__id'
            descripcion column: 'nodsdscr'

        }
    }


    static constraints = {


        descripcion(size:1..63, blank:true, nullable:true ,attributes:[title:'descripcion'])



    }


    String toString(){

        return this.descripcion

    }
}
