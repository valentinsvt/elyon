package elyon

import org.springframework.dao.DataIntegrityViolationException

class RelacionDependenciaLaboralController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [relacionDependenciaLaboralInstanceList: RelacionDependenciaLaboral.list(params), params: params]
    } //list

    def form_ajax() {
        def relacionDependenciaLaboralInstance = new RelacionDependenciaLaboral(params)
        if (params.id) {
            relacionDependenciaLaboralInstance = RelacionDependenciaLaboral.get(params.id)
            if (!relacionDependenciaLaboralInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Relacion Dependencia Laboral con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [relacionDependenciaLaboralInstance: relacionDependenciaLaboralInstance]
    } //form_ajax

    def save() {
        def relacionDependenciaLaboralInstance
        if (params.id) {
            relacionDependenciaLaboralInstance = RelacionDependenciaLaboral.get(params.id)
            if (!relacionDependenciaLaboralInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Relacion Dependencia Laboral con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            relacionDependenciaLaboralInstance.properties = params
        }//es edit
        else {
            relacionDependenciaLaboralInstance = new RelacionDependenciaLaboral(params)
        } //es create
        if (!relacionDependenciaLaboralInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Relacion Dependencia Laboral " + (relacionDependenciaLaboralInstance.id ? relacionDependenciaLaboralInstance.id : "") + "</h4>"

            str += "<ul>"
            relacionDependenciaLaboralInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Relacion Dependencia Laboral " + relacionDependenciaLaboralInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Relacion Dependencia Laboral " + relacionDependenciaLaboralInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def relacionDependenciaLaboralInstance = RelacionDependenciaLaboral.get(params.id)
        if (!relacionDependenciaLaboralInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Relacion Dependencia Laboral con id " + params.id
            redirect(action: "list")
            return
        }
        [relacionDependenciaLaboralInstance: relacionDependenciaLaboralInstance]
    } //show

    def delete() {
        def relacionDependenciaLaboralInstance = RelacionDependenciaLaboral.get(params.id)
        if (!relacionDependenciaLaboralInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Relacion Dependencia Laboral con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            relacionDependenciaLaboralInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Relacion Dependencia Laboral " + relacionDependenciaLaboralInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Relacion Dependencia Laboral " + (relacionDependenciaLaboralInstance.id ? relacionDependenciaLaboralInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
