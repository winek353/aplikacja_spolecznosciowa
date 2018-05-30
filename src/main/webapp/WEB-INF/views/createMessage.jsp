<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 30.05.2018
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" , action="sendMessage">
    User:
    <input type="text" , name="recipient"><br>
    <br/>
    <textarea rows="10" cols="60" name="message"></textarea>
    <br/>
    <input type="submit" value="send" />
</form>
</body>
</html>
