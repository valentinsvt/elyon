package elyon

import org.springframework.dao.DataIntegrityViolationException

class GestionTelefonicaController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [gestionTelefonicaInstanceList: GestionTelefonica.list(params), params: params]
    } //list

    def gestion() {

        def lote = Lote.get(params.id)

        def estadoGestion = new EstadoGestion()

        def restantes = Lote.findAllByOrdenDeTrabajoAndEstadoGestionIsNull(lote.ordenDeTrabajo)

        def estadoLlamada = EstadoLlamada.get(11)

        def estadoLlamadaT = new EstadoLlamada()

        def gestion = GestionTelefonica.findAllByLoteAndEstadoLlamada(lote, estadoLlamada, [sort: "id"])
//        def gestion = GestionTelefonica.findAllByLote(lote, [sort:"id"])

//        println ">>>>>>>>>>>>>>>>>"+gestion.id


        [lote: lote, gestion: gestion, estadoGestion: estadoGestion, restantes: restantes, estadoLlamada: estadoLlamada, estadoLlamadaT: estadoLlamadaT]


    }

    def saveGestion() {
        def msg = "ok"
        def loteId = params.lote
        def estadoGestionId = params.estadoGestion

        def lote = Lote.get(loteId)
        lote.estadoGestion = EstadoGestion.get(estadoGestionId)
        if (!lote.save(flush: true)) {
            msg += "error save lote: " + lote.errors
            println "error save lote: " + lote.errors
        } else {
            msg += "save lote ${lote.id} ok "
        }

        params.each { k, v ->
            if (v instanceof java.lang.String) {

            } else {
                def id = v.id
                def estadoId = v.estado
                def observaciones = v.observaciones

                def gestion = GestionTelefonica.get(id)
                gestion.estadoLlamada = EstadoLlamada.get(estadoId)
                gestion.observaciones = observaciones.trim()
                if (!gestion.save(flush: true)) {
                    msg += "ERROR:" + gestion.errors
                    println "error save gestion " + id + ": " + gestion.errors
                } else {
                    msg += "save gestion telefonica " + id + " ok "
                }
            }
        }

//        println msg
//        render msg

        redirect (controller: "llamada", action: "registro", id: params.lote)
    }

    def form_ajax() {
        def gestionTelefonicaInstance = new GestionTelefonica(params)
        if (params.id) {
            gestionTelefonicaInstance = GestionTelefonica.get(params.id)
            if (!gestionTelefonicaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Gestion Telefonica con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [gestionTelefonicaInstance: gestionTelefonicaInstance]
    } //form_ajax

    def save() {
        def gestionTelefonicaInstance
        if (params.id) {
            gestionTelefonicaInstance = GestionTelefonica.get(params.id)
            if (!gestionTelefonicaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Gestion Telefonica con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            gestionTelefonicaInstance.properties = params
        }//es edit
        else {
            gestionTelefonicaInstance = new GestionTelefonica(params)
        } //es create
        if (!gestionTelefonicaInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Gestion Telefonica " + (gestionTelefonicaInstance.id ? gestionTelefonicaInstance.id : "") + "</h4>"

            str += "<ul>"
            gestionTelefonicaInstance.errors.allErrors.each { err ->
                def msg = err.defaultMessage
                err.arguments.eachWithIndex {  arg, i ->
                    msg = msg.replaceAll("\\{" + i + "}", arg.toString())
                }
                str += "<li>" + msg + "</li>"
            }
            str += "</ul>"

            flash.message = str
            redirect(action: 'list')
            return
        }

        if (params.id) {
            flash.clase = "alert-success"
            flash.message = "Se ha actualizado correctamente Gestion Telefonica " + gestionTelefonicaInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Gestion Telefonica " + gestionTelefonicaInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def gestionTelefonicaInstance = GestionTelefonica.get(params.id)
        if (!gestionTelefonicaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Gestion Telefonica con id " + params.id
            redirect(action: "list")
            return
        }
        [gestionTelefonicaInstance: gestionTelefonicaInstance]
    } //show

    def delete() {
        def gestionTelefonicaInstance = GestionTelefonica.get(params.id)
        if (!gestionTelefonicaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Gestion Telefonica con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            gestionTelefonicaInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Gestion Telefonica " + gestionTelefonicaInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Gestion Telefonica " + (gestionTelefonicaInstance.id ? gestionTelefonicaInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
