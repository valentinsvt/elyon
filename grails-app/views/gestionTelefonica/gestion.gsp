<%--
  Created by IntelliJ IDEA.
  User: fabricio
  Date: 10/31/12
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Gestion de Llamadas</title>
    <style type="text/css">
    .row {
        margin-bottom: 5px;
    }

    textarea {
        height: 60px;
    }
    </style>

</head>

<body>


<fieldset class="borde">
    <legend>Datos de la Campaña</legend>

    <div class="row span12">

        <div class="span1">Campaña</div>

        <div class="span3"><g:textField name="campana" disabled="true" value="${lote.campana}"/></div>

        <div class="span1">Número de Orden</div>

        <div class="span3"><g:textField name="numeroOrden" disabled="true" value="${lote.ordenDeTrabajo.numero}"/></div>

        <div class="span1">Clientes restantes:</div>

        <div class="span2"><g:textField name="restantes" style="width: 80px" disabled="true"
                                        value="${restantes.size()}"/></div>

    </div>

</fieldset>

<fieldset class="borde">

    <legend>Datos del Cliente</legend>

    <div class="row span12">

        <div class="span1">
            Código del Cliente
        </div>

        <div class="span3"><g:textField name="codigoCliente" disabled="true" value="${lote.cedula}"/></div>

        <div class="span1">
            Nombre del Cliente
        </div>

        <div class="span3"><g:textField name="nombreCliente" disabled="true" value="${lote.nombre}"/></div>

        <div class="span1">Estado Gestión</div>


        <div class="span2"><g:select name="estadoGestion" from="${lote?.estadoGestion?.descripcion}"
                                     /></div>

    </div>

    <div class="row span10">

        <div class="span1">Direccion del Cliente</div>

        <div class="span3"><g:textField name="direccion" disabled="true" style="width: 750px"
                                        value="${lote.direccion1} ${lote.direccion2}"/></div>

    </div>

    <div class="row span12">

        <div class="span1">Ciudad del Cliente</div>

        <div class="span2"><g:textField name="ciudad" disabled="true" style="width: 150px"
                                        value="${lote.ciudad}"/></div>

        <div class="span1">Tipo de Tarjeta</div>

        <div class="span2"><g:textField name="tipoTarjeta" disabled="true" style="width: 150px"
                                        value="${lote.tipoTarjeta}"/></div>

        <div class="span1">Cupo Normal</div>

        <div class="span2"><g:textField name="cupo1" disabled="true" style="width: 150px"
                                        value="${lote.cupoNormal}"/></div>

    </div>

    <div class="row span12">

        <div class="span1">Código Tarjeta</div>

        <div class="span2"><g:textField name="codigoTarjeta" disabled="true" style="width: 150px"
                                        value="${lote.codigo}"/></div>

        <div class="span1">Cupo Total</div>

        <div class="span2"><g:textField name="cupo2" disabled="true" style="width: 150px"
                                        value="${lote.cupoTotal}"/></div>

        <div class="span1">Tipo Cliente</div>

        <div class="span2"><g:textField name="tipCliente" disabled="true" style="width: 150px"
                                        value="${lote.tipoCliente}"/></div>

    </div>

    <div class="row span12">

        <div class="span1">Observaciones</div>

        <div class="span2"><g:textField name="observaciones" disabled="true" style="width: 750px"
                                        value="${lote.observaciones}"/></div>

    </div>

</fieldset>

<fieldset class="borde">
    <legend>Detalle de la Gestión Telefónica</legend>



    <g:each in="${gestion}" var="gestionT" status="i">

        <div class="row span12">

            <div class="span1">Teléfono</div>

            <div class="span2"><g:textField name="telefono1" disabled="true" style="width: 150px"
                                            value="${gestionT.telefono}"/></div>

            <div class="span1">Fecha Proceso</div>

            <div class="span2"><g:textField name="fecha" disabled="true"  style="width: 150px"
                                            value="${gestionT.fecha}" /></div>

            <div class="span1">Observaciones</div>

            <div class="span2"><g:textField name="observaciones" disabled="false" style="width: 150px"
                                            value="${gestionT.observaciones}"/></div>

            <div class="span1">Estado de la Llamada</div>

            <div class="span1"><g:select name="estado"  style="width: 130px"
                                         from="${gestionT.estadoLlamada}" /></div>
        </div>

    </g:each>

    <div align="center" style="margin-top: 70px">

    <g:link controller="gestionTelefonica" action="saveGestion" class="btn btn-guardar">Guardar</g:link>

    <g:link controller="lote" action="busqueda" class="btn">Salir</g:link>

    </div>

</fieldset>

</body>
</html>