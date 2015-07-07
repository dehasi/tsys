/**
 * Created by Rafa on 07.07.2015.
 */


function validateForm(formName) {
    var name = document.forms[formName]["name"].value;
    var lastname = document.forms[formName]["lastname"].value;

    var e = document.getElementById("city");
    var cityId = e.options[e.selectedIndex].value;

    if (name == null || name == "") {
        alert("login field must be filled out");
        return false;
    }

    if (lastname == null || lastname  == "") {
        alert("password  field must be filled out");
        return false;
    }
    if (cityId == null || cityId  == "0") {
        alert("Select city");
        return false;
    }
}