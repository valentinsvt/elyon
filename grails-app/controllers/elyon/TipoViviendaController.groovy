package elyon

import org.springframework.dao.DataIntegrityViolationException

class TipoViviendaController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [tipoViviendaInstanceList: TipoVivienda.list(params), params: params]
    } //list

    def form_ajax() {
        def tipoViviendaInstance = new TipoVivienda(params)
        if (params.id) {
            tipoViviendaInstance = TipoVivienda.get(params.id)
            if (!tipoViviendaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Tipo Vivienda con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [tipoViviendaInstance: tipoViviendaInstance]
    } //form_ajax

    def save() {
        def tipoViviendaInstance
        if (params.id) {
            tipoViviendaInstance = TipoVivienda.get(params.id)
            if (!tipoViviendaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Tipo Vivienda con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            tipoViviendaInstance.properties = params
        }//es edit
        else {
            tipoViviendaInstance = new TipoVivienda(params)
        } //es create
        if (!tipoViviendaInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Tipo Vivienda " + (tipoViviendaInstance.id ? tipoViviendaInstance.id : "") + "</h4>"

            str += "<ul>"
            tipoViviendaInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Tipo Vivienda " + tipoViviendaInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Tipo Vivienda " + tipoViviendaInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def tipoViviendaInstance = TipoVivienda.get(params.id)
        if (!tipoViviendaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Tipo Vivienda con id " + params.id
            redirect(action: "list")
            return
        }
        [tipoViviendaInstance: tipoViviendaInstance]
    } //show

    def delete() {
        def tipoViviendaInstance = TipoVivienda.get(params.id)
        if (!tipoViviendaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Tipo Vivienda con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            tipoViviendaInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Tipo Vivienda " + tipoViviendaInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Tipo Vivienda " + (tipoViviendaInstance.id ? tipoViviendaInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
