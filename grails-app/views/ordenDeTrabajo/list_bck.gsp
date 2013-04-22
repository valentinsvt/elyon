<%@ page import="elyon.OrdenDeTrabajo" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="main">
        <title>
            Lista de Orden De Trabajos
        </title>
        <script src="${resource(dir: 'js/jquery/plugins/jquery-validation-1.9.0', file: 'jquery.validate.min.js')}"></script>
        <script src="${resource(dir: 'js/jquery/plugins/jquery-validation-1.9.0', file: 'messages_es.js')}"></script>
    </head>

    <body>

        <div class="span12">
            <g:if test="${flash.message}">
                <div class="alert ${flash.clase ?: 'alert-info'}" role="status">
                    <a class="close" data-dismiss="alert" href="#">×</a>
                    ${flash.message}
                </div>
            </g:if>
        </div>

        <div class="span8 btn-group navigation" role="navigation">
            <a href="#" class="btn btn-ajax btn-new">
                <i class="icon-file"></i>
                Crear  Orden De Trabajo
            </a>
        </div>

        <div id="search" class="pull-right"></div>

        <g:form action="delete" name="frmDelete-OrdenDeTrabajo">
            <g:hiddenField name="id"/>
        </g:form>

        %{--<div id="list-OrdenDeTrabajo" class="span12" role="main" style="margin-top: 10px;">--}%

            <table class="table table-bordered table-striped table-condensed table-hover">
                <thead>
                    <tr>

                        <th>Campana</th>

                        <th>Operador</th>

                        <g:sortableColumn property="numero" title="Numero" style="width:50px"/>

                        <th style="width: 100px;">Lote asignado</th>
                        %{--<th style="width: 100px;">Asignar lote</th>--}%
                        <th width="150">Acciones</th>
                    </tr>
                </thead>
                <tbody class="paginate">
                    <g:each in="${ordenDeTrabajoInstanceList}" status="i" var="ordenDeTrabajoInstance">
                        <tr>

                            <td>${fieldValue(bean: ordenDeTrabajoInstance, field: "campana")}</td>

                            <td>${fieldValue(bean: ordenDeTrabajoInstance, field: "usro")}</td>

                            <td style="text-align: center">${fieldValue(bean: ordenDeTrabajoInstance, field: "numero")}</td>
                            <td style="text-align: center">
                                <a class="btn btn-small btn-inverse btn-ajax verLote" href="#" rel="tooltip" title="Ver" iden="${ordenDeTrabajoInstance.id}" style="margin: auto">
                                    <i class="icon-tasks icon-large" style="margin-right: 5px"></i>
                                    Ver
                                </a>
                            </td>
                            %{--<td style="text-align: center">--}%
                                %{--<a class="btn btn-small btn-warning btn-ajax asignarLote" href="#" rel="tooltip" title="Asignar lote" iden="${ordenDeTrabajoInstance.id}" style="margin: auto">--}%
                                    %{--<i class="icon-share icon-large" style="margin-right: 5px"></i>--}%
                                    %{--Asignar--}%
                                %{--</a>--}%
                            %{--</td>--}%

                            <td style="text-align: center">
                                <a class="btn btn-small btn-show btn-ajax" href="#" rel="tooltip" title="Ver" data-id="${ordenDeTrabajoInstance.id}">
                                    <i class="icon-zoom-in icon-large"></i>
                                </a>
                                <a class="btn btn-small btn-edit btn-ajax" href="#" rel="tooltip" title="Editar" data-id="${ordenDeTrabajoInstance.id}">
                                    <i class="icon-pencil icon-large"></i>
                                </a>

                                <a class="btn btn-small btn-delete" href="#" rel="tooltip" title="Eliminar" data-id="${ordenDeTrabajoInstance.id}">
                                    <i class="icon-trash icon-large"></i>
                                </a>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>

        %{--</div>--}%

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
            var url = "${resource(dir:'images', file:'spinner_24.gif')}";
            var spinner = $("<img style='margin-left:15px;' src='" + url + "' alt='Cargando...'/>");

            function submitForm(btn) {
                if ($("#frmSave-OrdenDeTrabajo").valid()) {
                    btn.replaceWith(spinner);
                }
                $("#frmSave-OrdenDeTrabajo").submit();
            }

            $(function () {

                $(".verLote").click(function () {
                    $.ajax({
                        type    : "POST",
                        url     : "${createLink(action:'lotePorOrden')}",
                        data    : "orden=" + $(this).attr("iden"),
                        success : function (msg) {
                            var btnOk = $('<a href="#" data-dismiss="modal" class="btn">Cancelar</a>');
                            $("#modalHeader").removeClass("btn-edit btn-show btn-delete").addClass("btn-inverse");
                            $("#modalTitle").html("Lote asigando");
                            $("#modalBody").html(msg);
                            $("#modalFooter").html("").append(btnOk)
                            $("#modal-OrdenDeTrabajo").modal("show");
                        }
                    });
                });
                $(".asignarLote").click(function () {
                    var orden = $(this).attr("iden")
                    $.ajax({
                        type    : "POST",
                        url     : "${createLink(action:'listaAsignar')}",
                        data    : "orden=" + $(this).attr("iden"),
                        success : function (msg) {
                            var btnOk = $('<a href="#" data-dismiss="modal" class="btn">Cancelar</a>');
                            var btnSave = $('<a href="#"  class="btn btn-primary"><i class="icon-save"></i> Guardar</a>');

                            btnSave.click(function () {
                                var data = "orden=" + orden + "&lote="
                                $.each($(".chk"), function () {

                                    if ($(this).attr("checked") == "checked") {
                                        data += "" + $(this).attr("id") + ","
                                    }
                                });
                                $.ajax({
                                    type    : "POST",
                                    url     : "${createLink(action:'asignarLote')}",
                                    data    : data,
                                    success : function (msg) {
                                        $("#modal-OrdenDeTrabajo").modal("hide");
                                    }
                                });
                            });
                            $("#modalHeader").removeClass("btn-edit btn-show btn-delete").addClass("btn-warning");
                            $("#modalTitle").html("Asignar lote");
                            $("#modalBody").html(msg);
                            $("#modalFooter").html("").append(btnOk).append(btnSave)
                            $("#modal-OrdenDeTrabajo").modal("show");
                        }
                    });
                });

                $('[rel=tooltip]').tooltip();

                $(".paginate").paginate({
                    maxRows        : 10,
                    searchPosition : $("#search")
                });

                $(".btn-new").click(function () {
                    $.ajax({
                        type    : "POST",
                        url     : "${createLink(action:'form_ajax')}",
                        success : function (msg) {
                            var btnOk = $('<a href="#" data-dismiss="modal" class="btn">Cancelar</a>');
                            var btnSave = $('<a href="#"  class="btn btn-success"><i class="icon-save"></i> Guardar</a>');

                            btnSave.click(function () {
                                submitForm(btnSave);
                                return false;
                            });

                            $("#modalHeader").removeClass("btn-edit btn-show btn-delete");
                            $("#modalTitle").html("Crear Orden De Trabajo");
                            $("#modalBody").html(msg);
                            $("#modalFooter").html("").append(btnOk).append(btnSave);
                            $("#modal-OrdenDeTrabajo").modal("show");
                        }
                    });
                    return false;
                }); //click btn new

                $(".btn-edit").click(function () {
                    var id = $(this).data("id");
                    $.ajax({
                        type    : "POST",
                        url     : "${createLink(action:'form_ajax')}",
                        data    : {
                            id : id
                        },
                        success : function (msg) {
                            var btnOk = $('<a href="#" data-dismiss="modal" class="btn">Cancelar</a>');
                            var btnSave = $('<a href="#"  class="btn btn-success"><i class="icon-save"></i> Guardar</a>');

                            btnSave.click(function () {
                                submitForm(btnSave);
                                return false;
                            });

                            $("#modalHeader").removeClass("btn-edit btn-show btn-delete").addClass("btn-edit");
                            $("#modalTitle").html("Editar Orden De Trabajo");
                            $("#modalBody").html(msg);
                            $("#modalFooter").html("").append(btnOk).append(btnSave);
                            $("#modal-OrdenDeTrabajo").modal("show");
                        }
                    });
                    return false;
                }); //click btn edit

                $(".btn-show").click(function () {
                    var id = $(this).data("id");
                    $.ajax({
                        type    : "POST",
                        url     : "${createLink(action:'show_ajax')}",
                        data    : {
                            id : id
                        },
                        success : function (msg) {
                            var btnOk = $('<a href="#" data-dismiss="modal" class="btn btn-primary">Aceptar</a>');
                            $("#modalHeader").removeClass("btn-edit btn-show btn-delete").addClass("btn-show");
                            $("#modalTitle").html("Ver Orden De Trabajo");
                            $("#modalBody").html(msg);
                            $("#modalFooter").html("").append(btnOk);
                            $("#modal-OrdenDeTrabajo").modal("show");
                        }
                    });
                    return false;
                }); //click btn show

                $(".btn-delete").click(function () {
                    var id = $(this).data("id");
                    $("#id").val(id);
                    var btnOk = $('<a href="#" data-dismiss="modal" class="btn">Cancelar</a>');
                    var btnDelete = $('<a href="#" class="btn btn-danger"><i class="icon-trash"></i> Eliminar</a>');

                    btnDelete.click(function () {
                        btnDelete.replaceWith(spinner);
                        $("#frmDelete-OrdenDeTrabajo").submit();
                        return false;
                    });

                    $("#modalHeader").removeClass("btn-edit btn-show btn-delete").addClass("btn-delete");
                    $("#modalTitle").html("Eliminar Orden De Trabajo");
                    $("#modalBody").html("<p>¿Está seguro de querer eliminar este Orden De Trabajo?</p>");
                    $("#modalFooter").html("").append(btnOk).append(btnDelete);
                    $("#modal-OrdenDeTrabajo").modal("show");
                    return false;
                });

            });

        </script>

    </body>
</html>
