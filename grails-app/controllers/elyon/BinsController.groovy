package elyon

import org.springframework.dao.DataIntegrityViolationException

class BinsController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [binsInstanceList: Bins.list(params), params: params]
    } //list

    def form_ajax() {
        def binsInstance = new Bins(params)
        if (params.id) {
            binsInstance = Bins.get(params.id)
            if (!binsInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Bins con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [binsInstance: binsInstance]
    } //form_ajax

    def save() {
        def binsInstance
        if (params.id) {
            binsInstance = Bins.get(params.id)
            if (!binsInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Bins con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            binsInstance.properties = params
        }//es edit
        else {
            binsInstance = new Bins(params)
        } //es create
        if (!binsInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Bins " + (binsInstance.id ? binsInstance.id : "") + "</h4>"

            str += "<ul>"
            binsInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Bins " + binsInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Bins " + binsInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def binsInstance = Bins.get(params.id)
        if (!binsInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Bins con id " + params.id
            redirect(action: "list")
            return
        }
        [binsInstance: binsInstance]
    } //show

    def delete() {
        def binsInstance = Bins.get(params.id)
        if (!binsInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Bins con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            binsInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Bins " + binsInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Bins " + (binsInstance.id ? binsInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
