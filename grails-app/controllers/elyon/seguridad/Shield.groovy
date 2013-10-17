package elyon.seguridad

class Shield {
    def beforeInterceptor = [action: this.&auth, except: 'login']
    /**
     * Verifica si se ha iniciado una sesión
     * Verifica si el usuario actual tiene los permisos para ejecutar una acción
     */
    def auth() {

//        println "an " + actionName + " cn " + controllerName + "  "

//        println session
        session.an = actionName
        session.cn = controllerName
        session.pr = params
//        return true
        /** **************************************************************************/
        if (!session.usuario) {//            println "1"
            redirect(controller: 'login', action: 'login')
            session.finalize()
            return false
        } else {//            println "2"
            //verificacion de permisos
            if (!session.unidad) {
                if (this.isAllowed()) {
                    return true
                }
                response.sendError(403)
                return false
            }
            /*************************************************************************** */
        }
    }

    boolean isAllowed() {

        def usr = Usro.get(session.usuario.id)

//        println "AQUI " + usr.tipo
        if (usr.tipo == 'a') {
            return true
        } else {
            def allowedUser = [:]
            allowedUser.lote = ["busqueda", "buscarLote", "busquedaRegistro", "buscarLoteRegistro"]
            allowedUser.gestionTelefonica = ["gestion", "saveGestion","siguiente","ingresoVenta"]
            allowedUser.llamada = ["registro", "saveRegistro","cambiarParroquia","cambiarSucursal","cambiarOficina"]
            allowedUser.inicio = ["index"]

            if (allowedUser[controllerName]) {
//                println allowedUser
//                println controllerName
//                println actionName
//                println allowedUser[controllerName]
                if (allowedUser[controllerName].contains(actionName)) {
                    return true
                } else {
                    return false
                }
            } else {
//                println "NOT ALLOWED"
                return false
            }
        }
    }
}
 
