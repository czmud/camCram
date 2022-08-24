package com.czmud.camcram.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.czmud.camcram.models.DrawsInVan;

@Repository
public interface DrawsInVanRepository extends CrudRepository<DrawsInVan, Long> {
	Optional<DrawsInVan> findOneByDrawIdAndVanId( Long drawId, Long vanId );
	
	Optional<DrawsInVan> findById( Long id );
}
