package elyon

import org.springframework.dao.DataIntegrityViolationException

class TipoDeIdentificacionController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [tipoDeIdentificacionInstanceList: TipoDeIdentificacion.list(params), params: params]
    } //list

    def form_ajax() {
        def tipoDeIdentificacionInstance = new TipoDeIdentificacion(params)
        if (params.id) {
            tipoDeIdentificacionInstance = TipoDeIdentificacion.get(params.id)
            if (!tipoDeIdentificacionInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Tipo De Identificacion con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [tipoDeIdentificacionInstance: tipoDeIdentificacionInstance]
    } //form_ajax

    def save() {
        def tipoDeIdentificacionInstance
        if (params.id) {
            tipoDeIdentificacionInstance = TipoDeIdentificacion.get(params.id)
            if (!tipoDeIdentificacionInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Tipo De Identificacion con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            tipoDeIdentificacionInstance.properties = params
        }//es edit
        else {
            tipoDeIdentificacionInstance = new TipoDeIdentificacion(params)
        } //es create
        if (!tipoDeIdentificacionInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Tipo De Identificacion " + (tipoDeIdentificacionInstance.id ? tipoDeIdentificacionInstance.id : "") + "</h4>"

            str += "<ul>"
            tipoDeIdentificacionInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Tipo De Identificacion " + tipoDeIdentificacionInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Tipo De Identificacion " + tipoDeIdentificacionInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def tipoDeIdentificacionInstance = TipoDeIdentificacion.get(params.id)
        if (!tipoDeIdentificacionInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Tipo De Identificacion con id " + params.id
            redirect(action: "list")
            return
        }
        [tipoDeIdentificacionInstance: tipoDeIdentificacionInstance]
    } //show

    def delete() {
        def tipoDeIdentificacionInstance = TipoDeIdentificacion.get(params.id)
        if (!tipoDeIdentificacionInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Tipo De Identificacion con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            tipoDeIdentificacionInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Tipo De Identificacion " + tipoDeIdentificacionInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Tipo De Identificacion " + (tipoDeIdentificacionInstance.id ? tipoDeIdentificacionInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
