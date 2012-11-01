
<%@ page import="elyon.GestionTelefonica" %>

<div id="create-GestionTelefonica" class="span" role="main">
    <g:form class="form-horizontal" name="frmSave-GestionTelefonica" action="save">
        <g:hiddenField name="id" value="${gestionTelefonicaInstance?.id}"/>
                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Estado Llamada
                </span>
            </div>

            <div class="controls">
                <g:select id="estadoLlamada" name="estadoLlamada.id" from="${elyon.EstadoLlamada.list()}" optionKey="id" class="many-to-one  required" value="${gestionTelefonicaInstance?.estadoLlamada?.id}"/>
                <span class="mandatory">*</span>
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Lote
                </span>
            </div>

            <div class="controls">
                <g:select id="lote" name="lote.id" from="${elyon.Lote.list()}" optionKey="id" class="many-to-one  required" value="${gestionTelefonicaInstance?.lote?.id}"/>
                <span class="mandatory">*</span>
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Telefono
                </span>
            </div>

            <div class="controls">
                <g:textField name="telefono" class=" required" value="${gestionTelefonicaInstance?.telefono}"/>
                <span class="mandatory">*</span>
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Fecha
                </span>
            </div>

            <div class="controls">
                <elm:datepicker name="fecha" class=" required" value="${gestionTelefonicaInstance?.fecha}"/>

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
                <g:textField name="observaciones" class="" value="${gestionTelefonicaInstance?.observaciones}"/>
                
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
    </g:form>

<script type="text/javascript">
    $("#frmSave-GestionTelefonica").validate({
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
