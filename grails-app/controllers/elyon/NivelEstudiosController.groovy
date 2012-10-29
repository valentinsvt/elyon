package elyon

import org.springframework.dao.DataIntegrityViolationException

class NivelEstudiosController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [nivelEstudiosInstanceList: NivelEstudios.list(params), params: params]
    } //list

    def form_ajax() {
        def nivelEstudiosInstance = new NivelEstudios(params)
        if(params.id) {
            nivelEstudiosInstance = NivelEstudios.get(params.id)
            if(!nivelEstudiosInstance) {
                flash.clase = "alert-error"
                flash.message =  "No se encontró Nivel Estudios con id " + params.id
                redirect(action:  "list")
                return
            } //no existe el objeto
        } //es edit
        return [nivelEstudiosInstance: nivelEstudiosInstance]
    } //form_ajax

    def save() {
        def nivelEstudiosInstance
        if(params.id) {
            nivelEstudiosInstance = NivelEstudios.get(params.id)
            if(!nivelEstudiosInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Nivel Estudios con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            nivelEstudiosInstance.properties = params
        }//es edit
        else {
            nivelEstudiosInstance = new NivelEstudios(params)
        } //es create
        if (!nivelEstudiosInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Nivel Estudios " + (nivelEstudiosInstance.id ? nivelEstudiosInstance.id : "") + "</h4>"

            str += "<ul>"
            nivelEstudiosInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Nivel Estudios " + nivelEstudiosInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Nivel Estudios " + nivelEstudiosInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def nivelEstudiosInstance = NivelEstudios.get(params.id)
        if (!nivelEstudiosInstance) {
            flash.clase = "alert-error"
            flash.message =  "No se encontró Nivel Estudios con id " + params.id
            redirect(action: "list")
            return
        }
        [nivelEstudiosInstance: nivelEstudiosInstance]
    } //show

    def delete() {
        def nivelEstudiosInstance = NivelEstudios.get(params.id)
        if (!nivelEstudiosInstance) {
            flash.clase = "alert-error"
            flash.message =  "No se encontró Nivel Estudios con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            nivelEstudiosInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message =  "Se ha eliminado correctamente Nivel Estudios " + nivelEstudiosInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message =  "No se pudo eliminar Nivel Estudios " + (nivelEstudiosInstance.id ? nivelEstudiosInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
