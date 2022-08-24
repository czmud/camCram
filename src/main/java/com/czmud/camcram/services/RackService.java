package com.czmud.camcram.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.czmud.camcram.models.Rack;
import com.czmud.camcram.repositories.RackRepository;

@Service
public class RackService {
	@Autowired
	private RackRepository rackRepository;
	
	public Rack getRackById( Long id ) {
		Optional<Rack> checkForRack = rackRepository.findById( id );
		if( checkForRack.isEmpty() ) {
			return null;
		}
		return checkForRack.get();
	}
	
	public Rack createNewRack( Rack newRack, BindingResult bindingResult ) {		
		return rackRepository.save( newRack );
	}
	
	public void deleteRackById( Long rackId, Long climberId ) {
		
		Optional<Rack> checkForRack = rackRepository.findById( rackId );
		if( checkForRack.isEmpty() ) {
			return;
		}
		
		Rack deleteRack = checkForRack.get();
		
		if( ! deleteRack.getClimber().getId().equals(climberId) ) {
			return;
		}
		
		rackRepository.deleteById( rackId );
	}
	
}
