package elyon

import org.springframework.dao.DataIntegrityViolationException

class TipoTarjetaController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [tipoTarjetaInstanceList: TipoTarjeta.list(params), params: params]
    } //list

    def form_ajax() {
        def tipoTarjetaInstance = new TipoTarjeta(params)
        if (params.id) {
            tipoTarjetaInstance = TipoTarjeta.get(params.id)
            if (!tipoTarjetaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Tipo Tarjeta con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [tipoTarjetaInstance: tipoTarjetaInstance]
    } //form_ajax

    def save() {
        def tipoTarjetaInstance
        if (params.id) {
            tipoTarjetaInstance = TipoTarjeta.get(params.id)
            if (!tipoTarjetaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Tipo Tarjeta con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            tipoTarjetaInstance.properties = params
        }//es edit
        else {
            tipoTarjetaInstance = new TipoTarjeta(params)
        } //es create
        if (!tipoTarjetaInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Tipo Tarjeta " + (tipoTarjetaInstance.id ? tipoTarjetaInstance.id : "") + "</h4>"

            str += "<ul>"
            tipoTarjetaInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Tipo Tarjeta " + tipoTarjetaInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Tipo Tarjeta " + tipoTarjetaInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def tipoTarjetaInstance = TipoTarjeta.get(params.id)
        if (!tipoTarjetaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Tipo Tarjeta con id " + params.id
            redirect(action: "list")
            return
        }
        [tipoTarjetaInstance: tipoTarjetaInstance]
    } //show

    def delete() {
        def tipoTarjetaInstance = TipoTarjeta.get(params.id)
        if (!tipoTarjetaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Tipo Tarjeta con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            tipoTarjetaInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Tipo Tarjeta " + tipoTarjetaInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Tipo Tarjeta " + (tipoTarjetaInstance.id ? tipoTarjetaInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
