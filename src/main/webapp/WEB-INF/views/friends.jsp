<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 28.05.2018
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
