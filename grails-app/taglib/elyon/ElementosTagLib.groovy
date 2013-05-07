package elyon

import org.codehaus.groovy.grails.commons.DomainClassArtefactHandler
import org.springframework.beans.SimpleTypeConverter
import org.springframework.context.MessageSourceResolvable
import org.springframework.web.servlet.support.RequestContextUtils

class ElementosTagLib {
    static namespace = "elm"

    /**
     * attrs:
     *      class       clase
     *      name        name
     *      id          id (opcional, si no existe usa el mismo name)
     *      value       value (groovy Date)
     *      format      format para el Date (groovy)
     *      onClose     funcion js a ejecutarse cuando se cierra el datepicker (se usa <elm:datepicker onClose="funcion" />
     *      onSelect    funcion js a ejecutarse cuando se selecciona una fecha (se usa <elm:datepicker onSelect="funcion" />
     *      yearRange   rango de años en el select de años:  The range of years displayed in the year drop-down: either relative to today's year ("-nn:+nn"),
     *                                                       relative to the currently selected year ("c-nn:c+nn"), absolute ("nnnn:nnnn"), or combinations of these formats ("nnnn:-nn").
     *                                                       Note that this option only affects what appears in the drop-down, to restrict which dates may be selected use the minDate and/or maxDate options.
     *      minDate     fecha minima seleccionable
     *      maxDate     fecha maxima seleccionable:   The minimum selectable date. When set to null, there is no minimum.
     *                                                  Multiple types supported:
     *                                                       Date: A date object containing the minimum date.
     *                                                       Number: A number of days from today. For example 2 represents two days from today and -1 represents yesterday.
     *                                                       String: A string in the format defined by the dateFormat option, or a relative date.
     *                                                                      Relative dates must contain value and period pairs;
     *                                                                      valid periods are "y" for years, "m" for months, "w" for weeks, and "d" for days.
     *                                                                      For example, "+1m +7d" represents one month and seven days from today.
     */
    def datepicker = { attrs ->
        println "+++++" + attrs

        def str = ""
        def clase = attrs.remove("class")
        def name = attrs.remove("name")
        def id = attrs.id ? attrs.remove("id") : name
        if (id.contains(".")) {
            id = id.replaceAll("\\.", "_")
        }

        def value = attrs.remove("value")
        if (value.toString() == 'none') {
            value = null
        } else if (!value) {
            value = null
        }

        def format = attrs.format ? attrs.remove("format") : "dd-MM-yyyy"
        def formatJs = format
        formatJs = formatJs.replaceAll("M", "m")
        formatJs = formatJs.replaceAll("yyyy", "yy")

        println ">>>>" + attrs

        println value
        println format
        println g.formatDate(date: value, format: format)

        str += "<input type='text'  autocomplete='off' class='datepicker " + clase + "' name='" + name + "' id='" + id + "' value='" + g.formatDate(date: value, format: format) + "'"
        str += renderAttributes(attrs)
        str += "/>"

        def js = "<script type='text/javascript'>"
        /*js += '$(function() {'*/
        js += '$("#' + id + '").datepicker({'
        js += 'dateFormat: "' + formatJs + '",'
        js += 'changeMonth: true,'
        js += 'changeYear: true'
        if (attrs.onClose) {
            js += ','
            js += 'onClose: ' + attrs.onClose
        }
        if (attrs.onSelect) {
            js += ','
            js += 'onSelect: ' + attrs.onSelect
        }
        if (attrs.yearRange) {
            js += ','
//            println attrs.yearRange
            js += 'yearRange: "' + attrs.yearRange + '"'
        }
        if (attrs.minDate) {
            js += ","
            js += "minDate:" + attrs.minDate
        }
        if (attrs.maxDate) {
            js += ","
            js += "maxDate:" + attrs.maxDate
        }
//        js += 'showOn          : "both",'
//        js += 'buttonImage     : "' + resource(dir: 'images', file: 'calendar.png') + '",'
//        js += 'buttonImageOnly : true'
        /*js += '});'*/
        js += '});'
        js += "</script>"

        out << str
        out << js
    }

