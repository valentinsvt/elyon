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

        <script src="${resource(dir: 'js/jquery/plugins/jquery-validation-1.9.0', file: 'jquery.validate.min.js')}"></script>
        <script src="${resource(dir: 'js/jquery/plugins/jquery-validation-1.9.0', file: 'messages_es.js')}"></script>

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

        form {
            margin : 0;
        }
        </style>
    </head>

    <body>

        <g:if test="${lote}">
            <div class="span12 btn-group" role="navigation">
                <a href="#" class="btn btn-save">
                    <i class="icon-save"></i>
                    Guardar
                </a>
                <a href="#" class="btn btn-del disabled%{--${data?.id ? '' : 'disabled'}--}%">
                    <i class="icon-trash"></i>
                    Eliminar
                </a>
            </div>

            <fieldset class="form-horizontal">
                <legend>Datos del cliente</legend>

                <div class="row">
                    <div class="span1">Identificación</div>

                    <div class="span2">
                        <span class="span2 uneditable-input">${lote.cedula}</span>
                    </div>

                    <div class="span1">Cliente</div>

                    <div class="span4">
                        <span class="span4 uneditable-input">${lote.nombre}</span>
                    </div>
                    <div class="span1">Tipo tarjeta</div>

                    <div class="span3">
                        <span class="span3 uneditable-input">${lote.tipoTarjeta}</span>
                    </div>
                </div>

                <div class="row">

%{--
                    <div class="span1">Cupo Total</div>
                    <div class="span3">
                        <span class="span3 uneditable-input">
                            <g:formatNumber number="${lote.cupoTotal}" maxFractionDigits="2" minFractionDigits="2"/>
                        </span>
                    </div>
--}%

                    <div class="span1">Cupo</div>

                    <div class="span2">
                        <span class="span2 uneditable-input">
                            <g:formatNumber number="${lote.cupoNormal}" maxFractionDigits="2" minFractionDigits="2"/>
                        </span>
                    </div>
                    <div class="span1">Dirección</div>
                    <div class="span8">
                        <span class="span8 uneditable-input">${lote.direccion1}</span>
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

                    <g:form action="saveRegistro" name="frmRegistroLlamada">
                        <g:hiddenField name="lote.id" value="${lote.id}"/>
                        <g:hiddenField name="id" value="${data?.id}"/>

                        <div id="datosCliente">
                            <div class="row">
                                <div class="span1 offset1">Tipo Ident</div>
                                <div class="span2">
                                    <g:select from="${elyon.TipoDeIdentificacion.list([sort: 'descripcion'])}" name="tipoDeIdentificacion.id" class="span2" value="${data?.tipoDeIdentificacionId}"
                                              optionKey="id"/>
                                </div>

                                <div class="span1">Num Ident</div>

                                <div class="span2"><g:textField name="numeroIdentificacion" readonly="true" class="span2 digits" value="${data?.numeroIdentificacion ?: lote.cedula}" maxlength="10"/></div>

                                <div class="span1">Nombre 1</div>

                                <div class="span2"><g:textField name="nombre1" class="span2" value="${data?.nombre1?:lote.nombre.tokenize()[2]}" maxlength="20"/></div>
                            </div>

                            <div class="row">
                                <div class="span1 offset1">Nombre 2</div>

                                <div class="span2"><g:textField name="nombre2" class="span2" value="${data?.nombre2?:lote.nombre.tokenize()[3]}" maxlength="20"/></div>

                                <div class="span1">Apellido 1</div>

                                <div class="span2"><g:textField name="apellido1" class="span2" value="${data?.apellido1?:lote.nombre.tokenize()[0]}" maxlength="20"/></div>

                                <div class="span1">Apellido 2</div>

                                <div class="span2"><g:textField name="apellido2" class="span2" value="${data?.apellido2?:lote.nombre.tokenize()[1]}" maxlength="20"/></div>
                            </div>

                            <div class="row">

                                <div class="span1 offset1">Fecha Nac</div>

                                <div class="span2"><elm:datepicker name="fechaNacimiento" class="span2" value="${data?.fechaNacimiento?.format('dd-MM-yyyy')}"/></div>

                                <div class="span1">Estado Civil</div>

                                <div class="span2">
                                    <g:select from="${elyon.EstadoCivil.list([sort: 'descripcion'])}" name="estadoCivil.id" class="span2" value="${data?.estadoCivilId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>

                                <div class="span1">Sexo</div>

                                <div class="span2">
                                    <g:select from="${elyon.Sexo.list([sort: 'descripcion'])}" name="sexo.id" class="span2" value="${data?.sexoId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="span1 offset1">Nombre tarjeta</div>

                                <div class="span2"><g:textField name="nombre" class="span2" value="${data?.nombre?:lote.nombre.tokenize()[2]+" "+lote.nombre.tokenize()[0]}" maxlength="19"/></div>

                                <div class="span1">Nacionalidad</div>

                                <div class="span2">
                                    <g:select from="${elyon.Nacionalidad.list([sort: 'descripcion'])}" name="nacionalidad.id" class="span2" value="${data?.nacionalidadId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>

                                <div class="span1">Lugar entrega</div>

                                <div class="span2">
                                    <g:select from="${elyon.Ruta.list([sort: 'descripcion'])}" name="rutaEntrega.id" class="span2" value="${data?.rutaEntregaId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="span1 offset1">Dirección 1</div>

                                <div class="span5"><g:textField name="direccionResidencia" class="span5" value="${data?.direccionResidencia}" maxlength="150"/></div>

                                <div class="span1">Lugar ent est cta</div>

                                <div class="span2">
                                    <g:select from="${elyon.Ruta.list([sort: 'descripcion'])}" name="rutaEstadosCuenta.id" class="span2" value="${data?.rutaEstadosCuentaId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="span1 offset1">Dirección 2</div>

                                <div class="span5"><g:textField name="direccionTrabajo" class="span5" value="${data?.direccionTrabajo}" maxlength="150"/></div>

                                <div class="span1">Profesión</div>

                                <div class="span2">
                                    <g:select from="${elyon.Profesion.list([sort: 'descripcion'])}" name="profesion.id" class="span2" value="${data?.profesionId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>
                            </div>

                            <div class="row">

                                <div class="span1 offset1">Nivel educación</div>

                                <div class="span2">
                                    <g:select from="${elyon.NivelEstudios.list([sort: 'descripcion'])}" name="nivelEstudios.id" class="span2" value="${data?.nivelEstudiosId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>

                                <div class="span1">Carga fam</div>

                                <div class="span1"><g:textField name="cargaFamiliar" class="span1 number" value="${data?.cargaFamiliar?:0}"/></div>

                                <div class="span1">Teléfono</div>

                                <div class="span2"><g:textField name="telefono" class="span2" value="${data?.telefono}" maxlength="20"/></div>


                            </div>

                            <div class="row">

