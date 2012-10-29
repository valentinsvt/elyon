package elyon

class MenuTagLib {
    static namespace = "mn"

    def menu = { attrs ->

        def strItems = ""
        strItems += '<li class="active"><a href="#">Home</a></li>'
        strItems += '<li><a href="#">Link</a></li>'
        strItems += '<li><a href="#">Link</a></li>'

        strItems += '<li class="dropdown">'
        strItems += '<a href="#" class="dropdown-toggle" data-toggle="dropdown">Account<b class="caret"></b></a>'
        strItems += '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">'
        strItems += '<li><a href="#">Link</a></li>'
        strItems += '<li><a href="#">Link</a></li>'
        strItems += '</ul>'
        strItems += '</li>'

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
        html += '<li><a href="' + g.createLink(controller: 'loginLuz', action: 'logout') + '"><i class="icon-off icon-white"></i> Salir</a></li>'
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
