<%@ page import="elyon.OrdenDeTrabajo" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="main">
        <title>
            Lista de Orden De Trabajos
        </title>
    </head>

    <body>

        <%@ page import="elyon.Lote; elyon.OrdenDeTrabajo" %>

        <g:form class="form-horizontal" name="frmSave-OrdenDeTrabajo" action="save">
            <g:hiddenField name="id" value="${ordenDeTrabajoInstance?.id}"/>

            <div class="alert alert-error hide" style="margin-bottom: 15px;" id="divError" data-err="0">
                <h5>Alerta</h5>
                <span id="spanError">ERRORES AQUI</span>
            </div>

            <div class="row" style="margin-bottom: 15px;">

                <div class="span1">Campaña:</div>

                <div class="span2">
                    <elm:select id="campana" name="campana.id" from="${elyon.Campana.list()}" optionKey="id" class="many-to-one required span2"
                                optionValue="descripcion"
                                value="${ordenDeTrabajoInstance?.campana?.id}" noSelection="['-1': '- Seleccione -']"/>
                </div>

                <div class="span1">Usuario:</div>

                <div class="span2">
                    <g:select id="usro" name="usro.id" from="${elyon.seguridad.Usro.list()}" optionKey="id" class="many-to-one required span2"
                              value="${ordenDeTrabajoInstance?.usro?.id}" noSelection="['-1': '- Seleccione -']"/>
                </div>

                <div class="span1">Cantidad:</div>

                <div class="span1">
                    <g:field type="number" name="numero" class=" required span1" value="${fieldValue(bean: ordenDeTrabajoInstance, field: 'numero')}"/>
                </div>

                <div class="span1" style="width: 80px;">
                    <a href="#" class="btn btn-success btn-small" id="btnAdd" data-camp="-1">
                        <i class="icon-plus"></i> Asignar</a>
                </div>
            </div>

            <div id="divUsuarios">
                <div class="row" style="margin-bottom: 15px;">
                    <div class="span9" id="divInfo"></div>
                </div>

                <table class="table table-bordered table-striped table-condensed table-hover">
                    <thead>
                        <th>Usuario</th>
                        <th>Cantidad</th>
                        <th style="width: 80px;"></th>
                    </thead>
                    <tbody id="tbUsuarios">
                        <td colspan="3" class="alert alert-info" id="eliminar">
                            <i class="icon-warning-sign"></i>
                            No se han agregado usuarios a esta campaña
                        </td>
                    </tbody>
                </table>
            </div>

        </g:form>

        <div class="modal grande hide fade" id="modal-OrdenDeTrabajo">
            <div class="modal-header" id="modalHeader">
                <button type="button" class="close darker" data-dismiss="modal">
                    <i class="icon-remove-circle"></i>
                </button>

                <h3 id="modalTitle"></h3>
            </div>

            <div class="modal-body" id="modalBody">
            </div>

            <div class="modal-footer" id="modalFooter">
            </div>
        </div>

        <script type="text/javascript">

            function valida() {
                var err = 0;
                var errores = "";
                var camp = $("#btnAdd").data("camp");
                var disp = $("#btnAdd").data("disponibles");
                var usu = $("#usro").val();
                var cant = parseFloat($("#numero").val());

                if (camp == "-1" || camp == -1) {
                    err++;
                    errores += "<li id='errCamp'>Seleccione una campaña</li>"
                }
                if (disp <= 0) {
                    err++;
                    errores += "<li id='errCamp'>Seleccione una campaña con elementos disponibles</li>"
                }
                if (cant > disp) {
                    err++;
                    errores += "<li id='errCant'>No puede ingresar un número mayor a " + disp + "</li>"
                }
                if (usu == "-1" || usu == -1) {
                    err++;
                    errores += "<li id='errUsu'>Seleccione un usuario</li>"
                }
                if (cant <= 0) {
                    err++;
                    errores += "<li id='errCant'>Ingrese una cantidad válida</li>"
                }

                if (errores == "") {
                    return true;
                } else {
                    $("#spanError").html("<ul>" + errores + "</ul>");
                    $("#divError").show().data("err", err);
                    return false;
                }
            }

            function validErr() {
                var err = parseInt($("#divError").data("err"));
                err = err - 1;
                if (err == 0) {
                    $("#divError").hide();
                }
            }

            function eliminar($btn) {
                if (confirm("Está seguro de querer eliminar a este usuario de esta campaña?")) {
                    $btn.replaceWith(spinner);
                    var id = $btn.attr("id");
                    $.ajax({
                        type    : "POST",
                        url     : "${createLink(action:'delUsu')}",
                        data    : {
                            orden : id
                        },
                        success : function (msg) {
                            $("#divUsuarios").html(msg);
                        }
                    });
                }
            }

            function ver($btn) {
                var id = $btn.attr("id");
                $.ajax({
                    type    : "POST",
                    url     : "${createLink(action:'lotePorOrden')}",
                    data    : "orden=" + id,
                    success : function (msg) {
                        var btnOk = $('<a href="#" data-dismiss="modal" class="btn">Cancelar</a>');
                        $("#modalHeader").removeClass("btn-edit btn-show btn-delete").addClass("btn-inverse");
                        $("#modalTitle").html("Lote asigando");
                        $("#modalBody").html(msg);
                        $("#modalFooter").html("").append(btnOk);
                        $("#modal-OrdenDeTrabajo").modal("show");
                    }
                });
            }

            $(function () {
                $("#campana").val("-1");

                $("#numero").bind({
                    keydown : function (ev) {
                        return validarInt(ev);
                    },
                    keyup   : function (ev) {
                        var $err = $("#errCant");
                        if ($.trim($(this).val()) == "" || $(this).val() == "0") {
                            if ($err) $err.hide();
                            validErr();
                        } else {
                            if ($err) $err.show();
                        }
                    }
                });

                $("#campana").change(function () {
                    var camp = $(this).val();
                    $.ajax({
                        type    : "POST",
                        url     : "${createLink(action:'valoresCamp')}",
                        data    : {
                            camp : camp
                        },
                        success : function (msg) {
                            var parts = msg.split("_");
                            $("#btnAdd").data({
                                camp        : camp,
                                total       : parseInt(parts[0]),
                                usados      : parseInt(parts[1]),
                                disponibles : parseInt(parts[2])
                            });
                        }
                    });
                    $.ajax({
                        type    : "POST",
                        url     : "${createLink(action:'loadCamp')}",
                        data    : {
                            camp : camp
                        },
                        success : function (msg) {
//                $("#tbUsuarios").html(msg);
                            $("#divUsuarios").html(msg);
                        }
                    });
                    var $err = $("#errCamp");
                    if ($(this).val() != '-1' && $(this).val() != -1) {
                        if ($err) $err.hide();
                        validErr();
                    } else {
                        if ($err) $err.show();
                    }
                });
                $("#usro").change(function () {
                    var $err = $("#errUsu");
                    if ($(this).val() != '-1' && $(this).val() != -1) {
                        if ($err) $err.hide();
                        validErr();
                    } else {
                        if ($err) $err.show();
                    }
                });

                $("#btnAdd").click(function () {
                    if (valida()) {
                        $(this).hide().after(spinner);

//            $("#campana").replaceWith($("#campana option:selected").text());

                        var camp = $(this).data("camp");
                        var usu = $("#usro").val();
                        var cant = $("#numero").val();

                        $.ajax({
                            type    : "POST",
                            url     : "${createLink(action: 'addUsu')}",
                            data    : {
                                camp : camp,
                                usu  : usu,
                                cant : cant
                            },
                            success : function (msg) {
                                $("#divUsuarios").html(msg);
                                spinner.remove();
                                $("#btnAdd").show();
                            }
                        });
                    }
                    return false;
                });
            });
        </script>

    </body>
</html>
