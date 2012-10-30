<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 10/30/12
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Registro de datos de la llamada</title>
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
        <fieldset class="form-horizontal">
            <legend>Datos del cliente</legend>

            <div class="row">
                <div class="span1">Identificación</div>

                <div class="span3"><g:textField name="ci" class="span3"/></div>

                <div class="span1">Cliente</div>

                <div class="span7"><g:textField name="cliente" class="span7"/></div>
            </div>

            <div class="row">
                <div class="span1">Tipo tarjeta</div>

                <div class="span3"><g:textField name="tipoT" class="span3"/></div>

                <div class="span1">Cupo Total</div>

                <div class="span3"><g:textField name="cupoTotal" class="span3"/></div>

                <div class="span1">Cupo Normal</div>

                <div class="span3"><g:textField name="cupoNormal" class="span3"/></div>
            </div>

            <div class="row">
                <div class="span1">Dirección</div>

                <div class="span11"><g:textField name="direccion" class="span11"/></div>
            </div>

            <div class="row">
                <div class="span1">Fonos 1-2</div>

                <div class="span3"><g:textField name="fonos12" class="span3"/></div>

                <div class="span1">Fonos 3-4</div>

                <div class="span3"><g:textField name="fonos34" class="span3"/></div>

                <div class="span1">Fonos 5-6</div>

                <div class="span3"><g:textField name="fonos56" class="span3"/></div>
            </div>

            <div id="tabs">
                <ul>
                    <li><a href="#datosCliente">Datos cliente</a></li>
                    <li><a href="#ubicacionCliente">Ubicación cliente</a></li>
                    <li><a href="#referencias">Referencias</a></li>
                </ul>

                <div id="datosCliente">
                    <div class="row">
                        <div class="span1 offset1">Tipo Ident</div>

                        <div class="span2"><g:select from="${elyon.TipoDeIdentificacion.list([sort: 'descripcion'])}" name="tipoId" class="span2"/></div>

                        <div class="span1">Nombre 1</div>

                        <div class="span2"><g:textField name="nombre1" class="span2"/></div>

                        <div class="span1">Nombre 2</div>

                        <div class="span2"><g:textField name="nombre2" class="span2"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Apellido 1</div>

                        <div class="span2"><g:textField name="apellido1" class="span2"/></div>

                        <div class="span1">Apellido 2</div>

                        <div class="span2"><g:textField name="apellido2" class="span2"/></div>

                        <div class="span1">Fecha Nac</div>

                        <div class="span2"><elm:datepicker name="fechaNac" class="span2"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Estado Civil</div>

                        <div class="span2"><g:select from="${elyon.EstadoCivil.list([sort: 'descripcion'])}" name="estadoCivil" class="span2"/></div>

                        <div class="span1">Sexo</div>

                        <div class="span2"><g:select from="${elyon.Sexo.list([sort: 'descripcion'])}" name="sexo" class="span2"/></div>

                        <div class="span1">Lugar entrega</div>

                        <div class="span2"><g:textField name="lugarEntrega" class="span2"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Dirección 1</div>

                        <div class="span5"><g:textField name="direccion1" class="span5"/></div>

                        <div class="span1">Lugar ent est cta</div>

                        <div class="span2"><g:textField name="lugarEstadoCta" class="span2"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Dirección 2</div>

                        <div class="span5"><g:textField name="direccion2" class="span5"/></div>

                        <div class="span1">Profesión</div>

                        <div class="span2"><g:select from="${elyon.Profesion.list([sort: 'descripcion'])}" name="profesion" class="span2"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Nombre tarjeta</div>

                        <div class="span2"><g:textField name="nombreTarjeta" class="span2"/></div>

                        <div class="span1">Nacionalidad</div>

                        <div class="span2"><g:select from="${elyon.Nacionalidad.list([sort: 'descripcion'])}" name="nacionalidad" class="span2"/></div>

                        <div class="span1">Nivel educa</div>

                        <div class="span2"><g:select from="${elyon.NivelEstudios.list([sort: 'descripcion'])}" name="nivelEducacion" class="span2"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Carga fam</div>

                        <div class="span2"><g:textField name="cargaFam" class="span2"/></div>

                        <div class="span1">Teléfono</div>

                        <div class="span2"><g:textField name="telefono" class="span2"/></div>

                        <div class="span1">Tipo tarjeta</div>

                        <div class="span2"><g:select from="${elyon.TipoTarjeta.list([sort: 'descripcion'])}" name="tipoTarjeta" class="span2"/></div>
                    </div>
                </div>

                <div id="ubicacionCliente">
                    <div class="row">
                        <div class="span1 offset1">Ciudad</div>

                        <div class="span2"><g:select from="${elyon.Ciudad.list([sort: 'descripcion'])}" name="ciudad" class="span2"/></div>

                        <div class="span1">Sucursal</div>

                        <div class="span2"><g:select from="${elyon.Sucursal.list([sort: 'descripcion'])}" name="sucursal" class="span2"/></div>

                        <div class="span1">Oficina</div>

                        <div class="span2"><g:select from="${elyon.Oficina.list([sort: 'descripcion'])}" name="oficina" class="span2"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Parroquia</div>

                        <div class="span2"><g:select from="${elyon.Parroquia.list([sort: 'descripcion'])}" name="parroquia" class="span2"/></div>

                        <div class="span1">Telf trabajo</div>

                        <div class="span2"><g:textField name="telefonoTrabajo" class="span2"/></div>

                        <div class="span1">Email</div>

                        <div class="span2"><g:textField name="email" class="span2"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Empresa</div>

                        <div class="span2"><g:textField name="empresa" class="span2"/></div>

                        <div class="span1">Actividad</div>

                        <div class="span2"><g:select from="${elyon.ActividadEconomica.list([sort: 'descripcion'])}" name="actividad" class="span2"/></div>

                        <div class="span1">Ingr mensuales</div>

                        <div class="span2"><g:textField name="ingresosMensuales" class="span2"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Tipo tarj</div>

                        <div class="span2"><g:select from="${elyon.TipoTarjeta.list([sort: 'descripcion'])}" name="tipoTarj" class="span2"/></div>

                        <div class="span1"></div>

                        <div class="span2"></div>

                        <div class="span1">Celular</div>

                        <div class="span2"><g:textField name="celular" class="span2"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Tipo vivienda</div>

                        <div class="span2"><g:select from="${elyon.TipoTarjeta.list([sort: 'descripcion'])}" name="tipoTarj" class="span2"/></div>

                        <div class="span1">Valor vivienda</div>

                        <div class="span2"><g:textField name="celular" class="span2"/></div>

                        <div class="span1">Fecha ini res</div>

                        <div class="span2"><elm:datepicker name="fechaIniRes" class="span2"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Relación dependencia</div>

                        <div class="span2"><g:select from="${elyon.RelacionDependenciaLaboral.list([sort: 'descripcion'])}" name="relacionDependencia" class="span2"/></div>

                        <div class="span1">Origen ingresos</div>

                        <div class="span2"><g:select from="${elyon.OrigenIngresos.list([sort: 'descripcion'])}" name="origenIngresos" class="span2"/></div>

                        <div class="span1">Patrimonio</div>

                        <div class="span2"><g:textField name="patrimonio" class="span2"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Fecha ini trab act</div>

                        <div class="span2"><elm:datepicker name="fechaIniTrabAct" class="span2"/></div>

                        <div class="span1">Fecha ini ult trab</div>

                        <div class="span2"><elm:datepicker name="fechaIniUltTrab" class="span2"/></div>

                        <div class="span1">Fecha fin ult trab</div>

                        <div class="span2"><elm:datepicker name="fechaFinUltTrab" class="span2"/></div>
                    </div>
                </div>

                <div id="referencias">

                    <div class="row">
                        <div class="span1 offset1">Tipo ident</div>

                        <div class="span2"><g:select from="${elyon.TipoDeIdentificacion.list([sort: 'descripcion'])}" name="tipoIdRef" class="span2"/></div>

                        <div class="span1">Cédula</div>

                        <div class="span2"><g:textField name="cedulaRef" class="span2"/></div>

                        <div class="span1">Nombre 1</div>

                        <div class="span2"><g:textField name="nombre1Ref" class="span2"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Nombre 2</div>

                        <div class="span2"><g:textField name="nombre2Ref" class="span2"/></div>

                        <div class="span1">Apellido 1</div>

                        <div class="span2"><g:textField name="apellid1Ref" class="span2"/></div>

                        <div class="span1">Apellido 2</div>

                        <div class="span2"><g:textField name="apellido2Ref" class="span2"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Dir Par</div>

                        <div class="span2"><g:textField name="dirPar" class="span2"/></div>

                        <div class="span1">Fono par</div>

                        <div class="span2"><g:textField name="fonoPar" class="span2"/></div>

                        <div class="span1">Parentesco</div>

                        <div class="span2"><g:textField name="Parentesco" class="span2"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Tipo cliente</div>

                        <div class="span2"><g:textField name="tipoCliente" class="span2"/></div>
                    </div>

                </div>
            </div>

            <div class="row">
                <div class="span1">Comentarios</div>

                <div class="span7"><g:textArea name="comentarios" cols="5" rows="5" class="span7"/></div>

                <div class="span1">Contacto alterno</div>

                <div class="span3"><g:textArea name="contactoAlterno" cols="5" rows="5" class="span3"/></div>

            </div>

        </fieldset>

        <script>
            $(function () {
                $("#tabs").tabs({
                    heightStyle : "auto"
                });
            });
        </script>

    </body>
</html>