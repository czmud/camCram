package com.czmud.camcram.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.czmud.camcram.models.Climber;
import com.czmud.camcram.models.LoginClimber;

@Controller
public class MainController {
	@GetMapping("/")
	public String index( @ModelAttribute("newClimber") Climber newClimber,
						 @ModelAttribute("newLoginClimber") LoginClimber newLoginClimber) {
	return "index.jsp";
	}
	
	@GetMapping("/register")
	public String registrationForm(@ModelAttribute("newClimber") Climber newClimber ) {
		return "registration.jsp";
	}
}
