package elyon

import org.springframework.dao.DataIntegrityViolationException

class SucursalController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [sucursalInstanceList: Sucursal.list(params), params: params]
    } //list

    def form_ajax() {
        def sucursalInstance = new Sucursal(params)
        if (params.id) {
            sucursalInstance = Sucursal.get(params.id)
            if (!sucursalInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Sucursal con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [sucursalInstance: sucursalInstance]
    } //form_ajax

    def save() {
        def sucursalInstance
        if (params.id) {
            sucursalInstance = Sucursal.get(params.id)
            if (!sucursalInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Sucursal con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            sucursalInstance.properties = params
        }//es edit
        else {
            sucursalInstance = new Sucursal(params)
        } //es create
        if (!sucursalInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Sucursal " + (sucursalInstance.id ? sucursalInstance.id : "") + "</h4>"

            str += "<ul>"
            sucursalInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Sucursal " + sucursalInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Sucursal " + sucursalInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def sucursalInstance = Sucursal.get(params.id)
        if (!sucursalInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Sucursal con id " + params.id
            redirect(action: "list")
            return
        }
        [sucursalInstance: sucursalInstance]
    } //show

    def delete() {
        def sucursalInstance = Sucursal.get(params.id)
        if (!sucursalInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Sucursal con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            sucursalInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Sucursal " + sucursalInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Sucursal " + (sucursalInstance.id ? sucursalInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
