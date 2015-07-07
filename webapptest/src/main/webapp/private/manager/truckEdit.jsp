<%--
  Created by IntelliJ IDEA.
  User: Rafa
  Date: 07.07.2015
  Time: 3:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>Edit truck</title>
  <script type="text/javascript" src = "/lib/jquery-1.11.3.js"></script>
  <script type="text/javascript" src = "/lib/validateTruckForm.js"></script>
</head>
<body>
<%@include file="header.jsp"%>

<td>${truck.status}</td>

<br>
truck = ${truck }
<br>
<form  method="POST" action="" onsubmit = "return validateForm('editTruck')"  id="editTruck">
  <input type="hidden" name="hiddenid" value="${truck.id}">
  Id:<br>
  <input type="text" name="tid" value="${truck.id}">
  <br>
  Duty time:<br>
  <input type="text" name="duty" value="${truck.dutySize}">
  <br>
  Capacity:<br>
  <input type="text" name="capacity" value="${truck.capacity}">
  <br>
  Status:<br>

  <input type="radio" name="status" value="0"  id ="OK"
        <c:if test="${truck.status == 'OK'}"> checked</c:if>
   >
  <label for="OK">OK</label>
  <br>
  <input type="radio" name="status" value="1"  id ="DEFECTIVE"
  <c:if test="${truck.status != 'OK'}"> checked</c:if>
          >
  <label for="DEFECTIVE">DEFECTIVE</label>
  <br>

  City:<br>
  <select name="city" id="city">
    <option selected="selected" value="0"> Select city: </option>
    <c:forEach var="city" items="${cities}">
      <option value="${city.id}"
              <c:if test="${city.id == truck.city.id}">
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
