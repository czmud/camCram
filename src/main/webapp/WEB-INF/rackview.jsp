<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	<title>Cam Cram</title>
</head>
<body class="content">
	<div class="d-flex justify-content-center">
		<div class="col d-flex flex-column align-items-center">
	<h1><c:out value="${rack.route}"/></h1>
	<a class="mx-2" href="/climbers/log-climber-out">Logout</a><br>
	<a class="mx-2" href="/van">Back to the Van</a><br>
	
	<p>Climbing Area: <c:out value="${ rack.area }"/></p>
	<p>Date of Climb: <c:out value="${ rack.dateClimbed }"/></p>
	<p>Notes: <c:out value="${ rack.notes }"/></p>
	<h5>Your Rack</h5>
	<hr>
	<h5>Add Gear to your rack</h5>
		
		<div class="accordion" id="accordionExample">
		    <div class="accordion-item">
		        <h2 class="accordion-header" id="headingOne">
		            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
		                Protection
		            </button>
		        </h2>
		        <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">            
		            <div class="accordion-body d-flex flex-column overflow-auto" style="max-height: 300px; width: 300px">
		            	<table>
		            	<c:forEach var="oneProtectionsInVan" items="${ climber.van.protectionsInVans }">
							<tr class="d-flex">
								<td>
									<form:form action="/racks/add-protection-to-rack/${ oneProtectionsInVan.id }" method="post">
										<input class="btn-success rounded-circle px-2" type="submit" value="+">
									</form:form>
								</td>
								<td class="mx-3">
									<c:out value="${ oneProtectionsInVan.protection.manufacturer.abbreviation }"/>
									<c:out value="${ oneProtectionsInVan.protection.name }"/>
									<c:if test="${ oneProtectionsInVan.count > 1 }">
										(<c:out value="${ oneProtectionsInVan.count }"/>x)
									</c:if>
								</td>
							</tr>
						</c:forEach>
		            	</table>
		            </div>
		        </div>
		    </div>
		    <div class="accordion-item">
		        <h2 class="accordion-header" id="headingTwo">
		            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
		                Quickdraws
		            </button>
		        </h2>
		        <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
		            <div class="accordion-body d-flex flex-column overflow-auto" style="max-height: 300px; width: 300px">
		 				<table>			
						<c:forEach var="oneDrawsInVan" items="${ climber.van.drawsInVans }">
							<tr class="d-flex">
								<td>
									<form:form action="/rack/add-draw-to-rack/${ oneDrawsInVan.id }" method="post">
										<input class="btn-success rounded-circle px-2" type="submit" value="+">
									</form:form>
								</td>
								<td class="mx-3">
									<c:out value="${ oneDrawsInVan.draw.manufacturer.abbreviation }"/>
									<c:out value="${ oneDrawsInVan.draw.name }"/>
									<c:if test="${ oneDrawsInVan.count > 1 }">
										(<c:out value="${ oneDrawsInVan.count }"/>x)
									</c:if>
								</td>
							</tr>
						</c:forEach>
						</table>
		            </div>
		        </div>
		    </div>
		    <div class="accordion-item">
		        <h2 class="accordion-header" id="headingThree">
		            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
		                Belay Devices
		            </button>
		        </h2>
		        <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
		            <div class="accordion-body d-flex flex-column  overflow-auto" style="max-height: 300px; width: 300px">
						<table>			
						<c:forEach var="oneBelayDevicesInVan" items="${ climber.van.belayDevicesInVans }">
							<tr class="d-flex">
								<td>
									<form:form action="/rack/add-belay-device-to-rack/${ oneBelayDevicesInVan.id }" method="post">
										<input class="btn-success rounded-circle px-2" type="submit" value="+">
									</form:form>
								</td>
								<td class="mx-3">
									<c:out value="${ oneBelayDevicesInVan.belayDevice.manufacturer.abbreviation }"/>
									<c:out value="${ oneBelayDevicesInVan.belayDevice.name }"/>
									<c:if test="${ oneBelayDevicesInVan.count > 1 }">
										(<c:out value="${ oneBelayDevicesInVan.count }"/>x)
									</c:if>
								</td>
							</tr>
						</c:forEach>
						</table>
		            </div>
		        </div>
		    </div>
		</div>
		
		
		
		
		
		</div>
		<div class="col bg-success">
			<p>Sandbox</p>
		</div>
	</div>
	
</body>
</html>