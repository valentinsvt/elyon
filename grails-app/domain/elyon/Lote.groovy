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
    String telefonoTrabajoAnterior
    String ciudad
    String tipoTarjeta
    double cupo1
    String codigo
    double cupo2
    String tipoCliente
    String observaciones
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
            telefonoTrabajoAnterior column: 'lotetban'
            ciudad column: 'lotecdad'
            tipoTarjeta column: 'lotetptj'
            cupo1 column: 'lotecup1'
            codigo column: 'lotecdgo'
            cupo2 column: 'lotecup2'
            tipoCliente column: 'lotetpcl'
            observaciones column: 'loteobsr'
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
        telefonoTrabajoAnterior(size: 1..15, blank: true, nullable: true, attributes: [title: 'telefonoTrabajoAnterior'])
        ciudad(size: 1..63, blank: true, nullable: true, attributes: [title: 'ciudad'])
        tipoTarjeta(size: 1..15, blank: true, nullable: true, attributes: [title: 'tipoTarjeta'])
        cupo1(blank: true, nullable: true, attributes: [title: 'cupo1'])
        codigo(size: 1..15, blank: true, nullable: true, attributes: [title: 'codigo'])
        cupo2(blank: true, nullable: true, attributes: [title: 'cupo2'])
        tipoCliente(size: 1..15, blank: true, nullable: true, attributes: [title: 'tipoCliente'])
        observaciones(size: 1..127, blank: true, nullable: true, attributes: [title: 'observaciones'])
    }

    String toString(){

        return this.nombre

    }
}