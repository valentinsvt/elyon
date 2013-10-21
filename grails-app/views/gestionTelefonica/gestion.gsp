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

<g:form action="saveGestion" name="frmGestion">
    <g:hiddenField name="lote" value="${gestion[0]?.lote?.id}"/>
    <fieldset class="borde">
        <legend style="color: #0088CC">Datos de la Campaña</legend>

        <div class="row span12">

            <div class="span1">Campaña</div>

            <div class="span3"><g:textField name="campana" disabled="true" value="${lote.campana}"/></div>

            <div class="span1">Número de Orden</div>

            <div class="span3"><g:textField name="numeroOrden" disabled="true"
                                            value="${lote.ordenDeTrabajo.numero}"/></div>

            <div class="span1">Clientes restantes:</div>

            <div class="span2"><g:textField name="restantes" style="width: 80px" disabled="true"
                                            value="${restantes.size()}"/></div>

        </div>

    </fieldset>

    <fieldset class="borde">

        <legend style="color: #0088CC">Datos del Cliente</legend>

        <div class="row span12">

            <div class="span1">
                Código del Cliente
            </div>

            <div class="span1"><g:textField name="codigoCliente" disabled="true" value="${lote.cedula}"/></div>

            <div class="span1" style="margin-left: 200px">
                Nombre del Cliente
            </div>

            <div class="span1"><g:textField name="nombreCliente" disabled="true" value="${lote.nombre}"
                                            style="width: 280px"/></div>

            <div class="span1" style="margin-left: 250px">Estado Gestión</div>


            <div class="span2"><g:select name="estadoGestion" from="${elyon.EstadoGestion.list([sort:'descripcion'])}"  optionKey="id" optionValue="descripcion"
                                         value="${lote?.estadoGestion?.id}" noSelection="['':'Seleccione']"/></div>

        </div>

        <div class="row span12">

            <div class="span1">Dirección del Cliente</div>


            <div class="span3"><g:textField name="direccion" disabled="true" style="width: 350px"
                                            value="${lote.direccion1}"/></div>


        <div class="span2" style="margin-left: 120px">Dirección #2 del Cliente</div><div class="span1"><g:textField name="direccion2" disabled="true" style="width: 350px" value="${lote.direccion2}"/> </div>

        </div>

        <div class="row span12">

            <div class="span1">Ciudad del Cliente</div>

            <div class="span2"><g:textField name="ciudad" disabled="true" style="width: 150px"
                                            value="${lote.ciudad}"/></div>

            <div class="span1">Tipo de Tarjeta</div>

            <div class="span2"><g:textField name="tipoTarjeta" disabled="true" style="width: 150px; margin-left: -40px"
                                            value="${lote.tipoTarjeta}"/></div>

            <div class="span1" style="margin-left: -20px">Cupo</div>

            <div class="span2"><g:textField name="cupo1" disabled="true" style="width: 150px; margin-left: -40px"
                                            value="${lote.cupoNormal}"/></div>


            <div class="span1" style="margin-left: -20px">No Desea</div>
            
            <div class="span2"><g:select from="${elyon.NoDesea.list()}" name="noDesea" value="${noDesea?.id}" noSelection="['':'Seleccione']" optionKey="id" optionValue="descripcion" style="margin-left: -6px"/></div>

        </div>

        <div class="row span12">

%{--
            <div class="span1">Código Tarjeta</div>
            <div class="span2"><g:textField name="codigoTarjeta" disabled="true" style="width: 150px"
                                            value="${lote.codigo}"/></div>
--}%

%{--
            <div class="span1">Cupo Total</div>

            <div class="span2"><g:textField name="cupo2" disabled="true" style="width: 150px"
                                            value="${lote.cupoTotal}"/></div>
--}%

%{--
            <div class="span1">Tipo Cliente</div>
            <div class="span2"><g:textField name="tipCliente" disabled="true" style="width: 150px"
                                            value="${lote.tipoCliente}"/></div>
--}%

        </div>

%{--
        <div class="row span12">
            <div class="span1">Observaciones</div>
            <div class="span2"><g:textField name="observaciones" disabled="true" style="width: 750px"
                                            value="${lote.observaciones}"/></div>
        </div>
--}%

    </fieldset>

    <fieldset class="borde">
        <legend style="color: #0088CC">Detalle de la Gestión Telefónica</legend>



        <g:each in="${gestion}" var="gestionT" status="i">
            <g:hiddenField name="${gestionT.id}.id" value="${gestionT.id}"/>
            <div class="row span12">

                <div class="span1">Teléfono ${i + 1}</div>

                <div class="span1"><g:textField name="telefono" disabled="true" style="width: 100px"
                                                value="${gestionT.telefono}" optionKey="id"/></div>

                <div class="span1" style="padding-left: 50px">Fecha Proceso</div>

                <div class="span1"><g:textField name="fecha" disabled="true" style="width: 80px"
                                                value="${gestionT.fecha}"/></div>

                <div class="span1" style="padding-left: 50px">Observaciones</div>

                <div class="span2"><g:textField name="${gestionT.id}.observaciones" disabled="false"
                                                style="width: 250px"
                                                value="${gestionT.observaciones}"/></div>

                <div class="span1" style="padding-left: 80px">Estado de la Llamada</div>

                <div class="span1"><g:select name="${gestionT.id}.estado" style="width: 130px"
                                             from="${elyon.EstadoLlamada.list([sort: 'descripcion'])}"
                                             value="${gestionT?.estadoLlamadaId}" optionKey="id"
                                             optionValue="descripcion"/></div>
            </div>
        </g:each>

        <div class="span2 btn-group" style="margin-top: 20px; margin-bottom: 20px; margin-left: 450px">


            <g:link controller="lote" action="busqueda" class="btn btn-salir"><i class="icon-hand-left"></i>Salir</g:link>
            <g:link controller="gestionTelefonica" action="saveGestion" class="btn btn-guardar btn-primary"><i class="icon-save"></i>Guardar</g:link>
            <g:link controller="gestionTelefonica" action="siguiente" class="btn btn-next btn-primary" params="[id:lote.id,orden:lote.ordenDeTrabajo.id]"><i class="icon-arrow-right"></i>Siguiente</g:link>
            <g:link controller="gestionTelefonica" action="ingresoVenta" class="btn btn-venta btn-primary"><i class="icon-money"></i>Ingreso Venta</g:link>
        </div>
    </fieldset>
</g:form>
<script>

    $(function () {
//    $("#frmGestion").validate();
        $(".btn-guardar").click(function () {
            $(this).replaceWith(spinner);
            $("#frmGestion").submit();
            return false;
        });
        $(".btn-venta").click(function () {
            $(this).replaceWith(spinner);
            $("#frmGestion").attr("action","${g.createLink(action: 'ingresoVenta')}")
            $("#frmGestion").submit();
            return false;
        });

    });


</script>

</body>
</html>