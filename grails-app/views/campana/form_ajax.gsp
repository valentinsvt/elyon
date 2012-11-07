
<%@ page import="elyon.Campana" %>

<div id="create-Campana" class="span" role="main">
    <g:form class="form-horizontal" name="frmSave-Campana" action="procesa" controller="lote" enctype="multipart/form-data">
        <g:hiddenField name="id" value="${campanaInstance?.id}"/>
                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Descripción
                </span>
            </div>

            <div class="controls">
                <g:textField name="descripcion" maxlength="63" class=" required" value="${campanaInstance?.descripcion}"/>
                <span class="mandatory">*</span>
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse " style="height:25px;">
                    Lote
                </span>
            </div>

            <div class="controls">
                <input type="file" name="archivo">
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
    </g:form>

<script type="text/javascript">
    $("#frmSave-Campana").validate({
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
