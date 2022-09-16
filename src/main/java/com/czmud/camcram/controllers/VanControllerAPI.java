package com.czmud.camcram.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.czmud.camcram.apiModels.BelayDeviceSerializer;
import com.czmud.camcram.apiModels.DrawSerializer;
import com.czmud.camcram.apiModels.ProtectionSerializer;
import com.czmud.camcram.models.BelayDevicesInVan;
import com.czmud.camcram.models.DrawsInVan;
import com.czmud.camcram.models.ProtectionsInVan;
import com.czmud.camcram.models.Van;
import com.czmud.camcram.services.ClimberService;
import com.czmud.camcram.services.VanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@RestController
@RequestMapping("/api/van")
public class VanControllerAPI {
	@Autowired
	private ClimberService climberService;
	@Autowired
	private VanService vanService;
	
	@GetMapping("/protections-in-van")
	public String vanDashboardProtections(HttpSession session) throws JsonProcessingException {

		List<ProtectionsInVan> protectionsInVans = climberService.getClimberById( (Long) session.getAttribute("climberId") ).getVan().getProtectionsInVans();
		
		SimpleModule module = new SimpleModule();
		module.addSerializer(new ProtectionSerializer(ProtectionsInVan.class));
		ObjectMapper mapper = new ObjectMapper();
		String protectionsJson = mapper.registerModule(module)
				.writer(new DefaultPrettyPrinter())
				.writeValueAsString(protectionsInVans);
		return protectionsJson;
	}
	
	@GetMapping("/belay-devices-in-van")
	public String vanDashboardBelayDevices(HttpSession session) throws JsonProcessingException {
		
		List<BelayDevicesInVan> belayDevicesInVans = climberService.getClimberById( (Long) session.getAttribute("climberId") ).getVan().getBelayDevicesInVans();
		
		SimpleModule module = new SimpleModule();
		module.addSerializer(new BelayDeviceSerializer(BelayDevicesInVan.class));
		ObjectMapper mapper = new ObjectMapper();
		String belayDevicesJson = mapper.registerModule(module)
				.writer(new DefaultPrettyPrinter())
				.writeValueAsString(belayDevicesInVans);
		return belayDevicesJson;
	}
	
	@GetMapping("/draws-in-van")
	public String vanDashboardDraws(HttpSession session) throws JsonProcessingException {

		List<DrawsInVan> drawsInVans = climberService.getClimberById( (Long) session.getAttribute("climberId") ).getVan().getDrawsInVans();
		
		SimpleModule module = new SimpleModule();
		module.addSerializer(new DrawSerializer(DrawsInVan.class));
		ObjectMapper mapper = new ObjectMapper();
		String drawsJson = mapper.registerModule(module)
				.writer(new DefaultPrettyPrinter())
				.writeValueAsString(drawsInVans);
		return drawsJson;
	}
	
	@PostMapping("/add-protection-to-van")
	public String addProtectionToVan( @RequestParam("protectionId") Long protectionId,
									  HttpSession session ) throws JsonProcessingException {
		
		Van updateVan = climberService.getClimberById( (Long) session.getAttribute("climberId")).getVan();
		
		ProtectionsInVan addProtectionsInVan = vanService.addProtectionToVan( updateVan , protectionId);
		
		SimpleModule module = new SimpleModule();
		module.addSerializer(new ProtectionSerializer(ProtectionsInVan.class));
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.registerModule(module)
				.writer(new DefaultPrettyPrinter())
				.writeValueAsString(addProtectionsInVan);
		
		return jsonResult;
	}
	
	@PutMapping("/remove-protection-from-van")
	public String removeProtectionFromVan( @RequestParam("protectionsInVanId") Long protectionsInVanId,
										   HttpSession session) throws JsonProcessingException {
		
		ProtectionsInVan removedProtectionsInVan = vanService.removeProtectionFromVan( protectionsInVanId);
		
		SimpleModule module = new SimpleModule();
		module.addSerializer(new ProtectionSerializer(ProtectionsInVan.class));
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.registerModule(module)
				.writer(new DefaultPrettyPrinter())
				.writeValueAsString(removedProtectionsInVan);
		
		return jsonResult;
	}
	
	@PostMapping("/add-belay-device-to-van")
	public String addBelayDeviceToVan( @RequestParam("belayDeviceId") Long belayDeviceId,
									  HttpSession session ) throws JsonProcessingException {
		
		Van updateVan = climberService.getClimberById( (Long) session.getAttribute("climberId")).getVan();
		
		BelayDevicesInVan addBelayDevicesInVan = vanService.addBelayDeviceToVan( updateVan , belayDeviceId);
		
		SimpleModule module = new SimpleModule();
		module.addSerializer(new BelayDeviceSerializer(BelayDevicesInVan.class));
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.registerModule(module)
				.writer(new DefaultPrettyPrinter())
				.writeValueAsString(addBelayDevicesInVan);
		
		return jsonResult;
	}
	
	@PutMapping("/remove-belay-device-from-van")
	public String removeBelayDeviceFromVan( @RequestParam("belayDevicesInVanId") Long belayDevicesInVanId,
										   HttpSession session) throws JsonProcessingException {
		
		BelayDevicesInVan removedBelayDevicesInVan = vanService.removeBelayDeviceFromVan( belayDevicesInVanId);
		
		SimpleModule module = new SimpleModule();
		module.addSerializer(new BelayDeviceSerializer(BelayDevicesInVan.class));
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.registerModule(module)
				.writer(new DefaultPrettyPrinter())
				.writeValueAsString(removedBelayDevicesInVan);
		
		return jsonResult;
	}
	
	@PostMapping("/add-draw-to-van")
	public String addDrawToVan( @RequestParam("drawId") Long drawId,
									  HttpSession session ) throws JsonProcessingException {
		
		Van updateVan = climberService.getClimberById( (Long) session.getAttribute("climberId")).getVan();
		
		DrawsInVan addDrawsInVan = vanService.addDrawToVan( updateVan , drawId);
		
		SimpleModule module = new SimpleModule();
		module.addSerializer(new DrawSerializer(DrawsInVan.class));
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.registerModule(module)
				.writer(new DefaultPrettyPrinter())
				.writeValueAsString(addDrawsInVan);
		
		return jsonResult;
	}
	
	@PutMapping("/remove-draw-from-van")
	public String removeDrawFromVan( @RequestParam("drawsInVanId") Long drawsInVanId,
										   HttpSession session) throws JsonProcessingException {
		
		DrawsInVan removedDrawsInVan = vanService.removeDrawFromVan( drawsInVanId);
		
		SimpleModule module = new SimpleModule();
		module.addSerializer(new DrawSerializer(DrawsInVan.class));
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.registerModule(module)
				.writer(new DefaultPrettyPrinter())
				.writeValueAsString(removedDrawsInVan);
		
		return jsonResult;
	}
	
}
