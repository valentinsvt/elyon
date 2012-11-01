package elyon

class InicioController extends elyon.seguridad.Shield {

    def index() {
    }

    def cargarIngresos() {
        def file = new File("/home/svt/Downloads/ingresos.csv")

        file.eachLine {
            def parts = it.split("&")
            def org = new OrigenIngresos()
            org.codigo = parts[0].trim()
            org.descripcion = parts[1].trim().toUpperCase()

            if (!org.save(flush: true)) {
                println "error " + org.errors
            }
        }
    }

    def cargarParroquias() {
        println "cargar parroquias"
        def file = new File("/home/svt/Downloads/parrCiudad.csv")

        file.eachLine {
            def parts = it.split("&")
            if (parts[0].size() < 8)
                parts[0] = "0" + parts[0]
            def ciudad = Ciudad.findByCodigo(parts[0].trim())
            // println "ciudad "+ciudad+" buscando "+parts[0].trim()+"  "+parts[0]
            def parr = new Parroquia()
            parr.ciudad = ciudad
            parr.codigo = parts[1].trim()
            parr.descripcion = parts[2].trim()
            if (!parr.save(flush: true))
                println "no se grabo " + parr.errors + "  " + it
        }
    }

    def cargarActividades() {
        println "cargar actividades"
        def file = new File("/home/svt/Downloads/actividades.csv")

        file.eachLine {
            def parts = it.split("&")

            def actividad = new ActividadEconomica()
            actividad.codigo = parts[0].trim()
            actividad.descripcion = parts[1].trim()
            if (!actividad.save(flush: true))
                println "no se grabo " + actividad.errors + "  " + it
        }

    }

    def cargarSucursales() {
        println "cargar suc"
        def file = new File("/home/svt/Downloads/sucursales.csv")

        file.eachLine {
            def parts = it.split("&")
            if (parts[0].size() < 8)
                parts[0] = "0" + parts[0]
            def ciudad = Ciudad.findByCodigo(parts[0].trim())
            def suc = new Sucursal()
            suc.codigo = parts[1].trim()
            suc.ciudad = ciudad
            suc.descripcion = parts[2].trim().toUpperCase()
            if (!suc.save(flush: true))
                println "no se grabo " + suc.errors + "  " + it
        }

    }

    def cargarOficias() {
        def file = new File("/home/svt/Downloads/oficinas.csv")

        file.eachLine {
            def parts = it.split("&")
            if (parts[0].trim() != parts[1].trim()) {
                def suc = Sucursal.findByCodigo(parts[0].trim())
                def ofi = Oficina.findByCodigo(parts[1].trim())
                if (!ofi) {
                    println "no ofi " + it
                    ofi = new Oficina()
                    ofi.sucursal = suc
                    ofi.descripcion = parts[2].trim().toUpperCase()
                    ofi.codigo = parts[1].trim()
                    if (!ofi.save(flush: true))
                        println "no se grabo " + ofi.errors + "  " + it
                }
            }
        }
    }
}
