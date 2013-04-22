/**
 * Created with IntelliJ IDEA.
 * User: luz
 * Date: 8/18/12
 * Time: 11:05 PM
 * To change this template use File | Settings | File Templates.
 */

//pads left
String.prototype.lpad = function (padString, length) {
    var str = this;
    while (str.length < length) {
        str = padString + str;
    }
    return str;
}

//pads right
String.prototype.rpad = function (padString, length) {
    var str = this;
    while (str.length < length) {
        str = str + padString;
    }
    return str;
}

jQuery.expr[":"].icontains = jQuery.expr.createPseudo(function (arg) {
    return function (elem) {
//        console.log(arg, elem, jQuery(elem).text().toUpperCase().indexOf(arg.toUpperCase()) >= 0);
        return jQuery(elem).text().toUpperCase().indexOf(arg.toUpperCase()) >= 0;
    };
});

jQuery.extend(
    jQuery.expr[':'].containsCI = function (a, i, m) {
        //-- faster than jQuery(a).text()
        var sText = (a.textContent || a.innerText || "");
        var zRegExp = new RegExp(m[3], 'i');
//        console.log(sText, zRegExp.test(sText));
        return zRegExp.test(sText);
    }
);

function validarDec(ev) {
    /*
     48-57      -> numeros
     96-105     -> teclado numerico
     188        -> , (coma)
     190        -> . (punto) teclado
     110        -> . (punto) teclado numerico
     8          -> backspace
     46         -> delete
     9          -> tab
     37         -> flecha izq
     39         -> flecha der
     */
    return ((ev.keyCode >= 48 && ev.keyCode <= 57) ||
            (ev.keyCode >= 96 && ev.keyCode <= 105) ||
            (ev.keyCode == 190 || ev.keyCode == 110) ||
            ev.keyCode == 8 || ev.keyCode == 46 || ev.keyCode == 9 ||
            ev.keyCode == 37 || ev.keyCode == 39);
}
function validarInt(ev) {
    /*
     48-57      -> numeros
     96-105     -> teclado numerico
     188        -> , (coma)
     190        -> . (punto) teclado
     110        -> . (punto) teclado numerico
     8          -> backspace
     46         -> delete
     9          -> tab
     37         -> flecha izq
     39         -> flecha der
     */
    return ((ev.keyCode >= 48 && ev.keyCode <= 57) ||
            (ev.keyCode >= 96 && ev.keyCode <= 105) ||
            ev.keyCode == 8 || ev.keyCode == 46 || ev.keyCode == 9 ||
            ev.keyCode == 37 || ev.keyCode == 39);
}
