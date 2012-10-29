package elyon

import org.springframework.dao.DataIntegrityViolationException

class RangoIngresosController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [rangoIngresosInstanceList: RangoIngresos.list(params), params: params]
    } //list

    def form_ajax() {
        def rangoIngresosInstance = new RangoIngresos(params)
        if (params.id) {
            rangoIngresosInstance = RangoIngresos.get(params.id)
            if (!rangoIngresosInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Rango Ingresos con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [rangoIngresosInstance: rangoIngresosInstance]
    } //form_ajax

    def save() {
        def rangoIngresosInstance
        if (params.id) {
            rangoIngresosInstance = RangoIngresos.get(params.id)
            if (!rangoIngresosInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Rango Ingresos con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            rangoIngresosInstance.properties = params
        }//es edit
        else {
            rangoIngresosInstance = new RangoIngresos(params)
        } //es create
        if (!rangoIngresosInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Rango Ingresos " + (rangoIngresosInstance.id ? rangoIngresosInstance.id : "") + "</h4>"

            str += "<ul>"
            rangoIngresosInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Rango Ingresos " + rangoIngresosInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Rango Ingresos " + rangoIngresosInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def rangoIngresosInstance = RangoIngresos.get(params.id)
        if (!rangoIngresosInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Rango Ingresos con id " + params.id
            redirect(action: "list")
            return
        }
        [rangoIngresosInstance: rangoIngresosInstance]
    } //show

    def delete() {
        def rangoIngresosInstance = RangoIngresos.get(params.id)
        if (!rangoIngresosInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Rango Ingresos con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            rangoIngresosInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Rango Ingresos " + rangoIngresosInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Rango Ingresos " + (rangoIngresosInstance.id ? rangoIngresosInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
