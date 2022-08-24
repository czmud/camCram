package com.czmud.camcram.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.czmud.camcram.models.Rack;
import com.czmud.camcram.services.ClimberService;
import com.czmud.camcram.services.RackService;

@Controller
@RequestMapping("/racks")
public class RackController {
	@Autowired
	private RackService rackService;
	@Autowired
	private ClimberService climberService;
	
	@GetMapping("/{rackId}")
	public String viewRack( @PathVariable("rackId") Long rackId,
							Model model, HttpSession session ) {
		
		if( session.getAttribute("climberId") == null ) {
			session.invalidate();
			return "redirect:/";
		}
		
		model.addAttribute("climber", climberService.getClimberById( (Long) session.getAttribute("climberId") ));
		model.addAttribute("rack", rackService.getRackById( rackId ));
		
		return "rackview.jsp";
	}
	
	@GetMapping("/new")
	public String newRackForm( @ModelAttribute("newRack") Rack newRack, 
								 Model model, HttpSession session) {
		
		if( session.getAttribute("climberId") == null ) {
			session.invalidate();
			return "redirect:/";
		}
		
		model.addAttribute("climber", climberService.getClimberById( (Long) session.getAttribute("climberId") ));
		
		return "racknew.jsp";
	}
	
	@PostMapping("/create-new-rack")
	public String createNewRack( Model model, HttpSession session,
								   @Valid @ModelAttribute("newRack") Rack newRack,
								   BindingResult bindingResult ) {
		
		if( bindingResult.hasErrors() ) {
			model.addAttribute("climber", climberService.getClimberById( (Long) session.getAttribute("climberId") ));
			return "racknew.jsp";
		}
		
		Rack updatedRack = rackService.createNewRack( newRack, bindingResult );
		
		if( bindingResult.hasErrors() ) {
			model.addAttribute("climber", climberService.getClimberById( (Long) session.getAttribute("climberId") ));
			return "racknew.jsp";
		}
		
		String rackId = updatedRack.getId().toString();
		
		return "redirect:/racks/"+rackId;
	}
	
//	@PutMapping("/edit-rack/{id}")
//	public String updateRack( @Valid @ModelAttribute("editRack") Rack editRack,
//							BindingResult bindingResult,
//							Model model, HttpSession session,
//							@PathVariable("id") Long rackId) {
//				
//		if( !session.getAttribute("climberId").equals( editRack.getClimber().getId()) ) {
//			return "redirect:/racks/"+rackId;
//		}
//		
//		if( bindingResult.hasErrors() ) {
//			return "rackedit.jsp";
//		}
//		
//		rackService.updateRack( editRack, bindingResult );
//		
//		if( bindingResult.hasFieldErrors( "climber" ) ) {
//			return "redirect:/climbers/log-climber-out";
//		}
//		
//		if( bindingResult.hasFieldErrors( "id" ) ) {
//			return "redirect:/racks";
//		}
//		
//		
//		return "redirect:/racks/"+rackId;
//	}
	
	@DeleteMapping("/delete-rack/{rackId}")
	public String destroyRack( @PathVariable("rackId") Long rackId,
							   HttpSession session ) {
		
		rackService.deleteRackById(rackId, (Long) session.getAttribute("climberId"));
		
		return "redirect:/racks";
	}
	
}
