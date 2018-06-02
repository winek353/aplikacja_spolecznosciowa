<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>


<c:if test="${not empty loggedInUserId}">
	<nav class="navbar navbar-expand-sm bg-warning navbar-light navbar-fixed-top">
		<!-- Links -->
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="friends">friend list</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="messages">messages</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="events">events</a>
			</li>
		</ul>
	<%--<input type="text" placeholder="Search" action="Search">--%>
		<%--<form class="form-inline" action="">--%>
			<%--<input class="form-control mr-sm-2" type="text" placeholder="Search">--%>
			<%--<button class="btn btn-success" type="submit">Search</button>--%>
		<%--</form>--%>


		<form style="float:right" method="post" , action="logout">
			<button style="position:absolute; right:10px; top:5px;" type="submit" class="btn btn-outline-primary">log out</button>
		</form>
	</nav>
</c:if>
	
<c:if test="${empty loggedInUserId}">
	<nav class="navbar navbar-expand-sm bg-warning navbar-light justify-content-end">
		<%--<ul class="navbar-nav">--%>
			<%--<li class="nav-item">--%>
				<%--<a class="nav-link" href="../views/register.jsp">register now</a>--%>
			<%--</li>--%>
		<%--</ul>--%>
		<form class="form-inline" method="post" action="submitLoginForm">
			<form class="form" action="submitLoginForm">
				<%--<label>Email address:</label>--%>
				<input class="form-control" type="text" name="username" placeholder="username" required>
				<%--<label>Password:</label>--%>
				<input class="form-control" type="password" name="password" placeholder="password" required>
				<button type="submit" class="btn btn-outline-primary">Submit</button>
			</form>
		</form>
	</nav>
</c:if>

</body>
</html>
