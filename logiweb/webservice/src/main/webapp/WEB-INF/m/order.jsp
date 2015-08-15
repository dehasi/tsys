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

<form id = "create" name="createOrder" method="get" action="/m/createorder"   onsubmit = "return validateBaggageCount()" >
  create order with:
  <input type = "text" id = "bgcnt" size="5" value="1" name = "count" class="form-horizontal">  baggages
  <input type="submit" id="createButton" value="create" class="btn btn-success">
</form>


<br>
  <c:choose>
    <c:when test="${orders != null}" >
      <table class="table  table-bordered sortable">
        <thead>
      <tr class="active">
        <th class="sorttable_numeric" >order id</th>
        <th>truck id</th>
        <th class="sorttable_numeric" >baggage id</th>
        <th>baggage name</th>
        <th>load city</th>
        <th>unload city</th>
        <th>order status</th>
      </tr>
        </thead>
        <tbody>
      <c:forEach var="order" items="${orders}" >

          <c:forEach var="route" items="${order.routes}" step="2" varStatus="vs">
            <tr>
              <td>${order.orderId}</td>
              <td>${order.turckId}</td>
              <td>${route.baggage.id}</td>
              <td>${route.baggage.name}</td>
              <td>${route.city.name}</td>
              <td>${order.routes[vs.index+1].city.name}</td>
                <td>
                 <c:choose>
                   <c:when test="${order.status == 1}">
                     DONE
                   </c:when>
                   <c:when test="${order.status == 1}">
                     NOT DONE
                   </c:when>
                   <c:otherwise>
                     NOT DONE
                   </c:otherwise>
                 </c:choose>
                </td>

            </tr>
          </c:forEach>

      </c:forEach>
        </tbody>
      </table>
    </c:when>

    <c:otherwise>
      not orders yet
    </c:otherwise>

  </c:choose>
  <script src="/js/sorttable.js"></script>
</div>
</body>
</html>
