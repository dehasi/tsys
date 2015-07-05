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
</head>
<%@include  file="header.jsp" %>
<%-- all free inOrder defective --%>
<table>
  <tr>
    <td> <a href="truck?show=all">all</a> </td>
    <td> <a href="truck?show=free">free</a> </td>
    <td> <a href="truck?show=inorder">inOrder</a> </td>
    <td> <a href="truck?show=defective">defective</a> </td>
    <td> <a href="truck?show=ok">ok</a> </td>
  </tr>
</table>

show param = ${show}
<br>
<c:choose>
  <c:when test="${trucks != null }">
    <table>
      <tr>
        <th>id</th>
        <th>duty size</th>
        <th>capacity</th>
        <th>status</th>
        <th>city</th>
        <th colspan=2>action</th>
      </tr>
      <c:forEach var = "truck" items="${trucks}">
        <tr>
          <td>${truck.id}</td>
          <td>${truck.dutySize}</td>
          <td>${truck.capacity}</td>
          <td>${truck.status}</td>
          <td>${truck.city.name}</td>
          <td>update</td>
          <td>delete</td>
        </tr>
      </c:forEach>
    </table>
  </c:when>

  <c:otherwise>
   No trucks yet. add.
  </c:otherwise>
</c:choose>
</body>
</html>
