package com.czmud.camcram.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.czmud.camcram.models.Climber;
import com.czmud.camcram.models.LoginClimber;
import com.czmud.camcram.services.ClimberService;

@Controller
@RequestMapping("/climbers")
public class ClimberController {
	@Autowired
	private ClimberService climberService;
	
	@PostMapping("/create-new-climber")
	public String createNewClimber( @Valid @ModelAttribute("newClimber") Climber newClimber,
								    BindingResult bindingResult, HttpSession session) {
		
		newClimber = climberService.createNewClimber( newClimber, bindingResult );
		
		if( bindingResult.hasErrors() ) {
			session.invalidate();
			return "registration.jsp";
		}
		
		Long climberId = newClimber.getId();
		session.setAttribute("climberId", climberId );
		
		return "redirect:/van";
	}
	
	@PostMapping("/log-climber-in")
	public String logClimberIn( @Valid @ModelAttribute("newLoginClimber") LoginClimber newLoginClimber,
							    BindingResult bindingResult, HttpSession session) {
		
		if( bindingResult.hasErrors() ) {
			session.invalidate();
			return "index.jsp";
		}
		
		Climber climber = climberService.login( newLoginClimber, bindingResult );
		
		if( bindingResult.hasErrors() ) {
			session.invalidate();
			return "index.jsp";
		}
			
		Long climberId = climber.getId();
		session.setAttribute("climberId", climberId ); 
		
		return "redirect:/van";
	}
	
	@GetMapping("/log-climber-out")
	public String logClimberOut( HttpSession session ) {
		
		session.invalidate();
		return "redirect:/";
	}
}
