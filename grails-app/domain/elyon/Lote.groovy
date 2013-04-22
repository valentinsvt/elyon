package elyon
class Lote implements Serializable {
    String cedula
    String nombre
    String direccion1
    String direccion2
    String telefono1
    String telefono2
    String telefono3
    String telefono4
    String telefono5
    String telefono6
    String telefono7
    String telefonoTrabajoAnterior
    String ciudad
    String tipoTarjeta
    double cupoNormal
    String codigo
    double cupoTotal     // no se usa
    String tipoCliente
    String observaciones
    Campana campana
    OrdenDeTrabajo ordenDeTrabajo
    EstadoGestion estadoGestion
    String estado=""      /*N no mostrar en las listas... cualquier otra cosa si mostrar*/
    NoDesea noDesea


    static mapping = {
        table 'lote'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'lote__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'lote__id'
            cedula column: 'lotecdla'
            nombre column: 'lotenmbr'
            direccion1 column: 'lotedir1'
            direccion2 column: 'lotedir2'
            telefono1 column: 'lotetlf1'
            telefono2 column: 'lotetlf2'
            telefono3 column: 'lotetlf3'
            telefono4 column: 'lotetlf4'
            telefono5 column: 'lotetlf5'
            telefono6 column: 'lotetlf6'
            telefono7 column: 'lotetlf7'
            telefonoTrabajoAnterior column: 'lotetban'
            ciudad column: 'lotecdad'
            tipoTarjeta column: 'lotetptj'
            cupoNormal column: 'lotecup1'
            codigo column: 'lotecdgo'
            cupoTotal column: 'lotecup2'
            tipoCliente column: 'lotetpcl'
            observaciones column: 'loteobsr'
            campana column: 'camp__id'
            ordenDeTrabajo column: 'ortb__id'
            estadoGestion column: 'edgs__id'
            estado column: 'loteedto'
            noDesea column: 'nods__id'
        }
    }
    static constraints = {
        cedula(size: 1..10, blank: true, nullable: true, attributes: [title: 'cedula'])
        nombre(size: 1..63, blank: true, nullable: true, attributes: [title: 'nombre'])
        direccion1(size: 1..127, blank: true, nullable: true, attributes: [title: 'direccion1'])
        direccion2(size: 1..127, blank: true, nullable: true, attributes: [title: 'direccion2'])
        telefono1(size: 1..15, blank: true, nullable: true, attributes: [title: 'telefono1'])
        telefono2(size: 1..15, blank: true, nullable: true, attributes: [title: 'telefono2'])
        telefono3(size: 1..15, blank: true, nullable: true, attributes: [title: 'telefono3'])
        telefono4(size: 1..15, blank: true, nullable: true, attributes: [title: 'telefono4'])
        telefono5(size: 1..15, blank: true, nullable: true, attributes: [title: 'telefono5'])
        telefono6(size: 1..15, blank: true, nullable: true, attributes: [title: 'telefono6'])
        telefono7(size: 1..15, blank: true, nullable: true, attributes: [title: 'telefono7'])
        telefonoTrabajoAnterior(size: 1..15, blank: true, nullable: true, attributes: [title: 'telefonoTrabajoAnterior'])
        ciudad(size: 1..63, blank: true, nullable: true, attributes: [title: 'ciudad'])
        tipoTarjeta(size: 1..15, blank: true, nullable: true, attributes: [title: 'tipoTarjeta'])
        cupoNormal(blank: true, nullable: true, attributes: [title: 'cupoNormal'])
        codigo(size: 1..15, blank: true, nullable: true, attributes: [title: 'codigo'])
        cupoTotal(blank: true, nullable: true, attributes: [title: 'cupoTotal'])
        tipoCliente(size: 1..15, blank: true, nullable: true, attributes: [title: 'tipoCliente'])
        observaciones(size: 1..127, blank: true, nullable: true, attributes: [title: 'observaciones'])
        campana(blank: true, nullable: true, attributes:[title: 'campana'])
        ordenDeTrabajo(blank:true,nullable: true,attributes:[title: 'Orden de trabajo'])
        estadoGestion(blank:true,nullable: true,attributes:[title: 'Estado de gestión'])
        estado(blank: true,nullable: true,size: 1..1)

        noDesea(blank: true, nullable: true, attributes: [title: 'noDesea'])

    }

    String toString(){

        return this.nombre

    }
}