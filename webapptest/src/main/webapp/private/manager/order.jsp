<%--
  Created by IntelliJ IDEA.
  User: Rafa
  Date: 05.07.2015
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Order managering</title>
  <script type="text/javascript" src = "/js/jquery-1.11.3.js"></script>
  <%--<script type="text/javascript" src = "/js/validateTruckForm.js"></script>--%>
</head>
<body>
<%@include  file="header.jsp" %>
  <table>
    <tr>
      <td> <a href="order?action=show&show=all">all</a> </td>
      <td> <a href="order?action=show&show=done">done</a> </td>
      <td> <a href="order?action=show&show=notdone">notdone</a> </td>
    </tr>
  </table>
<br>

<br>
  <c:choose>
    <c:when test="${orders != null}" >

      <c:forEach var="order" items="${orders}">
        OrderId = ${order.orderId} TruckId = ${order.turckId}
        <table>
          <tr>
            <th>#</th>
            <th>city</th>
            <th>baggage</th>
            <th>action</th>
            <th>isDone</th>
          </tr>

          <c:forEach var="route" items="${order.routes}">
            <tr>
              <td>${route.number}</td>
              <td>${route.city}</td>
              <td>${route.baggageId} (${route.baggage.name})</td>
              <td>${route.baggageStatus}</td>
              <td>${route.isDone}</td>
            </tr>
          </c:forEach>
        </table>
      </c:forEach>
    </c:when>

    <c:otherwise>
      not orders yet
    </c:otherwise>

  </c:choose>
</body>
</html>
