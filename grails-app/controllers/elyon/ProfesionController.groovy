package elyon

import org.springframework.dao.DataIntegrityViolationException

class ProfesionController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [profesionInstanceList: Profesion.list(params), params: params]
    } //list

    def form_ajax() {
        def profesionInstance = new Profesion(params)
        if(params.id) {
            profesionInstance = Profesion.get(params.id)
            if(!profesionInstance) {
                flash.clase = "alert-error"
                flash.message =  "No se encontró Profesion con id " + params.id
                redirect(action:  "list")
                return
            } //no existe el objeto
        } //es edit
        return [profesionInstance: profesionInstance]
    } //form_ajax

    def save() {
        def profesionInstance
        if(params.id) {
            profesionInstance = Profesion.get(params.id)
            if(!profesionInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Profesion con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            profesionInstance.properties = params
        }//es edit
        else {
            profesionInstance = new Profesion(params)
        } //es create
        if (!profesionInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Profesion " + (profesionInstance.id ? profesionInstance.id : "") + "</h4>"

            str += "<ul>"
            profesionInstance.errors.allErrors.each { err ->
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

        if(params.id) {
            flash.clase = "alert-success"
            flash.message = "Se ha actualizado correctamente Profesion " + profesionInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Profesion " + profesionInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def profesionInstance = Profesion.get(params.id)
        if (!profesionInstance) {
            flash.clase = "alert-error"
            flash.message =  "No se encontró Profesion con id " + params.id
            redirect(action: "list")
            return
        }
        [profesionInstance: profesionInstance]
    } //show

    def delete() {
        def profesionInstance = Profesion.get(params.id)
        if (!profesionInstance) {
            flash.clase = "alert-error"
            flash.message =  "No se encontró Profesion con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            profesionInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message =  "Se ha eliminado correctamente Profesion " + profesionInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message =  "No se pudo eliminar Profesion " + (profesionInstance.id ? profesionInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
