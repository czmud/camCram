package com.czmud.camcram.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.czmud.camcram.models.Draw;

@Repository
public interface DrawRepository extends CrudRepository<Draw, Long> {
	List<Draw> findAll();
	
	Optional<Draw> findById( Long id );
}
