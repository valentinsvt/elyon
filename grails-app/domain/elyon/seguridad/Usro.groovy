package elyon.seguridad

class Usro implements Serializable {
    Persona persona
    String login
    String password
    String autorizacion
    String sigla
    int activo
    Date fechaPass
    String observaciones

    static auditable = [ignore: ['password']]

    static mapping = {
        table 'usro'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'usro__id'
        id generator: 'identity'
        version false

        columns {
            persona column: 'prsn__id'
            login column: 'usrologn'
            password column: 'usropass'
            autorizacion column: 'usroatrz'
            sigla column: 'usrosgla'
            activo column: 'usroactv'
            fechaPass column: 'usrofcps'
            observaciones column: 'usroobsr'
        }
    }

    static constraints = {
        persona(blank: false, nullable: false, attributes: [title: 'Persona'], unique: true)
        login(size: 1..15, blank: false, nullable: false, unique: true, attributes: [title: 'Nombre de usuario'])
        password(size: 1..64, blank: false, nullable: false, password: true, attributes: [title: 'Contraseña para el ingreso al sistema'])
        autorizacion(size: 1..255, blank: false, nullable: false, password: true, attributes: [title: 'Contraseña para autorizaciones'])
        sigla(size: 1..8, blank: false, nullable: false, attributes: [title: 'Sigla del usuario'])
        activo(size: 1..1, blank: false, nullable: false, attributes: [title: 'Usuario activo o no'])
        fechaPass(blank: true, nullable: true, attributes: [title: 'Fecha de cambio de contraseña'])
        observaciones(size: 1..255, blank: true, nullable: true, attributes: [title: 'Observaciones'])
    }


    String toString() {
        return "${this.login}"
    }

}
