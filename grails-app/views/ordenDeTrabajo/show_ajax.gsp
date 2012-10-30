
<%@ page import="elyon.OrdenDeTrabajo" %>

<div id="show-ordenDeTrabajo" class="span5" role="main">

    <form class="form-horizontal">
    
    <g:if test="${ordenDeTrabajoInstance?.campana}">
        <div class="control-group">
            <div>
                <span id="campana-label" class="control-label label label-inverse">
                    Campana
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="campana-label">
        %{--<g:link controller="campana" action="show" id="${ordenDeTrabajoInstance?.campana?.id}">--}%
                    ${ordenDeTrabajoInstance?.campana?.encodeAsHTML()}
        %{--</g:link>--}%
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${ordenDeTrabajoInstance?.usro}">
        <div class="control-group">
            <div>
                <span id="usro-label" class="control-label label label-inverse">
                    Usro
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="usro-label">
        %{--<g:link controller="usro" action="show" id="${ordenDeTrabajoInstance?.usro?.id}">--}%
                    ${ordenDeTrabajoInstance?.usro?.encodeAsHTML()}
        %{--</g:link>--}%
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${ordenDeTrabajoInstance?.numero}">
        <div class="control-group">
            <div>
                <span id="numero-label" class="control-label label label-inverse">
                    Numero
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="numero-label">
                    <g:fieldValue bean="${ordenDeTrabajoInstance}" field="numero"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    </form>
</div>
