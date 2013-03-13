package elyon
class Data implements Serializable {

    TipoDeIdentificacion tipoDeIdentificacion
    String numeroIdentificacion
    String nombre1

    String nombre2
    String apellido1
    String apellido2

    Date fechaNacimiento
    EstadoCivil estadoCivil
    Sexo sexo

    String nombre
    Nacionalidad nacionalidad
    Ruta rutaEntrega

    String direccionResidencia
    Ruta rutaEstadosCuenta

    String direccionTrabajo
    Profesion profesion

    NivelEstudios nivelEstudios
    Integer cargaFamiliar
    String telefono

    TipoTarjeta tipoTarjeta
    Bins bins
    double cupo



    Oficina oficina

    Parroquia parroquia
    String telefonoTrabajo /**/
    String email

    String empresa /**/
    ActividadEconomica actividadEconomica
    RangoIngresos rangoIngresos

    String celular /**/

    TipoVivienda tipoVivienda
    Integer valorVivienda
    Date fechaInicioResidencia /**/

    RelacionDependenciaLaboral relacionDependenciaLaboral
    OrigenIngresos origenIngresos
    double patrimonio

    Date fechaInicioTrabajoActual /**/
    Date fechaInicioTrabajoAnterior
    Date fechaFinTrabajoAnterior




    TipoDeIdentificacion tipoDeIdentificacionReferenciaPersonal
    String idReferenciaPersonal
    String nombre1ReferenciaPersonal

    String nombre2ReferenciaPersonal
    String apellido1ReferenciaPersonal
    String apellido2ReferenciaPersonal

    String direccionReferenciaPersonal
    String telefonoReferenciaPersonal
    Parentesco parentesco

    String comentarios /**/
    String contactoAlterno /**/

    TipoDeIdentificacion tipoDeIdentificacionPrincipal
    String identificacionPrincipal
    Bins binsPrincipal
    Afinidad afinidad


    Vendedor vendedor
    Lote lote


