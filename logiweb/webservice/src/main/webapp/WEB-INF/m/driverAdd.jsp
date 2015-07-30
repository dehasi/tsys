<%--
  Created by IntelliJ IDEA.
  User: Rafa
  Date: 05.07.2015
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Add new driver</title>
    <script type="text/javascript" src = "/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src = "/js/validateDriverForm.js"></script>
    <%@include  file="/bootstyle.jsp" %>
</head>
<body>

  <div class="container">
  <%@include file="header.jsp"%>

  <h3>add driver</h3>

  <form  method="POST" action="" onsubmit = "return validateForm('addDriver')"  id="addDriver" class="form col-xs-3">
    First name:<br>
    <input type="text" name="name" class="form-control">
    <br>
    Last name:<br>
    <input type="text" name="lastname" class="form-control">
    <br>
    City:<br>
    <select name="city" id="city" class="form-control">
      <option selected="selected" value="0"> Select city: </option>
      <c:forEach var="city" items="${cities}">
        <option value="${city.id}">
          ${city.name}
        </option>
      </c:forEach>
    </select>
    <p class="submit"><input type="submit" name="do" value="Add"  class="btn btn-primary btn-block" ></p>
  </form>
</div>
</body>
</html>
