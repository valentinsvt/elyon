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
<fieldset class="borde">
    <legend>
        Criterio de la Consulta de Datos
    </legend>

    <div class="row span5">

        <g:radio name="grupo" value="1"/>


        <div class="span1">Código:</div>

        <div class="span3">

            <g:textField name="codigo" class="span3"/>
        </div>


    </div>

    <div class="row span5">

        <g:radio name="grupo" value="2"/>

        <div class="span1">Nombre:</div>

        <div class="span3">
            <g:textField name="nombre" class="span3"/>
        </div>

    </div>

    <div class="row span10">

        <g:submitButton name="Desplegar"/>

        <g:submitButton name="Salir"/>

    </div>



</fieldset>

<fieldset class="borde">
    <legend>
        Información de Clientes
    </legend>

    <div class="span10">
        <table border="1">

            %{--<div class="span10">--}%

                <thead class="info">
            <tr>

                %{--<th>Id</th>--}%
                <th style="text-align: center">Campaña</th>
                <th style="text-align: center">Orden</th>
                <th style="text-align: center">Codigo/Cedula</th>
                <th style="text-align: center">Cliente</th>
                <th>Direccion 1</th>
                <th>Direccion 2</th>
                <th>Teléfono 1</th>
                <th>Teléfono 2</th>
                <th>Teléfono 3</th>
                <th>Teléfono 4</th>
                <th>Teléfono 5</th>
                <th>Teléfono 6</th>
                <th>Teléfono Trabajo Anterior</th>
                <th>Ciudad</th>
                <th>Tipo Tarjeta</th>
                <th>Cupo Normal</th>
                <th>Codigo</th>
                <th>Cupo Total</th>
                <th>Tipo Cliente</th>
                <th>Observaciones</th>


            </tr>
                </thead>

            %{--</div>--}%
            <tbody>

            <div class="span10">

                <td class="campana">${loteOrdenTrabajo.lote.campana}</td>

                <td class="orden">${loteOrdenTrabajo.ordenDeTrabajo.numero}</td>

                <td class="cedula">${loteOrdenTrabajo.lote.cedula}</td>

                <td class="cliente">${loteOrdenTrabajo.lote.nombre}</td>

                <td class="direccion1">${loteOrdenTrabajo.lote.direccion1}</td>

                <td class="direccion2">${loteOrdenTrabajo.lote.direccion2}</td>

                <td class="telefono1">${loteOrdenTrabajo.lote.telefono1}</td>

                <td class="telefono2">${loteOrdenTrabajo.lote.telefono2}</td>

                <td class="telefono3">${loteOrdenTrabajo.lote.telefono3}</td>

                <td class="telefono4">${loteOrdenTrabajo.lote.telefono4}</td>

                <td class="telefono5">${loteOrdenTrabajo.lote.telefono5}</td>

                <td class="telefono6">${loteOrdenTrabajo.lote.telefono6}</td>

                <td class="telefonoTA">${loteOrdenTrabajo.lote.telefonoTrabajoAnterior}</td>

                <td class="ciudad">${loteOrdenTrabajo.lote.ciudad}</td>

                <td class="tipoTarjeta">${loteOrdenTrabajo.lote.tipoTarjeta}</td>

                <td class="cupoNormal">${loteOrdenTrabajo.lote.cupo1}</td>

                <td class="codigo">${loteOrdenTrabajo.lote.codigo}</td>

                <td class="cupoTotal">${loteOrdenTrabajo.lote.cupo2}</td>

                <td class="tipoCliente">${loteOrdenTrabajo.lote.tipoCliente}</td>

                <td class="observaciones">${loteOrdenTrabajo.lote.observaciones}</td>

            </div>

            </tbody>

        </table>

    </div>

</fieldset>



</div>
</body>
</html>