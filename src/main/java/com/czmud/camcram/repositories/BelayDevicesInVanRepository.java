package com.czmud.camcram.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.czmud.camcram.models.BelayDevicesInVan;

@Repository
public interface BelayDevicesInVanRepository extends CrudRepository<BelayDevicesInVan, Long> {
	Optional<BelayDevicesInVan> findOneByBelayDeviceIdAndVanId( Long belayDeviceId, Long vanId );
	
	Optional<BelayDevicesInVan> findById( Long id );
}
