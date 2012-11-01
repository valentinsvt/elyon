package elyon

import elyon.seguridad.Usro

class OrdenDeTrabajo {

    Campana campana
    Usro usro
    int numero =0

    static mapping = {
        table 'ortb'
        cache usage:'read-write', include:'non-lazy'
        id column:'ortb__id'
        id generator:'identity'
        version false
        columns {
            id column:'ortb__id'
            campana column: 'camp__id'
            usro column: 'usro__id'
            numero column: 'ortbnmro'
        }
    }
    static constraints = {
        campana(blank:false,attributes:[title:'campaña'])
        usro(blank:false,attributes:[title:'usuario'])
        numero(blank:false, attributes:[title: 'numero'])
    }


}