package elyon.seguridad

import org.springframework.dao.DataIntegrityViolationException

class UsroController extends Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def checkUniqueUser() {
//        println params
        if (params.id) {
//            println "EDIT"
            def user = Usro.get(params.id)
            if (user.login.trim() == params.login.trim()) {
//                println "1"
                render true
            } else {
                def users = Usro.countByLogin(params.login.trim())
                if (users == 0) {
//                    println "2"
                    render true
                } else {
//                    println "3"
                    render false
                }
            }
        } else {
//            println "CREATE"
            def users = Usro.countByLogin(params.login.trim())
            if (users == 0) {
//                println "4"
                render true
            } else {
//                println "5"
                render false
            }
        }
    }

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [usroInstanceList: Usro.list(params), params: params]
    } //list

    def form_ajax() {
        def usroInstance = new Usro(params)
        if (params.id) {
            usroInstance = Usro.get(params.id)
            if (!usroInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Usro con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [usroInstance: usroInstance]
    } //form_ajax

    def save() {
        if (params.fechaInicio) {
            params.fechaInicio = new Date().parse("dd-MM-yyyy", params.fechaInicio)
        }
        if (params.fechaFin) {
            params.fechaFin = new Date().parse("dd-MM-yyyy", params.fechaFin)
        }
        if (params.fechaNacimiento) {
            params.fechaNacimiento = new Date().parse("dd-MM-yyyy", params.fechaNacimiento)
        }
        if (params.password) {
            params.password = params.password.encodeAsMD5()
        }
        if (params.autorizacion) {
            params.autorizacion = params.autorizacion.encodeAsMD5()
        }

        def usroInstance
        if (params.id) {
            usroInstance = Usro.get(params.id)
            if (!usroInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Usro con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            usroInstance.properties = params
        }//es edit
        else {
            usroInstance = new Usro(params)
        } //es create
        if (!usroInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Usro " + (usroInstance.id ? usroInstance.id : "") + "</h4>"

            str += "<ul>"
            usroInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Usro " + usroInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Usro " + usroInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def usroInstance = Usro.get(params.id)
        if (!usroInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Usro con id " + params.id
            redirect(action: "list")
            return
        }
        [usroInstance: usroInstance]
    } //show

    def delete() {
        def usroInstance = Usro.get(params.id)
        if (!usroInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Usro con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            usroInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Usro " + usroInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Usro " + (usroInstance.id ? usroInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
