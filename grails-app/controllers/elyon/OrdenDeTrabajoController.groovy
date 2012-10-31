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

//        def path = servletContext.getRealPath("/") + "lotes/"
//        def f = request.getFile('archivo')
//        def inputStream = new ByteArrayInputStream(f.getBytes())
////        println "f  "+f+"  "+f.class
//        Workbook workbook = Workbook.getWorkbook(inputStream)
//        def msn
//        if (f && !f.empty) {
//            def fileName = f.getOriginalFilename()
//            def ext
//            def parts = fileName.split("\\.")
//            fileName = ""
//            parts.eachWithIndex { obj, i ->
//                if (i < parts.size() - 1) {
//                    fileName += obj
//                } else {
//                    ext = obj
//                }
//            }
//            def reps = [
//                    "a": "[àáâãäåæ]",
//                    "e": "[èéêë]",
//                    "i": "[ìíîï]",
//                    "o": "[òóôõöø]",
//                    "u": "[ùúûü]",
//
//                    "A": "[ÀÁÂÃÄÅÆ]",
//                    "E": "[ÈÉÊË]",
//                    "I": "[ÌÍÎÏ]",
//                    "O": "[ÒÓÔÕÖØ]",
//                    "U": "[ÙÚÛÜ]",
//
//                    "n": "[ñ]",
//                    "c": "[ç]",
//
//                    "N": "[Ñ]",
//                    "C": "[Ç]",
//
//                    "": "[\\!@\\\$%\\^&*()='\"\\/<>:;\\.,\\?]",
//
//                    "_": "[\\s]"
//            ]
//
//            reps.each { k, v ->
//                fileName = (fileName.trim()).replaceAll(v, k)
//            }
//
//            fileName = fileName+"_"+new Date().format("mm_ss")+"." + "pdf"
//
//            def pathFile = path + File.separatorChar + fileName
//            def src = new File(pathFile)
//
//
//            if (src.exists()) {
//                msn="Ya existe un archivo con ese nombre. Por favor cámbielo."
//            } else {
//
//            }
//        }

//        def ordenDeTrabajoInstance
//        if(params.id) {
//            ordenDeTrabajoInstance = OrdenDeTrabajo.get(params.id)
//            if(!ordenDeTrabajoInstance) {
//                flash.clase = "alert-error"
//                flash.message = "No se encontró Orden De Trabajo con id " + params.id
//                redirect(action: 'list')
//                return
//            }//no existe el objeto
//            ordenDeTrabajoInstance.properties = params
//        }//es edit
//        else {
//            ordenDeTrabajoInstance = new OrdenDeTrabajo(params)
//        } //es create
//        if (!ordenDeTrabajoInstance.save(flush: true)) {
//            flash.clase = "alert-error"
//            def str = "<h4>No se pudo guardar Orden De Trabajo " + (ordenDeTrabajoInstance.id ? ordenDeTrabajoInstance.id : "") + "</h4>"
//
//            str += "<ul>"
//            ordenDeTrabajoInstance.errors.allErrors.each { err ->
//                def msg = err.defaultMessage
//                err.arguments.eachWithIndex {  arg, i ->
//                    msg = msg.replaceAll("\\{" + i + "}", arg.toString())
//                }
//                str += "<li>" + msg + "</li>"
//            }
//            str += "</ul>"
//
//            flash.message = str
//            redirect(action: 'list')
//            return
//        }
//
//        if(params.id) {
//            flash.clase = "alert-success"
//            flash.message = "Se ha actualizado correctamente Orden De Trabajo " + ordenDeTrabajoInstance.id
//        } else {
//            flash.clase = "alert-success"
//            flash.message = "Se ha creado correctamente Orden De Trabajo " + ordenDeTrabajoInstance.id
//        }
//        redirect(action: 'list')
//



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
