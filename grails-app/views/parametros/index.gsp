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
                <div class="row">
                    <div class="span3">
                        <ul class="nav nav-pills nav-stacked">
                            <li class="hoverable" data-info="">
                                <g:link controller="ciudad">Ciudad</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="parroquia">Parroquia</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="sucursal">Sucursal</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="oficina">Oficina</g:link>
                            </li>
                        </ul>
                    </div>

                    <div class="span8 desc">
                    </div>
                </div>

            </div>

            <div id="tabGenerales">
                <div class="row">
                    <div class="span3">
                        <ul class="nav nav-pills nav-stacked">
                            <li class="hoverable" data-info="">
                                <g:link controller="actividadEconomica">Actividad económica</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="afinidad">Afinidad</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="bins">Bins</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="estadoCivil">Estado civil</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="nacionalidad">Nacionalidad</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="nivelEstudios">Nivel de estudios</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="origenIngresos">Origen de ingresos</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="parentesco">Parentesco</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="profesion">Profesión</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="rangoIngresos">Rango de ingresos</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="relacionDependenciaLaboral">Relación de dependencia labral</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="ruta">Ruta</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="sexo">Sexo</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="tipoDeIdentificacion">Tipo de identificación</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="tipoTarjeta">Tipo de tarjeta</g:link>
                            </li>
                            <li class="hoverable" data-info="">
                                <g:link controller="tipoVivienda">Tipo de vivienda</g:link>
                            </li>
                        </ul>
                    </div>

                    <div class="span8 desc">
                    </div>
                </div>
            </div>
        </div>


        <script>
            $(function () {
                $("#tabs").tabs();

                $(".hoverable").hover(function () {
                    var header = "<h2>" + $.trim($(this).text()) + "</h2>";
                    var texto = $(this).data("info") ? $(this).data("info") : "";

                    $(".desc").addClass("alert alert-info").html(header + texto);
                }, function () {
                    $(".desc").removeClass("alert alert-info").html("");
                });

            });
        </script>
    </body>
</html>