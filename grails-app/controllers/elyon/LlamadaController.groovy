package elyon

class LlamadaController extends elyon.seguridad.Shield {

    def index() {
        redirect(action: 'registro')
    }

    def registro() {
        def data, lote = Lote.get(params.id)
        //println lote
        println params
        if (lote) {
            data = Data.findAllByLote(lote)

            if (data.size() == 0) {
                println "No se encontró"
                data = new Data()
            } else if (data.size() == 1) {
                data = data[0]
            } else {
                println "Mas de 1"
                data = new Data()
            }
        } else {
            data = null
            lote = null
        }


        def tipoTarjeta = lote.tipoTarjeta.toLowerCase();
        if (tipoTarjeta == 'nacional')     {


            tipoTarjeta  = ' nacional'

        }

        def listaTipoTarjeta = Bins.list([sort: 'descripcion']).findAll{it.descripcion.toLowerCase().contains(tipoTarjeta)}

        println(listaTipoTarjeta)
        println(listaTipoTarjeta[0]?.class)


        return [data: data, lote: lote, listaTipoTarjeta: listaTipoTarjeta]
    }


    def cambiarParroquia(){
//        println "cambiar par "+params
        def ciudad = Ciudad.get(params.ciudad)
        def pars = []
        if(ciudad)
            pars=Parroquia.findAllByCiudad(ciudad)
        [pars:pars]
    }

    def cambiarSucursal(){
        def ciudad = Ciudad.get(params.ciudad)
        def sucs = []
        if(ciudad)
            sucs=Sucursal.findAllByCiudad(ciudad)
        [sucs:sucs]
    }

    def cambiarOficina(){
        def suc = Sucursal.get(params.sucursal)
        def ofis = []
        if(suc)
            ofis=Oficina.findAllBySucursal(suc)
        [ofis:ofis]
    }


    def saveRegistro() {

        println "saveRegistro" + params
        params.fechaRegistro = new Date()
        def data
        if (params.id) {
            data = Data.get(params.id)
        } else {
            data = new Data()
        }
        data.properties = params
//        data.fechaRegistro = new Date();
        if (!data.save(flush: true)) {
            println "ERRORES:"
            println data.errors
        } else {
            println "OK"
        }

        redirect(controller: "lote", action: "busqueda")
    }

}
