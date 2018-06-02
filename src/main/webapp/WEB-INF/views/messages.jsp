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
<p>
    <a href = "http://localhost:8080/messageForm">create message</a>
</p>
<p>messages:
<table  width='350', border = \1\>
    <c:forEach items="${user.messages}" var="message">
        <tr>
            <td bgcolor='#D6D6D6'>author ${message.author}</td>
        </tr>
        <tr>
            <td>${message.messageText}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
