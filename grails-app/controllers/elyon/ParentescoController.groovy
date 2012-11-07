package elyon

import org.springframework.dao.DataIntegrityViolationException

class ParentescoController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [parentescoInstanceList: Parentesco.list(params), params: params]
    } //list

    def form_ajax() {
        def parentescoInstance = new Parentesco(params)
        if (params.id) {
            parentescoInstance = Parentesco.get(params.id)
            if (!parentescoInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Parentesco con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [parentescoInstance: parentescoInstance]
    } //form_ajax

    def save() {
        def parentescoInstance
        if (params.id) {
            parentescoInstance = Parentesco.get(params.id)
            if (!parentescoInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Parentesco con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            parentescoInstance.properties = params
        }//es edit
        else {
            parentescoInstance = new Parentesco(params)
        } //es create
        if (!parentescoInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Parentesco " + (parentescoInstance.id ? parentescoInstance.id : "") + "</h4>"

            str += "<ul>"
            parentescoInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Parentesco " + parentescoInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Parentesco " + parentescoInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def parentescoInstance = Parentesco.get(params.id)
        if (!parentescoInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Parentesco con id " + params.id
            redirect(action: "list")
            return
        }
        [parentescoInstance: parentescoInstance]
    } //show

    def delete() {
        def parentescoInstance = Parentesco.get(params.id)
        if (!parentescoInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Parentesco con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            parentescoInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Parentesco " + parentescoInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Parentesco " + (parentescoInstance.id ? parentescoInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
