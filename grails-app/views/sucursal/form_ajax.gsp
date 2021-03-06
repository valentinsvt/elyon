
<%@ page import="elyon.Sucursal" %>

<div id="create-Sucursal" class="span" role="main">
    <g:form class="form-horizontal" name="frmSave-Sucursal" action="save">
        <g:hiddenField name="id" value="${sucursalInstance?.id}"/>
                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Ciudad
                </span>
            </div>

            <div class="controls">
                <g:select id="ciudad" name="ciudad.id" from="${elyon.Ciudad.list()}" optionKey="id" class="many-to-one " value="${sucursalInstance?.ciudad?.id}" noSelection="['null': '']"/>
                
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
                <g:textField name="codigo" maxlength="3" class=" required" value="${sucursalInstance?.codigo}"/>
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
                <g:textField name="descripcion" maxlength="100" class="" value="${sucursalInstance?.descripcion}"/>
                
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
    </g:form>

<script type="text/javascript">
    $("#frmSave-Sucursal").validate({
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
