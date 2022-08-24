package com.czmud.camcram.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.czmud.camcram.models.Climber;

@Repository
public interface ClimberRepository extends CrudRepository<Climber, Long> {
	Optional<Climber> findByEmail( String email );
	
	Optional<Climber> findById( Long id );
}
