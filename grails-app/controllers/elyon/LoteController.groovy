package elyon

import jxl.Workbook
import jxl.Sheet
import jxl.Cell

class LoteController {

    def index() { }


    def procesa(){
        def path = servletContext.getRealPath("/") + "lotes/"
        def f = request.getFile('archivo')
        def inputStream = new ByteArrayInputStream(f.getBytes())
        if (f && !f.empty) {
            Workbook workbook = Workbook.getWorkbook(inputStream)

            def campos =[:]
            def mapa = toMap(Lote)
            def error  =""

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
            } else{

                workbook.getNumberOfSheets().times { sheet ->
                    Sheet s = workbook.getSheet(sheet)


                    if (!s.getSettings().isHidden()) {
//                println "hoja"+ s.getName()
                        Cell[] row = null
                        s.getRows().times { i ->
                            row = s.getRow(i)
                            if (row.length > 0) {
                                if (i == 0) {
                                    row.length.times { j ->
                                        campos.put(row[j].getContents().trim().toLowerCase().replaceAll(" ","").replaceAll("\\.",""),j)

                                    }
                                    campos = compare(campos,mapa)
//                            println "headers "+campos
                                    if (campos.size()<8){
                                        error = "Error: No se pudo procesar los nombres de las columnas en el archivo exel"
                                    }

                                }else {
                                    def registro = new Lote()
                                    def parametros=[:]
                                    campos.each {cmp->
                                        if (row[cmp.value.toInteger()].getContents()){
                                            if (mapa[cmp.key]=~"elyon")
                                                parametros[cmp.key+".id"]=row[cmp.value.toInteger()].getContents()
                                            else
                                                parametros[cmp.key]=row[cmp.value.toInteger()].getContents()
                                        }

                                    }
                                    registro.properties=parametros
                                    if (!registro.save(flush: true)){
                                        println "error save "+registro.errors
                                    }else{
                                        def lo = new LoteOrdenTrabajo()
                                        lo.ordenDeTrabajo=ordenDeTrabajoInstance
                                        lo.lote=registro
                                        if (!lo.save(flush: true))
                                            println "error lo "+lo.errors
                                    }
                                }
                            }

                        }


                    }
                }

                if(params.id) {
                    flash.clase = "alert-success"
                    flash.message = "Se ha actualizado correctamente Orden De Trabajo " + ordenDeTrabajoInstance.id
                } else {
                    flash.clase = "alert-success"
                    flash.message = "Se ha creado correctamente Orden De Trabajo " + ordenDeTrabajoInstance.id
                }
            }


            redirect(action: 'list',controller: "ordenDeTrabajo")





        }

    }

    HashMap toMap(dominio) {
        def mapa = [:]
        dominio.properties.declaredFields.each {
            if (it.getName().substring(0, 1) != "\$" && it.getName().substring(0, 1) != "") {
                mapa.put(it.getName().toString().toLowerCase(), it.getType())
            }
        }
        return mapa
    }
    HashMap compare(excel,dominio){

        def result =[:]
        def score = 0
        def top = 0
        excel.each{xl->
            def palabra = xl.key
            def tam =3,pos=0
            def iteraciones = Math.floor(palabra.size()/tam).toInteger()
            def segmentos = []
            def campo = ""
            def columna = -1
//            println "palabra "+xl.key+" "+top+" "+campo+" "+columna
            iteraciones.times {t->
                if(pos+tam<palabra.size())
                    if (t < iteraciones-1)
                        segmentos.add(palabra.substring(pos,pos+tam).replaceAll("�","[aeioun]"))
                    else
                        segmentos.add(palabra.substring(pos,palabra.size()).replaceAll("�","[aeioun]"))
                else
                    segmentos.add(palabra.substring(pos,palabra.size()).replaceAll("�","[aeioun]"))
                // print "pos "+pos+" "+palabra.size()+" "+segmentos[segmentos.size()]
                pos=tam*(t+1)

            }
//            println "segmentos "+segmentos + "============>>>>>>>>>>>>>>>>>>>>"
            dominio.each{d->
                if(!result[d.key]){
                    segmentos.each {s->
                        if(d.key.toString()=~s){
//                            println "mach "+s+" "+d.key
                            score++
                        }else{
//                            println "no mach "+s+" "+d.key
                        }
                    }
                    if (score==segmentos.size())
                        score+=40
                    if(score>0)
                        score+=1/Math.abs(palabra.size()-(d.key.size()+0.1))
                    if (score>top){
                        top=score
                        campo=d.key
                        columna=xl.value
//                        println "new top "+top+" "+campo+" # "+columna
                    }
                    score=0
                }


            }
            result.put(campo,columna)
            top=0
            campo=""
            columna=-1


        }

//        println "result "+result
        return result

    }

}

