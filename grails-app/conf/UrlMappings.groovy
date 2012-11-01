class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }

//        "/"(view: "/index")
        "/"(controller: 'login', action: 'login')
        "500"(view: '/error')
        "403"(controller: "shield", action: "error403")
        "404"(controller: "shield", action: "error404")
    }
}
