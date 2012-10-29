package elyon

import org.springframework.dao.DataIntegrityViolationException

class NacionalidadController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [nacionalidadInstanceList: Nacionalidad.list(params), params: params]
    } //list

    def form_ajax() {
        def nacionalidadInstance = new Nacionalidad(params)
        if (params.id) {
            nacionalidadInstance = Nacionalidad.get(params.id)
            if (!nacionalidadInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Nacionalidad con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [nacionalidadInstance: nacionalidadInstance]
    } //form_ajax

    def save() {
        def nacionalidadInstance
        if (params.id) {
            nacionalidadInstance = Nacionalidad.get(params.id)
            if (!nacionalidadInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Nacionalidad con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            nacionalidadInstance.properties = params
        }//es edit
        else {
            nacionalidadInstance = new Nacionalidad(params)
        } //es create
        if (!nacionalidadInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Nacionalidad " + (nacionalidadInstance.id ? nacionalidadInstance.id : "") + "</h4>"

            str += "<ul>"
            nacionalidadInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Nacionalidad " + nacionalidadInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Nacionalidad " + nacionalidadInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def nacionalidadInstance = Nacionalidad.get(params.id)
        if (!nacionalidadInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Nacionalidad con id " + params.id
            redirect(action: "list")
            return
        }
        [nacionalidadInstance: nacionalidadInstance]
    } //show

    def delete() {
        def nacionalidadInstance = Nacionalidad.get(params.id)
        if (!nacionalidadInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Nacionalidad con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            nacionalidadInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Nacionalidad " + nacionalidadInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Nacionalidad " + (nacionalidadInstance.id ? nacionalidadInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
