
<%@ page import="elyon.EstadoGestion" %>

<div id="show-estadoGestion" class="span5" role="main">

    <form class="form-horizontal">
    
    <g:if test="${estadoGestionInstance?.codigo}">
        <div class="control-group">
            <div>
                <span id="codigo-label" class="control-label label label-inverse">
                    Codigo
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="codigo-label">
                    <g:fieldValue bean="${estadoGestionInstance}" field="codigo"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${estadoGestionInstance?.descripcion}">
        <div class="control-group">
            <div>
                <span id="descripcion-label" class="control-label label label-inverse">
                    Descripcion
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="descripcion-label">
                    <g:fieldValue bean="${estadoGestionInstance}" field="descripcion"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${estadoGestionInstance?.lista}">
        <div class="control-group">
            <div>
                <span id="lista-label" class="control-label label label-inverse">
                    Lista
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="lista-label">
                    <g:fieldValue bean="${estadoGestionInstance}" field="lista"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    </form>
</div>
