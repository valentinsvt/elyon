
<%@ page import="elyon.Parroquia" %>

<div id="show-parroquia" class="span5" role="main">

    <form class="form-horizontal">
    
    <g:if test="${parroquiaInstance?.ciudad}">
        <div class="control-group">
            <div>
                <span id="ciudad-label" class="control-label label label-inverse">
                    Ciudad
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="ciudad-label">
        %{--<g:link controller="ciudad" action="show" id="${parroquiaInstance?.ciudad?.id}">--}%
                    ${parroquiaInstance?.ciudad?.encodeAsHTML()}
        %{--</g:link>--}%
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${parroquiaInstance?.codigo}">
        <div class="control-group">
            <div>
                <span id="codigo-label" class="control-label label label-inverse">
                    Codigo
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="codigo-label">
                    <g:fieldValue bean="${parroquiaInstance}" field="codigo"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${parroquiaInstance?.descripcion}">
        <div class="control-group">
            <div>
                <span id="descripcion-label" class="control-label label label-inverse">
                    Descripcion
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="descripcion-label">
                    <g:fieldValue bean="${parroquiaInstance}" field="descripcion"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    </form>
</div>
