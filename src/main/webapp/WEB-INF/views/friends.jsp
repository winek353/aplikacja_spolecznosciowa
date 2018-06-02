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
<p>friend requests:
<table  width='350'>
    <c:forEach items="${user.friendRequests}" var="friendRequest">
        <form method="get" action="addFriend">
            <tr>
                <td>from ${friendRequest.requesterUsername}</td>
                <input type="hidden" name="friendRequestId" value="${friendRequest.id}">
                <td><input type="submit" , value="accept"></td>
            </tr>
        </form>
    </c:forEach>
</table>

<p>Send friend request:
<form method="get" , action="sendFriendRequest">
    <table>
        <tr>
            <td>friend name</td>
            <td><input type="text" , name="friendName"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" , value="send"></td>
        </tr>
    </table>
</form>

Friends:
    <table>
        <c:forEach items="${user.friends}" var="friend">
        <tr>
            <td>${friend.username}</td>
        </tr>
        </c:forEach>
    </table>

</body>
</html>
