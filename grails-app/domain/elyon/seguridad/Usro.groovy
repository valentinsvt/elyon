package elyon.seguridad

class Usro implements Serializable {

    String cedula
    String nombre
    String apellido
    Date fechaNacimiento
    Date fechaInicio
    Date fechaFin
    String cargo

    String login
    String password
    String observaciones

    int activo

    static auditable = [ignore: ['password']]

    static mapping = {
        table 'usro'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'usro__id'
        id generator: 'identity'
        version false

        columns {
            cedula column: 'usrocdla'
            nombre column: 'usronmbr'
            apellido column: 'usroapll'
            fechaNacimiento column: 'usrofcna'
            fechaInicio column: 'usrofcin'
            fechaFin column: 'usrofcfn'
            cargo column: 'usrocrgo'

            login column: 'usrologn'
            password column: 'usropass'
            observaciones column: 'usroobsr'

            activo column: 'usroactv'
        }
    }

    static constraints = {
        cedula(size: 1..10, attributes: [title: 'cedula'])
        nombre(size: 1..30, attributes: [title: 'nombre'])
        apellido(size: 1..30, attributes: [title: 'apellido'])
        fechaNacimiento(blank: true, nullable: true, attributes: [title: 'fechaNacimiento'])
        fechaInicio(blank: true, nullable: true, attributes: [title: 'fechaInicio'])
        fechaFin(blank: true, nullable: true, attributes: [title: 'fechaFin'])
        cargo(size: 1..50, blank: true, nullable: true, attributes: [title: 'cargo'])

        login(size: 1..15, blank: false, nullable: false, unique: true, attributes: [title: 'Nombre de usuario'])
        password(size: 1..64, blank: false, nullable: false, password: true, attributes: [title: 'Contraseña para el ingreso al sistema'])
        observaciones(size: 1..255, blank: true, nullable: true, attributes: [title: 'Observaciones'])

        activo(size: 1..1, blank: false, nullable: false, attributes: [title: 'Usuario activo o no'])
    }


    String toString() {
        return "${this.login}"
    }

}
