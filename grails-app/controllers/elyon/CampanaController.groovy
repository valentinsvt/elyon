package elyon

import org.springframework.dao.DataIntegrityViolationException

class CampanaController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [campanaInstanceList: Campana.list(params), params: params]
    } //list

    def form_ajax() {
        def campanaInstance = new Campana(params)
        if (params.id) {
            campanaInstance = Campana.get(params.id)
            if (!campanaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Campana con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [campanaInstance: campanaInstance]
    } //form_ajax

    def save() {
        def campanaInstance
        if (params.id) {
            campanaInstance = Campana.get(params.id)
            if (!campanaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Campana con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            campanaInstance.properties = params
        }//es edit
        else {
            campanaInstance = new Campana(params)
        } //es create
        if (!campanaInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Campana " + (campanaInstance.id ? campanaInstance.id : "") + "</h4>"

            str += "<ul>"
            campanaInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Campana " + campanaInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Campana " + campanaInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def campanaInstance = Campana.get(params.id)
        if (!campanaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Campana con id " + params.id
            redirect(action: "list")
            return
        }
        [campanaInstance: campanaInstance]
    } //show

    def delete() {
        def campanaInstance = Campana.get(params.id)
        if (!campanaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Campana con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            campanaInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Campana " + campanaInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Campana " + (campanaInstance.id ? campanaInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
