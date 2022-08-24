package com.czmud.camcram.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.czmud.camcram.models.Climber;
import com.czmud.camcram.models.LoginClimber;
import com.czmud.camcram.models.Van;
import com.czmud.camcram.repositories.ClimberRepository;

@Service
public class ClimberService {
	@Autowired
	private ClimberRepository climberRepository;
	
	public Climber createNewClimber( Climber newClimber, BindingResult result ) {
		
		Optional<Climber> checkForClimber = climberRepository.findByEmail( newClimber.getEmail() );
		if( checkForClimber.isPresent() ) {
			result.rejectValue("email", "Matches", "Account with same email already exists");
		}
		
		if( ! newClimber.getPassword().equals(newClimber.getConfirmPassword() )) {
			result.rejectValue("confirmPassword", "Matches", "password confirmation and password must match" );
		}
		
		if(result.hasErrors() ) {
			return null;
		}
		
		newClimber.setPasswordHash( BCrypt.hashpw(newClimber.getPassword(), BCrypt.gensalt()));
		newClimber.setVan( new Van() );
		
		return climberRepository.save( newClimber );
	}
	
	public Climber login( LoginClimber newLoginClimber, BindingResult bindingResult) {
		
		Optional<Climber> checkForClimber = climberRepository.findByEmail( newLoginClimber.getEmail() );
		if( checkForClimber.isEmpty() ) {
			bindingResult.rejectValue("email", "Matches", "Email not found");
			return null;
		}
		Climber climber = checkForClimber.get();
		
		if(!BCrypt.checkpw(newLoginClimber.getPassword(), climber.getPasswordHash() )) {
			bindingResult.rejectValue("password", "Matches", "Invalid Password");
		}
		
		if( bindingResult.hasErrors() ) {
		    return null;
		}
		return climber;
	}
	
	
	public Climber getClimberById( Long id ) {
		Optional<Climber> checkForClimber = climberRepository.findById( id );
		if( checkForClimber.isEmpty() ) {
			return null;
		}
		return checkForClimber.get();
	}
	
	
	
	
}
