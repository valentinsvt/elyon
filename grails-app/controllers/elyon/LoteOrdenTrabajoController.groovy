package elyon

import org.springframework.dao.DataIntegrityViolationException

class LoteOrdenTrabajoController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [loteOrdenTrabajoInstanceList: LoteOrdenTrabajo.list(params), params: params]
    } //list


    def busqueda(){

      def loteOrdenTrabajo = LoteOrdenTrabajo.get(1)

        [loteOrdenTrabajo: loteOrdenTrabajo]
    }

    def form_ajax() {
        def loteOrdenTrabajoInstance = new LoteOrdenTrabajo(params)
        if (params.id) {
            loteOrdenTrabajoInstance = LoteOrdenTrabajo.get(params.id)
            if (!loteOrdenTrabajoInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Lote Orden Trabajo con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [loteOrdenTrabajoInstance: loteOrdenTrabajoInstance]
    } //form_ajax

    def save() {
        def loteOrdenTrabajoInstance
        if (params.id) {
            loteOrdenTrabajoInstance = LoteOrdenTrabajo.get(params.id)
            if (!loteOrdenTrabajoInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Lote Orden Trabajo con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            loteOrdenTrabajoInstance.properties = params
        }//es edit
        else {
            loteOrdenTrabajoInstance = new LoteOrdenTrabajo(params)
        } //es create
        if (!loteOrdenTrabajoInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Lote Orden Trabajo " + (loteOrdenTrabajoInstance.id ? loteOrdenTrabajoInstance.id : "") + "</h4>"

            str += "<ul>"
            loteOrdenTrabajoInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Lote Orden Trabajo " + loteOrdenTrabajoInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Lote Orden Trabajo " + loteOrdenTrabajoInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def loteOrdenTrabajoInstance = LoteOrdenTrabajo.get(params.id)
        if (!loteOrdenTrabajoInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Lote Orden Trabajo con id " + params.id
            redirect(action: "list")
            return
        }
        [loteOrdenTrabajoInstance: loteOrdenTrabajoInstance]
    } //show

    def delete() {
        def loteOrdenTrabajoInstance = LoteOrdenTrabajo.get(params.id)
        if (!loteOrdenTrabajoInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Lote Orden Trabajo con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            loteOrdenTrabajoInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Lote Orden Trabajo " + loteOrdenTrabajoInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Lote Orden Trabajo " + (loteOrdenTrabajoInstance.id ? loteOrdenTrabajoInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
