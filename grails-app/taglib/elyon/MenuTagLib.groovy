package elyon

import elyon.seguridad.Usro

class MenuTagLib {
    static namespace = "mn"

    def renderItem(item, tipo) {
        def str = "", clase = ""
        if (session.cn == item.controller && session.an == item.action) {
            clase = "active"
        }
        if (item.items) {
            clase += " dropdown"
        }
        str += "<li class='" + clase + "'>"
        if (item.items) {
            str += "<a href='" + item.url + "' class='dropdown-toggle' data-toggle='dropdown'>" + item.label + "<b class=\"caret\"></b></a>"
            str += '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">'
            item.items.each { t, i ->
                str += renderItem(i, t)
            }
            str += "</ul>"
        } else {
            str += "<a href='" + createLink(controller: item.controller, action: item.action) + "'>" + item.label + "</a>"
        }
        str += "</li>"

        return str
    }

    def menu = { attrs ->

        def usu = Usro.get(session.usuario.id)


        def items = [:]

//        items.inicio = [:]
//        items.inicio.url = createLink(controller: "inicio", action: "index")
//        items.inicio.label = "Inicio"

        switch (usu.tipo) {
            case "a":
                //admin
                //campana, orden de trabajo, parametros

                items.administracion = [:]
                items.administracion.label = "Administración"
                items.administracion.items = [:]
                items.administracion.items.usuarios = [:]
                items.administracion.items.usuarios.label = "Usuarios"
                items.administracion.items.usuarios.controller = "usro"
                items.administracion.items.usuarios.action = "index"
                items.administracion.items.parametros = [:]
                items.administracion.items.parametros.label = "Parámetros"
                items.administracion.items.parametros.controller = "parametros"
                items.administracion.items.parametros.action = "index"
                items.administracion.items.reportes = [:]
                items.administracion.items.reportes.label = "Ventas"
                items.administracion.items.reportes.controller = "reportes"
                items.administracion.items.reportes.action = "archivo"
                items.administracion.items.reportesXl = [:]
                items.administracion.items.reportesXl.label = "Ventas Excel"
                items.administracion.items.reportesXl.controller = "reportes"
                items.administracion.items.reportesXl.action = "aExcel"

                items.campana = [:]
                items.campana.controller = "campana"
                items.campana.action = "list"
                items.campana.label = "Campaña"

                items.ordenDeTrabajo = [:]
                items.ordenDeTrabajo.controller = "ordenDeTrabajo"
                items.ordenDeTrabajo.action = "list"
                items.ordenDeTrabajo.label = "Orden de trabajo"

                break;
            case "u":
                //usuario
                //llamada, gestion telefonica

                items.agendamientos = [:]
                items.agendamientos.controller = "lote"
                items.agendamientos.action = "busqueda"
                items.agendamientos.label = "Agendamientos"

                items.registro = [:]
                items.registro.controller = "lote"
                items.registro.action = "busquedaRegistro"
                items.registro.label = "Registro datos llamada"

                break;
        }

//        items.test = [:]
//        items.test.url = createLink(controller: "inicio", action: "list")
//        items.test.label = "Test"
//        items.test.items = [:]
//        items.test.items.test = [:]
//        items.test.items.test.url = createLink(controller: "inicio", action: "list")
//        items.test.items.test.label = "Test test"


        def strItems = ""
        items.each { tipo, item ->
            strItems += renderItem(item, tipo)
        }

        def html = ""
        html += '<div class="navbar navbar-static-top navbar-inverse">'
        html += '<div class="navbar-inner">'
        html += '<div class="container">'
        html += '<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">'
        html += '<span class="icon-bar"></span>'
        html += '<span class="icon-bar"></span>'
        html += '<span class="icon-bar"></span>'
        html += '</a>'
        html += '<a class="brand" href="#">'
        html += attrs.title
        html += '</a>'

        html += '<div class="nav-collapse">'
        html += '<ul class="nav">'
        html += strItems
        html += ' <li class="divider-vertical"></li>'
        html += '<li><a href="' + g.createLink(controller: 'login', action: 'logout') + '"><i class="icon-off icon-white"></i> Salir</a></li>'
//        html += '<li><a href="#contact">Contact</a></li>'
        html += '</ul>'
        html += '<p class="navbar-text pull-right" id="countdown"></p>'
        html += '</div><!--/.nav-collapse -->'
        html += '</div>'
        html += '</div>'
        html += '</div>'

        out << html
    } //menu
}
