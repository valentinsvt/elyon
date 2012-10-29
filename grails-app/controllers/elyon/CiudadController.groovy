package elyon

import org.springframework.dao.DataIntegrityViolationException

class CiudadController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [ciudadInstanceList: Ciudad.list(params), params: params]
        /*focking commit luz */
    } //list


    def form_ajax() {
        def ciudadInstance = new Ciudad(params)
        if(params.id) {
            ciudadInstance = Ciudad.get(params.id)
            if(!ciudadInstance) {
                flash.clase = "alert-error"
                flash.message =  "No se encontró Ciudad con id " + params.id
                redirect(action:  "list")
                return
            } //no existe el objeto
        } //es edit
        return [ciudadInstance: ciudadInstance]
    } //form_ajax

    def save() {
        def ciudadInstance
        if(params.id) {
            ciudadInstance = Ciudad.get(params.id)
            if(!ciudadInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Ciudad con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            ciudadInstance.properties = params
        }//es edit
        else {
            ciudadInstance = new Ciudad(params)
        } //es create
        if (!ciudadInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Ciudad " + (ciudadInstance.id ? ciudadInstance.id : "") + "</h4>"

            str += "<ul>"
            ciudadInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Ciudad " + ciudadInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Ciudad " + ciudadInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def ciudadInstance = Ciudad.get(params.id)
        if (!ciudadInstance) {
            flash.clase = "alert-error"
            flash.message =  "No se encontró Ciudad con id " + params.id
            redirect(action: "list")
            return
        }
        [ciudadInstance: ciudadInstance]
    } //show

    def delete() {
        def ciudadInstance = Ciudad.get(params.id)
        if (!ciudadInstance) {
            flash.clase = "alert-error"
            flash.message =  "No se encontró Ciudad con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            ciudadInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message =  "Se ha eliminado correctamente Ciudad " + ciudadInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message =  "No se pudo eliminar Ciudad " + (ciudadInstance.id ? ciudadInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
