package elyon.seguridad
class Persona implements Serializable {
    int codigo
    String cedula
    String nombre
    String apellido
    Date fechaNacimiento
    Date fechaInicio
    Date fechaFin
    String cargo
    static mapping = {
        table 'prsn'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'prsn__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'prsn__id'
            codigo column: 'prsncdgo'
            cedula column: 'prsncdla'
            nombre column: 'prsnnmbr'
            apellido column: 'prsnapll'
            fechaNacimiento column: 'prsnfcna'
            fechaInicio column: 'prsnfcin'
            fechaFin column: 'prsnfcfn'
            cargo column: 'prsncrgo'
        }
    }
    static constraints = {
        cedula(size: 1..10, attributes: [title: 'cedula'])
        nombre(size: 1..30, attributes: [title: 'nombre'])
        apellido(size: 1..30, attributes: [title: 'apellido'])
        codigo(blank: false, attributes: [title: 'numero'])
        fechaNacimiento(blank: true, nullable: true, attributes: [title: 'fechaNacimiento'])
        fechaInicio(blank: true, nullable: true, attributes: [title: 'fechaInicio'])
        fechaFin(blank: true, nullable: true, attributes: [title: 'fechaFin'])
        cargo(size: 1..50, blank: true, nullable: true, attributes: [title: 'cargo'])
    }
}