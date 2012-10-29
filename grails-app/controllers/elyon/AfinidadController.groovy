package elyon

import org.springframework.dao.DataIntegrityViolationException

class AfinidadController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [afinidadInstanceList: Afinidad.list(params), params: params]
    } //list

    def form_ajax() {
        def afinidadInstance = new Afinidad(params)
        if (params.id) {
            afinidadInstance = Afinidad.get(params.id)
            if (!afinidadInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró  Afinidad con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [afinidadInstance: afinidadInstance]
    } //form_ajax

    def save() {
        def afinidadInstance
        if (params.id) {
            afinidadInstance = Afinidad.get(params.id)
            if (!afinidadInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró  Afinidad con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            afinidadInstance.properties = params
        }//es edit
        else {
            afinidadInstance = new Afinidad(params)
        } //es create
        if (!afinidadInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar  Afinidad " + (afinidadInstance.id ? afinidadInstance.id : "") + "</h4>"

            str += "<ul>"
            afinidadInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente  Afinidad " + afinidadInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente  Afinidad " + afinidadInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def afinidadInstance = Afinidad.get(params.id)
        if (!afinidadInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró  Afinidad con id " + params.id
            redirect(action: "list")
            return
        }
        [afinidadInstance: afinidadInstance]
    } //show

    def delete() {
        def afinidadInstance = Afinidad.get(params.id)
        if (!afinidadInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró  Afinidad con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            afinidadInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente  Afinidad " + afinidadInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar  Afinidad " + (afinidadInstance.id ? afinidadInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
