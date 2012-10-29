package elyon

import org.springframework.dao.DataIntegrityViolationException

class EstadoCivilController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [estadoCivilInstanceList: EstadoCivil.list(params), params: params]
    } //list

    def form_ajax() {
        def estadoCivilInstance = new EstadoCivil(params)
        if(params.id) {
            estadoCivilInstance = EstadoCivil.get(params.id)
            if(!estadoCivilInstance) {
                flash.clase = "alert-error"
                flash.message =  "No se encontró Estado Civil con id " + params.id
                redirect(action:  "list")
                return
            } //no existe el objeto
        } //es edit
        return [estadoCivilInstance: estadoCivilInstance]
    } //form_ajax

    def save() {
        def estadoCivilInstance
        if(params.id) {
            estadoCivilInstance = EstadoCivil.get(params.id)
            if(!estadoCivilInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Estado Civil con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            estadoCivilInstance.properties = params
        }//es edit
        else {
            estadoCivilInstance = new EstadoCivil(params)
        } //es create
        if (!estadoCivilInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Estado Civil " + (estadoCivilInstance.id ? estadoCivilInstance.id : "") + "</h4>"

            str += "<ul>"
            estadoCivilInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Estado Civil " + estadoCivilInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Estado Civil " + estadoCivilInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def estadoCivilInstance = EstadoCivil.get(params.id)
        if (!estadoCivilInstance) {
            flash.clase = "alert-error"
            flash.message =  "No se encontró Estado Civil con id " + params.id
            redirect(action: "list")
            return
        }
        [estadoCivilInstance: estadoCivilInstance]
    } //show

    def delete() {
        def estadoCivilInstance = EstadoCivil.get(params.id)
        if (!estadoCivilInstance) {
            flash.clase = "alert-error"
            flash.message =  "No se encontró Estado Civil con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            estadoCivilInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message =  "Se ha eliminado correctamente Estado Civil " + estadoCivilInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message =  "No se pudo eliminar Estado Civil " + (estadoCivilInstance.id ? estadoCivilInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
