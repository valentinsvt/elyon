<%--
  Created by IntelliJ IDEA.
  User: fabricio
  Date: 10/30/12
  Time: 1:13 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Consulta de Datos</title>
        <script src="${resource(dir: 'js/jquery/plugins', file: 'jquery.livequery.js')}"></script>
        <style type="text/css">
        .row {
            margin-bottom : 10px;
        }

        #tabs {
            margin-top    : 25px;
            margin-bottom : 15px
        }

        textarea {
            height : 60px;
        }
        </style>
    </head>

    <body>
        <bsc:buscador name="lote.id" value="" accion="buscarLoteRegistro" campos="${camposLote}" label="Lote" tipo="lista"/>
    </body>
</html>