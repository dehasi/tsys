<%--
  Created by IntelliJ IDEA.
  User: Rafa
  Date: 03.07.2015
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <script type="text/javascript" src = "/lib/jquery-1.11.3.js"></script>
</head>
<body>
<div>
  <a id = "truckLink" href="/private/manager/truck?show=all">Trucks</a>
  <a id = "driverLink" href="#">Divers</a>
  <a id = "orderLink" href="#">Orders</a>
  <a id = "createLink" href="#">Create order</a>
</div>
val = ${key}

<%--<script>--%>
  <%--function showTrucks() {--%>
    <%--$.get("/private/manager/truck");--%>
  <%--}--%>
  <%--$('a#truckLink').click(function(){ showTrucks(); return true; });--%>
<%--</script>--%>


</body>
</html>
