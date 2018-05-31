<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 31.05.2018
  Time: 09:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