%{--
                                <div class="span1 offset1">Tipo tarjeta</div>
                                <div class="span2">
                                    <g:select from="${elyon.TipoTarjeta.list([sort: 'descripcion'])}" name="tipoTarjeta.id" class="span2" value="${data?.tipoTarjetaId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>
--}%
                                <div class="span1 offset1">Tipo de tarjeta(Bins)</div>

                                <div class="span2">
                                    <g:select from="${listaTipoTarjeta}" name="bins.id" class="span2" value="${data?.binsId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>


%{--
                                <div class="span1">Cupo</div>
                                <div class="span2"><g:textField name="cupo" class="span2 number" value="${data?.cupo}"/></div>
--}%
                            </div>
                        </div>

                        <div id="ubicacionCliente">
                            <div class="row">
                                <div class="span1 offset1">Ciudad</div>

                                <div class="span2">
                                    <g:select from="${elyon.Ciudad.list([sort: 'descripcion'])}" name="ciudad.id" class="span2" value="${data?.parroquia?.ciudadId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id" id="ciudad"/>
                                </div>

                                <div class="span1">Sucursal</div>

                                <div class="span2" id="div_suc">
                                    <g:select from="${elyon.Sucursal.list([sort: 'descripcion'])}" name="sucursal.id" class="span2" value="${data?.oficina?.sucursalId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>

                                <div class="span1">Oficina</div>

                                <div class="span2" id="div_ofi">
                                    <g:select from="${elyon.Oficina.list([sort: 'descripcion'])}" name="oficina.id" class="span2" value="${data?.oficinaId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="span1 offset1">Parroquia</div>

                                <div class="span2" id="div_par">
                                    <g:select from="${elyon.Parroquia.list([sort: 'descripcion'])}" name="parroquia.id" class="span2" value="${data?.parroquiaId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>

                                <div class="span1">Telf trabajo</div>

                                <div class="span2"><g:textField name="telefonoTrabajo" class="span2" value="${data?.telefonoTrabajo}"/></div>

                                <div class="span1">Email</div>

                                <div class="span2"><g:textField name="email" class="span2 email" value="${data?.email}" maxlength="50"/></div>
                            </div>

                            <div class="row">
                                <div class="span1 offset1">Empresa</div>

                                <div class="span2"><g:textField name="empresa" class="span2" value="${data?.empresa}" maxlength="50"/></div>

                                <div class="span1">Actividad</div>

                                <div class="span2">
                                    <g:select from="${elyon.ActividadEconomica.list([sort: 'descripcion'])}" name="actividadEconomica.id" class="span2" value="${data?.actividadEconomicaId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>

                                <div class="span1">Ingr mensuales</div>

                                <div class="span2">
                                    <g:select from="${elyon.RangoIngresos.list([sort: 'descripcion'])}" name="rangoIngresos.id" class="span2" value="${data?.rangoIngresosId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="span1 offset1">%{--Tipo tarj--}%</div>

                                <div class="span2">%{--<g:select from="${elyon.TipoTarjeta.list([sort: 'descripcion'])}" name="tipoTarjeta.id" class="span2" noSelection="['':'-Seleccione-']"/>--}%</div>

                                <div class="span1"></div>

                                <div class="span2"></div>

                                <div class="span1">Celular</div>

                                <div class="span2"><g:textField name="celular" class="span2" value="${data?.celular}" maxlength="20"/></div>
                            </div>

                            <div class="row">
                                <div class="span1 offset1">Tipo vivienda</div>

                                <div class="span2">
                                    <g:select from="${elyon.TipoVivienda.list([sort: 'descripcion'])}" name="tipoVivienda.id" id="tipoVivienda" class="span2" value="${data?.tipoViviendaId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>

                                <div class="span1">Valor vivienda</div>


                                    <div class="span2"><g:textField name="valorVivienda" class="span2 number" value="${data?.valorVivienda?:0}" readonly="true"/></div>

                                <div class="span1">Fecha ini res</div>

                                <div class="span2"><elm:datepicker name="fechaIniRes" class="span2" value="${data?.fechaInicioResidencia?.format('dd-MM-yyyy')}"/></div>
                            </div>

                            <div class="row">
                                <div class="span1 offset1">Relación dependencia</div>

                                <div class="span2">
                                    <g:select from="${elyon.RelacionDependenciaLaboral.list([sort: 'descripcion'])}" name="relacionDependenciaLaboral.id" class="span2" value="${data?.relacionDependenciaLaboralId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>

                                <div class="span1">Origen ingresos</div>

                                <div class="span2">
                                    <g:select from="${elyon.OrigenIngresos.list([sort: 'descripcion'])}" name="origenIngresos.id" class="span2" value="${data?.origenIngresosId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>

                                <div class="span1">Patrimonio</div>

                                <div class="span2"><g:textField name="patrimonio" class="span2 number" value="${data?.patrimonio}"/></div>
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

                                <div class="span2">
                                    <g:select from="${elyon.TipoDeIdentificacion.list([sort: 'descripcion'])}" name="tipoDeIdentificacionReferenciaPersonal.id" class="span2 required" value="${data?.tipoDeIdentificacionReferenciaPersonalId}"
                                              optionKey="id"/>
                                </div>

                                <div class="span1">Cédula</div>

                                <div class="span2"><g:textField name="idReferenciaPersonal" class="span2 digits" value="${data?.idReferenciaPersonal?:0000000000}" maxlength="10"/></div>

                                <div class="span1">Nombre 1</div>

                                <div class="span2"><g:textField name="nombre1Ref" class="span2" value="${data?.nombre1ReferenciaPersonal}" maxlength="20"/></div>
                            </div>

                            <div class="row">
                                <div class="span1 offset1">Nombre 2</div>

                                <div class="span2"><g:textField name="nombre2Ref" class="span2" value="${data?.nombre2ReferenciaPersonal}" maxlength="20"/></div>

                                <div class="span1">Apellido 1</div>

                                <div class="span2"><g:textField name="apellid1Ref" class="span2" value="${data?.apellido1ReferenciaPersonal}" maxlength="20"/></div>

                                <div class="span1">Apellido 2</div>

                                <div class="span2"><g:textField name="apellido2Ref" class="span2" value="${data?.apellido2ReferenciaPersonal}" maxlength="20"/></div>
                            </div>

                            <div class="row">
                                <div class="span1 offset1">Dir Par</div>

                                <div class="span2"><g:textField name="dirPar" class="span2" value="${data?.direccionReferenciaPersonal}" maxlength="150"/></div>

                                <div class="span1">Fono par</div>

                                <div class="span2"><g:textField name="fonoPar" class="span2" value="${data?.telefonoReferenciaPersonal}" maxlength="20"/></div>

                                <div class="span1">Parentesco</div>

                                <div class="span2">
                                    <g:select from="${Parentesco.list([sort: 'descripcion'])}" name="parentesco.id" class="span2" value="${data?.parentescoId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>
                            </div>

