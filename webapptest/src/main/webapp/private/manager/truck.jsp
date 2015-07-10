<%--
  Created by IntelliJ IDEA.
  User: Rafa
  Date: 03.07.2015
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>truck managering </title>


  <%@include  file="/bootstyle.jsp" %>


</head>
<%@include  file="header.jsp" %>

<div class="container">
<table class="table">
  <tr class="active" >
    <td> <a href="truck?action=show&show=all">all</a> </td>
    <td> <a href="truck?action=show&show=free">free</a> </td>
    <td> <a href="truck?action=show&show=inorder">inOrder</a> </td>
    <td> <a href="truck?action=show&show=defective">defective</a> </td>
    <td> <a href="truck?action=show&show=ok">ok</a> </td>
    <td> <a href="truck?action=add">Add</a> </td>
  </tr>
</table>

<%--show param = ${show}--%>
<br>
<c:choose>
  <c:when test="${trucks != null }">


    <table  class="table table-hover  table-bordered" >
      <tr class="info">
        <th>id</th>
        <th>duty size</th>
        <th>capacity</th>
        <th>status</th>
        <th>order</th>
        <th>city</th>
        <th colspan=2>action</th>
      </tr>
      <c:forEach var = "truck" items="${trucks}">
        <tr>
          <td>${truck.id}</td>
          <td>${truck.dutySize}</td>
          <td>${truck.capacity}</td>
          <td>${truck.status}</td>

          <td>
            <c:if test="${truck.orderId != null}">
              <a href="/private/manager/order?action=show&show=id&id=${truck.orderId}">${truck.orderId}</a>
            </c:if>
          </td>

          <td>${truck.city.name}</td>
          <c:choose>
            <c:when test="${truck.orderId == null }">
              <td> <a href="truck?action=edit&id=${truck.id}">Edit</a> </td>
              <td> <a href="truck?action=delete&id=${truck.id}">Delete</a> </td>
            </c:when>
            <c:otherwise>
              <td>Edit </td>
              <td>Delete </td>
            </c:otherwise>
          </c:choose>
          <%--<td> <a href="driver?action=edit&id=${driver.id}">Edit</a> </td>--%>
          <%--<td>  <a href="driver?action=delete&id=${driver.id}">Delete</a></td>--%>
        </tr>
      </c:forEach>
    </table>

  </c:when>

  <c:otherwise>
   No trucks yet. add.
  </c:otherwise>
</c:choose>
</div>
</body>
</html>
