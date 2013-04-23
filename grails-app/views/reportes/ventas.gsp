<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>
        Ventas
    </title>
    <script src="${resource(dir: 'js/jquery/plugins/jquery-validation-1.9.0', file: 'jquery.validate.min.js')}"></script>
    <script src="${resource(dir: 'js/jquery/plugins/jquery-validation-1.9.0', file: 'messages_es.js')}"></script>
</head>

<body>

<div id="ventasDialog">

    %{--<fieldset>--}%
    <div class="span3" style="margin-bottom: 20px; margin-top: 20px">

        Fecha Inicio: <elm:datepicker name="fechaInicio" class="datepicker" style="width: 100px; margin-left: 6px"/>

    </div>

    <div class="span3">

        Fecha Fin:    <elm:datepicker name="fechaFin" class="datepicker" style="width: 100px; margin-left: 20px"/>

    </div>

    %{--</fieldset>--}%
</div>

<script type="text/javascript">

    var fechaInicio

    var fechaFin

    $("#ventasDialog").dialog({

        autoOpen: false,
        resizable: false,
        modal: false,
        draggable: false,
        closeOnEscape: false,
        width: 350,
        height: 250,
        position: 'center',
        title: 'Seleccione la fecha para imprimir el reporte',
        buttons: {
            "Aceptar": function () {
                fechaInicio = $("#fechaInicio").val()
                fechaFin = $("#fechaFin").val()

                if(fechaInicio != '' && fechaFin != ''){


                    location.href = "${createLink(controller: 'reportes', action: 'ventasReporte')}?fechaInicio=" + fechaInicio + "&fechaFin=" + fechaFin
                    $("#ventasDialog").dialog("close");


                } else {



                }


            },
            "Cancelar": function () {
                $("#ventasDialog").dialog("close");
            }
        }

    });


    function cargarVentas() {
        $("#ventasDialog").dialog("open");
    };


    cargarVentas();








</script>

</body>

</html>