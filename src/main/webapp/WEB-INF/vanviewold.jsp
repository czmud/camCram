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
<body class="container">
	
	<h2>The Van!</h2>
	<a class="mx-2" href="/climbers/log-climber-out">Logout</a><br>
	<a class="mx-2" href="/racks/new">+New Rack</a><br>
	<hr>
	<h2>Gear in Your Van:</h2>
	<p> Total weight: <c:out value="${ climber.van.displayTotalWeight() }"/>
		(<c:out value="${ climber.van.displayTotalWeightImperial() }"/> )
	</p>
	<div class="d-flex m-3">
		<div class="col-2 overflow-auto" style="max-height: 300px">
			<h5>Protection (<c:out value="${ climber.van.displayProtectionsWeight() }"/>)
			</h5>
			<table>
		<c:forEach var="oneProtectionsInVan" items="${ climber.van.protectionsInVans }">
			<tr class="d-flex">
				<td>
					<form:form action="/van/remove-protection-from-van/${ oneProtectionsInVan.id }">
						<input type="hidden" name="_method" value="put">
						<input class="btn-danger rounded-circle px-2" type="submit" value="-">
					</form:form>
				</td>
				<td>
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
		<div class="col-2 overflow-auto" style="max-height: 300px">
			<h5>Draws (<c:out value="${ climber.van.displayDrawsWeight() }"/>)
			</h5>
			<table>
		<c:forEach var="oneDrawsInVan" items="${ climber.van.drawsInVans }">
			<tr class="d-flex">
				<td>
					<form:form action="/van/remove-draw-from-van/${ oneDrawsInVan.id }">
						<input type="hidden" name="_method" value="put">
						<input class="btn-danger rounded-circle px-2" type="submit" value="-">
					</form:form>
				</td>
				<td>
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
		<div class="col-2 overflow-auto" style="max-height: 300px">
			<h5>Belay Devices (<c:out value="${ climber.van.displayBelayDevicesWeight() }"/>)
			</h5>
			<table>
		<c:forEach var="oneBelayDevicesInVan" items="${ climber.van.belayDevicesInVans }">
			<tr class="d-flex">
				<td>
					<form:form action="/van/remove-belayDevice-from-van/${ oneBelayDevicesInVan.id }">
						<input type="hidden" name="_method" value="put">
						<input class="btn-danger rounded-circle px-2" type="submit" value="-">
					</form:form>
				</td>
				<td>
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
	<hr>
	
	<h2>Racks:</h2>
	<c:forEach var="oneRack" items="${ climber.racks }">
		<p>
			<a href="/racks/${ oneRack.id }">
				<c:out value="${ oneRack.route }"/>
			</a>
		</p>
	</c:forEach>
	
	<hr>
	<h2>Gear Shop</h2>

		
	
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
            	<c:forEach var="oneProtection" items="${ protections }">
					<tr class="d-flex">
						<td>
							<form:form action="/van/add-protection-to-van/${ oneProtection.id }" method="post">
								<input class="btn-success rounded-circle px-2" type="submit" value="+">
							</form:form>
						</td>
						<td class="mx-3">
						<c:out value="${ oneProtection.manufacturer.abbreviation }"/>
						<c:out value="${ oneProtection.name }"/>
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
				<c:forEach var="oneDraw" items="${ draws }">
					<tr class="d-flex">
						<td>
							<form:form action="/van/add-draw-to-van/${ oneDraw.id }" method="post">
								<input class="btn-success rounded-circle px-2" type="submit" value="+">
							</form:form>
						</td>
						<td class="mx-3">
						<c:out value="${ oneDraw.manufacturer.abbreviation }"/>
						<c:out value="${ oneDraw.name }"/>
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
            <div class="accordion-body d-flex flex-column overflow-auto" style="max-height: 300px; width: 300px">
				<table>			
				<c:forEach var="oneBelayDevice" items="${ belayDevices }">
					<tr class="d-flex">
						<td>
							<form:form action="/van/add-belay-device-to-van/${ oneBelayDevice.id }" method="post">
								<input class="btn-success rounded-circle px-2" type="submit" value="+">
							</form:form>
						</td>
						<td class="mx-3">
						<c:out value="${ oneBelayDevice.manufacturer.abbreviation }"/>
						<c:out value="${ oneBelayDevice.name }"/>
						</td>
					</tr>
				</c:forEach>
				</table>
            </div>
        </div>
    </div>
</div>

	
</body>
</html>