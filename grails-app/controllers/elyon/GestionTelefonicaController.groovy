package elyon

import elyon.seguridad.Usro
import org.springframework.dao.DataIntegrityViolationException

import static elyon.OrdenDeTrabajo.*

class GestionTelefonicaController extends elyon.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } //index

    def list() {
        [gestionTelefonicaInstanceList: GestionTelefonica.list(params), params: params]
    } //list

    def gestion() {

        println("params" + params)

        def lote = Lote.get(params.id)

        def data = Data.get(lote.id)


//        def data2 = Data.findByLote(lote)

        def noDesea = NoDesea.get(lote?.noDesea?.id)

        def estadoGestion = new EstadoGestion()

        def restantes = Lote.findAllByOrdenDeTrabajoAndEstadoGestionIsNull(lote.ordenDeTrabajo)

        def estadoLlamada = EstadoLlamada.get(11)

        def estadoLlamadaT = new EstadoLlamada()

//        def gestion = GestionTelefonica.findAllByLoteAndEstadoLlamada(lote, estadoLlamada, [sort: "id"])

        def gestion = GestionTelefonica.findAllByLote(lote)

//        def gestion = GestionTelefonica.findAllByLote(lote, [sort:"id"])

//        println ">>>>>>>>>>>>>>>>>"+gestion.id


        [lote: lote, gestion: gestion, estadoGestion: estadoGestion, restantes: restantes, estadoLlamada: estadoLlamada, estadoLlamadaT: estadoLlamadaT, data: data, noDesea: noDesea]


    }


    def siguiente(){
        println "acutal "+params
        def actual = params.id
        def usro = Usro.get(session.usuario.id)
        def orden= OrdenDeTrabajo.get(params.orden)
        def next = nextLote(actual.toInteger(),params.orden.toInteger())
        if(!next){
            redirect(controller: "lote",action: "busqueda")
            return
        }
        println "siguiente? "+next.id
        redirect(action: "gestion",id:next.id)

    }


    def nextLote(actual,orden){
        println "next lote "+actual+"  "+orden
        def next = null
        def cont = 0
        def lotes
        while(!next){
//           lotes =  Lote.findAllByOrdenDeTrabajoAndIdGreaterThan(orden,actual.toLong(),[sort:"id"])
            lotes =  Lote.findAll("from Lote where ordenDeTrabajo=${orden} and id >${actual} and estadoGestion in (0, 1, 6, 8) order by id")
            if(lotes.size()>0){
                next=lotes[0]
            }else{
                def nextOrden = nextOrden(orden)
                if(nextOrden==orden)
                    next=nextLote(0,orden)
                else
                 next=nextLote(actual,nextOrden)

            }
            cont++
            if(cont==10)
                break;
        }

       return next
    }
    
    def nextOrden(orden){
        def next = null
        while(!next){
            def ordenes = OrdenDeTrabajo.findAllByUsroAndIdGreaterThan(session.usuario,orden,[sort:"id"])
            if(ordenes.size()>0)
                next=ordenes[0].id
            else{
                next=nextOrden(0)
            }

        }
        return next
    }

    def saveGestion() {

        println(params)

        println "save gestion?"
        def msg = "ok"
        def loteId = params.lote
        def estadoGestionId = params.estadoGestion
        def estadoGestion = EstadoGestion.get(estadoGestionId)
        def lote = Lote.get(loteId)

        println("lote" + lote )
//
//        def data = Data.findByLote(lote)
//
//        println("data" + data)

        def noDesea = NoDesea.get(params.noDesea)

        //println "estado "+estadoGestion
        if (estadoGestion){
            if (estadoGestion.lista=="S")
                lote.estado="N"
        }
        //println "estado "+lote.estado
        lote.estadoGestion = estadoGestion

        lote.noDesea = noDesea
//
//        data.noDesea = noDesea
//
//        data.save(flush: true)
//


        if (!lote.save(flush: true)) {
            msg += "error save lote: " + lote.errors
            println "error save lote: " + lote.errors
        } else {
            msg += "save lote ${lote.id} ok "
        }

        params.each { k, v ->
            if (v instanceof java.lang.String) {

            } else {
                def id = v.id
                def estadoId = v.estado
                def observaciones = v.observaciones

                def gestion = GestionTelefonica.get(id)
                gestion.estadoLlamada = EstadoLlamada.get(estadoId)
                gestion.observaciones = observaciones.trim()
                if (!gestion.save(flush: true)) {
                    msg += "ERROR:" + gestion.errors
                    println "error save gestion " + id + ": " + gestion.errors
                } else {
                    msg += "save gestion telefonica " + id + " ok "
                }
            }
        }

//        println msg
//        render msg
        redirect ( action: "siguiente", params:[id:lote.id,orden:lote.ordenDeTrabajo.id])
    }
    def ingresoVenta() {

        println(params)

        println "save gestion?"
        def msg = "ok"
        def loteId = params.lote
        def estadoGestionId = params.estadoGestion
        def estadoGestion = EstadoGestion.get(estadoGestionId)
        def lote = Lote.get(loteId)

        println("lote" + lote )
//
//        def data = Data.findByLote(lote)
//
//        println("data" + data)

        def noDesea = NoDesea.get(params.noDesea)

        //println "estado "+estadoGestion
        if (estadoGestion){
            if (estadoGestion.lista=="S")
                lote.estado="N"
        }
        //println "estado "+lote.estado
        lote.estadoGestion = estadoGestion

        lote.noDesea = noDesea
//
//        data.noDesea = noDesea
//
//        data.save(flush: true)
//


        if (!lote.save(flush: true)) {
            msg += "error save lote: " + lote.errors
            println "error save lote: " + lote.errors
        } else {
            msg += "save lote ${lote.id} ok "
        }

        params.each { k, v ->
            if (v instanceof java.lang.String) {

            } else {
                def id = v.id
                def estadoId = v.estado
                def observaciones = v.observaciones

                def gestion = GestionTelefonica.get(id)
                gestion.estadoLlamada = EstadoLlamada.get(estadoId)
                gestion.observaciones = observaciones.trim()
                if (!gestion.save(flush: true)) {
                    msg += "ERROR:" + gestion.errors
                    println "error save gestion " + id + ": " + gestion.errors
                } else {
                    msg += "save gestion telefonica " + id + " ok "
                }
            }
        }

//        println msg
//        render msg
        redirect (controller: "llamada", action: "registro", id: params.lote)
    }

    def form_ajax() {
        def gestionTelefonicaInstance = new GestionTelefonica(params)
        if (params.id) {
            gestionTelefonicaInstance = GestionTelefonica.get(params.id)
            if (!gestionTelefonicaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Gestion Telefonica con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [gestionTelefonicaInstance: gestionTelefonicaInstance]
    } //form_ajax

    def save() {
        def gestionTelefonicaInstance
        if (params.id) {
            gestionTelefonicaInstance = GestionTelefonica.get(params.id)
            if (!gestionTelefonicaInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Gestion Telefonica con id " + params.id
                redirect(action: 'list')
                return
            }//no existe el objeto
            gestionTelefonicaInstance.properties = params
        }//es edit
        else {
            gestionTelefonicaInstance = new GestionTelefonica(params)
        } //es create
        if (!gestionTelefonicaInstance.save(flush: true)) {
            flash.clase = "alert-error"
            def str = "<h4>No se pudo guardar Gestion Telefonica " + (gestionTelefonicaInstance.id ? gestionTelefonicaInstance.id : "") + "</h4>"

            str += "<ul>"
            gestionTelefonicaInstance.errors.allErrors.each { err ->
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
            flash.message = "Se ha actualizado correctamente Gestion Telefonica " + gestionTelefonicaInstance.id
        } else {
            flash.clase = "alert-success"
            flash.message = "Se ha creado correctamente Gestion Telefonica " + gestionTelefonicaInstance.id
        }
        redirect(action: 'list')
    } //save

    def show_ajax() {
        def gestionTelefonicaInstance = GestionTelefonica.get(params.id)
        if (!gestionTelefonicaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Gestion Telefonica con id " + params.id
            redirect(action: "list")
            return
        }
        [gestionTelefonicaInstance: gestionTelefonicaInstance]
    } //show

    def delete() {
        def gestionTelefonicaInstance = GestionTelefonica.get(params.id)
        if (!gestionTelefonicaInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Gestion Telefonica con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            gestionTelefonicaInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Gestion Telefonica " + gestionTelefonicaInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Gestion Telefonica " + (gestionTelefonicaInstance.id ? gestionTelefonicaInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
