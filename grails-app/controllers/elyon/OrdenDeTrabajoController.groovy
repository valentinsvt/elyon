package elyon

import elyon.seguridad.Usro
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
        println "loteorden " + params
        def orden = OrdenDeTrabajo.get(params.orden.toLong())
        def lote = Lote.findAllByOrdenDeTrabajo(orden)
        [lote: lote, orden: orden]
    }

    def listaAsignar = {
        def orden = OrdenDeTrabajo.get(params.orden)
        def campana = orden.campana
        def lote = Lote.findAllByOrdenDeTrabajoIsNullAndCampana(campana)
        [lote: lote, orden: orden]
    }

    def asignarLote = {
//        println "asignar lote "+params
        def orden = OrdenDeTrabajo.get(params.orden)
        def parts = params.lote.split(",")
        parts.each {
            if (it != "") {
                def lote = Lote.get(it)
                lote.ordenDeTrabajo = orden
                if (!lote.save(flush: true))
                    println "error lote " + lote.errors
                else {
                    6.times { num ->
                        def campo = "telefono" + (num + 1)
                        if (lote.properties[campo]) {
                            def gt = new GestionTelefonica()
                            gt.estadoLlamada = EstadoLlamada.findByDescripcion("Llamar")
                            gt.fecha = new Date()
                            gt.lote = lote
                            gt.telefono = lote.properties[campo]
                            gt.observaciones = "Por llamar"
                            if (!gt.save(flush: true))
                                println "error gt " + gt.errors
                        }
                    }
                }

            }
        }
        render "ok"
    }

    def form_ajax() {
        def ordenDeTrabajoInstance = new OrdenDeTrabajo(params)
        if (params.id) {
            ordenDeTrabajoInstance = OrdenDeTrabajo.get(params.id)
            if (!ordenDeTrabajoInstance) {
                flash.clase = "alert-error"
                flash.message = "No se encontró Orden De Trabajo con id " + params.id
                redirect(action: "list")
                return
            } //no existe el objeto
        } //es edit
        return [ordenDeTrabajoInstance: ordenDeTrabajoInstance]
    } //form_ajax

    def valoresCamp() {
        def camp = Campana.get(params.camp)
        def val = valores(camp)
        render val.all + "_" + val.usados + "_" + val.disp
    }

    def valores(Campana camp) {
        def all = Lote.countByCampana(camp)
        def disp = Lote.countByCampanaAndOrdenDeTrabajoIsNull(camp)
        def usados = Lote.countByCampanaAndOrdenDeTrabajoIsNotNull(camp)

//        println all + "  " + disp + '  ' + usados + " " + (all - disp)

        return [
                all: all,
                disp: disp,
                usados: usados
        ]
    }

    def loadCamp() {
        def camp = Campana.get(params.camp.toLong())
        render tabla(camp)
    }

    private String tabla(Campana camp) {

        def val = valores(camp)

        def tb = "";

        tb += '<div class="row" style="margin-bottom: 15px;">'
        tb += '<div class="span9" id="divInfo">La campaña tiene ' + val.all + ' lotes, de los cuales ' + val.usados + ' están asignados y ' + val.disp + ' disponibles.</div>'
        tb += '</div>'

        tb += '<table class="table table-bordered table-striped table-condensed table-hover">'
        tb += '<thead>'
        tb += '<th>Usuario</th>'
        tb += '<th>Cantidad</th>'
        tb += '<th style="width: 150px;">Acciones</th>'
        tb += '</thead>'
        tb += '<tbody id="tbUsuarios">'

        OrdenDeTrabajo.findAllByCampana(camp).each { o ->
            tb += "<tr>"
            tb += "<td>" + o.usro.login + "</td>"
            tb += "<td>" + o.numero + " (" + Lote.countByCampanaAndOrdenDeTrabajo(camp, o) + ")" + "</td>"
            tb += "<td style='text-align=center'><a href='#' class='btn btn-danger btn-small btnDel' id='${o.id}'><i class='icon-trash'></i> Eliminar</a>"
            tb += '<a class="btn btn-small btn-inverse btn-ajax btnVer" href="#" rel="tooltip" title="Ver" id="' + o.id + '" style="margin-left:5px;">'
            tb += '<i class="icon-tasks icon-large"></i>'
            tb += 'Ver'
            tb += '</a></td>'
            tb += "</tr>"
        }
        tb += '</tbody>'
        tb += '</table>'

        def js = '<script type="text/javascript">'
        js += '$(".btnDel").click(function() {'
        js += 'eliminar($(this));'
        js += '});'
        js += '$(".btnVer").click(function() {'
        js += 'ver($(this));'
        js += '});'
        js += "</script>"

        return tb + js
    }

    def delUsu() {
        def err = 0
        def orden = OrdenDeTrabajo.get(params.orden.toLong())
        def camp = orden.campana

        Lote.findAllByOrdenDeTrabajo(orden).each { lote ->
            lote.ordenDeTrabajo = null
            if (!lote.save(flush: true)) {
                err++
            }
        }
        try {
            orden.delete(flush: true)
        } catch (e) {
            println e
        }
        render tabla(camp)
    }

    def addUsu() {

        def camp = Campana.get(params.camp.toLong())
        def usu = Usro.get(params.usu.toLong())
        def cant = params.cant.toInteger()
        def errores = 0

        def ordenDeTrabajoInstance = new OrdenDeTrabajo([
                campana: camp,
                usro: usu,
                numero: cant
        ])
        if (ordenDeTrabajoInstance.save(flush: true)) {
            def c = Lote.createCriteria()
            def lotes = c.list {
                eq("campana", camp)
                isNull("ordenDeTrabajo")
                maxResults(cant)
            }
//            println "lotes: " + lotes
            lotes.each { lote ->
                lote.ordenDeTrabajo = ordenDeTrabajoInstance
                if (!lote.save(flush: true)) {
                    println lote.errors
                    errores++
                } else {
                    6.times { num ->
                        def campo = "telefono" + (num + 1)
                        if (lote.properties[campo]) {
                            def gt = new GestionTelefonica()
                            gt.estadoLlamada = EstadoLlamada.findByDescripcion("Llamar")
                            gt.fecha = new Date()
                            gt.lote = lote
                            gt.telefono = lote.properties[campo]
                            gt.observaciones = "Por llamar"
                            if (!gt.save(flush: true))
                                println "error gt " + gt.errors
                        }
                    }
                    println "ok " + lote.id
                }
            }
            if (errores == 0) {
//                render "OK_" + ordenDeTrabajoInstance.id
            } else {
//                render "Ha ocurrido un error al asignar " + errores + " lote" + (errores == 1 ? "" : "s")
            }
        } else {
            println ordenDeTrabajoInstance.errors
//            render "Ha ocurrido un error al crear la orden de trabajo"
        }
        render tabla(camp)
    }

    def save() {
        def ordenDeTrabajoInstance
        if (params.id) {
            ordenDeTrabajoInstance = OrdenDeTrabajo.get(params.id)
            if (!ordenDeTrabajoInstance) {
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
                err.arguments.eachWithIndex { arg, i ->
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
            flash.message = "No se encontró Orden De Trabajo con id " + params.id
            redirect(action: "list")
            return
        }
        [ordenDeTrabajoInstance: ordenDeTrabajoInstance]
    } //show

    def delete() {
        def ordenDeTrabajoInstance = OrdenDeTrabajo.get(params.id)
        if (!ordenDeTrabajoInstance) {
            flash.clase = "alert-error"
            flash.message = "No se encontró Orden De Trabajo con id " + params.id
            redirect(action: "list")
            return
        }

        try {
            ordenDeTrabajoInstance.delete(flush: true)
            flash.clase = "alert-success"
            flash.message = "Se ha eliminado correctamente Orden De Trabajo " + ordenDeTrabajoInstance.id
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.clase = "alert-error"
            flash.message = "No se pudo eliminar Orden De Trabajo " + (ordenDeTrabajoInstance.id ? ordenDeTrabajoInstance.id : "")
            redirect(action: "list")
        }
    } //delete
} //fin controller
