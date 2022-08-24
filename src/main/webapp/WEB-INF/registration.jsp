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
	<h1>Register New Climber</h1>
	<table>
	<form:form action="/climbers/create-new-climber" metho="post" modelAttribute="newClimber">
		<tr>
			<td><form:label path="firstName">First Name:</form:label></td>
			<td><form:input path="firstName"/></td>
			<td><form:errors class="text-danger" path="firstName"/></td>
		</tr>
		<tr>
			<td><form:label path="lastName">Last Name:</form:label></td>
			<td><form:input path="lastName"/></td>
			<td><form:errors class="text-danger" path="lastName"/></td>
		</tr>
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
			<td><form:label path="confirmPassword">Confirm Pw:</form:label></td>
			<td><form:input path="confirmPassword" type="password"/></td>
			<td><form:errors class="text-danger" path="confirmPassword"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="Register"></td>
		</tr>
	</form:form>
	</table>
	
	<br><p>Already have an account?</p>
	<a href="/">Login Page</a>
	
</body>
</html>