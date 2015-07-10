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
    <script type="text/javascript" src = "/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src = "/js/validateDriverForm.js"></script>
    <%@include  file="/bootstyle.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="header.jsp"%>
    <h3>edit driver</h3>

<form  method="POST" action="" onsubmit = "return validateForm('editDriver')"  id="editDriver" class="form col-xs-3">
  First name:<br>
  <input type="text" name="name" value="${driver.name}" class="form-control">
  <br>
  Last name:<br>
  <input type="text" name="lastname" value="${driver.lastName}" class="form-control">
  <br>
  City:<br>
  <select name="city" id="city" class="form-control">
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
  <p class="submit"><input type="submit" name="do" value="Save" class="btn btn-primary btn-block" ></p>
</form>
</div>
</body>
</html>
