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
            <h2 class="form-signin-heading" >Login to Web App</h2>

            <%--<div class="col-xs-3">--%>
            <input type="text" name="login" value="manager" placeholder="username:" class="form-control col-xs-3" size="5">
            <%--</div>--%>
            <%--<div class="col-xs-3">--%>
            <input type="password" name="password" value="secret" placeholder="password" class="form-control col-xs-3">
            <%--</div>--%>
            <%--<div class="col-xs-4">--%>
            <input class="btn btn-primary btn-block " type="submit" name="commit" value="Login">
            </div>
        </form>




    </div>





</body>
</html>