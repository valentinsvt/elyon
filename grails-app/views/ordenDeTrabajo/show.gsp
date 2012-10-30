<%@ page import="elyon.OrdenDeTrabajo" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'ordenDeTrabajo.label', default: 'OrdenDeTrabajo')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-ordenDeTrabajo" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                     default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-ordenDeTrabajo" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list ordenDeTrabajo">

        <g:if test="${ordenDeTrabajoInstance?.campana}">
            <li class="fieldcontain">
                <span id="campana-label" class="property-label"><g:message code="ordenDeTrabajo.campana.label"
                                                                           default="Campana"/></span>

                <span class="property-value" aria-labelledby="campana-label"><g:link controller="campana" action="show"
                                                                                     id="${ordenDeTrabajoInstance?.campana?.id}">${ordenDeTrabajoInstance?.campana?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

        <g:if test="${ordenDeTrabajoInstance?.usro}">
            <li class="fieldcontain">
                <span id="usro-label" class="property-label"><g:message code="ordenDeTrabajo.usro.label"
                                                                        default="Usro"/></span>

                <span class="property-value" aria-labelledby="usro-label"><g:link controller="usro" action="show"
                                                                                  id="${ordenDeTrabajoInstance?.usro?.id}">${ordenDeTrabajoInstance?.usro?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

        <g:if test="${ordenDeTrabajoInstance?.numero}">
            <li class="fieldcontain">
                <span id="numero-label" class="property-label"><g:message code="ordenDeTrabajo.numero.label"
                                                                          default="Numero"/></span>

                <span class="property-value" aria-labelledby="numero-label"><g:fieldValue
                        bean="${ordenDeTrabajoInstance}" field="numero"/></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${ordenDeTrabajoInstance?.id}"/>
            <g:link class="edit" action="edit" id="${ordenDeTrabajoInstance?.id}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
