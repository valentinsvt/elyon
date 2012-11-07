package elyon

class LlamadaController extends elyon.seguridad.Shield {

    def index() {
        redirect(action: 'registro')
    }

    def registro() {
        def data, lote = Lote.get(params.id)
//        println lote
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
        return [data: data, lote: lote]
    }

    def saveRegistro() {
        println params
        def data
        if (params.id) {
            data = Data.get(params.id)
        } else {
            data = new Data()
        }
        data.properties = params
        if (!data.save(flush: true)) {
            println "ERRORES:"
            println data.errors
        } else {
            println "OK"
        }

        render params
    }

}