    /**
     * A helper tag for creating HTML selects.<br/>
     *
     * Examples:<br/>
     * &lt;g:select name="user.age" from="${18..65}" value="${age}" /&gt;<br/>
     * &lt;g:select name="user.company.id" from="${Company.list()}" value="${user?.company.id}" optionKey="id" /&gt;<br/>
     *
     * @emptyTag
     *
     * @attr name REQUIRED the select name
     * @attr id the DOM element id - uses the name attribute if not specified
     * @attr from REQUIRED The list or range to select from
     * @attr keys A list of values to be used for the value attribute of each "option" element.
     * @attr optionKey By default value attribute of each &lt;option&gt; element will be the result of a "toString()" call on each element. Setting this allows the value to be a bean property of each element in the list.
     * @attr optionValue By default the body of each &lt;option&gt; element will be the result of a "toString()" call on each element in the "from" attribute list. Setting this allows the value to be a bean property of each element in the list.
     * @attr value The current selected value that evaluates equals() to true for one of the elements in the from list.
     * @attr multiple boolean value indicating whether the select a multi-select (automatically true if the value is a collection, defaults to false - single-select)
     * @attr valueMessagePrefix By default the value "option" element will be the result of a "toString()" call on each element in the "from" attribute list. Setting this allows the value to be resolved from the I18n messages. The valueMessagePrefix will be suffixed with a dot ('.') and then the value attribute of the option to resolve the message. If the message could not be resolved, the value is presented.
     * @attr noSelection A single-entry map detailing the key and value to use for the "no selection made" choice in the select box. If there is no current selection this will be shown as it is first in the list, and if submitted with this selected, the key that you provide will be submitted. Typically this will be blank - but you can also use 'null' in the case that you're passing the ID of an object
     * @attr disabled boolean value indicating whether the select is disabled or enabled (defaults to false - enabled)
     * @attr readonly boolean value indicating whether the select is read only or editable (defaults to false - editable)
     */
    Closure select = { attrs ->
        if (!attrs.name) {
            throwTagError("Tag [select] is missing required attribute [name]")
        }
        if (!attrs.containsKey('from')) {
            throwTagError("Tag [select] is missing required attribute [from]")
        }
        def messageSource = grailsAttributes.getApplicationContext().getBean("messageSource")
        def locale = RequestContextUtils.getLocale(request)
        def writer = out
        def from = attrs.remove('from')
        def keys = attrs.remove('keys')
        def optionKey = attrs.remove('optionKey')
        def optionValue = attrs.remove('optionValue')
        def optionClass = attrs.remove('optionClass')
        def value = attrs.remove('value')
        if (value instanceof Collection && attrs.multiple == null) {
            attrs.multiple = 'multiple'
        }
        if (value instanceof CharSequence) {
            value = value.toString()
        }
        def valueMessagePrefix = attrs.remove('valueMessagePrefix')
        def classMessagePrefix = attrs.remove('classMessagePrefix')
        def noSelection = attrs.remove('noSelection')
        if (noSelection != null) {
            noSelection = noSelection.entrySet().iterator().next()
        }
        booleanToAttribute(attrs, 'disabled')
        booleanToAttribute(attrs, 'readonly')

        writer << "<select "
        // process remaining attributes
        outputAttributes(attrs, writer, true)

        writer << '>'
        writer.println()

        if (noSelection) {
            renderNoSelectionOptionImpl(writer, noSelection.key, noSelection.value, value)
            writer.println()
        }

        // create options from list
        if (from) {
            from.eachWithIndex { el, i ->
                def keyValue = null
                writer << '<option '
                if (keys) {
                    keyValue = keys[i]
                    writeValueAndCheckIfSelected(keyValue, value, writer)
                } else if (optionKey) {
                    def keyValueObject = null
                    if (optionKey instanceof Closure) {
                        keyValue = optionKey(el)
                    } else if (el != null && optionKey == 'id' && grailsApplication.getArtefact(DomainClassArtefactHandler.TYPE, el.getClass().name)) {
                        keyValue = el.ident()
                        keyValueObject = el
                    } else {
                        keyValue = el[optionKey]
                        keyValueObject = el
                    }
                    writeValueAndCheckIfSelected(keyValue, value, writer, keyValueObject)
                } else {
                    keyValue = el
                    writeValueAndCheckIfSelected(keyValue, value, writer)
                }

                /** **********************************************************************************************************************************************************/
                if (optionClass) {
                    if (optionClass instanceof Closure) {
                        writer << "class='" << optionClass(el).toString().encodeAsHTML() << "'"
                    } else {
                        writer << "class='" << el[optionClass].toString().encodeAsHTML() << "'"
                    }
                } else if (el instanceof MessageSourceResolvable) {
                    writer << "class='" << messageSource.getMessage(el, locale) << "'"
                } else if (classMessagePrefix) {
                    def message = messageSource.getMessage("${classMessagePrefix}.${keyValue}", null, null, locale)
                    if (message != null) {
                        writer << "class='" << message.encodeAsHTML() << "'"
                    } else if (keyValue && keys) {
                        def s = el.toString()
                        if (s) writer << "class='" << s.encodeAsHTML() << "'"
                    } else if (keyValue) {
                        writer << "class='" << keyValue.encodeAsHTML() << "'"
                    } else {
                        def s = el.toString()
                        if (s) writer << "class='" << s.encodeAsHTML() << "'"
                    }
                } else {
                    def s = el.toString()
                    if (s) writer << "class='" << s.encodeAsHTML() << "'"
                }
                /** **********************************************************************************************************************************************************/

                writer << '>'
                if (optionValue) {
                    if (optionValue instanceof Closure) {
                        writer << optionValue(el).toString().encodeAsHTML()
                    } else {
                        writer << el[optionValue].toString().encodeAsHTML()
                    }
                } else if (el instanceof MessageSourceResolvable) {
                    writer << messageSource.getMessage(el, locale)
                } else if (valueMessagePrefix) {
                    def message = messageSource.getMessage("${valueMessagePrefix}.${keyValue}", null, null, locale)
                    if (message != null) {
                        writer << message.encodeAsHTML()
                    } else if (keyValue && keys) {
                        def s = el.toString()
                        if (s) writer << s.encodeAsHTML()
                    } else if (keyValue) {
                        writer << keyValue.encodeAsHTML()
                    } else {
                        def s = el.toString()
                        if (s) writer << s.encodeAsHTML()
                    }
                } else {
                    def s = el.toString()
                    if (s) writer << s.encodeAsHTML()
                }
                writer << '</option>'
                writer.println()
            }
        }
        // close tag
        writer << '</select>'
    }

//    /**
//     * Dump out attributes in HTML compliant fashion.
//     */
//    void outputAttributes(attrs, writer) {
//        attrs.remove('tagName') // Just in case one is left
//        attrs.each { k, v ->
//            writer << k
//            writer << '="'
//            writer << v.encodeAsHTML()
//            writer << '" '
//        }
//    }

