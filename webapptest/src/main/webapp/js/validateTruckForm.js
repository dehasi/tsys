/**
 * Created by Rafa on 07.07.2015.
 */
function validateForm(formName) {
    alert("validate form both")
    var id = document.forms[formName]["tid"].value;
    alert("tid = " + id);
    var duty = document.forms[formName]["duty"].value;
    var capacity = document.forms[formName]["capacity"].value;
    var status = ''
    if (document.getElementById('OK').checked) {
        status = document.getElementById('OK').value;
    }
    if (document.getElementById('DEFECTIVE').checked) {
        status = document.getElementById('DEFECTIVE').value;
    }

    var e = document.getElementById("city");
    var cityId = e.options[e.selectedIndex].value;

    if (id == null || id == "") {
        alert("login field must be filled out");
        return false;
    }

    if (duty == null || duty  == "") {
        alert("password  field must be filled out");
        return false;
    }
    if (cityId == null || cityId  == "0") {
        alert("Select city");
        return false;
    }
}