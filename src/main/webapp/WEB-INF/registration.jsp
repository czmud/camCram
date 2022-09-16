<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<title>Cam Cram</title>
</head>
<body>
	<div class="cmcrm-bg-royal-blue">
	<div class="container d-flex justify-content-start align-items-center">
		<div class="d-flex justify-content-start align-items-center">
			<h2>CamCram</h2>
			<img class="mx-3" src="/assets/navbar/cam-r.png" alt="cam" style="height: 30px"/>
		</div>
	</div>
	</div>
	<div class="d-flex flex-column align-items-center">
	<h3 class="m-4">Create Account</h3>
	<div class="d-flex justify-content-center">
		<form:form action="/climbers/create-new-climber" metho="post" modelAttribute="newClimber">
			<div class="form-group row my-1">
				<form:label class="col-sm-4 col-form-label py-1"  path="firstName">First Name:</form:label>
				<div class="col-sm-8">
					<form:input path="firstName"/>
				</div>
			</div>
			<div class="form-group row my-2">
				<form:errors class="text-danger" path="firstName"/>
			</div>
			<div class="form-group row my-1">
				<form:label class="col-sm-4 col-form-label py-1"  path="lastName">Last Name:</form:label>
				<div class="col-sm-8">
					<form:input path="lastName"/>
				</div>
			</div>
			<div class="form-group row my-2">
				<form:errors class="text-danger" path="lastName"/>
			</div>
			<div class="form-group row my-1">
				<form:label class="col-sm-4 col-form-label py-1"  path="email">Email:</form:label>
				<div class="col-sm-8">
					<form:input path="email"/>
				</div>
			</div>
			<div class="form-group row my-2">
				<form:errors class="text-danger" path="email"/>
			</div>
			<div class="form-group row my-1">
				<form:label class="col-sm-4 col-form-label py-1"  path="password">Password:</form:label>
				<div class="col-sm-8">
					<form:input path="password" type="password"/>
				</div>
			</div>
			<div class="form-group row my-2">
				<form:errors class="text-danger" path="password"/>
			</div>
			<div class="form-group row my-1">
				<form:label class="col-sm-4 col-form-label py-1"  path="confirmPassword">Confirm Pw:</form:label>
				<div class="col-sm-8">
					<form:input path="confirmPassword" type="password"/>
				</div>
			</div>
			<div class="form-group row my-2">
				<form:errors class="text-danger" path="confirmPassword"/>
			</div>
			<div class="form-group row my-1 d-flex justify-content-end">
				<div class="col-sm-4">
					<button type="submit" class="btn btn-dark">Register</button>
				</div>
			</div>
		</form:form>
	</div>
	<p class="m-4">
		Already have an account?
		<a href="/">Login</a>
	</p>
	
	</div>
</body>
</html>