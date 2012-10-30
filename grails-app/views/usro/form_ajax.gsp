<%@ page import="elyon.seguridad.Usro" %>

<div id="create-Usro" class="span" role="main">
<g:form class="form-horizontal" name="frmSave-Usro" action="save">
    <g:hiddenField name="id" value="${usroInstance?.id}"/>

    <div class="control-group">
        <div>
            <span class="control-label label label-inverse">
                Cedula
            </span>
        </div>

        <div class="controls">
            <g:textField name="cedula" maxlength="10" class="" value="${usroInstance?.cedula}"/>

            <p class="help-block ui-helper-hidden"></p>
        </div>
    </div>


    <div class="control-group">
        <div>
            <span class="control-label label label-inverse">
                Nombre
            </span>
        </div>

        <div class="controls">
            <g:textField name="nombre" maxlength="30" class="" value="${usroInstance?.nombre}"/>

            <p class="help-block ui-helper-hidden"></p>
        </div>
    </div>


    <div class="control-group">
        <div>
            <span class="control-label label label-inverse">
                Apellido
            </span>
        </div>

        <div class="controls">
            <g:textField name="apellido" maxlength="30" class="" value="${usroInstance?.apellido}"/>

            <p class="help-block ui-helper-hidden"></p>
        </div>
    </div>


    <div class="control-group">
        <div>
            <span class="control-label label label-inverse">
                Fecha Nacimiento
            </span>
        </div>

        <div class="controls">
            <elm:datepicker name="fechaNacimiento" class="" value="${usroInstance?.fechaNacimiento}"/>


            <p class="help-block ui-helper-hidden"></p>
        </div>
    </div>


    <div class="control-group">
        <div>
            <span class="control-label label label-inverse">
                Fecha Inicio
            </span>
        </div>

        <div class="controls">
            <elm:datepicker name="fechaInicio" class="" value="${usroInstance?.fechaInicio}"/>


            <p class="help-block ui-helper-hidden"></p>
        </div>
    </div>


    <div class="control-group">
        <div>
            <span class="control-label label label-inverse">
                Fecha Fin
            </span>
        </div>

        <div class="controls">
            <elm:datepicker name="fechaFin" class="" value="${usroInstance?.fechaFin}"/>


            <p class="help-block ui-helper-hidden"></p>
        </div>
    </div>


    <div class="control-group">
        <div>
            <span class="control-label label label-inverse">
                Cargo
            </span>
        </div>

        <div class="controls">
            <g:textField name="cargo" maxlength="50" class="" value="${usroInstance?.cargo}"/>

            <p class="help-block ui-helper-hidden"></p>
        </div>
    </div>


    <div class="control-group">
        <div>
            <span class="control-label label label-inverse">
                Login
            </span>
        </div>

        <div class="controls">
            <g:textField name="login" maxlength="15" class=" required" value="${usroInstance?.login}"/>
            <span class="mandatory">*</span>

            <p class="help-block ui-helper-hidden"></p>
        </div>
    </div>


    <div class="control-group">
        <div>
            <span class="control-label label label-inverse">
                Password
            </span>
        </div>

        <div class="controls">
            <g:field type="password" name="password" maxlength="64" class=" required" value="${usroInstance?.password}"/>
            <span class="mandatory">*</span>

            <p class="help-block ui-helper-hidden"></p>
        </div>
    </div>


    <div class="control-group">
        <div>
            <span class="control-label label label-inverse">
                Verificar Password
            </span>
        </div>

        <div class="controls">
            <g:field type="password" name="passwordVerif" equalTo="#password" maxlength="64" class=" required" value="${usroInstance?.password}"/>
            <span class="mandatory">*</span>

            <p class="help-block ui-helper-hidden"></p>
        </div>
    </div>


    <div class="control-group">
        <div>
            <span class="control-label label label-inverse">
                Observaciones
            </span>
        </div>

        <div class="controls">
            <g:textArea name="observaciones" cols="40" rows="5" maxlength="255" class="" value="${usroInstance?.observaciones}"/>

            <p class="help-block ui-helper-hidden"></p>
        </div>
    </div>


    <div class="control-group">
        <div>
            <span class="control-label label label-inverse">
                Activo
            </span>
        </div>

        <div class="controls">
            <g:field type="number" name="activo" class=" required" value="${fieldValue(bean: usroInstance, field: 'activo')}"/>
            <span class="mandatory">*</span>

            <p class="help-block ui-helper-hidden"></p>
        </div>
    </div>

</g:form>

<script type="text/javascript">
    $("#frmSave-Usro").validate({
        errorPlacement : function (error, element) {
            element.parent().find(".help-block").html(error).show();
        },
        success        : function (label) {
            label.parent().hide();
        },
        errorClass     : "label label-important",
        submitHandler  : function (form) {
            $(".btn-success").replaceWith(spinner);
            form.submit();
        },
        rules          : {
            login : {
                remote : {
                    url  : "${createLink(action:'checkUniqueUser')}",
                    type : "post",
                    data : {
                        id   : "${usroInstance?.id}",
                        user : $("#login").val()
                    }
                }
            }
        },
        messages       : {
            login : {
                remote : "Login ya utilizado"
            }
        }
    });

    $("input").keyup(function (ev) {
        if (ev.keyCode == 13) {
            submitForm($(".btn-success"));
        }
    });
</script>
