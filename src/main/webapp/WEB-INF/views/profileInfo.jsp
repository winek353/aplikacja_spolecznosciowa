<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 26.05.2018
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
profile
username: ${profile.username}
email: ${profile.email}
<form method="post" , action="changeProfile">
    <table>
        <%--<tr>--%>
            <%--<input type="checkbox" name="sex" value="male"><br>--%>
        <%--</tr>--%>
        <tr>
            <td><input type="text" , name="aboutMe", value=${profile.aboutMe}></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" , value="change"></td>
        </tr>
    </table>
</form>
</body>
</html>