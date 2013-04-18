
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>
        Archivo de Ventas
    </title>
    %{--<script src="${resource(dir: 'js/jquery/plugins/jquery-validation-1.9.0', file: 'jquery.validate.min.js')}"></script>--}%
    <script src="${resource(dir: 'js/jquery/plugins/jquery-validation-1.9.0', file: 'messages_es.js')}"></script>
  </head>
<body>

<div id="ventasDialog">

    <fieldset>
           <div class="span3" style="margin-bottom: 20px; margin-top: 20px">
            Fecha Inicio: <elm:datepicker name="fechaInicio" class="datepicker" style="width: 100px; margin-left: 6px" />
        </div>

        <div class="span3">
            Fecha Fin:    <elm:datepicker name="fechaFin" class="datepicker" style="width: 100px; margin-left: 20px"/>

        </div>

    </fieldset>
</div>

 <script type="text/javascript">

       var fechaInicio

       var fechaFin

     $("#ventasDialog").dialog({

         autoOpen  : false,
         resizable : false,
         modal     : true,
         draggable : false,
         width     : 350,
         height    : 250,
         position  : 'center',
         title     : 'Seleccione las fechas para generar el archivo',
         buttons   : {
             "Aceptar" : function () {
//
//
//             console.log($("#fechaInicio").val())
//                console.log($("#fechaFin").val())

                    fechaInicio =   $("#fechaInicio").val()
                    fechaFin =   $("#fechaFin").val()


                 location.href="${createLink(controller: 'reportes', action: 'ventasReporte')}?fechaInicio=" + fechaInicio + "&fechaFin=" + fechaFin


                 $("#ventasDialog").dialog("close");

                 %{--$.ajax({--}%
                     %{--type    : "POST",--}%
                     %{--url     : "${createLink(controller: 'reportes', action: 'ventas')}",--}%
                     %{--data    : {--}%
                         %{--fechaInicio : fechaInicio ,--}%
                         %{--fechaFin : fechaFin--}%

                     %{--},--}%
                     %{--success : function (msg) {--}%

                         %{--if (msg == 'ok') {--}%

                             %{--location.href = "${createLink(action: 'registroObra')}"--}%

                         %{--} else {--}%

                         %{--}--}%

                     %{--}--}%
                 %{--});--}%



             },

             "Cancelar" : function () {


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