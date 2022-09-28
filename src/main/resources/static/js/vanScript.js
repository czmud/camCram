let protectionsInVans;
let belayDevicesInVans;
let drawsInVans;
let showRemoveButton = false;

async function onPageLoad(){
	let protectionsResponse = await fetch("http://localhost:9090/api/van/protections-in-van");
	protectionsInVans = await protectionsResponse.json();
	let belayDevicesResponse = await fetch("http://localhost:9090/api/van/belay-devices-in-van");
	belayDevicesInVans = await belayDevicesResponse.json();
	let drawsResponse = await fetch("http://localhost:9090/api/van/draws-in-van");
	drawsInVans = await drawsResponse.json();
	
	sortGear( getNominalRange, protectionsInVans );
	populateProtectionsInVan( protectionsInVans );
	
	populateBelayDevicesInVan( belayDevicesInVans );
	
	populateDrawsInVan( drawsInVans );
}

onPageLoad();

function sortGear(propertyRetriever, arr){
	arr.sort(function(a,b){
		let valA = propertyRetriever(a);
		let valB = propertyRetriever(b);
		
		return valA - valB;
	});
}

function getNominalRange(protection){
	return protection.range.nominal;
}


function populateProtectionsInVan( protectionsInVans ){
		
	let camElement = document.getElementById("van-cams");
	let stopperElement = document.getElementById("van-stoppers");
	let miscElement = document.getElementById("van-misc");
	let stringHTML = "";
		
	camElement.innerHTML = "";
	stopperElement.innerHTML = "";
	miscElement.innerHTML = "";
		
	for( let onePro of protectionsInVans ){
			stringHTML += "<div class='pro-group'>"
				+"<button class='btn-danger rounded-circle px-2 remove-pro'"
				+( showRemoveButton ? "" : "style='display:none'" )
				+" onclick='removeProtectionFromVan("
				+onePro.id
				+")'>-</button>";
			for(let i = 0; i<onePro.count; i++){				
				stringHTML +=
						"<img src="
						+(onePro.protection.imageUrl ?
						onePro.protection.imageUrl
						:
						"'/assets/placeholders/"+onePro.type+".png'"
						)
						+" alt='pro img' style='height: 100px'>";
			}
			stringHTML +="<span class='pro-hover' >"
				+onePro.protection.manufacturer.abbreviation+" "
				+onePro.protection.name
				+((onePro.count > 1 ? " ("+onePro.count+"x)" : " "))
				+"</span>"
				+"</div>";
		
		if( onePro.type === 'cam'){
			camElement.innerHTML += stringHTML;
		} else if( onePro.type === 'stopper' ){
			stopperElement.innerHTML += stringHTML;
		} else if ( onePro.type === 'misc' ){
			miscElement.innerHTML += stringHTML;
		}
		stringHTML = "";	
		
	}
		
}

function populateBelayDevicesInVan( belayDevicesInVans ){
	
	let belayDeviceElement = document.getElementById("van-belay-devices");
	let stringHTML = "";
		
	belayDeviceElement.innerHTML = "";
		
	for( let belay of belayDevicesInVans ){
			stringHTML += "<div class='pro-group'>"
				+"<button class='btn-danger rounded-circle px-2 remove-pro'"
				+( showRemoveButton ? "" : "style='display:none'" )
				+" onclick='removeBelayDeviceFromVan("
				+belay.id
				+")'>-</button>";
			for(let i = 0; i<belay.count; i++){				
				stringHTML +=
						"<img src="
						+(belay.belayDevice.imageUrl ?
						belay.belayDevice.imageUrl
						:
						"'/assets/placeholders/belay-device.png'"
						)
						+" alt='pro img' style='height: 100px'>";
			}
			stringHTML +="<span class='pro-hover' >"
				+belay.belayDevice.manufacturer.abbreviation+" "
				+belay.belayDevice.name
				+((belay.count > 1 ? " ("+belay.count+"x)" : " "))
				+"</span>"
				+"</div>";
		
		belayDeviceElement.innerHTML += stringHTML;
		stringHTML = "";
	}
}

function populateDrawsInVan( drawsInVans ){
	let drawElement = document.getElementById("van-draws");
	let stringHTML = "";
		
	drawElement.innerHTML = "";
		
	for( let oneDraw of drawsInVans ){
			stringHTML += "<div class='pro-group'>"
				+"<button class='btn-danger rounded-circle px-2 remove-pro'"
				+( showRemoveButton ? "" : "style='display:none'" )
				+" onclick='removeDrawFromVan("
				+oneDraw.id
				+")'>-</button>";
			for(let i = 0; i<oneDraw.count; i++){				
				stringHTML +=
						"<img src="
						+(oneDraw.draw.imageUrl ?
						oneDraw.draw.imageUrl
						:
						"'/assets/placeholders/quickdraw.png'"
						)
						+" alt='pro img' style='height: 100px'>";
			}
			stringHTML +="<span class='pro-hover' >"
				+oneDraw.draw.manufacturer.abbreviation+" "
				+oneDraw.draw.name
				+((oneDraw.count > 1 ? " ("+oneDraw.count+"x)" : " "))
				+"</span>"
				+"</div>";
		
		drawElement.innerHTML += stringHTML;
		stringHTML = "";
	}
}


