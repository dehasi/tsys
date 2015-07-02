<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Login Form</title>
    <link rel="stylesheet" href="css/style.css">

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



<section class="container">
    <div class="login">
        <h1>Login to Web App</h1>

        <form name="myForm"  method="post" action="login" onsubmit = "return validateForm()" >
            <p><input type="text" name="login" value="1" placeholder="username:"></p>
            <p><input type="password" name="password" value="password" placeholder="password"></p>
            <!--<p class="remember_me">-->
                <!--<label>-->
                    <!--<input type="checkbox" name="remember_me" id="remember_me">-->
                    <!--Remember me on this computer-->
                <!--</label>-->
            <!--</p>-->
            <p class="submit"><input type="submit" name="commit" value="Login"></p>
        </form>
    </div>

    <!--<div class="login-help">-->
        <!--<p>Forgot your password? <a href="index.html">Click here to reset it</a>.</p>-->
    <!--</div>-->
</section>

</body>
</html>