    /**
     * renders attributes in HTML compliant fashion returning them in a string
     */
    String renderAttributes(attrs) {
        def ret = ""
        attrs.remove('tagName') // Just in case one is left
        attrs.each { k, v ->
            ret += k
            ret += '="'
            if (v) {
                ret += v.encodeAsHTML()
            } else {
                ret += ""
            }
            ret += '" '
        }
        return ret
    }

    /**
     * Some attributes can be defined as Boolean values, but the html specification
     * mandates the attribute must have the same value as its name. For example,
     * disabled, readonly and checked.
     */
    private void booleanToAttribute(def attrs, String attrName) {
        def attrValue = attrs.remove(attrName)
        // If the value is the same as the name or if it is a boolean value,
        // reintroduce the attribute to the map according to the w3c rules, so it is output later
        if (Boolean.valueOf(attrValue) ||
                (attrValue instanceof String && attrValue?.equalsIgnoreCase(attrName))) {
            attrs.put(attrName, attrName)
        } else if (attrValue instanceof String && !attrValue?.equalsIgnoreCase('false')) {
            // If the value is not the string 'false', then we should just pass it on to
            // keep compatibility with existing code
            attrs.put(attrName, attrValue)
        }
    }

    /**
     * Dump out attributes in HTML compliant fashion.
     */
    void outputAttributes(attrs, writer, boolean useNameAsIdIfIdDoesNotExist = false) {
        attrs.remove('tagName') // Just in case one is left
        attrs.each { k, v ->
            writer << k
            writer << '="'
            writer << v.encodeAsHTML()
            writer << '" '
        }
        if (useNameAsIdIfIdDoesNotExist) {
            outputNameAsIdIfIdDoesNotExist(attrs, writer)
        }
    }

    Closure renderNoSelectionOption = { noSelectionKey, noSelectionValue, value ->
        renderNoSelectionOptionImpl(out, noSelectionKey, noSelectionValue, value)
    }

    def renderNoSelectionOptionImpl(out, noSelectionKey, noSelectionValue, value) {
        // If a label for the '--Please choose--' first item is supplied, write it out
        out << "<option value=\"${(noSelectionKey == null ? '' : noSelectionKey)}\"${noSelectionKey == value ? ' selected="selected"' : ''}>${noSelectionValue.encodeAsHTML()}</option>"
    }

    private outputNameAsIdIfIdDoesNotExist(attrs, out) {
        if (!attrs.containsKey('id') && attrs.containsKey('name')) {
            out << 'id="'
            out << attrs.name?.encodeAsHTML()
            out << '" '
        }
    }


    private writeValueAndCheckIfSelected(keyValue, value, writer) {
        writeValueAndCheckIfSelected(keyValue, value, writer, null)
    }

    private writeValueAndCheckIfSelected(keyValue, value, writer, el) {

        boolean selected = false
        def keyClass = keyValue?.getClass()
        if (keyClass.isInstance(value)) {
            selected = (keyValue == value)
        } else if (value instanceof Collection) {
            // first try keyValue
            selected = value.contains(keyValue)
            if (!selected && el != null) {
                selected = value.contains(el)
            }
        }
        // GRAILS-3596: Make use of Groovy truth to handle GString <-> String
        // and other equivalent types (such as numbers, Integer <-> Long etc.).
        else if (keyValue == value) {
            selected = true
        } else if (keyClass && value != null) {
            try {
                def typeConverter = new SimpleTypeConverter()
                value = typeConverter.convertIfNecessary(value, keyClass)
                selected = (keyValue == value)
            }
            catch (e) {
                // ignore
            }
        }
        writer << "value=\"${keyValue}\" "
        if (selected) {
            writer << 'selected="selected" '
        }
    }





}
