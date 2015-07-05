<%--
  Created by IntelliJ IDEA.
  User: Rafa
  Date: 06.07.2015
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>Edit driver</title>
  <script type="text/javascript" src = "/lib/jquery-1.11.3.js"></script>
  <script  type="text/javascript">
    function validateForm() {
      var name = document.forms["editDriver"]["name"].value;
      var lastname = document.forms["editDriver"]["lastname"].value;

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
  </script>
</head>
<body>

<form  method="POST" action="" onsubmit = "return validateForm()"  id="editDriver">
  First name:<br>
  <input type="text" name="name" value="${driver.name}">
  <br>
  Last name:<br>
  <input type="text" name="lastname" value="${driver.lastName}">
  <br>
  City:<br>
  <select name="city" id="city">
    <option selected="selected" value="0"> Select city: </option>
    <c:forEach var="city" items="${cities}">
      <option value="${city.id}"
              <c:if test="${city.id == driver.city.id}">
                      selected="selected"
              </c:if>
      >
          ${city.name}
      </option>
    </c:forEach>
  </select>
  <p class="submit"><input type="submit" name="do" value="Save"></p>
</form>


</body>
</html>
