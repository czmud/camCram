package com.czmud.camcram.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.czmud.camcram.models.BelayDevice;

@Repository
public interface BelayDeviceRepository extends CrudRepository<BelayDevice, Long> {
	List<BelayDevice> findAll();
	
	Optional<BelayDevice> findById( Long id );
}
