<%--
  Created by IntelliJ IDEA.
  User: Rafa
  Date: 03.07.2015
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title></title>
  <script type="text/javascript" src = "/lib/jquery-1.11.3.js"></script>
</head>
<body>
<div>
  <a id = "truckLink" href="/private/manager/truck?action=show&show=all">Trucks</a>
  <a id = "driverLink" href="/private/manager/driver?action=show&show=all">Divers</a>
  <a id = "orderLink" href="/private/manager/order?action=show&show=all">Orders</a>
  <a id = "createLink" href="/private/manager/order?action=create">Create order</a>
  <a id = "logout" href="#">Logout</a>
</div>

<%--<script>--%>
  <%--function showTrucks() {--%>
    <%--$.get("/private/manager/truck");--%>
  <%--}--%>
  <%--$('a#truckLink').click(function(){ showTrucks(); return true; });--%>
<%--</script>--%>


</body>
</html>
