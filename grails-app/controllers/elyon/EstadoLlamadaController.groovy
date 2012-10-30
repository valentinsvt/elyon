package elyon

import org.springframework.dao.DataIntegrityViolationException

class EstadoLlamadaController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [estadoLlamadaInstanceList: EstadoLlamada.list(params), params: params]
    } //list

    def form_ajax() {
        def estadoLlamadaInstance = new EstadoLlamada(params)
        if (params.id) {
            estadoLlamadaInstance = EstadoLlamada.get(params.id)
            if (!estadoLlamadaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Estado Llamada con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [estadoLlamadaInstance: estadoLlamadaInstance]
    } //form_ajax

    def save() {
        def estadoLlamadaInstance
        if (params.id) {
            estadoLlamadaInstance = EstadoLlamada.get(params.id)
            if (!estadoLlamadaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Estado Llamada con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            estadoLlamadaInstance.properties = params
        }//es edit
        else {
            estadoLlamadaInstance = new EstadoLlamada(params)
        } //es create
        if (!estadoLlamadaInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Estado Llamada " + (estadoLlamadaInstance.id ? estadoLlamadaInstance.id : "") + "</h4>"

            str += "<ul>"
            estadoLlamadaInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Estado Llamada " + estadoLlamadaInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Estado Llamada " + estadoLlamadaInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def estadoLlamadaInstance = EstadoLlamada.get(params.id)
        if (!estadoLlamadaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Estado Llamada con id " + params.id
            redirect(action: "list")
            return
        }
        [estadoLlamadaInstance: estadoLlamadaInstance]
    } //show

    def delete() {
        def estadoLlamadaInstance = EstadoLlamada.get(params.id)
        if (!estadoLlamadaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Estado Llamada con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            estadoLlamadaInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Estado Llamada " + estadoLlamadaInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Estado Llamada " + (estadoLlamadaInstance.id ? estadoLlamadaInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
