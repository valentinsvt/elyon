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
            <th>Codigo/Cedula</th>
            <th>Cliente</th>
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


            </thead>

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