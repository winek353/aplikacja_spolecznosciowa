<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 02.03.2018
  Time: 01:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${msg}
<form method="post" , action="submitLoginForm">
    <table>
        <tr>
            <td>User Name</td>
            <td><input type="text" , name="username"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" , name="password"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" , value="login"></td>
        </tr>
    </table>
</form>
</body>
</html>
