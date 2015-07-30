/**
 * Created by Rafa on 11.07.2015.
 */
function isNumber(o) {
    return typeof o === 'number' && isFinite(o);
}

function sendRedirect(url, method) {
    var form = document.createElement('form');
    form.method = method;
    form.action = url;
    form.submit();
}