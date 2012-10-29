package elyon
class Data implements Serializable {

    TipoDeIdentificacion tipoDeIdentificacion
    Ruta rutaEntrega
    TipoDeIdentificacion tipoDeIdentificacionReferenciaPersonal
    TipoDeIdentificacion tipoDeIdentificacionPrincipal
    Bins binsPrincipal
    Pariente pariente
    Bins bins
    TipoTarjeta tipoTarjeta
    Afinidad afinidad
    RelacionDependenciaLaboral relacionDependenciaLaboral
    TipoVivienda tipoVivienda
    RangoIngresos rangoIngresos
    OrigenIngresos origenIngresos
    ActividadEconomica actividadEconomica
    Profesion profesion
    NivelEstudios nivelEstudios
    EstadoCivil estadoCivil
    Sexo sexo
    Nacionalidad nacionalidad
    Parroquia parroquia
    Oficina oficina
    Ruta rutaEstadosCuenta
    Vendedor vendedor
    String numeroIdentificacion
    String apellido1
    String apellido2
    String nombre1
    String nombre2
    String direccionResidencia
    String direccionTrabajo
    String telefono
    String email
    Date fechaNacimiento
    double patrimonio
    double valorVivienda
    double tiempoResidencia
    double tiempoUltimoTrabajo
    Date fechaInicioTrabajoAnterior
    Date fechaFinTrabajoAnterior
    double cargaFamiliar
    double cupo
    String nombre
    String apellido1ReferenciaPersonal
    String apellido2ReferenciaPersonal
    String nombre1ReferenciaPersonal
    String nombre2ReferenciaPersonal
    String idReferenciaPersonal
    String direccionReferenciaPersonal
    String telefonoReferenciaPersonal
    String indicadorPrincipal
    LoteOrdenTrabajo loteOrdenTrabajo


