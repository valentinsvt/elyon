
<%@ page import="elyon.Lote" %>

<div id="create-Lote" class="span" role="main">
    <g:form class="form-horizontal" name="frmSave-Lote" action="save">
        <g:hiddenField name="id" value="${loteInstance?.id}"/>
                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Cedula
                </span>
            </div>

            <div class="controls">
                <g:textField name="cedula" maxlength="10" class="" value="${loteInstance?.cedula}"/>
                
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
                <g:textField name="nombre" maxlength="63" class="" value="${loteInstance?.nombre}"/>
                
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Direccion1
                </span>
            </div>

            <div class="controls">
                <g:textField name="direccion1" maxlength="127" class="" value="${loteInstance?.direccion1}"/>
                
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Direccion2
                </span>
            </div>

            <div class="controls">
                <g:textField name="direccion2" maxlength="127" class="" value="${loteInstance?.direccion2}"/>
                
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Telefono1
                </span>
            </div>

            <div class="controls">
                <g:textField name="telefono1" maxlength="15" class="" value="${loteInstance?.telefono1}"/>
                
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Telefono2
                </span>
            </div>

            <div class="controls">
                <g:textField name="telefono2" maxlength="15" class="" value="${loteInstance?.telefono2}"/>
                
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Telefono3
                </span>
            </div>

            <div class="controls">
                <g:textField name="telefono3" maxlength="15" class="" value="${loteInstance?.telefono3}"/>
                
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Telefono4
                </span>
            </div>

            <div class="controls">
                <g:textField name="telefono4" maxlength="15" class="" value="${loteInstance?.telefono4}"/>
                
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Telefono5
                </span>
            </div>

            <div class="controls">
                <g:textField name="telefono5" maxlength="15" class="" value="${loteInstance?.telefono5}"/>
                
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Telefono6
                </span>
            </div>

            <div class="controls">
                <g:textField name="telefono6" maxlength="15" class="" value="${loteInstance?.telefono6}"/>
                
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Telefono Trabajo Anterior
                </span>
            </div>

            <div class="controls">
                <g:textField name="telefonoTrabajoAnterior" maxlength="15" class="" value="${loteInstance?.telefonoTrabajoAnterior}"/>
                
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Ciudad
                </span>
            </div>

            <div class="controls">
                <g:textField name="ciudad" maxlength="63" class="" value="${loteInstance?.ciudad}"/>
                
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Tipo Tarjeta
                </span>
            </div>

            <div class="controls">
                <g:textField name="tipoTarjeta" maxlength="15" class="" value="${loteInstance?.tipoTarjeta}"/>
                
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Cupo1
                </span>
            </div>

            <div class="controls">
                <g:field type="number" name="cupo1" class=" required" value="${fieldValue(bean: loteInstance, field: 'cupo1')}"/>
                <span class="mandatory">*</span>
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
                <g:textField name="codigo" maxlength="15" class="" value="${loteInstance?.codigo}"/>
                
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Cupo2
                </span>
            </div>

            <div class="controls">
                <g:field type="number" name="cupo2" class=" required" value="${fieldValue(bean: loteInstance, field: 'cupo2')}"/>
                <span class="mandatory">*</span>
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Tipo Cliente
                </span>
            </div>

            <div class="controls">
                <g:textField name="tipoCliente" maxlength="15" class="" value="${loteInstance?.tipoCliente}"/>
                
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
                <g:textField name="observaciones" maxlength="127" class="" value="${loteInstance?.observaciones}"/>
                
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Campana
                </span>
            </div>

            <div class="controls">
                <g:select id="campana" name="campana.id" from="${elyon.Campana.list()}" optionKey="id" class="many-to-one " value="${loteInstance?.campana?.id}" noSelection="['null': '']"/>
                
                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        

                
    </g:form>

<script type="text/javascript">
    $("#frmSave-Lote").validate({
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
