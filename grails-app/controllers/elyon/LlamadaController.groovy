package elyon

class LlamadaController {

    def index() {
        redirect(action: 'registro')
    }

    def registro() {
        /*Todo cambiar esto a la nueva estructura de la base de datos*/
//        params.id = 1
//
//        def data = new Data(), lote = new Lote()
//
//        def loteOrdenTrabajo = LoteOrdenTrabajo.get(params.id)
//        if (loteOrdenTrabajo) {
//            lote = loteOrdenTrabajo?.lote
//            data = Data.findAllByLoteOrdenTrabajo(loteOrdenTrabajo)
//
//            if (data.size() == 0) {
//                println "No se encontró"
//            } else if (data.size() == 1) {
//                data = data[0]
//            } else {
//                println "Mas de 1"
//            }
//        }
//
//        lote = Lote.get(1)
//        data = new Data()
//
//        return [data: data, lote: lote]
    }

}
