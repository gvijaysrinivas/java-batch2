 <%@ page 
	import="com.fdm.wealthnow.common.User" %>
 <%@ page 
 	import="com.fdm.wealthnow.dao.UserDAO" %>	
<html>
	<head>
			<title>Home Page</title>
	</head>
	<body>
		 	<% User currentUser = (User) (session.getAttribute("loggedInUser"));%>
			<H1> Welcome <%= currentUser.getFullName() %></H1>
			<H2> Balance : $<%= UserDAO.getBalance(currentUser.getUserId()) %></H2>
			
			<fieldset id style = "width: 210px">
			<legend><strong>Trading Platform Quick Links</strong></legend>
					<form action = post method >
						<a href="balancePage.jsp">Account Balance</a> <br>
						<a href="WatchList.jsp">Watchlists</a> <br>
						<a href="PortfolioView.jsp">User Portfolio Viewer</a> <br>
						<a href="OrderForm.jsp">Order Form</a> <br>
						<a href="logout.jsp">Logout</a>
					</form>
			</fieldset>
	</body>
</html>