function showHideRemoveButton( buttonElement ){
	showRemoveButton = !showRemoveButton;
		
	let removeElements = document.querySelectorAll(".remove-pro");
		
	if (showRemoveButton)	{
		for (let element of removeElements ){
			element.style.display="";
		}
		buttonElement.innerHTML = "done";
	} else {
		for (let element of removeElements ){
			element.style.display="none";
		}
		buttonElement.innerHTML = "edit";
	}
}

async function addProtectionToVan( protectionId ){
		
	let formData = new FormData();
	formData.append("protectionId", protectionId);
		
	let response = await fetch("http://localhost:9090/api/van/add-protection-to-van", { method:'POST', body: formData });
	let newProtectionsInVan = await response.json();
		
	if( ! newProtectionsInVan ){
		return;
	}
		
	if(newProtectionsInVan.count > 1 ){
		for( let pro of protectionsInVans ){
			if( pro.id === newProtectionsInVan.id ){
				pro.count = newProtectionsInVan.count;
				break;
			}
		}
	} else {
		protectionsInVans.push(newProtectionsInVan);
		sortGear( getNominalRange, protectionsInVans );
	}
		
	populateProtectionsInVan(protectionsInVans);
		
}

async function removeProtectionFromVan( protectionsInVanId ){
		
	let formData = new FormData();
	formData.append("protectionsInVanId", protectionsInVanId);
		
	let response = await fetch("http://localhost:9090/api/van/remove-protection-from-van", { method: 'PUT', body: formData })
	let removedProtectionsInVan = await response.json();
    
    if( ! removedProtectionsInVan ){
		return;
	}
		
	if( removedProtectionsInVan.count > 0 ){
		for( let pro of protectionsInVans ){
			if( pro.id === removedProtectionsInVan.id ){
				pro.count = removedProtectionsInVan.count;
				break;
			}
		}
	} else {
		for ( let i in protectionsInVans ){
			if( protectionsInVans[i].id === removedProtectionsInVan.id ){
				protectionsInVans.splice(i, 1);
				sortGear( getNominalRange, protectionsInVans );
				break;
			}
		}
	}
		
	populateProtectionsInVan(protectionsInVans);
		
}

async function addBelayDeviceToVan( belayDeviceId ){
		
	let formData = new FormData();
	formData.append("belayDeviceId", belayDeviceId);
		
	let response = await fetch("http://localhost:9090/api/van/add-belay-device-to-van", { method:'POST', body: formData });
	let newBelayDevicesInVan = await response.json();
		
	if( ! newBelayDevicesInVan ){
		return;
	}
		
	if(newBelayDevicesInVan.count > 1 ){
		for( let oneBelayDevice of belayDevicesInVans ){
			if( oneBelayDevice.id === newBelayDevicesInVan.id ){
				oneBelayDevice.count = newBelayDevicesInVan.count;
				break;
			}
		}
	} else {
		belayDevicesInVans.push(newBelayDevicesInVan);
	}
		
	populateBelayDevicesInVan(belayDevicesInVans);
		
}

async function removeBelayDeviceFromVan( belayDevicesInVanId ){
		
	let formData = new FormData();
	formData.append("belayDevicesInVanId", belayDevicesInVanId);
		
	let response = await fetch("http://localhost:9090/api/van/remove-belay-device-from-van", { method: 'PUT', body: formData })
	let removedBelayDevicesInVan = await response.json();
    
    if( ! removedBelayDevicesInVan ){
		return;
	}
		
	if( removedBelayDevicesInVan.count > 0 ){
		for( let oneBelayDevice of belayDevicesInVans ){
			if( oneBelayDevice.id === removedBelayDevicesInVan.id ){
				oneBelayDevice.count = removedBelayDevicesInVan.count;
				break;
			}
		}
	} else {
		for ( let i in belayDevicesInVans ){
			if( belayDevicesInVans[i].id === removedBelayDevicesInVan.id ){
				belayDevicesInVans.splice(i, 1);
				break;
			}
		}
	}
		
	populateBelayDevicesInVan(belayDevicesInVans);
		
}

async function addDrawToVan( drawId ){
		
	let formData = new FormData();
	formData.append("drawId", drawId);
		
	let response = await fetch("http://localhost:9090/api/van/add-draw-to-van", { method:'POST', body: formData });
	let newDrawsInVan = await response.json();
		
	if( ! newDrawsInVan ){
		return;
	}
		
	if(newDrawsInVan.count > 1 ){
		for( let oneDraw of drawsInVans ){
			if( oneDraw.id === newDrawsInVan.id ){
				oneDraw.count = newDrawsInVan.count;
				break;
			}
		}
	} else {
		drawsInVans.push(newDrawsInVan);
	}
		
	populateDrawsInVan(drawsInVans);
		
}

async function removeDrawFromVan( drawsInVanId ){
		
	let formData = new FormData();
	formData.append("drawsInVanId", drawsInVanId);
		
	let response = await fetch("http://localhost:9090/api/van/remove-draw-from-van", { method: 'PUT', body: formData })
	let removedDrawsInVan = await response.json();
    
    if( ! removedDrawsInVan ){
		return;
	}
		
	if( removedDrawsInVan.count > 0 ){
		for( let oneDraw of drawsInVans ){
			if( oneDraw.id === removedDrawsInVan.id ){
				oneDraw.count = removedDrawsInVan.count;
				break;
			}
		}
	} else {
		for ( let i in drawsInVans ){
			if( drawsInVans[i].id === removedDrawsInVan.id ){
				drawsInVans.splice(i, 1);
				break;
			}
		}
	}
		
	populateDrawsInVan(drawsInVans);
		
}