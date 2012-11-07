package elyon

import jxl.Workbook
import jxl.Sheet
import jxl.Cell
import elyon.seguridad.Usro

class LoteController extends elyon.seguridad.Shield {

    def index() { }

    def buscadorService

    def procesa() {
        def path = servletContext.getRealPath("/") + "lotes/"
        def f = request.getFile('archivo')
        def inputStream = new ByteArrayInputStream(f.getBytes())
        if (f && !f.empty) {
            Workbook workbook = Workbook.getWorkbook(inputStream)

            def campos = [:]
            def mapa = toMap(Lote)
            def error = ""

            def campanaInstace
            if (params.id) {
                campanaInstace = Campana.get(params.id)
                if (!campanaInstace) {
                    flash.clase = "alert-error"
                    flash.message = "No se encontró Orden De Trabajo con id " + params.id
                    redirect(action: 'list')
                    return
                }//no existe el objeto
                campanaInstace.properties = params
            }//es edit
            else {
                campanaInstace = new Campana(params)
            } //es create
            if (!campanaInstace.save(flush: true)) {
                flash.clase = "alert-error"
                def str = "<h4>No se pudo guardar Orden De Trabajo " + (ordenDeTrabajoInstance.id ? ordenDeTrabajoInstance.id : "") + "</h4>"

                str += "<ul>"
                campanaInstace.errors.allErrors.each { err ->
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
            } else {

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
                                        campos.put(row[j].getContents().trim().toLowerCase().replaceAll(" ", "").replaceAll("\\.", ""), j)

                                    }
                                    campos = compare(campos, mapa)
//                            println "headers "+campos
                                    if (campos.size() < 8) {
                                        error = "Error: No se pudo procesar los nombres de las columnas en el archivo exel"
                                    }

                                } else {
                                    def registro = new Lote()
                                    def parametros = [:]
                                    campos.each {cmp ->
                                        if (row[cmp.value.toInteger()].getContents()) {
                                            if (mapa[cmp.key] =~ "elyon")
                                                parametros[cmp.key + ".id"] = row[cmp.value.toInteger()].getContents()
                                            else
                                                parametros[cmp.key] = row[cmp.value.toInteger()].getContents()
                                        }

                                    }
                                    registro.properties = parametros
                                    registro.campana = campanaInstace
                                    def verificacion = Lote.findAllByCampanaAndCedula(campanaInstace, registro.cedula)
                                    if (verificacion.size() == 0) {
                                        if (!registro.save(flush: true)) {
                                            println "error save " + registro.errors
                                        }
                                    } else {
                                        println "no isert por repetido dentro de la campaña"
                                    }

                                }
                            }

                        }


                    }
                }

                if (params.id) {
                    flash.clase = "alert-success"
                    flash.message = "Se ha actualizado correctamente Orden De Trabajo " + campanaInstace.id
                } else {
                    flash.clase = "alert-success"
                    flash.message = "Se ha creado correctamente Orden De Trabajo " + campanaInstace.id
                }
            }
            redirect(action: 'list', controller: "campana")
        }
    }


    def busqueda() {
        def camposLote = ["nombre": ["Nombre", "string"], "cedula": ["Cédula", "string"]]
        [camposLote: camposLote]
    }

    def busquedaRegistro() {
        def camposLote = ["nombre": ["Nombre", "string"], "cedula": ["Cédula", "string"]]
        [camposLote: camposLote]
    }


    def buscarLoteRegistro() {
//        session.usuario = Usro.get(5)
        def closure = {lote ->
            return "" + lote.ordenDeTrabajo.campana + " #" + lote.ordenDeTrabajo.numero
        }
        def numeros = {numero ->
            return g.formatNumber(number: numero, format: "###,##0", minFractionDigits: 2, maxFractionDigits: 2)
        }
        def listaTitulos = ["Cédula", "Nombre", "Código", "O. de trabajo", "Ciudad", "Teléfono", "Estado", "Tipo", "Tarjeta", "Cupo", "Cupo Total"]
        def listaCampos = ["cedula", "nombre", "codigo", "orden", "ciudad", "telefono1", "estadoGestion", "tipoCliente", "tipoTarjeta", "cupoNormal", "cupoTotal"]
        def funciones = [null, null, null, ["closure": [closure, "&"]], null, null, null, null, null, ["closure": [numeros, "?"]], ["closure": [numeros, "?"]]]
        def url = g.createLink(action: "buscarLote", controller: "lote")
        def show = "registro"
        def link = "cedula"
        def numRegistros = 20
        def extras = " and ordenDeTrabajo in ("
        def ordenes = OrdenDeTrabajo.findAllByUsro(session.usuario)
        if (ordenes.size() == 0)
            extras += "-1) and estado != 'N'"
        ordenes.eachWithIndex {o, i ->
            extras += "" + o.id
            if (i < ordenes.size() - 1)
                extras += ","

        }
        extras += ") and estado != 'N'"
        def lista = buscadorService.buscar(Lote, "Lote", "excluyente", params, true, extras) /* Dominio, nombre del dominio , excluyente o incluyente ,params tal cual llegan de la interfaz del buscador, ignore case */
        lista.pop()
        if (!params.reporte) {
            render(view: '../lstaTbla', model: [listaTitulos: listaTitulos, listaCampos: listaCampos, lista: lista, funciones: funciones, url: url, controller: "llamada", show: show, link: link, numRegistros: numRegistros])
        } else {
            println "entro reporte"
            /*De esto solo cambiar el dominio, el parametro tabla, el paramtero titulo y el tamaño de las columnas (anchos)*/
            session.dominio = Lote
            session.funciones = funciones
            def anchos = [7, 18, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7] /*el ancho de las columnas en porcentajes... solo enteros*/
            redirect(controller: "reportes", action: "reporteBuscador", params: [listaCampos: listaCampos, listaTitulos: listaTitulos, tabla: "Lote", orden: params.orden, ordenado: params.ordenado, criterios: params.criterios, operadores: params.operadores, campos: params.campos, titulo: "Lotes", anchos: anchos, extras: extras, landscape: true])
        }
    }

    def buscarLote() {
//        session.usuario = Usro.get(5)
        def closure = {lote ->
            return "" + lote.ordenDeTrabajo.campana + " #" + lote.ordenDeTrabajo.numero
        }
        def numeros = {numero ->
            return g.formatNumber(number: numero, format: "###,##0", minFractionDigits: 2, maxFractionDigits: 2)
        }
        def listaTitulos = ["Cédula", "Nombre", "Código", "O. de trabajo", "Ciudad", "Teléfono","Estado", "Tipo", "Tarjeta", "Cupo", "Cupo Total"]
        def listaCampos = ["cedula", "nombre", "codigo", "orden", "ciudad", "telefono1", "estadoGestion", "tipoCliente", "tipoTarjeta", "cupoNormal", "cupoTotal"]
        def funciones = [null, null, null, ["closure": [closure, "&"]], null, null, null, null, null, ["closure": [numeros, "?"]], ["closure": [numeros, "?"]]]
        def url = g.createLink(action: "buscarLote", controller: "lote")
        def show = "gestion"
        def link = "cedula"
        def numRegistros = 20
        def extras = " and ordenDeTrabajo in ("
        def ordenes = OrdenDeTrabajo.findAllByUsro(session.usuario)
        if (ordenes.size() == 0)
            extras += "-1) and estado != 'N'"
        ordenes.eachWithIndex {o, i ->
            extras += "" + o.id
            if (i < ordenes.size() - 1)
                extras += ","

        }
        extras += ") and estado != 'N'"
        def lista = buscadorService.buscar(Lote, "Lote", "excluyente", params, true, extras) /* Dominio, nombre del dominio , excluyente o incluyente ,params tal cual llegan de la interfaz del buscador, ignore case */
        lista.pop()
        if (!params.reporte) {
            render(view: '../lstaTbla', model: [listaTitulos: listaTitulos, listaCampos: listaCampos, lista: lista, funciones: funciones, url: url, controller: "gestionTelefonica", show: show, link: link, numRegistros: numRegistros])
        } else {
            println "entro reporte"
            /*De esto solo cambiar el dominio, el parametro tabla, el paramtero titulo y el tamaño de las columnas (anchos)*/
            session.dominio = Lote
            session.funciones = funciones
            def anchos = [7, 18, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7] /*el ancho de las columnas en porcentajes... solo enteros*/
            redirect(controller: "reportes", action: "reporteBuscador", params: [listaCampos: listaCampos, listaTitulos: listaTitulos, tabla: "Lote", orden: params.orden, ordenado: params.ordenado, criterios: params.criterios, operadores: params.operadores, campos: params.campos, titulo: "Lotes", anchos: anchos, extras: extras, landscape: true])
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

    HashMap compare(excel, dominio) {

        def result = [:]
        def score = 0
        def top = 0
        excel.each {xl ->
            def palabra = xl.key
            def tam = 3, pos = 0
            def iteraciones = Math.floor(palabra.size() / tam).toInteger()
            def segmentos = []
            def campo = ""
            def columna = -1
//            println "palabra "+xl.key+" "+top+" "+campo+" "+columna
            iteraciones.times {t ->
                if (pos + tam < palabra.size())
                    if (t < iteraciones - 1)
                        segmentos.add(palabra.substring(pos, pos + tam).replaceAll("�", "[aeioun]"))
                    else
                        segmentos.add(palabra.substring(pos, palabra.size()).replaceAll("�", "[aeioun]"))
                else
                    segmentos.add(palabra.substring(pos, palabra.size()).replaceAll("�", "[aeioun]"))
                // print "pos "+pos+" "+palabra.size()+" "+segmentos[segmentos.size()]
                pos = tam * (t + 1)

            }
//            println "segmentos "+segmentos + "============>>>>>>>>>>>>>>>>>>>>"
            dominio.each {d ->
                if (!result[d.key]) {
                    segmentos.each {s ->
                        if (d.key.toString() =~ s) {
//                            println "mach "+s+" "+d.key
                            score++
                        } else {
//                            println "no mach "+s+" "+d.key
                        }
                    }
                    if (score == segmentos.size())
                        score += 40
                    if (score > 0)
                        score += 1 / Math.abs(palabra.size() - (d.key.size() + 0.1))
                    if (score > top) {
                        top = score
                        campo = d.key
                        columna = xl.value
//                        println "new top "+top+" "+campo+" # "+columna
                    }
                    score = 0
                }


            }
            result.put(campo, columna)
            top = 0
            campo = ""
            columna = -1


        }

//        println "result "+result
        return result

    }

}

