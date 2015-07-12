<%@page pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%response.setCharacterEncoding("UTF-8");%>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:choose>
    <c:when test="${empty sessionScope.locale}">
        <fmt:setLocale value="en_EN"/>
    </c:when>
    <c:when test="${sessionScope.locale == 'RU'}">
        <fmt:setLocale value="ru_RU"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en_EN"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="Login" var="login"/>
<!DOCTYPE html>





<head>
    <title>Login Form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/jquery-1.11.3.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <link href="css/signin.css" rel="stylesheet">

    <script>
        function validateForm() {
            var login = document.forms["myForm"]["login"].value;
            var password = document.forms["myForm"]["password"].value;

            if (login == null || login == "") {
                alert("login field must be filled out");
                return false;
            }

            if (password == null || password  == "") {
                alert("password  field must be filled out");
                return false;
            }
        }
    </script>

</head>
<body>

        <div class="container">
            <div class="col-xs-3">
        <form name="myForm"  method="post" action="login" onsubmit = "return validateForm()" class="form-signin" >
            <h2 class="form-signin-heading" ><fmt:message key="main.title" bundle="${login}"/></h2>
            <input type="text" name="login" value="manager" placeholder="<fmt:message key="text.username" bundle="${login}"/>" class="form-control col-xs-3" size="5">
            <input type="password" name="password" value="secret" placeholder="<fmt:message key="text.password" bundle="${login}"/>" class="form-control col-xs-3">
            <c:set var="ddd"> <fmt:message key="btn.lg"  bundle="${login}"/></c:set>
            <input class="btn btn-primary btn-block" type="submit" name="commit" value="${ddd}" >
            <%--<fmt:message key="button.login" bundle="${login}"/>--%>
            </div>
        </form>

            <a id="RU"  href="#" > RU</a>
            <a id="EN"  href="#" > EN</a>
    </div>

<script>

    $('#EN').click(function() {
        changeLang("EN")
    });


    $('#RU').click(function() {
        changeLang("RU")

    });

    function changeLang(lang) {
        $.ajax({
            url: '/locale',
            type: 'POST',
            data : {lang:lang},
            success: function() {
                location.reload();
            }
        });
    }
</script>



</body>
</html>