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
	<script type="text/javascript" src="/js/vanScript.js"></script>
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<title>Cam Cram</title>
</head>
<body>
	<div class="cmcrm-bg-royal-blue">
	<div class="container d-flex justify-content-between align-items-center">
		<div class="d-flex justify-content-start align-items-center">
			<h2>CamCram</h2>
			<img class="mx-3" src="/assets/navbar/cam-r.png" alt="cam" style="height: 30px"/>
		</div>
		<a class="btn btn-dark my-2 rounded p-1" href="/climbers/log-climber-out" role="button">Logout</a>
	</div>
	</div>


<div class="container">
	<div class="d-flex justify-content-between">
		<div class="d-flex align-items-center">
			<h3 class="my-2"><c:out value="${ climber.firstName }"/>'s Van</h3>
			<p class="m-3"> Total weight: <c:out value="${ climber.van.displayTotalWeight() }"/>
				(<c:out value="${ climber.van.displayTotalWeightImperial() }"/> )
			</p>
		</div>
		<div class="d-flex align-items-center">
			<button class="btn-danger my-2 rounded px-1" onclick="showHideRemoveButton(this)">edit</button>
		</div>
	</div>
	<hr class="my-2">
	<div class="d-flex">
		<div class="col">
			<div class="d-flex align-items-center">
				<h5 class="mx-2">Protection (<c:out value="${ climber.van.displayProtectionsWeight() }"/>)</h5>
			</div>
			<div id="van-cams" class="p-2 d-flex" style="width: 400px; min-height: 86px; background-color: darkseagreen">
			</div>
			<div id="van-stoppers" class="p-2 d-flex" style="width: 400px; background-color: darkseagreen">
			</div>
			<div id="van-misc" class="p-2 d-flex" style="width: 400px; background-color: darkseagreen">
			</div>
		</div>
		<div class="col">
			<div class="d-flex">
				<h5>Belay Devices (<c:out value="${ climber.van.displayBelayDevicesWeight() }"/>)</h5>
			</div>
			<div id="van-belay-devices" class="p-2 d-flex" style="width: 400px; min-height: 114px; background-color: darkseagreen">
			</div>
		</div>
		<div class="col">
			<div class="d-flex">
				<h5>Quick Draws (<c:out value="${ climber.van.displayDrawsWeight() }"/>)</h5>
			</div>
			<div id="van-draws" class="p-2 d-flex" style="width: 400px; min-height: 114px; background-color: darkseagreen">
			</div>
		</div>
	</div>
		<hr class="my-3">
	<div class="d-flex">
		
		
		<div class="col mx-2">
		<h3 class="mb-3">Gear Shop</h3>
	
			
		
		<div class="accordion" id="accordionExample">
		    <div class="accordion-item">
		        <h2 class="accordion-header" id="headingOne">
		            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
		                Protection
		            </button>
		        </h2>
		        <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">            
		            <div class="accordion-body d-flex flex-column overflow-auto" style="max-height: 300px">
		            	<table>
		            	<c:forEach var="oneProtection" items="${ protections }">
							<tr class="d-flex">
								<td>
									<button class="btn-success rounded-circle px-2" onclick="addProtectionToVan(${ oneProtection.id })">+</button>
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
		            <div class="accordion-body d-flex flex-column overflow-auto" style="max-height: 300px">
		 				<table>			
						<c:forEach var="oneDraw" items="${ draws }">
							<tr class="d-flex">
								<td>
									<button class="btn-success rounded-circle px-2" onclick="addDrawToVan(${ oneDraw.id })">+</button>
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
		            <div class="accordion-body d-flex flex-column overflow-auto" style="max-height: 300px">
						<table>			
						<c:forEach var="oneBelayDevice" items="${ belayDevices }">
							<tr class="d-flex">
								<td>
									<button class="btn-success rounded-circle px-2" onclick="addBelayDeviceToVan(${ oneBelayDevice.id })">+</button>
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
		</div>
	</div>
</div>
	
</body>
</html>