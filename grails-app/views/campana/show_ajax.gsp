
<%@ page import="elyon.Campana" %>

<div id="show-campana" class="span5" role="main">

    <form class="form-horizontal">
    
    <g:if test="${campanaInstance?.descripcion}">
        <div class="control-group">
            <div>
                <span id="descripcion-label" class="control-label label label-inverse">
                    Descripcion
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="descripcion-label">
                    <g:fieldValue bean="${campanaInstance}" field="descripcion"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    </form>
</div>
