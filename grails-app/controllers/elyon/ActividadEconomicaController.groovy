package elyon

import org.springframework.dao.DataIntegrityViolationException

class ActividadEconomicaController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [actividadEconomicaInstanceList: ActividadEconomica.list(params), params: params3]


    } //list

    def form_ajax() {
        def actividadEconomicaInstance = new ActividadEconomica(params)
        if(params.id) {
            actividadEconomicaInstance = ActividadEconomica.get(params.id)
            if(!actividadEconomicaInstance) {
                flash.clase = "alert-error"
                flash.message =  "No se encontró Actividad Economica con id " + params.id
                redirect(action:  "list")
                return
            } //no existe el objeto
        } //es edit
        return [actividadEconomicaInstance: actividadEconomicaInstance]
    } //form_ajax

    def save() {
        def actividadEconomicaInstance
        if(params.id) {
            actividadEconomicaInstance = ActividadEconomica.get(params.id)
            if(!actividadEconomicaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Actividad Economica con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            actividadEconomicaInstance.properties = params
        }//es edit
        else {
            actividadEconomicaInstance = new ActividadEconomica(params)
        } //es create
        if (!actividadEconomicaInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Actividad Economica " + (actividadEconomicaInstance.id ? actividadEconomicaInstance.id : "") + "</h4>"

            str += "<ul>"
            actividadEconomicaInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Actividad Economica " + actividadEconomicaInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Actividad Economica " + actividadEconomicaInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def actividadEconomicaInstance = ActividadEconomica.get(params.id)
        if (!actividadEconomicaInstance) {
            flash.clase = "alert-error"
            flash.message =  "No se encontró Actividad Economica con id " + params.id
            redirect(action: "list")
            return
        }
        [actividadEconomicaInstance: actividadEconomicaInstance]
    } //show

    def delete() {
        def actividadEconomicaInstance = ActividadEconomica.get(params.id)
        if (!actividadEconomicaInstance) {
            flash.clase = "alert-error"
            flash.message =  "No se encontró Actividad Economica con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            actividadEconomicaInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message =  "Se ha eliminado correctamente Actividad Economica " + actividadEconomicaInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message =  "No se pudo eliminar Actividad Economica " + (actividadEconomicaInstance.id ? actividadEconomicaInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
