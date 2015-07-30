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
    <script type="text/javascript" src = "/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src = "/js/validateTruckForm.js"></script>
    <%@include  file="/bootstyle.jsp" %>
</head>
<body>

<div class="container" >
<%@include file="header.jsp"%>

<h3>add truck</h3>

<form  method="POST" action="" onsubmit = "return validateForm('addTruck')"  id="addTruck" class="form col-xs-3">
    <label>Id</label>    <input type="text" name="tid" class="form-control">
    <br>
    <label>Duty time:</label>
    <input type="text" name="duty" class="form-control">
    <br>
    <label>Capacity:</label>
    <input type="text" name="capacity" class="form-control">
    <br>
    Status:<br>

    <input type="radio" name="status" value="0"  id ="OK" checked class="radio-inline">
    <label for="OK">OK</label>
    <br>
    <input type="radio" name="status" value="1"  id ="DEFECTIVE" checked class="radio-inline">
    <label for="DEFECTIVE">DEFECTIVE</label>
    <br>

    <label>City:</label>
    <select name="city" id="city" class="form-control">
        <option selected="selected" value="0"> Select city: </option>
        <c:forEach var="city" items="${cities}">
            <option value="${city.id}">
                    ${city.name}
            </option>
        </c:forEach>
    </select>
    <p>
    <input type="submit" name="do" value="Add" class="btn btn-primary btn-block" >
    </p>
</form>
</div>
</body>
</html>
