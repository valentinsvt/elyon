package elyon

class LoteOrdenTrabajo {

    Lote lote
    OrdenDeTrabajo ordenDeTrabajo
    EstadoGestion estadoGestion

    static mapping = {
        table 'otlt'
        cache usage:'read-write', include:'non-lazy'
        id column:'otlt__id'
        id generator:'identity'
        version false
        columns {
            id column:'otlt__id'
            lote column: 'lote__id'
            ordenDeTrabajo column: 'ortb__id'
            estadoGestion column: 'edgs__id'
        }
    }
    static constraints = {
        lote(blank:false,attributes:[title:'lote'])
        ordenDeTrabajo(blank:false,attributes:[title:'ordendeTrabajo'])
        estadoGestion(blank:false, attributes:[title: 'estadoGestion'])
    }

//    String toString() {
//
//        return this.descripcion
//
//    }
}