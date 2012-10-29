
<%@ page import="elyon.RangoIngresos" %>

<div id="show-rangoIngresos" class="span5" role="main">

    <form class="form-horizontal">
    
    <g:if test="${rangoIngresosInstance?.codigo}">
        <div class="control-group">
            <div>
                <span id="codigo-label" class="control-label label label-inverse">
                    Codigo
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="codigo-label">
                    <g:fieldValue bean="${rangoIngresosInstance}" field="codigo"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${rangoIngresosInstance?.descripcion}">
        <div class="control-group">
            <div>
                <span id="descripcion-label" class="control-label label label-inverse">
                    Descripcion
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="descripcion-label">
                    <g:fieldValue bean="${rangoIngresosInstance}" field="descripcion"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    <g:if test="${rangoIngresosInstance?.rango}">
        <div class="control-group">
            <div>
                <span id="rango-label" class="control-label label label-inverse">
                    Rango
                </span>
            </div>
            <div class="controls">
        
                <span aria-labelledby="rango-label">
                    <g:fieldValue bean="${rangoIngresosInstance}" field="rango"/>
                </span>
        
            </div>
        </div>
    </g:if>
    
    </form>
</div>
