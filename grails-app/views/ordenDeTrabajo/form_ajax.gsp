
<%@ page import="elyon.OrdenDeTrabajo" %>

<div id="create-OrdenDeTrabajo" class="span" role="main">
    <g:form class="form-horizontal" name="frmSave-OrdenDeTrabajo" action="procesa" controller="lote" enctype="multipart/form-data">
        <g:hiddenField name="id" value="${ordenDeTrabajoInstance?.id}"/>
                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Campaña
                </span>
            </div>

            <div class="controls">
                <g:select id="campana" name="campana.id" from="${elyon.Campana.list()}" optionKey="id" class="many-to-one  required" value="${ordenDeTrabajoInstance?.campana?.id}"/>
                <span class="mandatory">*</span>
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Usro
                </span>
            </div>

            <div class="controls">
                <g:select id="usro" name="usro.id" from="${elyon.seguridad.Usro.list()}" optionKey="id" class="many-to-one  required" value="${ordenDeTrabajoInstance?.usro?.id}"/>
                <span class="mandatory">*</span>
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Numero
                </span>
            </div>

            <div class="controls">
                <g:field type="number" name="numero" class=" required" value="${fieldValue(bean: ordenDeTrabajoInstance, field: 'numero')}"/>
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
    $("#frmSave-OrdenDeTrabajo").validate({
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
