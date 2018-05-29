<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 21.05.2018
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
you are logged in
    <p>
        <a href = "http://localhost:8080/profile">display profile</a>
    </p>
    <p>
        <a href = "http://localhost:8080/friends">friends</a>
    </p>
    <p>
        <form method="post" , action="logout">
            <td><input type="submit" , value="logout"></td>
        </form>
    </p>

</body>
</html>
