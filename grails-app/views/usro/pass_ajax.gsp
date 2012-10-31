<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 10/30/12
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>

<g:form class="form-horizontal" name="frmSave-Usro" action="savePass">
    <g:hiddenField name="id" value="${usroInstance?.id}"/>
    <div class="control-group">
        <div>
            <span class="control-label label label-inverse">
                Usuario
            </span>
        </div>

        <div class="controls">
            ${usroInstance.login}
        </div>
    </div>
    <g:if test="${usroInstance?.id}">
        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Password actual
                </span>
            </div>

            <div class="controls">
                <g:field type="password" name="passwordAct" maxlength="64" class=" required"/>
                <span class="mandatory">*</span>

                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>

        <div class="control-group">
            <div>
                <span class="control-label label label-inverse">
                    Nuevo password
                </span>
            </div>

            <div class="controls">
                <g:field type="password" name="password" maxlength="64" class=" required"/>
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
                <g:field type="password" name="passwordVerif" equalTo="#password" maxlength="64" class=" required"/>
                <span class="mandatory">*</span>

                <p class="help-block ui-helper-hidden"></p>
            </div>
        </div>
    </g:if>

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
            passwordAct : {
                remote : {
                    url  : "${createLink(action:'checkUserPass')}",
                    type : "post",
                    data : {
                        id : "${usroInstance?.id}"
                    }
                }
            }
        },
        messages       : {
            passwordAct : {
                remote : "El password actual no coincide"
            }
        }
    });

    $("input").keyup(function (ev) {
        if (ev.keyCode == 13) {
            submitForm($(".btn-success"));
        }
    });
</script>
