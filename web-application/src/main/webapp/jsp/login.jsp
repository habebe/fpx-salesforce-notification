<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
if (request.getSession().getAttribute("access") != null) {
%><jsp:forward
	page="../jsp/mainpage.jsp" />
<%
	}
%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Salesforce Opportunity Notification</title>

<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
</head>

<body>

<div class="container">

  <form class="form-signin" 
  action="${pageContext.request.contextPath}/authenticate/login.action"
  method="post">
    <h4 class="form-signin-heading">Salesforce Opportunity Notification</h4>
      <br/>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" name="inputEmail" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" name="inputPassword" id="inputPassword" class="form-control" placeholder="Password" required>
      <br/>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>    
  </form>
</div> 

</body>
</html>
