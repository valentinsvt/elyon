package elyon

class Operador {

    String nombre
    String apellido
    Date fechaInicio
    Date fechaFin
    String estado

    static mapping = {
        table 'oprd'
        cache usage:'read-write', include:'non-lazy'
        id column:'oprd__id'
        id generator:'identity'
        version false
        columns {
            id column:'oprd__id'
            nombre column: 'oprdnmbr'
            apellido column: 'oprdapll'
            fechaInicio column: 'oprdfcin'
            fechaFin column: 'oprdfcfn'
            estado column: 'oprdetdo'
        }
    }
    static constraints = {
        nombre (size:1..31,blank:false,attributes:[title:'nombre'])
        apellido(size:1..31,blank:false,attributes:[title:'apellido'])
        fechaInicio(blank: false, attributes:[title: 'fechaInicio'])
        fechaFin(blank: false, attributes:[title: 'fechaFin'])
        estado(blank: false, attributes:[title: 'estado'])

    }

    String toString() {

        return this.nombre

    }
}