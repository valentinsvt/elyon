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
</head>

<body>
<fieldset class="borde">
    <legend>
        Criterio de la Consulta de Datos
    </legend>

    <div class="row span10">

        <div class="span1">Código:</div>

        <div class="span3">

            <g:textField name="codigo" class="span3"/>
        </div>

    </div>

    <div class="row span10">

        <div class="span1">Nombre:</div>

        <div class="span3">
            <g:textField name="nombre" class="span3"/>
        </div>

    </div>

</fieldset>

<fieldset class="borde">
    <legend>
        Información de Clientes
    </legend>

    <div class="span10">
        <table>
            <thead class="info">

            %{--<th>Id</th>--}%
            <th>Campaña</th>
            <th>Orden</th>
            <th>Codigo</th>
            <th>Cliente</th>
            <th>Direccion 1</th>
            <th>Direccion 2</th>
            <th>Teléfono 1</th>
            <th>Teléfono 2</th>
            <th>Teléfono 3</th>
            <th>Teléfono 4</th>
            <th>Teléfono 5</th>
            <th>Teléfono 6</th>
            <th>Trabajo Anterior</th>
            <th>Ciudad</th>
            <th>Tipo Tarjeta</th>
            <th>Cupo Normal</th>
            <th>Codigo</th>
            <th>Cupo Total</th>
            <th>Tipo Cliente</th>
            <th>Observaciones</th>


            </thead>

            <tbody>

            <td class="campana">${}</td>

            </tbody>

        </table>

    </div>

</fieldset>

</div>
</body>
</html>