<%--
  Created by IntelliJ IDEA.
  User: Rafa
  Date: 07.07.2015
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Creating order</title>
    <script type="text/javascript" src = "/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src = "/js/createBaggage.js"></script>
</head>
<body>
<%@include file="header.jsp"%>
<h1>Order creating</h1>

<c:set var="myArray" value="'one', 'two', 'three'" />

  <form name="Bcount" onsubmit="return createBaggage()">
    Baggage count   <input name="count" value="1">     <input type="button" id="43" value="Add" onclick="Javascript:addTable()">
  </form>


<div id="myDynamicTable">

</div>
</body>
</html>
