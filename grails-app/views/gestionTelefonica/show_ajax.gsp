
<%@ page import="elyon.GestionTelefonica" %>

<div id="show-gestionTelefonica" class="span5" role="main">

    <form class="form-horizontal">
    
    <g:if test="${gestionTelefonicaInstance?.estadoLlamada}">
        <div class="control-group">
            <div>
                <span id="estadoLlamada-label" class="control-label label label-inverse">
                    Estado Llamada
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="estadoLlamada-label">
        %{--<g:link controller="estadoLlamada" action="show" id="${gestionTelefonicaInstance?.estadoLlamada?.id}">--}%
                    ${gestionTelefonicaInstance?.estadoLlamada?.encodeAsHTML()}
        %{--</g:link>--}%
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${gestionTelefonicaInstance?.lote}">
        <div class="control-group">
            <div>
                <span id="lote-label" class="control-label label label-inverse">
                    Lote
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="lote-label">
        %{--<g:link controller="lote" action="show" id="${gestionTelefonicaInstance?.lote?.id}">--}%
                    ${gestionTelefonicaInstance?.lote?.encodeAsHTML()}
        %{--</g:link>--}%
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${gestionTelefonicaInstance?.telefono}">
        <div class="control-group">
            <div>
                <span id="telefono-label" class="control-label label label-inverse">
                    Telefono
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="telefono-label">
                    <g:fieldValue bean="${gestionTelefonicaInstance}" field="telefono"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${gestionTelefonicaInstance?.fecha}">
        <div class="control-group">
            <div>
                <span id="fecha-label" class="control-label label label-inverse">
                    Fecha
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="fecha-label">
                    <g:formatDate date="${gestionTelefonicaInstance?.fecha}" />
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${gestionTelefonicaInstance?.observaciones}">
        <div class="control-group">
            <div>
                <span id="observaciones-label" class="control-label label label-inverse">
                    Observaciones
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="observaciones-label">
                    <g:fieldValue bean="${gestionTelefonicaInstance}" field="observaciones"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    </form>
</div>
