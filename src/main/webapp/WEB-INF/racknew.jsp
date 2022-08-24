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
<body>
	<h1>Create New Rack</h1>
	
	<a class="mx-2" href="/climbers/log-climber-out">Logout</a><br>
	
	<form:form action="/racks/create-new-rack" metho="post" modelAttribute="newRack">
		<input type="hidden" name="climber" value="${ climber.id }">
		
		<form:label path="route">Route:</form:label>
		<form:input path="route"/>
		<form:errors class="text-danger" path="route"/><br>
		
		<form:label path="area">Climbing Area:</form:label>
		<form:input path="area"/>
		<form:errors class="text-danger" path="area"/><br>
		
		<form:label path="dateClimbed">Date of Climb:</form:label>
		<form:input path="dateClimbed" type="date"/>
		<form:errors class="text-danger" path="dateClimbed"/><br>
		
		<form:label path="notes">Notes:</form:label>
		<form:textarea path="notes"/>
		<form:errors class="text-danger" path="notes"/><br>
		
		<a href="/van">Cancel</a>	
		<input type="submit" value="Add Gear >">
	</form:form>
	
</body>
</html>