%{--
                            <div class="row">
                                <div class="span1 offset1">Tipo id princ</div>

                                <div class="span2">
                                    <g:select from="${elyon.TipoDeIdentificacion.list([sort: 'descripcion'])}" name="tipoDeIdentificacionPrincipal.id" class="span2" value="${data?.tipoDeIdentificacionPrincipalId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>

                                <div class="span1">Num Ident princ</div>

                                <div class="span2"><g:textField name="identificacionPrincipal" class="span2" value="${data?.identificacionPrincipal}" maxlength="10"/></div>

                                <div class="span1">Bins princ</div>

                                <div class="span2">
                                    <g:select from="${elyon.Bins.list([sort: 'descripcion'])}" name="binsPrincipal.id" class="span2" value="${data?.binsPrincipalId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>
                            </div>
--}%

%{--
                            <div class="row">

                                <div class="span1 offset1">Afinidad</div>

                                <div class="span2">
                                    <g:select from="${elyon.Afinidad.list([sort: 'descripcion'])}" name="afinidad.id" class="span2" value="${data?.afinidadId}"
                                              noSelection="['': '-Seleccione-']" optionKey="id"/>
                                </div>
                            </div>
--}%
                        </div>
                    </g:form>
                </div>

%{--
                <div class="row">
                    <div class="span1">Comentarios</div>

                    <div class="span7"><g:textArea name="comentarios" cols="5" rows="5" class="span7" value="${data?.comentarios}"/></div>

                    <div class="span1">Contacto alterno</div>

                    <div class="span3"><g:textArea name="contactoAlterno" cols="5" rows="5" class="span3" value="${data?.contactoAlterno}"/></div>

                </div>
--}%

            </fieldset>
        </g:if>
        <g:else>
            <div class="alert alert-error">
                <h4>Alerta</h4>

                <p>No se encontró el lote solicitado</p>

                <p>
                    <g:link controller="lote" action="busquedaRegistro" class="btn btn-danger"><i class="icon-hand-left"></i> Regresar a la lista</g:link>
                </p>
            </div>
        </g:else>

        <script>
            $(function () {
                $("#tabs").tabs({
                    heightStyle : "auto"
                });
                $(".number").numeric({
                    decimal  : ".",
                    negative : false
                });
                $(".digits").numeric({
                    decimal  : false,
                    negative : false
                });

                $(".telefono,.celular,.fono").numeric({
                    decimal  : false,
                    negative : false
                });

                $("#frmRegistroLlamada").validate();
                $(".btn-save").click(function () {
                    $("#frmRegistroLlamada").submit();
                    return false;
                });

                $("#ciudad").change(function(){
                    var valor=$(this).val()
                    $.ajax({
                        type    : "POST",
                        url     : "${createLink(action:'cambiarParroquia')}",
                        data    : "ciudad=" + valor,
                        success : function (msg) {
                             $("#div_par").html(msg)
                        }
                    });
                    $.ajax({
                        type    : "POST",
                        url     : "${createLink(action:'cambiarSucursal')}",
                        data    : "ciudad=" + valor,
                        success : function (msg) {
                            $("#div_suc").html(msg)
                        }
                    });
                })

            });

            $("#tipoVivienda").change(function () {
//               console.log("entro")
                var viviendaSel = $("#tipoVivienda").val()

//                console.log(viviendaSel)

                if(viviendaSel == 4 || viviendaSel == 1){

                    $("#valorVivienda").attr("readonly", false);

                }else {

                    $("#valorVivienda").attr("readonly", true);
                    $("#valorVivienda").val("0");

                }

            });


        </script>

    </body>
</html>