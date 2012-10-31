<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 10/30/12
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="elyon.Parentesco" contentType="text/html;charset=UTF-8" %>
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
        <div class="span12 btn-group" role="navigation">
            <a href="#" class="btn btn-save">
                <i class="icon-save"></i>
                Guardar
            </a>
            <a href="#" class="btn btn-del ${data?.id ? '' : 'disabled'}">
                <i class="icon-trash"></i>
                Eliminar
            </a>
            <a href="#" class="btn btn-exit">
                <i class="icon-signout"></i>
                Salir
            </a>
        </div>

        <fieldset class="form-horizontal">
            <legend>Datos del cliente</legend>

            <div class="row">
                <div class="span1">Identificación</div>

                <div class="span3">
                    <span class="span3 uneditable-input">${lote.cedula}</span>
                </div>

                <div class="span1">Cliente</div>

                <div class="span7">
                    <span class="span7 uneditable-input">${lote.nombre}</span>
                </div>
            </div>

            <div class="row">
                <div class="span1">Tipo tarjeta</div>

                <div class="span3">
                    <span class="span3 uneditable-input">${lote.tipoTarjeta}</span>
                </div>

                <div class="span1">Cupo Total</div>

                <div class="span3">
                    <span class="span3 uneditable-input">
                        <g:formatNumber number="${lote.cupoTotal}" maxFractionDigits="2" minFractionDigits="2"/>
                    </span>
                </div>

                <div class="span1">Cupo Normal</div>

                <div class="span3">
                    <span class="span3 uneditable-input">
                        <g:formatNumber number="${lote.cupoNormal}" maxFractionDigits="2" minFractionDigits="2"/>
                    </span>
                </div>
            </div>

            <div class="row">
                <div class="span1">Dirección</div>

                <div class="span11">
                    <span class="span11 uneditable-input">${lote.direccion1}</span>
                </div>
            </div>

            <div class="row">
                <div class="span1">Fonos 1-2</div>

                <div class="span3">
                    <span class="span3 uneditable-input">${lote.telefono1 ? lote.telefono1 + ' ' : ''}${lote.telefono2}</span>
                </div>

                <div class="span1">Fonos 3-4</div>

                <div class="span3">
                    <span class="span3 uneditable-input">${lote.telefono3 ? lote.telefono3 + ' ' : ''}${lote.telefono4}</span>
                </div>

                <div class="span1">Fonos 5-6</div>

                <div class="span3">
                    <span class="span3 uneditable-input">${lote.telefono5 ? lote.telefono5 + ' ' : ''}${lote.telefono6}</span>
                </div>
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

                        <div class="span2"><g:select from="${elyon.TipoDeIdentificacion.list([sort: 'descripcion'])}" name="tipoDeIdentificacion.id" class="span2" value="${data?.tipoDeIdentificacionId}"/></div>

                        <div class="span1">Nombre 1</div>

                        <div class="span2"><g:textField name="nombre1" class="span2" value="${data?.nombre1}"/></div>

                        <div class="span1">Nombre 2</div>

                        <div class="span2"><g:textField name="nombre2" class="span2" value="${data?.nombre2}"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Apellido 1</div>

                        <div class="span2"><g:textField name="apellido1" class="span2" value="${data?.apellido1}"/></div>

                        <div class="span1">Apellido 2</div>

                        <div class="span2"><g:textField name="apellido2" class="span2" value="${data?.apellido2}"/></div>

                        <div class="span1">Fecha Nac</div>

                        <div class="span2"><elm:datepicker name="fechaNacimiento" class="span2" value="${data?.fechaNacimiento?.format('dd-MM-yyyy')}"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Estado Civil</div>

                        <div class="span2"><g:select from="${elyon.EstadoCivil.list([sort: 'descripcion'])}" name="estadoCivil.id" class="span2" value="${data?.estadoCivilId}"/></div>

                        <div class="span1">Sexo</div>

                        <div class="span2"><g:select from="${elyon.Sexo.list([sort: 'descripcion'])}" name="sexo.id" class="span2" value="${data?.sexoId}"/></div>

                        <div class="span1">Lugar entrega</div>

                        <div class="span2"><g:select from="${elyon.Ruta.list([sort: 'descripcion'])}" name="rutaEntrega.id" class="span2" value="${data.rutaEntregaId}"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Dirección 1</div>

                        <div class="span5"><g:textField name="direccionResidencia" class="span5" value="${data.direccionResidencia}"/></div>

                        <div class="span1">Lugar ent est cta</div>

                        <div class="span2"><g:select from="${elyon.Ruta.list([sort: 'descripcion'])}" name="rutaEstadosCuenta.id" class="span2" value="${data?.rutaEstadosCuentaId}"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Dirección 2</div>

                        <div class="span5"><g:textField name="direccionTrabajo" class="span5" value="${data.direccionTrabajo}"/></div>

                        <div class="span1">Profesión</div>

                        <div class="span2"><g:select from="${elyon.Profesion.list([sort: 'descripcion'])}" name="profesion.id" class="span2" value="${data?.profesionId}"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Nombre tarjeta</div>

                        <div class="span2"><g:textField name="nombre" class="span2" value="${data?.nombre}"/></div>

                        <div class="span1">Nacionalidad</div>

                        <div class="span2"><g:select from="${elyon.Nacionalidad.list([sort: 'descripcion'])}" name="nacionalidad.id" class="span2" value="${data?.nacionalidadId}"/></div>

                        <div class="span1">Nivel educa</div>

                        <div class="span2"><g:select from="${elyon.NivelEstudios.list([sort: 'descripcion'])}" name="nivelEstudios.id" class="span2" value="${data?.nivelEstudiosId}"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Carga fam</div>

                        <div class="span2"><g:textField name="cargaFamiliar" class="span2" value="${data.cargaFamiliar}"/></div>

                        <div class="span1">Teléfono</div>

                        <div class="span2"><g:textField name="telefono" class="span2" value="${data.telefono}"/></div>

                        <div class="span1">Tipo tarjeta</div>

                        <div class="span2"><g:select from="${elyon.TipoTarjeta.list([sort: 'descripcion'])}" name="tipoTarjeta.id" class="span2" value="${data?.tipoTarjetaId}"/></div>
                    </div>
                </div>

                <div id="ubicacionCliente">
                    <div class="row">
                        <div class="span1 offset1">Ciudad</div>

                        <div class="span2"><g:select from="${elyon.Ciudad.list([sort: 'descripcion'])}" name="ciudad.id" class="span2" value="${data?.parroquia?.ciudadId}"/></div>

                        <div class="span1">Sucursal</div>

                        <div class="span2"><g:select from="${elyon.Sucursal.list([sort: 'descripcion'])}" name="sucursal.id" class="span2" value="${data?.oficina?.sucursalId}"/></div>

                        <div class="span1">Oficina</div>

                        <div class="span2"><g:select from="${elyon.Oficina.list([sort: 'descripcion'])}" name="oficina.id" class="span2" value="${data?.oficinaId}"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Parroquia</div>

                        <div class="span2"><g:select from="${elyon.Parroquia.list([sort: 'descripcion'])}" name="parroquia.id" class="span2" value="${data?.parroquiaId}"/></div>

                        <div class="span1">Telf trabajo</div>

                        <div class="span2"><g:textField name="telefonoTrabajo" class="span2" value="${data?.telefonoTrabajo}"/></div>

                        <div class="span1">Email</div>

                        <div class="span2"><g:textField name="email" class="span2" value="${data?.email}"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Empresa</div>

                        <div class="span2"><g:textField name="empresa" class="span2" value="${data?.empresa}"/></div>

                        <div class="span1">Actividad</div>

                        <div class="span2"><g:select from="${elyon.ActividadEconomica.list([sort: 'descripcion'])}" name="actividadEconomica.id" class="span2" value="${data?.actividadEconomicaId}"/></div>

                        <div class="span1">Ingr mensuales</div>

                        <div class="span2"><g:select from="${elyon.RangoIngresos.list([sort: 'descripcion'])}" name="rangoIngresos.id" class="span2" value="${data?.rangoIngresosId}"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">%{--Tipo tarj--}%</div>

                        <div class="span2">%{--<g:select from="${elyon.TipoTarjeta.list([sort: 'descripcion'])}" name="tipoTarjeta.id" class="span2"/>--}%</div>

                        <div class="span1"></div>

                        <div class="span2"></div>

                        <div class="span1">Celular</div>

                        <div class="span2"><g:textField name="celular" class="span2" value="${data?.celular}"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Tipo vivienda</div>

                        <div class="span2"><g:select from="${elyon.TipoVivienda.list([sort: 'descripcion'])}" name="tipoVivienda.id" class="span2" value="${data?.tipoViviendaId}"/></div>

                        <div class="span1">Valor vivienda</div>

                        <div class="span2"><g:textField name="valorVivienda" class="span2" value="${data?.valorVivienda}"/></div>

                        <div class="span1">Fecha ini res</div>

                        <div class="span2"><elm:datepicker name="fechaIniRes" class="span2" value="${data?.fechaInicioResidencia?.format('dd-MM-yyyy')}"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Relación dependencia</div>

                        <div class="span2"><g:select from="${elyon.RelacionDependenciaLaboral.list([sort: 'descripcion'])}" name="relacionDependenciaLaboral.id" class="span2" value="${data?.relacionDependenciaLaboralId}"/></div>

                        <div class="span1">Origen ingresos</div>

                        <div class="span2"><g:select from="${elyon.OrigenIngresos.list([sort: 'descripcion'])}" name="origenIngresos.id" class="span2" value="${data?.origenIngresosId}"/></div>

                        <div class="span1">Patrimonio</div>

                        <div class="span2"><g:textField name="patrimonio" class="span2" value="${data?.patrimonio}"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Fecha ini trab act</div>

                        <div class="span2"><elm:datepicker name="fechaIniTrabAct" class="span2" value="${data?.fechaInicioTrabajoActual?.format('dd-MM-yyyy')}"/></div>

                        <div class="span1">Fecha ini ult trab</div>

                        <div class="span2"><elm:datepicker name="fechaIniUltTrab" class="span2" value="${data?.fechaInicioTrabajoAnterior?.format('dd-MM-yyyy')}"/></div>

                        <div class="span1">Fecha fin ult trab</div>

                        <div class="span2"><elm:datepicker name="fechaFinUltTrab" class="span2" value="${data?.fechaFinTrabajoAnterior?.format('dd-MM-yyyy')}"/></div>
                    </div>
                </div>

                <div id="referencias">

                    <div class="row">
                        <div class="span1 offset1">Tipo ident</div>

                        <div class="span2"><g:select from="${elyon.TipoDeIdentificacion.list([sort: 'descripcion'])}" name="tipoDeIdentificacionReferenciaPersonal" class="span2" value="${data?.tipoDeIdentificacionReferenciaPersonalId}"/></div>

                        <div class="span1">Cédula</div>

                        <div class="span2"><g:textField name="idReferenciaPersonal" class="span2" value="${data?.idReferenciaPersonal}"/></div>

                        <div class="span1">Nombre 1</div>

                        <div class="span2"><g:textField name="nombre1Ref" class="span2" value="${data?.nombre1ReferenciaPersonal}"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Nombre 2</div>

                        <div class="span2"><g:textField name="nombre2Ref" class="span2" value="${data?.nombre2ReferenciaPersonal}"/></div>

                        <div class="span1">Apellido 1</div>

                        <div class="span2"><g:textField name="apellid1Ref" class="span2" value="${data?.apellido1ReferenciaPersonal}"/></div>

                        <div class="span1">Apellido 2</div>

                        <div class="span2"><g:textField name="apellido2Ref" class="span2" value="${data?.apellido2ReferenciaPersonal}"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Dir Par</div>

                        <div class="span2"><g:textField name="dirPar" class="span2" value="${data?.direccionReferenciaPersonal}"/></div>

                        <div class="span1">Fono par</div>

                        <div class="span2"><g:textField name="fonoPar" class="span2" value="${data?.telefonoReferenciaPersonal}"/></div>

                        <div class="span1">Parentesco</div>

                        <div class="span2"><g:select from="${Parentesco.list([sort: 'descripcion'])}" name="parentesco" class="span2" value="${data?.parentescoId}"/></div>
                    </div>

                    <div class="row">
                        <div class="span1 offset1">Tipo cliente</div>

                        <div class="span2"><g:textField name="tipoCliente" class="span2"/></div>
                    </div>

                </div>
            </div>
            fechaInicioTrabajoActual
            <div class="row">
                <div class="span1">Comentarios</div>

                <div class="span7"><g:textArea name="comentarios" cols="5" rows="5" class="span7" value="${data?.comentarios}"/></div>

                <div class="span1">Contacto alterno</div>

                <div class="span3"><g:textArea name="contactoAlterno" cols="5" rows="5" class="span3" value="${data?.contactoAlterno}"/></div>

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