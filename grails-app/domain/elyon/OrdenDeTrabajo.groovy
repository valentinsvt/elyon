package elyon

class OrdenDeTrabajo {

    Campana campana
    Operador operador
    int numero


    static mapping = {
        table 'ortb'
        cache usage:'read-write', include:'non-lazy'
        id column:'ortb__id'
        id generator:'identity'
        version false
        columns {
            id column:'ortb__id'
            campana column: 'camp__id'
            operador column: 'oprd__id'
            numero column: 'ortbnmro'
        }
    }
    static constraints = {
        campana(blank:false,attributes:[title:'campaña'])
        operador(blank:false,attributes:[title:'operador'])
        numero(blank:false, attributes:[title: 'numero'])
    }

//    String toString() {
//
//        return this.numero
//
//    }
}