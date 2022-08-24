<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<title>Cam Cram</title>
</head>
<body class="container">
	<div class="d-flex justify-content-between bg-info align-items-center">
		<h1>CamCram</h1>
	</div>
	
	<h2>Log In</h2>
		<table>
		<form:form action="/climbers/log-climber-in" method="post" modelAttribute="newLoginClimber">
			<tr>
				<td><form:label path="email">Email:</form:label></td>
				<td><form:input path="email"/></td>
				<td><form:errors class="text-danger" path="email"/></td>
			</tr>
			<tr>
				<td><form:label path="password">Password:</form:label></td>
				<td><form:input path="password" type="password"/></td>
				<td><form:errors class="text-danger" path="password"/></td>
			</tr>			
			<tr>
				<td><input type="submit" value="Login"></td>
			</tr>			
		</form:form>
		</table>
	
	<br><p>Don't have an account?</p>
	<a href="/register">Register New Climber</a>
	
</body>
</html>