
<%@ page import="elyon.EstadoLlamada" %>

<div id="show-estadoLlamada" class="span5" role="main">

    <form class="form-horizontal">
    
    <g:if test="${estadoLlamadaInstance?.codigo}">
        <div class="control-group">
            <div>
                <span id="codigo-label" class="control-label label label-inverse">
                    Codigo
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="codigo-label">
                    <g:fieldValue bean="${estadoLlamadaInstance}" field="codigo"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${estadoLlamadaInstance?.descripcion}">
        <div class="control-group">
            <div>
                <span id="descripcion-label" class="control-label label label-inverse">
                    Descripcion
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="descripcion-label">
                    <g:fieldValue bean="${estadoLlamadaInstance}" field="descripcion"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    </form>
</div>
