<%--
  Created by IntelliJ IDEA.
  User: Rafa
  Date: 07.07.2015
  Time: 0:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Add new truck</title>
    <script type="text/javascript" src = "/lib/jquery-1.11.3.js"></script>
    <script type="text/javascript" src = "/lib/validateTruckForm.js"></script>
</head>
<body>
<%@include file="header.jsp"%>

<h1>add truck</h1>

<form  method="POST" action="" onsubmit = "return validateForm('addTruck')"  id="addTruck">
    Id:<br>
    <input type="text" name="tid">
    <br>
    Duty time:<br>
    <input type="text" name="duty">
    <br>
    Capacity:<br>
    <input type="text" name="capacity">
    <br>
    Status:<br>

    <input type="radio" name="status" value="0"  id ="OK" checked>
    <label for="OK">OK</label>
    <br>
    <input type="radio" name="status" value="1"  id ="DEFECTIVE">
    <label for="DEFECTIVE">DEFECTIVE</label>
    <br>

    City:<br>
    <select name="city" id="city">
        <option selected="selected" value="0"> Select city: </option>
        <c:forEach var="city" items="${cities}">
            <option value="${city.id}">
                    ${city.name}
            </option>
        </c:forEach>
    </select>
    <p class="submit"><input type="submit" name="do" value="Add"></p>
</form>

</body>
</html>
