<html>
    <head>
        <meta name="layout" content="main">
        <title>Reportes</title>

        <style type="text/css">
        .lista, .desc {
            float       : left;
            min-height  : 150px;
            margin-left : 25px;
        }

        .lista {
            width : 615px;
        }

        .desc {
            width : 265px;
        }

        .link {
            font-weight     : bold;
            text-decoration : none;
        }

        .noBullet {
            list-style : none;
            margin     : 1em;
            padding    : 0;
        }

        .noBullet li {
            margin-bottom : 10px;
        }

        .linkHover {
            text-decoration : overline underline;
        }
        </style>

    </head>

    <body>
        <div id="list-empresa" class="content scaffold-list" role="main">
            <div class="nav navegacion" style="background: #ffffff; box-shadow: none;">

            </div>
            <g:if test="${msn}">
                <div style="width: 800px;line-height: 30px;height: 30px;padding-left: 10px;">
                    ${msn}
                </div>
            </g:if>


            <div class="contenedor">
                <h1 style="margin-left: 20px;">Reportes</h1>
                <g:if test="${flash.message}">
                    <div class="message ${flash.clase}" role="status"><span
                            class="ss_sprite ${flash.ico}">&nbsp;</span>${flash.message}
                    </div>
                </g:if>



                <div class="ui-widget-content ui-corner-all lista">
                    <ul class="noBullet">
                        <li text="plcn">
                            <a href="#" id="ventasPdf" class="link">
                                Ventas
                            </a>

                        </li>

                        <li text="gscn">
                            <a href="#" id="ventasExcel" class="link">

                                Ventas Excel
                            </a>

                        </li>
                        <li text="cmpr">
                            <a href="#" id="detallado" class="link">
                                Reporte Detallado
                            </a>
                        </li>
                        <li text="auct">
                            <a href="#" id="consolidado" class="link">
                                Reporte Consolidado
                            </a>
                        </li>
                    </ul>
                </div>

            </div>
        </div>

        <div id="ventasDialog" class="hide">
            <div class="span3" style="margin-bottom: 20px; margin-top: 20px;">
                Fecha Inicio: <elm:datepicker name="fechaInicio" class="datepicker" style="width: 100px; margin-left: 6px;"/>
            </div>

            <div class="span3">
                Fecha Fin:    <elm:datepicker name="fechaFin" class="datepicker" style="width: 100px; margin-left: 20px;"/>
            </div>
        </div>

        <div id="ventasDialogExcel" class="hide">
            <div class="span3" style="margin-bottom: 20px; margin-top: 20px;">
                Fecha Inicio: <elm:datepicker name="fechaInicioExcel" class="datepicker" style="width: 100px; margin-left: 6px;"/>
            </div>

            <div class="span3">
                Fecha Fin:    <elm:datepicker name="fechaFinExcel" class="datepicker" style="width: 100px; margin-left: 20px;"/>
            </div>
        </div>

        <div id="reporteDetalladoDialog" class="hide">

            <div class="span3" style="margin-bottom: 20px; margin-top: 20px;">
                Fecha Inicio: <elm:datepicker name="fechaInicioDetallado" class="datepicker" style="width: 100px; margin-left: 6px;"/>
            </div>

            <div class="span3" style="margin-bottom: 10px">
                Fecha Fin:    <elm:datepicker name="fechaFinDetallado" class="datepicker" style="width: 100px; margin-left: 20px;"/>
            </div>

            <div style="margin-bottom: 10px; margin-left: 30px">
                Campaña:
                <g:select name="campaña" id="campaña"
                          from="${elyon.Campana.list()}"
                          optionKey="id" optionValue="descripcion"
                          class="ui-widget-content ui-corner-all" style="width: 180px; margin-left: 20px"/>
            </div>
        </div>


        <div id="dlgConsolidado" class="hide">
            <div class="span3" style="margin-bottom: 10px; margin-top: 20px;">
                Fecha Inicio: <elm:datepicker name="fechaInicioCons" class="datepicker" style="width: 100px; margin-left: 6px;"/>
            </div>

            <div class="span3" style="margin-bottom: 10px;">
                Fecha Fin:    <elm:datepicker name="fechaFinCons" class="datepicker" style="width: 100px; margin-left: 20px;"/>
            </div>

            <div class="span3" style="margin-bottom: 10px;">

                Campaña:     <g:select name="campanaCons"
                          from="${elyon.Campana.list()}" noSelection="['-1': 'Todas']"
                          optionKey="id" optionValue="descripcion"
                          class="ui-widget-content ui-corner-all" style="width: 150px; margin-left: 20px;"/>
            </div>

            <div class="span3" style="margin-bottom: 10px;">
                Usuario:         <g:select name="usu" id="usu" noSelection="['-1': 'Todos']"
                                           from="${elyon.seguridad.Usro.list()}"
                                           optionKey="id" optionValue="nombre"
                                           class="ui-widget-content ui-corner-all" style="width: 150px; margin-left: 30px;"/>
            </div>
        </div>



        <script type="text/javascript">

            var fechaInicio
            var fechaFin
            var fechaInicioExcel
            var fechaFinExcel
            var fechaInicioDetallado
            var fechaFinDetallado
            var campana

            $(function () {
                $("#ventasPdf").click(function () {
                    $("#ventasDialog").dialog("open");
                    return false;
                });

                $("#ventasExcel").click(function () {
                    $("#ventasDialogExcel").dialog("open");
                    return false;
                });

                $("#detallado").click(function () {
                    $("#reporteDetalladoDialog").dialog("open");
                    return false;
                });

                $("#consolidado").click(function () {
                    $("#dlgConsolidado").dialog("open");
                    return false;
                });

                $("#ventasDialog").dialog({
                    autoOpen      : false,
                    resizable     : false,
                    modal         : true,
                    draggable     : false,
                    closeOnEscape : false,
                    width         : 350,
                    height        : 250,
                    position      : 'center',
                    title         : 'Seleccione la fecha para imprimir el reporte',
                    buttons       : {
                        "Aceptar"  : function () {
                            fechaInicio = $("#fechaInicio").val()
                            fechaFin = $("#fechaFin").val()

                            if (fechaInicio != '' && fechaFin != '') {

                                location.href = "${createLink(controller: 'reportes', action: 'ventasReporte')}?fechaInicio=" + fechaInicio + "&fechaFin=" + fechaFin
                                $("#ventasDialog").dialog("close");

                            } else {

                            }

                        },
                        "Cancelar" : function () {
                            $("#ventasDialog").dialog("close");
                        }
                    }

                });

                //ventas excel
                $("#ventasDialogExcel").dialog({
                    autoOpen  : false,
                    resizable : false,
                    modal     : true,
                    draggable : false,
                    width     : 350,
                    height    : 250,
                    position  : 'center',
                    title     : 'Seleccione las fecha para generar el archivo',
                    buttons   : {
                        "Aceptar"  : function () {
                            fechaInicioExcel = $("#fechaInicioExcel").val()
                            fechaFinExcel = $("#fechaFinExcel").val()
                            if (fechaInicioExcel != '' && fechaFinExcel != '') {
                                location.href = "${createLink(controller: 'reportes', action: 'ventasReporteExcel')}?fechaInicio=" + fechaInicioExcel + "&fechaFin=" + fechaFinExcel
                                $("#ventasDialogExcel").dialog("close");
                            }
                            else {
                            }
                        },
                        "Cancelar" : function () {
                            $("#ventasDialogExcel").dialog("close");
                        }
                    }
                });
                //reporte Detallado
                $("#reporteDetalladoDialog").dialog({
                    autoOpen  : false,
                    resizable : false,
                    modal     : true,
                    draggable : false,
                    width     : 350,
                    height    : 350,
                    position  : 'center',
                    title     : 'Seleccione la campaña',
                    buttons   : {
                        "Aceptar"  : function () {

                            fechaInicioDetallado = $("#fechaInicioDetallado").val();
                            fechaFinDetallado = $("#fechaFinDetallado").val();
                            campana = $("#campaña").val()

                            if(fechaInicioDetallado != '' && fechaFinDetallado != ''){

                            if (campana != '') {
                                location.href = "${createLink(controller: 'reportes', action: 'reporteDetallado')}?id=" + campana + "&fechaInicio=" + fechaInicioDetallado + "&fechaFin=" + fechaFinDetallado
                                $("#reporteDetalladoDialog").dialog("close")
                            }
                            else {
                                $("#reporteDetalladoDialog").dialog("close")
                            }

                            } else {



                            }
                        },
                        "Cancelar" : function () {
                            $("#reporteDetalladoDialog").dialog("close");
                        }
                    }
                });

                //consolidado
                $("#dlgConsolidado").dialog({
                    autoOpen  : false,
                    resizable : false,
                    modal     : true,
                    draggable : false,
                    width     : 350,
                    height    : 350,
                    position  : 'center',
                    title     : 'Seleccione las fecha para generar el archivo',
                    buttons   : {
                        "Aceptar"  : function () {
                            fechaInicioExcel = $("#fechaInicioCons").val();
                            fechaFinExcel = $("#fechaFinCons").val();
                            var camp = $("#campanaCons").val();
                            var usu = $("#usu").val();

                            if (fechaInicioExcel != '' && fechaFinExcel != '') {
                                location.href = "${createLink(controller: 'reportes', action: 'reporteConsolidado')}?fi=" + fechaInicioExcel + "&ff=" + fechaFinExcel + "&camp=" + camp + "&op=" + usu;
                                $("#dlgConsolidado").dialog("close");
                            }
                            else {
                            }
                        },
                        "Cancelar" : function () {
                            $("#dlgConsolidado").dialog("close");
                        }
                    }
                });

            });

            //            function cargarVentas() {
            //                $("#ventasDialog").dialog("open");
            //            }
            //
            //            function cargarVentasExcel() {
            //                $("#ventasDialogExcel").dialog("open")
            //            }

        </script>
    </body>
</html>