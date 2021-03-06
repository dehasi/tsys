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
  <script type="text/javascript" src = "/js/createBaggage1.js"></script>
  <%@include  file="/bootstyle.jsp" %>
  <%--<script type="text/javascript" src = "/js/validateTruckForm.js"></script>--%>
</head>
<body>
<div class="container">
  <%@include  file="header.jsp" %>
  <table class="table">
    <tr class="info">
      <td> <a href="order?action=show&show=all">all</a> </td>
      <td> <a href="order?action=show&show=done">done</a> </td>
      <td> <a href="order?action=show&show=notdone">notdone</a> </td>
    </tr>
  </table>
  <br>

  <form id = "create" name="createOrder" method="get" action="/private/manager/createorder"   onsubmit = "return validateBaggageCount()" >
    create order with:
    <input type = "text" id = "bgcnt" size="5" value="1" name = "count" class="form-horizontal">  baggages
    <input type="submit" id="createButton" value="create" class="btn btn-success">
  </form>


  <br>
  <c:choose>
    <c:when test="${orders != null}" >

      <c:forEach var="order" items="${orders}" >
        <label> OrderId = ${order.orderId} TruckId = ${order.turckId}</label>
        <table class="table  table-bordered">
          <tr class="active">
            <th>#</th>
            <th>baggage</th>
            <th>load city</th>
            <th>isDone</th>
            <th>unload city</th>
            <th>isDone</th>
            <th>order status</th>
          </tr>

          <c:forEach var="route" items="${order.routes}" step="2" varStatus="vs">
            <tr>
              <td>${route.number}</td>
              <td>${route.baggageId} (${route.baggage.name})</td>
              <td>${route.city}</td>
              <td>${route.isDone}</td>

              <td>${order.routes[vs.index+1].city}</td>
              <td>${order.routes[vs.index+1].isDone}</td>
              <td>${order.status}</td>
            </tr>
          </c:forEach>
        </table>
      </c:forEach>
    </c:when>

    <c:otherwise>
      not orders yet
    </c:otherwise>

  </c:choose>

</div>
</body>
</html>
