package elyon

import org.springframework.dao.DataIntegrityViolationException

class OrigenIngresosController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [origenIngresosInstanceList: OrigenIngresos.list(params), params: params]
    } //list

    def form_ajax() {
        def origenIngresosInstance = new OrigenIngresos(params)
        if (params.id) {
            origenIngresosInstance = OrigenIngresos.get(params.id)
            if (!origenIngresosInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Origen Ingresos con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [origenIngresosInstance: origenIngresosInstance]
    } //form_ajax

    def save() {
        def origenIngresosInstance
        if (params.id) {
            origenIngresosInstance = OrigenIngresos.get(params.id)
            if (!origenIngresosInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Origen Ingresos con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            origenIngresosInstance.properties = params
        }//es edit
        else {
            origenIngresosInstance = new OrigenIngresos(params)
        } //es create
        if (!origenIngresosInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Origen Ingresos " + (origenIngresosInstance.id ? origenIngresosInstance.id : "") + "</h4>"

            str += "<ul>"
            origenIngresosInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Origen Ingresos " + origenIngresosInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Origen Ingresos " + origenIngresosInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def origenIngresosInstance = OrigenIngresos.get(params.id)
        if (!origenIngresosInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Origen Ingresos con id " + params.id
            redirect(action: "list")
            return
        }
        [origenIngresosInstance: origenIngresosInstance]
    } //show

    def delete() {
        def origenIngresosInstance = OrigenIngresos.get(params.id)
        if (!origenIngresosInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Origen Ingresos con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            origenIngresosInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Origen Ingresos " + origenIngresosInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Origen Ingresos " + (origenIngresosInstance.id ? origenIngresosInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
