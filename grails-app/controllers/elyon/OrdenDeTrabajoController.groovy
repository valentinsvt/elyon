package elyon

import org.springframework.dao.DataIntegrityViolationException


class OrdenDeTrabajoController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [ordenDeTrabajoInstanceList: OrdenDeTrabajo.list(params), params: params]
    } //list


    def lotePorOrden = {
        println "loteorden "+params
        def orden = OrdenDeTrabajo.get(params.orden)
        def lote = Lote.findAllByOrdenDeTrabajo(orden)
        [lote:lote,orden:orden]
    }

    def listaAsignar = {
        def orden = OrdenDeTrabajo.get(params.orden)
        def campana = orden.campana
        def lote = Lote.findAllByOrdenDeTrabajoIsNullAndCampana(campana)
        [lote:lote,orden:orden]
    }

    def asignarLote = {
//        println "asignar lote "+params
        def orden = OrdenDeTrabajo.get(params.orden)
        def parts = params.lote.split(",")
        parts.each {
            if(it!=""){
                def lote = Lote.get(it)
                lote.ordenDeTrabajo=orden
                if (!lote.save(flush: true))
                    println "error lote "+lote.errors
                else{
                    6.times {num->
                        def campo = "telefono"+(num+1)
                        if (lote.properties[campo]){
                            def gt = new GestionTelefonica()
                            gt.estadoLlamada=EstadoLlamada.findByDescripcion("Llamar")
                            gt.fecha=new Date()
                            gt.lote=lote
                            gt.telefono=lote.properties[campo]
                            gt.observaciones="Por llamar"
                            if (!gt.save(flush: true))
                                println "error gt "+gt.errors

                        }

                    }
                }

            }
        }
        render "ok"
    }

    def form_ajax() {
        def ordenDeTrabajoInstance = new OrdenDeTrabajo(params)
        if(params.id) {
            ordenDeTrabajoInstance = OrdenDeTrabajo.get(params.id)
            if(!ordenDeTrabajoInstance) {
                flash.clase = "alert-error"
                flash.message =  "No se encontró Orden De Trabajo con id " + params.id
                redirect(action:  "list")
                return
            } //no existe el objeto
        } //es edit
        return [ordenDeTrabajoInstance: ordenDeTrabajoInstance]
    } //form_ajax

    def save() {

        def ordenDeTrabajoInstance
        if(params.id) {
            ordenDeTrabajoInstance = OrdenDeTrabajo.get(params.id)
            if(!ordenDeTrabajoInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Orden De Trabajo con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            ordenDeTrabajoInstance.properties = params
        }//es edit
        else {
            ordenDeTrabajoInstance = new OrdenDeTrabajo(params)
        } //es create
        if (!ordenDeTrabajoInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Orden De Trabajo " + (ordenDeTrabajoInstance.id ? ordenDeTrabajoInstance.id : "") + "</h4>"

            str += "<ul>"
            ordenDeTrabajoInstance.errors.allErrors.each { err ->
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

        if(params.id) {
            flash.clase = "alert-success"
            flash.message = "Se ha actualizado correctamente Orden De Trabajo " + ordenDeTrabajoInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Orden De Trabajo " + ordenDeTrabajoInstance.id
        }
        redirect(action: 'list')




    } //save

    def show_ajax() {
        def ordenDeTrabajoInstance = OrdenDeTrabajo.get(params.id)
        if (!ordenDeTrabajoInstance) {
            flash.clase = "alert-error"
            flash.message =  "No se encontró Orden De Trabajo con id " + params.id
            redirect(action: "list")
            return
        }
        [ordenDeTrabajoInstance: ordenDeTrabajoInstance]
    } //show

    def delete() {
        def ordenDeTrabajoInstance = OrdenDeTrabajo.get(params.id)
        if (!ordenDeTrabajoInstance) {
            flash.clase = "alert-error"
            flash.message =  "No se encontró Orden De Trabajo con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            ordenDeTrabajoInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message =  "Se ha eliminado correctamente Orden De Trabajo " + ordenDeTrabajoInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message =  "No se pudo eliminar Orden De Trabajo " + (ordenDeTrabajoInstance.id ? ordenDeTrabajoInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