    static mapping = {
        table 'data'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'data__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'data__id'

            tipoDeIdentificacion column: 'tpid__id'
            rutaEntrega column: 'ruta__id'
            tipoDeIdentificacionReferenciaPersonal column: 'rfprtpid'
            tipoDeIdentificacionPrincipal column: 'prnctpid'
            parentesco column: 'prnt__id'
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
            identificacionPrincipal column: 'datainpr'
            lote column: 'lote__id'

            empresa column: 'dataempr'
            celular column: 'datacllr'
            telefonoTrabajo column: 'datatltr'
            fechaInicioResidencia column: 'datafirs'
            fechaInicioTrabajoActual column: 'datafita'

            comentarios column: 'datacmtr'
            contactoAlterno column: 'datacnal'
        }
    }
    static constraints = {

        tipoDeIdentificacion(blank: true, nullable: true, attributes: [title: 'tipoDeIdentificacion'])
        numeroIdentificacion(size: 1..10, blank: true, nullable: true, attributes: [title: 'numeroIdentificacion'])
        nombre1(size: 1..20, blank: true, nullable: true, attributes: [title: 'nombre1'])

        nombre2(size: 1..20, blank: true, nullable: true, attributes: [title: 'nombre2'])
        apellido1(size: 1..20, blank: true, nullable: true, attributes: [title: 'apellido1'])
        apellido2(size: 1..20, blank: true, nullable: true, attributes: [title: 'apellido2'])

        fechaNacimiento(blank: true, nullable: true, attributes: [title: 'fechaNacimiento'])
        estadoCivil(blank: true, nullable: true, attributes: [title: 'estadoCivil'])
        sexo(blank: true, nullable: true, attributes: [title: 'sexo'])

        nombre(size: 1..19, blank: true, nullable: true, attributes: [title: 'nombre'])
        nacionalidad(blank: true, nullable: true, attributes: [title: 'nacionales'])
        rutaEntrega(blank: true, nullable: true, attributes: [title: 'ruta'])

        direccionResidencia(size: 1..150, blank: true, nullable: true, attributes: [title: 'direccionResidencia'])
        rutaEstadosCuenta(blank: true, nullable: true, attributes: [title: 'ruta'])

        direccionTrabajo(size: 1..150, blank: true, nullable: true, attributes: [title: 'direccionTrabajo'])
        profesion(blank: true, nullable: true, attributes: [title: 'profecion'])

        nivelEstudios(blank: true, nullable: true, attributes: [title: 'nivelEstudios'])
        cargaFamiliar(blank: true, nullable: true, attributes: [title: 'cargaFamiliar'])
        telefono(size: 1..20, blank: true, nullable: true, attributes: [title: 'telefono'])

        tipoTarjeta(blank: true, nullable: true, attributes: [title: 'tipoTarjeta'])
        bins(blank: true, nullable: true, attributes: [title: 'bins'])
        cupo(blank: true, nullable: true, attributes: [title: 'cupo'])

        oficina(blank: true, nullable: true, attributes: [title: 'oficina'])

        parroquia(blank: true, nullable: true, attributes: [title: 'parroquia'])
        telefonoTrabajo(blank: true, nullable: true, attributes: [title: 'celular'])
        email(size: 1..50, blank: true, nullable: true, attributes: [title: 'email'])

        empresa(size:1..50, blank: true, nullable: true, attributes: [title: 'empresa'])
        actividadEconomica(blank: true, nullable: true, attributes: [title: 'actividadEconomica'])
        rangoIngresos(blank: true, nullable: true, attributes: [title: 'rangoIngresos'])

        celular(size:1..20, blank: true, nullable: true, attributes: [title: 'celular'])

        tipoVivienda(blank: true, nullable: true, attributes: [title: 'tipoVivienda'])
        valorVivienda(blank: true, nullable: true, attributes: [title: 'valorVivienda'])
        fechaInicioResidencia(blank: true, nullable: true, attributes: [title: 'fechaInicioResidencia'])


        relacionDependenciaLaboral(blank: true, nullable: true, attributes: [title: 'relacionDependenciaLaboral'])
        origenIngresos(blank: true, nullable: true, attributes: [title: 'origenIngresos'])
        patrimonio(blank: true, nullable: true, attributes: [title: 'patrimonio'])

        fechaInicioTrabajoActual(blank: true, nullable: true, attributes: [title: 'fechaInicioTrabajoActual '])
        fechaInicioTrabajoAnterior(blank: true, nullable: true, attributes: [title: 'fechaInicioTrabajoAnterior'])
        fechaFinTrabajoAnterior(blank: true, nullable: true, attributes: [title: 'fechaFinTrabajoAnterior'])

        tipoDeIdentificacionReferenciaPersonal(blank: true, nullable: true, attributes: [title: 'tipoDeIdentificacionReferenciaPersonal'])
        idReferenciaPersonal(size: 1..10, blank: true, nullable: true, attributes: [title: 'tipoDeIdentificacion'])
        nombre1ReferenciaPersonal(size: 1..20, blank: true, nullable: true, attributes: [title: 'nombre1ReferenciaPersonal'])

        nombre2ReferenciaPersonal(size: 1..20, blank: true, nullable: true, attributes: [title: 'nombre2ReferenciaPersonal'])
        apellido1ReferenciaPersonal(size: 1..20, blank: true, nullable: true, attributes: [title: 'apellido1ReferenciaPersonal'])
        apellido2ReferenciaPersonal(size: 1..20, blank: true, nullable: true, attributes: [title: 'apellido2ReferenciaPersonal'])

        direccionReferenciaPersonal(size: 1..150, blank: true, nullable: true, attributes: [title: 'direccionReferenciaPersonal'])
        telefonoReferenciaPersonal(size: 1..20, blank: true, nullable: true, attributes: [title: 'telefonoReferenciaPersonal'])
        parentesco(blank: true, nullable: true, attributes: [title: 'parentesco'])

        tipoDeIdentificacionPrincipal(blank: true, nullable: true, attributes: [title: 'tipoDeIdentificacionPrincipal'])
        identificacionPrincipal(size: 1..10, blank: true, nullable: true, attributes: [title: 'identificacionPrincipal'])
        binsPrincipal(blank: true, nullable: true, attributes: [title: 'binsPrincipal'])
        afinidad(blank: true, nullable: true, attributes: [title: 'afinidad'])

        comentarios(blank: true, nullable: true, attributes: [title: 'comentarios'])
        contactoAlterno(blank: true, nullable: true, attributes: [title: 'contactoAlterno'])

        vendedor(blank: true, nullable: true, attributes: [title: 'vendedor'])
        lote(blank: true, attributes: [title: 'lote'])
    }

    String toString() {
        return this.nombre
    }
}