
<%@ page import="elyon.EstadoGestion" %>

<div id="create-EstadoGestion" class="span" role="main">
    <g:form class="form-horizontal" name="frmSave-EstadoGestion" action="save">
        <g:hiddenField name="id" value="${estadoGestionInstance?.id}"/>
                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Codigo
                </span>
            </div>

            <div class="controls">
                <g:textField name="codigo" maxlength="5" class=" required" value="${estadoGestionInstance?.codigo}"/>
                <span class="mandatory">*</span>
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Descripcion
                </span>
            </div>

            <div class="controls">
                <g:textField name="descripcion" maxlength="31" class=" required" value="${estadoGestionInstance?.descripcion}"/>
                <span class="mandatory">*</span>
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Lista
                </span>
            </div>

            <div class="controls">
                <g:textField name="lista" maxlength="1" class=" required" value="${estadoGestionInstance?.lista}"/>
                <span class="mandatory">*</span>
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
    </g:form>

<script type="text/javascript">
    $("#frmSave-EstadoGestion").validate({
        errorPlacement : function (error, element) {
            element.parent().find(".help-block").html(error).show();
        },
        success        : function (label) {
            label.parent().hide();
        },
        errorClass     : "label label-important",
        submitHandler  : function(form) {
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
