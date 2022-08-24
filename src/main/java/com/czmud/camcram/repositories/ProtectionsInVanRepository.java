package com.czmud.camcram.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.czmud.camcram.models.ProtectionsInVan;

@Repository
public interface ProtectionsInVanRepository extends CrudRepository<ProtectionsInVan, Long> {
	Optional<ProtectionsInVan> findOneByProtectionIdAndVanId( Long protectionId, Long vanId );
	
	Optional<ProtectionsInVan> findById( Long id );
}
