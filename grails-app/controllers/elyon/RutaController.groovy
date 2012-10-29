package elyon

import org.springframework.dao.DataIntegrityViolationException

class RutaController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [rutaInstanceList: Ruta.list(params), params: params]
    } //list

    def form_ajax() {
        def rutaInstance = new Ruta(params)
        if (params.id) {
            rutaInstance = Ruta.get(params.id)
            if (!rutaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Ruta con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [rutaInstance: rutaInstance]
    } //form_ajax

    def save() {
        def rutaInstance
        if (params.id) {
            rutaInstance = Ruta.get(params.id)
            if (!rutaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Ruta con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            rutaInstance.properties = params
        }//es edit
        else {
            rutaInstance = new Ruta(params)
        } //es create
        if (!rutaInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Ruta " + (rutaInstance.id ? rutaInstance.id : "") + "</h4>"

            str += "<ul>"
            rutaInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Ruta " + rutaInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Ruta " + rutaInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def rutaInstance = Ruta.get(params.id)
        if (!rutaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Ruta con id " + params.id
            redirect(action: "list")
            return
        }
        [rutaInstance: rutaInstance]
    } //show

    def delete() {
        def rutaInstance = Ruta.get(params.id)
        if (!rutaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Ruta con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            rutaInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Ruta " + rutaInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Ruta " + (rutaInstance.id ? rutaInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
