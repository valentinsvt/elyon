package elyon

import org.springframework.dao.DataIntegrityViolationException

class LoteController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [loteInstanceList: Lote.list(params), params: params]
    } //list

    def form_ajax() {
        def loteInstance = new Lote(params)
        if (params.id) {
            loteInstance = Lote.get(params.id)
            if (!loteInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Lote con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [loteInstance: loteInstance]
    } //form_ajax

    def save() {
        def loteInstance
        if (params.id) {
            loteInstance = Lote.get(params.id)
            if (!loteInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Lote con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            loteInstance.properties = params
        }//es edit
        else {
            loteInstance = new Lote(params)
        } //es create
        if (!loteInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Lote " + (loteInstance.id ? loteInstance.id : "") + "</h4>"

            str += "<ul>"
            loteInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Lote " + loteInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Lote " + loteInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def loteInstance = Lote.get(params.id)
        if (!loteInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Lote con id " + params.id
            redirect(action: "list")
            return
        }
        [loteInstance: loteInstance]
    } //show

    def delete() {
        def loteInstance = Lote.get(params.id)
        if (!loteInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Lote con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            loteInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Lote " + loteInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Lote " + (loteInstance.id ? loteInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
