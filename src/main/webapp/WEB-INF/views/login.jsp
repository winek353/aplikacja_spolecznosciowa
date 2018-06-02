<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <jsp:include page="../navBars/nav.jsp"/>
    <title>Title</title>
</head>
<body>
<div class="alert alert-danger"><strong>${msg}!</strong></div>
<h1 class="pl-5 pt-5 display-1">Log in</h1>

<form method="post" action="submitLoginForm">
    <div class=" p-5 form-group">
        <label><b>User name:</b></label>
        <input class="form-control" type="text" placeholder="User name" name="username" required>

        <label><b>Password:</b></label>
        <input class="form-control" type="password" placeholder="Password" name="password"<%-- pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" --%> title="Must contain at least one number and one uppercase and lowercase letter, and at least 6 or more characters" required>

        <input type="checkbox" checked="checked"> Remember me

        <div>
            <a href="profile.jsp">
                <button type="submit" class="signupbtn">Log in</button>
            </a>
        </div>
    </div>
    <h4 class="pl-5"><a href="RegisterForm"><strong>Register now</strong></a></h4>
</form>
</body>
</html>
