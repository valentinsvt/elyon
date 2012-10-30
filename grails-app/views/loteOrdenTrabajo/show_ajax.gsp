
<%@ page import="elyon.LoteOrdenTrabajo" %>

<div id="show-loteOrdenTrabajo" class="span5" role="main">

    <form class="form-horizontal">
    
    <g:if test="${loteOrdenTrabajoInstance?.lote}">
        <div class="control-group">
            <div>
                <span id="lote-label" class="control-label label label-inverse">
                    Lote
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="lote-label">
        %{--<g:link controller="lote" action="show" id="${loteOrdenTrabajoInstance?.lote?.id}">--}%
                    ${loteOrdenTrabajoInstance?.lote?.encodeAsHTML()}
        %{--</g:link>--}%
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteOrdenTrabajoInstance?.ordenDeTrabajo}">
        <div class="control-group">
            <div>
                <span id="ordenDeTrabajo-label" class="control-label label label-inverse">
                    Orden De Trabajo
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="ordenDeTrabajo-label">
        %{--<g:link controller="ordenDeTrabajo" action="show" id="${loteOrdenTrabajoInstance?.ordenDeTrabajo?.id}">--}%
                    ${loteOrdenTrabajoInstance?.ordenDeTrabajo?.encodeAsHTML()}
        %{--</g:link>--}%
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteOrdenTrabajoInstance?.estadoGestion}">
        <div class="control-group">
            <div>
                <span id="estadoGestion-label" class="control-label label label-inverse">
                    Estado Gestion
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="estadoGestion-label">
        %{--<g:link controller="estadoGestion" action="show" id="${loteOrdenTrabajoInstance?.estadoGestion?.id}">--}%
                    ${loteOrdenTrabajoInstance?.estadoGestion?.encodeAsHTML()}
        %{--</g:link>--}%
                </span>
        
            </div>
        </div>
    </g:if>
    
    </form>
</div>
