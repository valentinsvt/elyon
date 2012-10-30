
<%@ page import="elyon.Sucursal" %>

<div id="show-sucursal" class="span5" role="main">

    <form class="form-horizontal">
    
    <g:if test="${sucursalInstance?.ciudad}">
        <div class="control-group">
            <div>
                <span id="ciudad-label" class="control-label label label-inverse">
                    Ciudad
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="ciudad-label">
        %{--<g:link controller="ciudad" action="show" id="${sucursalInstance?.ciudad?.id}">--}%
                    ${sucursalInstance?.ciudad?.encodeAsHTML()}
        %{--</g:link>--}%
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${sucursalInstance?.codigo}">
        <div class="control-group">
            <div>
                <span id="codigo-label" class="control-label label label-inverse">
                    Codigo
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="codigo-label">
                    <g:fieldValue bean="${sucursalInstance}" field="codigo"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${sucursalInstance?.descripcion}">
        <div class="control-group">
            <div>
                <span id="descripcion-label" class="control-label label label-inverse">
                    Descripcion
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="descripcion-label">
                    <g:fieldValue bean="${sucursalInstance}" field="descripcion"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    </form>
</div>
