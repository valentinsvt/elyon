<%@ page import="elyon.seguridad.Persona" %>

<div id="create-Persona" class="span" role="main">
<g:form class="form-horizontal" name="frmSave-Persona" action="save">
    <g:hiddenField name="id" value="${personaInstance?.id}"/>

    <div class="control-group">
        <div>
            <span class="control-label label label-inverse">
                Cedula
            </span>
        </div>

        <div class="controls">
            <g:textField name="cedula" maxlength="10" class="" value="${personaInstance?.cedula}"/>

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
            <g:textField name="nombre" maxlength="30" class="" value="${personaInstance?.nombre}"/>

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
            <g:textField name="apellido" maxlength="30" class="" value="${personaInstance?.apellido}"/>

            <p class="help-block ui-helper-hidden"></p>
        </div>
    </div>

    <div class="control-group">
        <div>
            <span class="control-label label label-inverse">
                Codigo
            </span>
        </div>

        <div class="controls">
            <g:field type="number" name="codigo" class=" required" value="${fieldValue(bean: personaInstance, field: 'codigo')}"/>
            <span class="mandatory">*</span>

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
            <elm:datepicker name="fechaNacimiento" class="" value="${personaInstance?.fechaNacimiento}"/>

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
            <elm:datepicker name="fechaInicio" class="" value="${personaInstance?.fechaInicio}"/>


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
            <elm:datepicker name="fechaFin" class="" value="${personaInstance?.fechaFin}"/>


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
            <g:textField name="cargo" maxlength="50" class="" value="${personaInstance?.cargo}"/>

            <p class="help-block ui-helper-hidden"></p>
        </div>
    </div>

</g:form>

<script type="text/javascript">
    $("#frmSave-Persona").validate({
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
        }
    });

    $("input").keyup(function (ev) {
        if (ev.keyCode == 13) {
            submitForm($(".btn-success"));
        }
    });
</script>
