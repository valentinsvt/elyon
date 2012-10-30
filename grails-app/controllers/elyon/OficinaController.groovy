package elyon

import org.springframework.dao.DataIntegrityViolationException

class OficinaController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [oficinaInstanceList: Oficina.list(params), params: params]
    } //list

    def form_ajax() {
        def oficinaInstance = new Oficina(params)
        if (params.id) {
            oficinaInstance = Oficina.get(params.id)
            if (!oficinaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Oficina con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [oficinaInstance: oficinaInstance]
    } //form_ajax

    def save() {
        def oficinaInstance
        if (params.id) {
            oficinaInstance = Oficina.get(params.id)
            if (!oficinaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Oficina con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            oficinaInstance.properties = params
        }//es edit
        else {
            oficinaInstance = new Oficina(params)
        } //es create
        if (!oficinaInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Oficina " + (oficinaInstance.id ? oficinaInstance.id : "") + "</h4>"

            str += "<ul>"
            oficinaInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Oficina " + oficinaInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Oficina " + oficinaInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def oficinaInstance = Oficina.get(params.id)
        if (!oficinaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Oficina con id " + params.id
            redirect(action: "list")
            return
        }
        [oficinaInstance: oficinaInstance]
    } //show

    def delete() {
        def oficinaInstance = Oficina.get(params.id)
        if (!oficinaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Oficina con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            oficinaInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Oficina " + oficinaInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Oficina " + (oficinaInstance.id ? oficinaInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
