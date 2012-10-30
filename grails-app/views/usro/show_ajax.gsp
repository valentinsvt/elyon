
<%@ page import="elyon.seguridad.Usro" %>

<div id="show-usro" class="span5" role="main">

    <form class="form-horizontal">
    
    <g:if test="${usroInstance?.cedula}">
        <div class="control-group">
            <div>
                <span id="cedula-label" class="control-label label label-inverse">
                    Cedula
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="cedula-label">
                    <g:fieldValue bean="${usroInstance}" field="cedula"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${usroInstance?.nombre}">
        <div class="control-group">
            <div>
                <span id="nombre-label" class="control-label label label-inverse">
                    Nombre
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="nombre-label">
                    <g:fieldValue bean="${usroInstance}" field="nombre"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${usroInstance?.apellido}">
        <div class="control-group">
            <div>
                <span id="apellido-label" class="control-label label label-inverse">
                    Apellido
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="apellido-label">
                    <g:fieldValue bean="${usroInstance}" field="apellido"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${usroInstance?.fechaNacimiento}">
        <div class="control-group">
            <div>
                <span id="fechaNacimiento-label" class="control-label label label-inverse">
                    Fecha Nacimiento
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="fechaNacimiento-label">
                    <g:formatDate date="${usroInstance?.fechaNacimiento}" />
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${usroInstance?.fechaInicio}">
        <div class="control-group">
            <div>
                <span id="fechaInicio-label" class="control-label label label-inverse">
                    Fecha Inicio
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="fechaInicio-label">
                    <g:formatDate date="${usroInstance?.fechaInicio}" />
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${usroInstance?.fechaFin}">
        <div class="control-group">
            <div>
                <span id="fechaFin-label" class="control-label label label-inverse">
                    Fecha Fin
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="fechaFin-label">
                    <g:formatDate date="${usroInstance?.fechaFin}" />
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${usroInstance?.login}">
        <div class="control-group">
            <div>
                <span id="login-label" class="control-label label label-inverse">
                    Login
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="login-label">
                    <g:fieldValue bean="${usroInstance}" field="login"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${usroInstance?.password}">
        <div class="control-group">
            <div>
                <span id="password-label" class="control-label label label-inverse">
                    Password
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="password-label">
                    <g:fieldValue bean="${usroInstance}" field="password"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${usroInstance?.observaciones}">
        <div class="control-group">
            <div>
                <span id="observaciones-label" class="control-label label label-inverse">
                    Observaciones
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="observaciones-label">
                    <g:fieldValue bean="${usroInstance}" field="observaciones"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${usroInstance?.activo}">
        <div class="control-group">
            <div>
                <span id="activo-label" class="control-label label label-inverse">
                    Activo
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="activo-label">
                    <g:fieldValue bean="${usroInstance}" field="activo"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    </form>
</div>
