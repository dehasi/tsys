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
</head>
<body>
<%@include  file="header.jsp" %>
  <table>
    <tr>
      <td> <a href="order?show=all">all</a> </td>
      <td> <a href="order?show=done">free</a> </td>
      <td> <a href="order?show=notdone">inOrder</a> </td>
    </tr>
  </table>


</body>
</html>
