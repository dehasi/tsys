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
  <script type="text/javascript" src = "/js/jquery-1.11.3.js"></script>
  <script type="text/javascript" src = "/js/validateTruckForm.js"></script>
  <%@include  file="/bootstyle.jsp" %>
</head>
<body>

<div class="container">
<%@include file="header.jsp"%>

  <h3>edit truck</h3>
<form  method="POST" action="" onsubmit = "return validateForm('editTruck')"  id="editTruck" class="form col-xs-3">
  <input type="hidden" name="hiddenid" value="${truck.id}">
  <label>Id</label>
  <input type="text" name="tid" value="${truck.id}" class="form-control">
  <br>
  <label>Duty time:</label>
  <input type="text" name="duty" value="${truck.dutySize}" class="form-control" >
  <br>
  <label>Capacity:</label>
  <input type="text" name="capacity" value="${truck.capacity}" class="form-control">
  <br>
  Status:<br>

  <input type="radio" name="status" value="0"  id ="OK" class="radio-inline"
        <c:if test="${truck.status == 'OK'}"> checked</c:if>
   >
  <label for="OK">OK</label>
  <br>
  <input type="radio" name="status" value="1"  id ="DEFECTIVE" class="radio-inline"
  <c:if test="${truck.status != 'OK'}"> checked</c:if>
          >
  <label for="DEFECTIVE">DEFECTIVE</label>
  <br>

  <label>City:</label>
  <select name="city" id="city" class="form-control">
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
  <p class="submit"><input type="submit" name="do" value="Save"   class="btn btn-primary btn-block btn-info"></p>
</form>


</div>
</body>
</html>
