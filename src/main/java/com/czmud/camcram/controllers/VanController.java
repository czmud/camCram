package com.czmud.camcram.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.czmud.camcram.models.Van;
import com.czmud.camcram.services.ClimberService;
import com.czmud.camcram.services.GearService;
import com.czmud.camcram.services.VanService;

@Controller
@RequestMapping("/van")
public class VanController {
	@Autowired
	private VanService vanService;
	@Autowired
	private GearService gearService;
	@Autowired
	private ClimberService climberService;
	
	@GetMapping("")
	public String vanDashboard(Model model, HttpSession session) {
		
		if( session.getAttribute("climberId") == null ) {
			session.invalidate();
			return "redirect:/";
		}
		
		model.addAttribute("climber", climberService.getClimberById( (Long) session.getAttribute("climberId")));
		model.addAttribute("protections", gearService.getAllProtections());
		model.addAttribute("draws", gearService.getAllDraws());
		model.addAttribute("belayDevices", gearService.getAllBelayDevices());
		
		return "vanview.jsp";
	}
	
	@PostMapping("/add-protection-to-van/{protectionId}")
	public String addProtectionToVan( @PathVariable("protectionId") Long protectionId,
									  HttpSession session ) {
		
		Van updateVan = climberService.getClimberById( (Long) session.getAttribute("climberId")).getVan(); 
		
		vanService.addProtectionToVan( updateVan , protectionId);
		
		
		return "redirect:/van";
	}
	
	@PutMapping("remove-protection-from-van/{protectionsInVanId}")
	public String removeProtectionFromVan( @PathVariable("protectionsInVanId") Long protectionsInVanId,
										   HttpSession session) {
		
		vanService.removeProtectionFromVan( protectionsInVanId);
		
		return "redirect:/van";
	}
	
	@PostMapping("/add-belay-device-to-van/{belayDeviceId}")
	public String addBelayDeviceToVan( @PathVariable("belayDeviceId") Long belayDeviceId,
									  HttpSession session ) {
		
		Van updateVan = climberService.getClimberById( (Long) session.getAttribute("climberId")).getVan(); 
		
		vanService.addBelayDeviceToVan( updateVan , belayDeviceId);
		
		
		return "redirect:/van";
	}
	
	@PutMapping("remove-belay-device-from-van/{belayDevicesInVanId}")
	public String removeBelayDeviceFromVan( @PathVariable("belayDevicesInVanId") Long belayDevicesInVanId,
										   HttpSession session) {
		
		vanService.removeBelayDeviceFromVan( belayDevicesInVanId);
		
		return "redirect:/van";
	}
	
	@PostMapping("/add-draw-to-van/{drawId}")
	public String addDrawToVan( @PathVariable("drawId") Long drawId,
									  HttpSession session ) {
		
		Van updateVan = climberService.getClimberById( (Long) session.getAttribute("climberId")).getVan(); 
		
		vanService.addDrawToVan( updateVan , drawId);
		
		
		return "redirect:/van";
	}
	
	@PutMapping("remove-draw-from-van/{drawsInVanId}")
	public String removeDrawFromVan( @PathVariable("drawsInVanId") Long drawsInVanId,
										   HttpSession session) {
		
		vanService.removeDrawFromVan( drawsInVanId);
		
		return "redirect:/van";
	}
	
	
	
	
}
