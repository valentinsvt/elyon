package elyon.seguridad

class Usro implements Serializable {

    String cedula
    String nombre
    String apellido
    Date fechaNacimiento
    Date fechaInicio
    Date fechaFin

    String login
    String password
    String observaciones

    String activo
    String tipo //a-> admin, u->usuario normal

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

            login column: 'usrologn'
            password column: 'usropass'
            observaciones column: 'usroobsr'

            activo column: 'usroactv'
            tipo column: 'usrotipo'
        }
    }

    static constraints = {
        cedula(size: 1..10, blank: false, nullable: false, unique: true, attributes: [title: 'Cédula'])
        nombre(size: 1..30, blank: false, nullable: false, attributes: [title: 'Nombre'])
        apellido(size: 1..30, blank: false, nullable: false, attributes: [title: 'Apellido'])
        fechaNacimiento(blank: true, nullable: true, attributes: [title: 'Fecha de Nacimiento'])
        fechaInicio(blank: true, nullable: true, attributes: [title: 'Fecha de Inicio'])
        fechaFin(blank: true, nullable: true, attributes: [title: 'Fecha de Fin'])

        login(size: 3..15, blank: false, nullable: false, unique: true, attributes: [title: 'Nombre de usuario'])
        password(size: 1..64, blank: false, nullable: false, password: true, attributes: [title: 'Contraseña para el ingreso al sistema'])
        observaciones(size: 1..255, blank: true, nullable: true, attributes: [title: 'Observaciones'])

        activo(size: 1..1, blank: false, nullable: false, attributes: [title: 'Usuario activo o no'])
        tipo(blank: false, nullable: false, inList: ["a", "u"], attributes: [title: 'Tipo de usuario: admin o normal'])
    }


    String toString() {
        return "${this.login}"
    }

}
