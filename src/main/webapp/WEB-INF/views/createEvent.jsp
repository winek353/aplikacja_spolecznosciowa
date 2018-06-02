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
    <form method="post" , action="createEvent">
        <table>
            <tr>
                <td>Event name</td>
                <td><input type="text" , name="eventName"></td>
            </tr>
            <tr>
                <td>Event start date</td>
                <td><input type="date" , name="eventDate"></td>
            </tr>

            <tr>
                <td></td>
                <td><br><input style="margin-left:121px;" type="submit"  value="create"></td>
            </tr>
        </table>
    </form>
</body>
</html>