    static mapping = {
        table 'data'
        cache usage:'read-write', include:'non-lazy'
        id column:'data__id'
        id generator:'identity'
        version false
        columns {
            id column:'data__id'

            tipoDeIdentificacion column: 'tpid__id'
            rutaEntrega column: 'ruta__id'
            tipoDeIdentificacionReferenciaPersonal column: 'rfprtpid'
            tipoDeIdentificacionPrincipal column: 'prnctpid'
            pariente column: 'prnt__id'
            bins column: 'bins__id'
            tipoTarjeta column: 'tptj__id'
            afinidad column: 'afnd__id'
            binsPrincipal column: 'prnc_bin'
            relacionDependenciaLaboral column: 'rldp__id'
            tipoVivienda column: 'tpvv__id'
            rangoIngresos column: 'rgig__id'
            origenIngresos column: 'orig__id'
            actividadEconomica column: 'acec__id'
            profesion column: 'prof__id'
            nivelEstudios column: 'nves__id'
            estadoCivil column: 'edcv__id'
            sexo column: 'sexo__id'
            nacionalidad column: 'ncnl__id'
            parroquia column: 'parr__id'
            oficina column: 'ofic__id'
            rutaEstadosCuenta column: 'rutaedcn'
            vendedor column: 'vend__id'
            numeroIdentificacion column: 'datanmid'
            apellido1 column: 'dataap01'
            apellido2 column: 'dataap02'
            nombre1 column: 'datanb01'
            nombre2 column: 'databn02'
            direccionResidencia column: 'datadrrs'
            direccionTrabajo column: 'datadrtb'
            telefono column: 'datatelf'
            email column: 'datamail'
            fechaNacimiento column: 'datafcna'
            patrimonio column: 'datapatr'
            valorVivienda column: 'datavlvv'
            tiempoResidencia column: 'datatirs'
            tiempoUltimoTrabajo column: 'datatiut'
            fechaInicioTrabajoAnterior column: 'datafcit'
            fechaFinTrabajoAnterior column: 'datafcft'
            cargaFamiliar column: 'datacrga'
            cupo column: 'datacupo'
            nombre column: 'datanmbr'
            idReferenciaPersonal column: 'datarfpr'
            apellido1ReferenciaPersonal column: 'dataa1rp'
            apellido2ReferenciaPersonal column: 'dataa2rf'
            nombre1ReferenciaPersonal column: 'datan1rp'
            nombre2ReferenciaPersonal column: 'datan2rp'
            direccionReferenciaPersonal column: 'datadrrp'
            telefonoReferenciaPersonal column: 'datatfrp'
            indicadorPrincipal column: 'datainpr'
            loteOrdenTrabajo column: 'otlt__id'
        }
    }
    static constraints = {

        tipoDeIdentificacion( blank:true, nullable:true ,attributes:[title:'tipoDeIdentificacion'])
        rutaEntrega( blank:true, nullable:true ,attributes:[title:'ruta'])
        tipoDeIdentificacionReferenciaPersonal( blank:true, nullable:true ,attributes:[title:'tipoDeIdentificacionReferenciaPersonal'])
        tipoDeIdentificacionPrincipal( blank:true, nullable:true ,attributes:[title:'tipoDeIdentificacionPrincipal'])
        pariente( blank:true, nullable:true ,attributes:[title:'pariente'])
        bins( blank:true, nullable:true ,attributes:[title:'bins'])
        tipoTarjeta( blank:true, nullable:true ,attributes:[title:'tipoTarjeta'])
        afinidad( blank:true, nullable:true ,attributes:[title:'afinidad'])
        binsPrincipal( blank:true, nullable:true ,attributes:[title:'binsPrincipal'])
        relacionDependenciaLaboral( blank:true, nullable:true ,attributes:[title:'relacionDependenciaLaboral'])
        tipoVivienda( blank:true, nullable:true ,attributes:[title:'tipoVivienda'])
        rangoIngresos( blank:true, nullable:true ,attributes:[title:'rangoIngresos'])
        origenIngresos( blank:true, nullable:true ,attributes:[title:'origenIngresos'])
        actividadEconomica( blank:true, nullable:true ,attributes:[title:'actividadEconomica'])
        profesion( blank:true, nullable:true ,attributes:[title:'profecion'])
        nivelEstudios( blank:true, nullable:true ,attributes:[title:'nivelEstudios'])
        estadoCivil( blank:true, nullable:true ,attributes:[title:'estadoCivil'])
        sexo( blank:true, nullable:true ,attributes:[title:'sexo'])
        nacionalidad( blank:true, nullable:true ,attributes:[title:'nacionales'])
        parroquia( blank:true, nullable:true ,attributes:[title:'parroquia'])
        oficina( blank:true, nullable:true ,attributes:[title:'oficina'])
        rutaEstadosCuenta( blank:true, nullable:true ,attributes:[title:'ruta'])
        vendedor( blank:true, nullable:true ,attributes:[title:'vendedor'])
        numeroIdentificacion(size:1..10, blank:true, nullable:true ,attributes:[title:'numeroIdentificacion'])
        apellido1(size:1..20, blank:true, nullable:true ,attributes:[title:'apellido1'])
        apellido2(size:1..20, blank:true, nullable:true ,attributes:[title:'apellido2'])
        nombre1(size:1..20, blank:true, nullable:true ,attributes:[title:'nombre1'])
        nombre2(size:1..20, blank:true, nullable:true ,attributes:[title:'nombre2'])
        direccionResidencia(size:1..150, blank:true, nullable:true ,attributes:[title:'direccionResidencia'])
        direccionTrabajo(size:1..150, blank:true, nullable:true ,attributes:[title:'direccionTrabajo'])
        telefono(size:1..20, blank:true, nullable:true ,attributes:[title:'telefono'])
        email(size:1..50, blank:true, nullable:true ,attributes:[title:'email'])
        fechaNacimiento( blank:true, nullable:true ,attributes:[title:'fechaNacimiento'])
        patrimonio( blank:true, nullable:true ,attributes:[title:'patrimonio'])
        valorVivienda( blank:true, nullable:true ,attributes:[title:'valorVivienda'])
        tiempoResidencia( blank:true, nullable:true ,attributes:[title:'tiempoResidencia'])
        tiempoUltimoTrabajo( blank:true, nullable:true ,attributes:[title:'tiempoUltimoTrabajo'])
        fechaInicioTrabajoAnterior( blank:true, nullable:true ,attributes:[title:'fechaInicioTrabajoAnterior'])
        fechaFinTrabajoAnterior( blank:true, nullable:true ,attributes:[title:'fechaFinTrabajoAnterior'])
        cargaFamiliar( blank:true, nullable:true ,attributes:[title:'cargaFamiliar'])
        cupo( blank:true, nullable:true ,attributes:[title:'cupo'])
        nombre(size:1..19, blank:true, nullable:true ,attributes:[title:'nombre'])
        idReferenciaPersonal(size:1..10, blank:true, nullable:true ,attributes:[title:'tipoDeIdentificacion'])
        apellido1ReferenciaPersonal(size:1..20, blank:true, nullable:true ,attributes:[title:'apellido1ReferenciaPersonal'])
        apellido2ReferenciaPersonal(size:1..20, blank:true, nullable:true ,attributes:[title:'apellido2ReferenciaPersonal'])
        nombre1ReferenciaPersonal(size:1..20, blank:true, nullable:true ,attributes:[title:'nombre1ReferenciaPersonal'])
        nombre2ReferenciaPersonal(size:1..20, blank:true, nullable:true ,attributes:[title:'nombre2ReferenciaPersonal'])
        direccionReferenciaPersonal(size:1..150, blank:true, nullable:true ,attributes:[title:'direccionReferenciaPersonal'])
        telefonoReferenciaPersonal(size:1..20, blank:true, nullable:true ,attributes:[title:'telefonoReferenciaPersonal'])
        indicadorPrincipal(size:1..10, blank:true, nullable:true ,attributes:[title:'indicadorPrincipal'])
        loteOrdenTrabajo(blank:true, attributes:[title: 'loteOrdenTrabajo'])
    }

    String toString(){

        return this.nombre

    }
}