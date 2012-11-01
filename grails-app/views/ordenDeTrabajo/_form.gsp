<%@ page import="elyon.OrdenDeTrabajo" %>



<div class="fieldcontain ${hasErrors(bean: ordenDeTrabajoInstance, field: 'campana', 'error')} required">
    <label for="campana">
        Campaña
        <span class="required-indicator">*</span>
    </label>
    <g:select id="campana" name="campana.id" from="${elyon.Campana.list()}" optionKey="id" class="many-to-one  required"
              value="${ordenDeTrabajoInstance?.campana?.id}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ordenDeTrabajoInstance, field: 'usro', 'error')} required">
    <label for="usro">
        <g:message code="ordenDeTrabajo.usro.label" default="Usro"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="usro" name="usro.id" from="${elyon.seguridad.Usro.list()}" optionKey="id"
              class="many-to-one  required" value="${ordenDeTrabajoInstance?.usro?.id}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ordenDeTrabajoInstance, field: 'numero', 'error')} required">
    <label for="numero">
        <g:message code="ordenDeTrabajo.numero.label" default="Numero"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field type="number" name="numero" class=" required"
             value="${fieldValue(bean: ordenDeTrabajoInstance, field: 'numero')}"/>
</div>


