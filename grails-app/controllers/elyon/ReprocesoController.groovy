package elyon

class ReprocesoController {
    def buscadorService
    def index() {}

    def busqueda() {
        def camposLote = ["nombre": ["Nombre", "string"], "cedula": ["Cédula", "string"],"estado":["Estado","string"]]
        [camposLote: camposLote]
    }

    def buscarLote() {
//        session.usuario = Usro.get(5)
        def extraEstados=""
        if (params.campos instanceof java.lang.String) {
            if (params.campos == "estado") {
                def estados = EstadoGestion.findAll("from EstadoGestion where upper(descripcion) like '%${params.criterios.toUpperCase()}%'")
                params.criterios = ""
                estados.eachWithIndex { p, i ->
                    extraEstados += "" + p.id
                    if (i < estados.size() - 1)
                        extraEstados += ","
                }
                if (extraEstados.size() < 1)
                    extraEstados = "-1"
                params.campos = ""
                params.operadores = ""
            }
        } else {
            def remove = []
            params.campos.eachWithIndex { p, i ->
                if (p == "estado") {
                    def estados = EstadoGestion.findAll("from EstadoGestion where upper(descripcion) like '%${params.criterios.toUpperCase()}%'")

                    estados.eachWithIndex { c, j ->
                        extraEstados += "" + c.id
                        if (j < estados.size() - 1)
                            extraEstados += ","
                    }
                    if (extraEstados.size() < 1)
                        extraEstados = "-1"
                    remove.add(i)
                }
            }
            remove.each {
                params.criterios[it] = null
                params.campos[it] = null
                params.operadores[it] = null
            }
        }
        def extras = " and ordenDeTrabajo is not null "
        if(extraEstados!=""){
             extras+=" and estadoGestion in (${extraEstados}) "
        }

        def closure = { lote ->
            return "" + lote.ordenDeTrabajo.campana + " #" + lote.ordenDeTrabajo.numero
        }
        def numeros = { numero ->
            return g.formatNumber(number: numero, format: "###,##0", minFractionDigits: 2, maxFractionDigits: 2)
        }
        def listaTitulos = ["Cédula", "Nombre", "Código", "O. de trabajo", "Ciudad", "Teléfono", "Estado", "Tipo", "Tarjeta", "Cupo", "Cupo Total"]
        def listaCampos = ["cedula", "nombre", "codigo", "orden", "ciudad", "telefono1", "estadoGestion", "tipoCliente", "tipoTarjeta", "cupoNormal", "cupoTotal"]
        def funciones = [null, null, null, ["closure": [closure, "&"]], null, null, null, null, null, ["closure": [numeros, "?"]], ["closure": [numeros, "?"]]]
        def url = g.createLink(action: "buscarLote", controller: "lote")
        def show = "gestion"
        def link = "cedula"
        def numRegistros = 20


//        def extras = ""
        def lista = buscadorService.buscar(Lote, "Lote", "excluyente", params, true, extras) /* Dominio, nombre del dominio , excluyente o incluyente ,params tal cual llegan de la interfaz del buscador, ignore case */
        lista.pop()
        if (!params.reporte) {
            render(view: '../lstaTbla', model: [listaTitulos: listaTitulos, listaCampos: listaCampos, lista: lista, funciones: funciones, url: url, controller: "reproceso", show: show, link: link, numRegistros: numRegistros])
        } else {
            println "entro reporte"
            /*De esto solo cambiar el dominio, el parametro tabla, el paramtero titulo y el tamaño de las columnas (anchos)*/
            session.dominio = Lote
            session.funciones = funciones
            def anchos = [7, 18, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7] /*el ancho de las columnas en porcentajes... solo enteros*/
            redirect(controller: "reportes", action: "reporteBuscador", params: [listaCampos: listaCampos, listaTitulos: listaTitulos, tabla: "Lote", orden: params.orden, ordenado: params.ordenado, criterios: params.criterios, operadores: params.operadores, campos: params.campos, titulo: "Lotes", anchos: anchos, extras: extras, landscape: true])
        }
    }

    def gestion(){

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

    def saveGestion(){
        println(params)
        println "save gestion?"
        def msg = "ok"
        def loteId = params.lote
        def estadoGestionId = params.estadoGestion
        def estadoGestion = EstadoGestion.get(estadoGestionId)
        def lote = Lote.get(loteId)
        def noDesea = NoDesea.get(params.noDesea)
        if (estadoGestion){
            if (estadoGestion.lista=="S")
                lote.estado="N"
        }
        lote.estadoGestion = estadoGestion
        lote.noDesea = noDesea
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
        redirect ( action: "busqueda")
    }


}
