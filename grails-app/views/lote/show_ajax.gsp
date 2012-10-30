
<%@ page import="elyon.Lote" %>

<div id="show-lote" class="span5" role="main">

    <form class="form-horizontal">
    
    <g:if test="${loteInstance?.cedula}">
        <div class="control-group">
            <div>
                <span id="cedula-label" class="control-label label label-inverse">
                    Cedula
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="cedula-label">
                    <g:fieldValue bean="${loteInstance}" field="cedula"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.nombre}">
        <div class="control-group">
            <div>
                <span id="nombre-label" class="control-label label label-inverse">
                    Nombre
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="nombre-label">
                    <g:fieldValue bean="${loteInstance}" field="nombre"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.direccion1}">
        <div class="control-group">
            <div>
                <span id="direccion1-label" class="control-label label label-inverse">
                    Direccion1
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="direccion1-label">
                    <g:fieldValue bean="${loteInstance}" field="direccion1"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.direccion2}">
        <div class="control-group">
            <div>
                <span id="direccion2-label" class="control-label label label-inverse">
                    Direccion2
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="direccion2-label">
                    <g:fieldValue bean="${loteInstance}" field="direccion2"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.telefono1}">
        <div class="control-group">
            <div>
                <span id="telefono1-label" class="control-label label label-inverse">
                    Telefono1
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="telefono1-label">
                    <g:fieldValue bean="${loteInstance}" field="telefono1"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.telefono2}">
        <div class="control-group">
            <div>
                <span id="telefono2-label" class="control-label label label-inverse">
                    Telefono2
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="telefono2-label">
                    <g:fieldValue bean="${loteInstance}" field="telefono2"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.telefono3}">
        <div class="control-group">
            <div>
                <span id="telefono3-label" class="control-label label label-inverse">
                    Telefono3
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="telefono3-label">
                    <g:fieldValue bean="${loteInstance}" field="telefono3"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.telefono4}">
        <div class="control-group">
            <div>
                <span id="telefono4-label" class="control-label label label-inverse">
                    Telefono4
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="telefono4-label">
                    <g:fieldValue bean="${loteInstance}" field="telefono4"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.telefono5}">
        <div class="control-group">
            <div>
                <span id="telefono5-label" class="control-label label label-inverse">
                    Telefono5
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="telefono5-label">
                    <g:fieldValue bean="${loteInstance}" field="telefono5"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.telefono6}">
        <div class="control-group">
            <div>
                <span id="telefono6-label" class="control-label label label-inverse">
                    Telefono6
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="telefono6-label">
                    <g:fieldValue bean="${loteInstance}" field="telefono6"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.telefonoTrabajoAnterior}">
        <div class="control-group">
            <div>
                <span id="telefonoTrabajoAnterior-label" class="control-label label label-inverse">
                    Telefono Trabajo Anterior
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="telefonoTrabajoAnterior-label">
                    <g:fieldValue bean="${loteInstance}" field="telefonoTrabajoAnterior"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.ciudad}">
        <div class="control-group">
            <div>
                <span id="ciudad-label" class="control-label label label-inverse">
                    Ciudad
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="ciudad-label">
                    <g:fieldValue bean="${loteInstance}" field="ciudad"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.tipoTarjeta}">
        <div class="control-group">
            <div>
                <span id="tipoTarjeta-label" class="control-label label label-inverse">
                    Tipo Tarjeta
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="tipoTarjeta-label">
                    <g:fieldValue bean="${loteInstance}" field="tipoTarjeta"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.cupo1}">
        <div class="control-group">
            <div>
                <span id="cupo1-label" class="control-label label label-inverse">
                    Cupo1
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="cupo1-label">
                    <g:fieldValue bean="${loteInstance}" field="cupo1"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.codigo}">
        <div class="control-group">
            <div>
                <span id="codigo-label" class="control-label label label-inverse">
                    Codigo
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="codigo-label">
                    <g:fieldValue bean="${loteInstance}" field="codigo"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.cupo2}">
        <div class="control-group">
            <div>
                <span id="cupo2-label" class="control-label label label-inverse">
                    Cupo2
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="cupo2-label">
                    <g:fieldValue bean="${loteInstance}" field="cupo2"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.tipoCliente}">
        <div class="control-group">
            <div>
                <span id="tipoCliente-label" class="control-label label label-inverse">
                    Tipo Cliente
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="tipoCliente-label">
                    <g:fieldValue bean="${loteInstance}" field="tipoCliente"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.observaciones}">
        <div class="control-group">
            <div>
                <span id="observaciones-label" class="control-label label label-inverse">
                    Observaciones
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="observaciones-label">
                    <g:fieldValue bean="${loteInstance}" field="observaciones"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${loteInstance?.campana}">
        <div class="control-group">
            <div>
                <span id="campana-label" class="control-label label label-inverse">
                    Campana
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="campana-label">
        %{--<g:link controller="campana" action="show" id="${loteInstance?.campana?.id}">--}%
                    ${loteInstance?.campana?.encodeAsHTML()}
        %{--</g:link>--}%
                </span>
        
            </div>
        </div>
    </g:if>
    
    </form>
</div>
