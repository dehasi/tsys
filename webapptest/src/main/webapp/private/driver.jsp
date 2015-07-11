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
    <%@include  file="/bootstyle.jsp" %>
</head>
<body>
<div class="container">
    <h3>driver info</h3>
    <td><a id = "logout" href="/logout">Logout</a></td>
    <%--	личный номер водителя--%>

    <h4>Driver id = ${driver.id}</h4>
    <h4>name = ${driver.name} ${driver.lastName}</h4>

        <c:choose>
        <c:when test="${route != null }">
            <%--	личные номера ко-водителей--%>
            <h3>partners:</h3>
            <table class="table">
                <tr class="active">
                    <th  >id</th>
                    <th  >name</th>
                </tr>
                <c:forEach var = "friend" items="${friends}">
                    <tr >
                        <td  > ${friend.id} </td>
                        <td  > ${friend.name}  ${friend.lastName} </td>
                    </tr>
                </c:forEach>
            </table>
            <br/>

            <%--	номер заказа--%>
            <h4>order: ${orderId}</h4>
            <%--	рег. номер фуры--%>
            <h4>truck: ${truckId}</h4>
            <%--	список маршрутных точек--%>
            <h3>Route</h3>
            <table class="table table-hover table-bordered table-striped">
                <tr class="active">
                    <th>#</th>
                    <th>city</th>
                    <th>baggage</th>
                    <th>action</th>
                    <th>isDone</th>
                </tr>
                <c:forEach var = "p" items="${route}">
                    <tr>
                        <td>${p.number}</td>
                        <td>${p.city}</td>
                        <td>${p.baggageId} (${p.baggage.name})</td>
                        <td>
                            <c:choose>
                                <c:when test="${p.baggageStatus == 0}">
                                    load
                                </c:when>
                            <c:otherwise>
                                unload
                            </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${p.isDone == 0}">
                                    NO
                                </c:when>
                                <c:otherwise>
                                    YES
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </c:when>
        
        <c:otherwise>
            driver in rest
        </c:otherwise>
    </c:choose>

</div>
</body>
</html>
