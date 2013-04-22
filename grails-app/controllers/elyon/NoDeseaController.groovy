package elyon

import org.springframework.dao.DataIntegrityViolationException

class NoDeseaController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [noDeseaInstanceList: NoDesea.list(params), params: params]
    } //list

    def form_ajax() {
        def noDeseaInstance = new NoDesea(params)
        if (params.id) {
            noDeseaInstance = NoDesea.get(params.id)
            if (!noDeseaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró No Desea con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [noDeseaInstance: noDeseaInstance]
    } //form_ajax

    def save() {
        def noDeseaInstance
        if (params.id) {
            noDeseaInstance = NoDesea.get(params.id)
            if (!noDeseaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró No Desea con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            noDeseaInstance.properties = params
        }//es edit
        else {
            noDeseaInstance = new NoDesea(params)
        } //es create
        if (!noDeseaInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar No Desea " + (noDeseaInstance.id ? noDeseaInstance.id : "") + "</h4>"

            str += "<ul>"
            noDeseaInstance.errors.allErrors.each { err ->
                def msg = err.defaultMessage
                err.arguments.eachWithIndex { arg, i ->
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
            flash.message = "Se ha actualizado correctamente No Desea " + noDeseaInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente No Desea " + noDeseaInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def noDeseaInstance = NoDesea.get(params.id)
        if (!noDeseaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró No Desea con id " + params.id
            redirect(action: "list")
            return
        }
        [noDeseaInstance: noDeseaInstance]
    } //show

    def delete() {
        def noDeseaInstance = NoDesea.get(params.id)
        if (!noDeseaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró No Desea con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            noDeseaInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente No Desea " + noDeseaInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar No Desea " + (noDeseaInstance.id ? noDeseaInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
