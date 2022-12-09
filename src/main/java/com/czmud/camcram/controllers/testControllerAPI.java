package com.czmud.camcram.controllers;

import java.lang.management.MemoryUsage;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.czmud.camcram.apiModels.ProtectionSerializer;
import com.czmud.camcram.diagnostics.MemoryUseTest;
import com.czmud.camcram.models.Climber;
import com.czmud.camcram.models.ProtectionsInVan;
import com.czmud.camcram.models.Van;
import com.czmud.camcram.services.ClimberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@RestController
public class testControllerAPI {
	@Autowired
	private ClimberService climberService;
	
	@GetMapping(value="api/memory-usage/heap")
	public MemoryUsage testHeapMemoryUse() {
		MemoryUseTest memoryUseTest = new MemoryUseTest();
		MemoryUsage testResponse = memoryUseTest.testHeapMemory();
		
		return testResponse;
	}
	
	@GetMapping(value="api/memory-usage/non-heap")
	public MemoryUsage testNonHeapMemoryUse() {
		MemoryUseTest memoryUseTest = new MemoryUseTest();
		MemoryUsage testResponse = memoryUseTest.testNonHeapMemory();
		
		return testResponse;
	}
	
	@GetMapping(value="api/testing")
	public String testAPI(HttpSession session) throws JsonProcessingException {
		
//		Long belayDeviceId = Long.valueOf(2);
//		String returnBelayDevice = new Gson().toJson(belayDeviceRepository.findById( belayDeviceId ).get());

		Long climberId = Long.valueOf(3);
		Climber climber = climberService.getClimberById( climberId );
		Van van = climber.getVan();
		List<ProtectionsInVan> protectionsInVans = van.getProtectionsInVans();
		
		ProtectionsInVan oneProtectionsInVan = protectionsInVans.get(1);
//		String returnProtection = gson.toJson(oneProtectionsInVan); 
//		String returnProtection = "{";
//		String oneJSON;
//		for( ProtectionsInVan oneProtectionsInVan : protectionsInVans ) {
//			oneJSON = String.format("%d:{id:%d,name:%s},",
//					oneProtectionsInVan.getId(), oneProtectionsInVan.getId(), oneProtectionsInVan.getProtection().getName());
//			returnProtection += oneJSON;
//		}
//		returnProtection += "}";
		
		van.getProtectionsInVans();
		van.getBelayDevicesInVans();
		van.getDrawsInVans();
		
//		String returnString = gson.toJson(oneProtectionsInVan);
		
		SimpleModule module = new SimpleModule();
		module.addSerializer(new ProtectionSerializer(ProtectionsInVan.class));
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.registerModule(module)
				.writer(new DefaultPrettyPrinter())
				.writeValueAsString(protectionsInVans);
		
		jsonResult = (String) session.getAttribute("climberId");
		
		return jsonResult;
	}
}
