<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 30.05.2018
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
