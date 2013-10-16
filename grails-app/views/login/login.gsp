<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 7/2/12
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Ingreso</title>

        <script src="${resource(dir: 'js/jquery/plugins/jquery-validation-1.9.0', file: 'jquery.validate.min.js')}"></script>
        <script src="${resource(dir: 'js/jquery/plugins/jquery-validation-1.9.0', file: 'messages_es.js')}"></script>
    </head>

    <body>

    <div style="width: 800px;">
    <img src="./images/central4.jpeg" alt="Sistema de Gestión Telefónica" height="263px;" width="240px;" style="margin-top:20px; ">
        <g:form class="well form-horizontal span" action="validarLogin" name="frmLogin" style="position: fixed; margin-left: 260px; top: 50px;">
            <fieldset >
                <legend>Ingreso</legend>

                <g:if test="${flash.message}">
                    <div class="alert alert-info" role="status">
                        <a class="close" data-dismiss="alert" href="#">×</a>
                        ${flash.message}
                    </div>
                </g:if>

                <div class="control-group">
                    <label class="control-label" for="login">Nombre de usuario</label>

                    <div class="controls">
                        <g:textField name="login" class="span2" required=""/>
                        <p class="help-block ui-helper-hidden"></p>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="pass">Password</label>

                    <div class="controls">
                        <g:passwordField name="pass" class="span2" required=""/>
                        <p class="help-block ui-helper-hidden"></p>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="pass">&nbsp;</label>
                    <a href="#" class="btn btn-primary" id="btnLogin">Entrar</a>
                </div>
            </fieldset>
            <div style="text-align: right; font-size: 10px;">Versión ${message(code: 'version', default: '0.1.0x')}</div>
        </g:form>
    </div>
        <script type="text/javascript">
            $(function () {

                $("input").keypress(function (ev) {
                    if (ev.keyCode == 13) {
                        $("#frmLogin").submit();
                    }
                });

                $("#btnLogin").click(function () {
                    $("#frmLogin").submit();
                });

                $("#frmLogin").validate({
                    errorPlacement : function (error, element) {
                        element.parent().find(".help-block").html(error).show();
                    },
                    success        : function (label) {
                        label.parent().hide();
                    },
                    errorClass     : "label label-important"
                });

            });
        </script>

    </body>
</html>