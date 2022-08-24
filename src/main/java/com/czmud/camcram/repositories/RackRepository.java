package com.czmud.camcram.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.czmud.camcram.models.Rack;

@Repository
public interface RackRepository extends CrudRepository<Rack, Long> {
	Optional<Rack> findById( Long id );
}
