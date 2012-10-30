package elyon

import org.springframework.dao.DataIntegrityViolationException

class EstadoGestionController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [estadoGestionInstanceList: EstadoGestion.list(params), params: params]
    } //list

    def form_ajax() {
        def estadoGestionInstance = new EstadoGestion(params)
        if (params.id) {
            estadoGestionInstance = EstadoGestion.get(params.id)
            if (!estadoGestionInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Estado Gestion con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [estadoGestionInstance: estadoGestionInstance]
    } //form_ajax

    def save() {
        def estadoGestionInstance
        if (params.id) {
            estadoGestionInstance = EstadoGestion.get(params.id)
            if (!estadoGestionInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Estado Gestion con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            estadoGestionInstance.properties = params
        }//es edit
        else {
            estadoGestionInstance = new EstadoGestion(params)
        } //es create
        if (!estadoGestionInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Estado Gestion " + (estadoGestionInstance.id ? estadoGestionInstance.id : "") + "</h4>"

            str += "<ul>"
            estadoGestionInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Estado Gestion " + estadoGestionInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Estado Gestion " + estadoGestionInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def estadoGestionInstance = EstadoGestion.get(params.id)
        if (!estadoGestionInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Estado Gestion con id " + params.id
            redirect(action: "list")
            return
        }
        [estadoGestionInstance: estadoGestionInstance]
    } //show

    def delete() {
        def estadoGestionInstance = EstadoGestion.get(params.id)
        if (!estadoGestionInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Estado Gestion con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            estadoGestionInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Estado Gestion " + estadoGestionInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Estado Gestion " + (estadoGestionInstance.id ? estadoGestionInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
