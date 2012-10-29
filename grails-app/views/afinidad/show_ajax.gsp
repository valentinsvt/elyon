
<%@ page import="elyon.Afinidad" %>

<div id="show-afinidad" class="span5" role="main">

    <form class="form-horizontal">
    
    <g:if test="${afinidadInstance?.bins}">
        <div class="control-group">
            <div>
                <span id="bins-label" class="control-label label label-inverse">
                    Bins
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="bins-label">
        %{--<g:link controller="bins" action="show" id="${afinidadInstance?.bins?.id}">--}%
                    ${afinidadInstance?.bins?.encodeAsHTML()}
        %{--</g:link>--}%
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${afinidadInstance?.codigo}">
        <div class="control-group">
            <div>
                <span id="codigo-label" class="control-label label label-inverse">
                    Codigo
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="codigo-label">
                    <g:fieldValue bean="${afinidadInstance}" field="codigo"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${afinidadInstance?.descripcion}">
        <div class="control-group">
            <div>
                <span id="descripcion-label" class="control-label label label-inverse">
                    Descripcion
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="descripcion-label">
                    <g:fieldValue bean="${afinidadInstance}" field="descripcion"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    </form>
</div>
