<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 11/1/12
  Time: 12:11 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Parámetros</title>
    </head>

    <body>

        <div id="tabs">
            <ul>
                <li><a href="#tabUbicacion">Ubicación</a></li>
                <li><a href="#tabGenerales">Generales</a></li>
            </ul>

            <div id="tabUbicacion">
                <ul class="unstyled">
                    <li>Ciudad</li>
                    <li>Parroquia</li>
                    <li>Sucursal</li>
                    <li>Oficina</li>
                </ul>
            </div>

            <div id="tabGenerales">

            </div>
        </div>


        <script>
            $(function () {
                $("#tabs").tabs();
            });
        </script>
    </body>
</html>