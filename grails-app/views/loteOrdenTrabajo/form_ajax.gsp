
<%@ page import="elyon.LoteOrdenTrabajo" %>

<div id="create-LoteOrdenTrabajo" class="span" role="main">
    <g:form class="form-horizontal" name="frmSave-LoteOrdenTrabajo" action="save">
        <g:hiddenField name="id" value="${loteOrdenTrabajoInstance?.id}"/>
                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Lote
                </span>
            </div>

            <div class="controls">
                <g:select id="lote" name="lote.id" from="${elyon.Lote.list()}" optionKey="id" class="many-to-one  required" value="${loteOrdenTrabajoInstance?.lote?.id}"/>
                <span class="mandatory">*</span>
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Orden De Trabajo
                </span>
            </div>

            <div class="controls">
                <g:select id="ordenDeTrabajo" name="ordenDeTrabajo.id" from="${elyon.OrdenDeTrabajo.list()}" optionKey="id" class="many-to-one  required" value="${loteOrdenTrabajoInstance?.ordenDeTrabajo?.id}"/>
                <span class="mandatory">*</span>
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Estado Gestion
                </span>
            </div>

            <div class="controls">
                <g:select id="estadoGestion" name="estadoGestion.id" from="${elyon.EstadoGestion.list()}" optionKey="id" class="many-to-one  required" value="${loteOrdenTrabajoInstance?.estadoGestion?.id}"/>
                <span class="mandatory">*</span>
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
    </g:form>

<script type="text/javascript">
    $("#frmSave-LoteOrdenTrabajo").validate({
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
