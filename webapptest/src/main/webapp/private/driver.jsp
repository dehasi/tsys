<%--
  Created by IntelliJ IDEA.
  User: Rafa
  Date: 01.07.2015
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>driver</title>
</head>
<body>
    <h1>driver info</h1>
    <table>
        <tr>
            <td>Driver id </td> <td>${driver.id} </td>
        </tr>
        <tr>
            <td>name </td> <td>${driver.name}  </td>
        </tr>

    </table>
    <%--	личный номер водителя--%>
    <%--	личные номера ко-водителей--%>
    <%--	рег. номер фуры--%>
    <%--	номер заказа--%>
    <%--	список маршрутных точек--%>
    <h3>partners:</h3>
    <table>
        <tr>
            <th>name<th>
            <th>id<th>
        </tr>

        <c:forEach var = "friend" items="${friends}">
            <tr>
                <td>${friend.name}</td>

                <td>${friend.id}</td>
            </tr>

        </c:forEach>
    </table>
    <br/>

    <h4>order: ${orgerId}</h4>
    <h4>truck: ${truckId}</h4>
    <h3>Route</h3>
    <table>
        <tr>
            <th>#</th>
            <th>city</th>
            <th>baggage</th>
            <th>action</th>
        </tr>
        <c:forEach var = "p" items="${route}">
            <tr>
                <td>${p.number}</td>
                <td>${p.city}</td>
                <td>${p.baggageId}</td>
                <td>${p.baggageStatus}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
