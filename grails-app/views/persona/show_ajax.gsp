
<%@ page import="elyon.seguridad.Persona" %>

<div id="show-persona" class="span5" role="main">

    <form class="form-horizontal">
    
    <g:if test="${personaInstance?.cedula}">
        <div class="control-group">
            <div>
                <span id="cedula-label" class="control-label label label-inverse">
                    Cedula
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="cedula-label">
                    <g:fieldValue bean="${personaInstance}" field="cedula"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${personaInstance?.nombre}">
        <div class="control-group">
            <div>
                <span id="nombre-label" class="control-label label label-inverse">
                    Nombre
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="nombre-label">
                    <g:fieldValue bean="${personaInstance}" field="nombre"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${personaInstance?.apellido}">
        <div class="control-group">
            <div>
                <span id="apellido-label" class="control-label label label-inverse">
                    Apellido
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="apellido-label">
                    <g:fieldValue bean="${personaInstance}" field="apellido"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${personaInstance?.codigo}">
        <div class="control-group">
            <div>
                <span id="codigo-label" class="control-label label label-inverse">
                    Codigo
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="codigo-label">
                    <g:fieldValue bean="${personaInstance}" field="codigo"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${personaInstance?.fechaNacimiento}">
        <div class="control-group">
            <div>
                <span id="fechaNacimiento-label" class="control-label label label-inverse">
                    Fecha Nacimiento
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="fechaNacimiento-label">
                    <g:formatDate date="${personaInstance?.fechaNacimiento}" />
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${personaInstance?.fechaInicio}">
        <div class="control-group">
            <div>
                <span id="fechaInicio-label" class="control-label label label-inverse">
                    Fecha Inicio
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="fechaInicio-label">
                    <g:formatDate date="${personaInstance?.fechaInicio}" />
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${personaInstance?.fechaFin}">
        <div class="control-group">
            <div>
                <span id="fechaFin-label" class="control-label label label-inverse">
                    Fecha Fin
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="fechaFin-label">
                    <g:formatDate date="${personaInstance?.fechaFin}" />
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${personaInstance?.cargo}">
        <div class="control-group">
            <div>
                <span id="cargo-label" class="control-label label label-inverse">
                    Cargo
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="cargo-label">
                    <g:fieldValue bean="${personaInstance}" field="cargo"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    </form>
</div>
