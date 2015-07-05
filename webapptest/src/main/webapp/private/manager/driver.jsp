<%--
  Created by IntelliJ IDEA.
  User: Rafa
  Date: 05.07.2015
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Drivers managering</title>
</head>
<body>
  <%@ include file="header.jsp"%>

  <p>
  <tr>
    <td> <a href="driver?action=show&show=all">all</a> </td>
    <td> <a href="driver?action=show&show=free">free</a> </td>
    <td> <a href="driver?action=show&show=inorder">inOrder</a> </td>
    <td> <a href="driver?action=add">Add driver</a> </td>
  </tr>

  <br>
  <c:choose>
    <c:when test="${ drivers != null}">
      <table>
        <tr>
          <th>id</th>
          <th>name</th>
          <th>last name</th>
          <th>hours worked</th>
          <th>city</th>
          <th>order</th>
          <th colspan=2>action</th>
        </tr>
        <c:forEach var = "driver" items = "${drivers}" >
          <tr>
            <td>${driver.id}</td>
            <td>${driver.name}</td>
            <td>${driver.lastName}</td>
            <td>${driver.hoursWorked}</td>
            <td>${driver.city.name}</td>
            <td><a href="#"> ${driver.orderRoute}</a> </td>
            <td> <a href="driver?action=edit&id=${driver.id}">Edit</a> </td>
            <td>  <a href="driver?action=delete&id=${driver.id}">Delete</a></td>
          </tr>
        </c:forEach>
      </table>
    </c:when>

    <c:otherwise>
      No drivers yet. add.
    </c:otherwise>
  </c:choose>
</body>
</html>
