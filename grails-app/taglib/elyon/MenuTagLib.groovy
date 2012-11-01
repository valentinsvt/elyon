package elyon

import elyon.seguridad.Usro

class MenuTagLib {
    static namespace = "mn"

    def renderItem(item, tipo) {
        def str = "", clase = ""
        if (session.cn == tipo) {
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
            str += "<a href='" + item.url + "'>" + item.label + "</a>"
        }
        str += "</li>"

        return str
    }

    def menu = { attrs ->

        def usu = Usro.get(session.usuario.id)


        def items = [:]

        items.inicio = [:]
        items.inicio.url = createLink(controller: "inicio", action: "index")
        items.inicio.label = "Inicio"

        switch (usu.tipo) {
            case "a":
                //admin
                //campana, orden de trabajo, parametros

                items.administracion = [:]
                items.administracion.label = "Administración"
                items.administracion.items = [:]
                items.administracion.items.usuarios = [:]
                items.administracion.items.usuarios.label = "Usuarios"
                items.administracion.items.usuarios.url = createLink(controller: "usro")
                items.administracion.items.parametros = [:]
                items.administracion.items.parametros.label = "Parámetros"
                items.administracion.items.parametros.url = createLink(controller: "parametros")
                items.administracion.items.reportes = [:]
                items.administracion.items.reportes.label = "Reportes"
                items.administracion.items.reportes.url = createLink(controller: "reportes")

                items.campana = [:]
                items.campana.url = createLink(controller: "campana", action: "index")
                items.campana.label = "Campaña"

                items.ordenDeTrabajo = [:]
                items.ordenDeTrabajo.url = createLink(controller: "ordenDeTrabajo", action: "index")
                items.ordenDeTrabajo.label = "Orden de trabajo"

                break;
            case "u":
                //usuario
                //llamada, gestion telefonica

                items.lote = [:]
                items.lote.url = createLink(controller: "lote", action: "busqueda")
                items.lote.label = "Agendamientos"

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
