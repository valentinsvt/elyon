package elyon

import jxl.Workbook
import jxl.Sheet
import jxl.Cell

class LoteController {

    def index() { }


    def procesa(){


        def file = new File("/home/svt/Downloads/cargaDatos.xls")
        def campos =[:]
        def mapa = toMap(Lote)
        def error  =""
        Workbook workbook = Workbook.getWorkbook(file)

        workbook.getNumberOfSheets().times { sheet ->
            Sheet s = workbook.getSheet(sheet)


            if (!s.getSettings().isHidden()) {
                println "hoja"+ s.getName()
                Cell[] row = null
                s.getRows().times { i ->
                    row = s.getRow(i)
                    if (row.length > 0) {
                        if (i == 0) {
                            row.length.times { j ->
                                campos.put(row[j].getContents().trim().toLowerCase().replaceAll(" ","").replaceAll("\\.",""),j)

                            }
                            campos = compare(campos,mapa)
                            println "headers "+campos
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
//                            println "paremtros "+parametros
                            registro.properties=parametros
                            if (!registro.save(flush: true)){
                                println "error save "+registro.errors
                            }
                        }
                    }

                }


            }
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

