<%--
  Created by IntelliJ IDEA.
  User: Rafa
  Date: 03.07.2015
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<div class="container">
  <table  class="table">
    <tr lass="active">
      <td><a id = "truckLink" href="truck?action=show&show=all">Trucks</a></td>
      <td><a id = "driverLink" href="driver?action=show&show=all">Divers</a></td>
      <td><a id = "orderLink" href="order?action=show&show=all">Orders</a></td>
      <td><a id = "logout" href="/webservice/logout">Logout</a></td>
    </tr>
  </table>
</div>

