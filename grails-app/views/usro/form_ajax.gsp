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
            <g:textField name="cedula" maxlength="10" class=" required" value="${usroInstance?.cedula}"/>
            <span class="mandatory">*</span>

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
            <g:textField name="nombre" maxlength="30" class=" required" value="${usroInstance?.nombre}"/>
            <span class="mandatory">*</span>

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
            <g:textField name="apellido" maxlength="30" class=" required" value="${usroInstance?.apellido}"/>
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
                Login
            </span>
        </div>

        <div class="controls">
            <g:textField name="login" maxlength="15" class=" required" value="${usroInstance?.login}"/>
            <span class="mandatory">*</span>

            <p class="help-block ui-helper-hidden"></p>
        </div>
    </div>

    <g:if test="${!usroInstance?.id}">
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
    </g:if>

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
            %{--<g:field type="number" name="activo" class=" required" value="${fieldValue(bean: usroInstance, field: 'activo')}"/>--}%

            <g:radioGroup name="activo" values="['1', '0']" labels="['Sí', 'No']" value="${usroInstance?.id ? usroInstance.activo : '0'}">
                ${it.label} ${it.radio}
            </g:radioGroup>

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
                        id   : "${usroInstance?.id}"
                    }
                }
            },
            cedula : {
                remote : {
                    url  : "${createLink(action:'checkUniqueCi')}",
                    type : "post",
                    data : {
                        id   : "${usroInstance?.id}"
                    }
                }
            }
        },
        messages       : {
            login : {
                remote : "Seleccione otro login"
            },
            cedula : {
                remote : "Ingrese otra cédula"
            }
        }
    });

    $("input").keyup(function (ev) {
        if (ev.keyCode == 13) {
            submitForm($(".btn-success"));
        }
    });
</script>
