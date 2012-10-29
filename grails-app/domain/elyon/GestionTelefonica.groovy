package elyon

class GestionTelefonica {

    EstadoLlamada estadoLlamada
    LoteOrdenTrabajo loteOrdenTrabajo
    String telefono
    Date fecha
    String observaciones

    static mapping = {
        table 'gstf'
        cache usage:'read-write', include:'non-lazy'
        id column:'gstf__id'
        id generator:'identity'
        version false
        columns {
            id column:'gstf__id'
            estadoLlamada column: 'edtf__id'
            loteOrdenTrabajo column: 'otlt__id'
            telefono column: 'gstftelf'
            fecha column: 'gstffcha'
            observaciones column: 'gstfobsr'
        }
    }
    static constraints = {
        estadoLlamada(blank:false,attributes:[title:'estadoLlamada'])
        loteOrdenTrabajo(blank:false,attributes:[title:'loteOrdenTrabajo'])
        telefono(blank: false, attributes:[title: 'telefono'])
        fecha(blank:false, attributes:[title: 'fecha'])
        observaciones(blank: true, attributes:[title: 'observaciones'])
    }

    String toString() {

        return this.observaciones

    }
}