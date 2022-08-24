package com.czmud.camcram.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.czmud.camcram.models.Van;

@Repository
public interface VanRepository extends CrudRepository<Van, Long> {
	
}
