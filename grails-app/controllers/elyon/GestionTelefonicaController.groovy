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

    def gestion (){

        def lote = Lote.get(75)

        def estadoGestion = new EstadoGestion()

        def restantes = Lote.findAllByOrdenDeTrabajoAndEstadoGestionIsNull(lote.ordenDeTrabajo)

        def estadoLlamada = EstadoLlamada.get(11)

        def  gestion = GestionTelefonica.findAllByLoteAndEstadoLlamada(lote,estadoLlamada)


        println(gestion.id)


        [lote:lote, gestion: gestion, estadoGestion: estadoGestion, restantes : restantes, estadoLlamada: estadoLlamada]


    }

    def saveGestion (){


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
