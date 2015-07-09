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

    <table id="cartGrid">
        <tr>
            <th>name</th>
            <th>weight</th>
            <th>load city</th>
            <th>unload city</th>
        </tr>

        <c:forEach var="i" begin="1" end="${count}">
            <tr id = "${i}">
                <td>
                    <input type = "text"  size="5"  name = "name"  id = "name${i}" placeholder = "name">
                </td>
                <td>
                    <input type = "text"  size="5"  name = "weight"  id = "weight${i}" placeholder = "weight">
                </td>
                <%----%>
                <td>
                    <select name="city" id="load${i}">
                        <option selected="selected" value="0"> Select city: </option>
                        <c:forEach var="city" items="${cities}">
                            <option value="${city.id}">
                                    ${city.name}
                            </option>
                        </c:forEach>
                    </select>
                </td>
                <%----%>
                <td>
                    <select name="city" id="unload${i}">
                    <option selected="selected" value="0"> Select city: </option>
                    <c:forEach var="city" items="${cities}">
                        <option value="${city.id}">
                                ${city.name}
                        </option>
                    </c:forEach>
                </select></td>
            </tr>
        </c:forEach>
    </table>


    find drivers and truck:
    <input type="submit" id="findStuff" value="find"  onclick="getDriverAndTruck()">


</div>
</body>
</html>
