package com.czmud.camcram.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czmud.camcram.models.BelayDevice;
import com.czmud.camcram.models.BelayDevicesInVan;
import com.czmud.camcram.models.Draw;
import com.czmud.camcram.models.DrawsInVan;
import com.czmud.camcram.models.Protection;
import com.czmud.camcram.models.ProtectionsInVan;
import com.czmud.camcram.models.Van;
import com.czmud.camcram.repositories.BelayDeviceRepository;
import com.czmud.camcram.repositories.BelayDevicesInVanRepository;
import com.czmud.camcram.repositories.DrawRepository;
import com.czmud.camcram.repositories.DrawsInVanRepository;
import com.czmud.camcram.repositories.ProtectionRepository;
import com.czmud.camcram.repositories.ProtectionsInVanRepository;

@Service
public class VanService {
	@Autowired
	private ProtectionRepository protectionRepository;
	@Autowired 
	private ProtectionsInVanRepository protectionsInVanRepository;
	@Autowired
	private DrawRepository drawRepository;
	@Autowired 
	private DrawsInVanRepository drawsInVanRepository;
	@Autowired
	private BelayDeviceRepository belayDeviceRepository;
	@Autowired 
	private BelayDevicesInVanRepository belayDevicesInVanRepository;
	
	public ProtectionsInVan addProtectionToVan( Van updateVan, Long protectionId ) {
		
		// search to see if same piece of protection is already in climbers van
		// if found, increment counter
		Optional<ProtectionsInVan> checkForExisting = protectionsInVanRepository.findOneByProtectionIdAndVanId(protectionId, updateVan.getId() );
		if( checkForExisting.isPresent() ) {
			ProtectionsInVan updateProtectionsInVan = checkForExisting.get();
			updateProtectionsInVan.setCount( updateProtectionsInVan.getCount() + 1 );
			return protectionsInVanRepository.save( updateProtectionsInVan );
		}
		
		// else if not existing
		// verify valid protection id was given
		Optional<Protection> checkForProtection = protectionRepository.findById( protectionId );
		if( checkForProtection.isEmpty() ) {
			return null;
		}

		Protection addProtection = checkForProtection.get();

		// and create new entry for climbers van with selected protection
		ProtectionsInVan addProtectionsInVan = new ProtectionsInVan();
		addProtectionsInVan.setProtection(addProtection);
		addProtectionsInVan.setVan(updateVan);
		
		return protectionsInVanRepository.save( addProtectionsInVan );
		
	}
	
	public ProtectionsInVan removeProtectionFromVan( Long protectionsInVanId ) {
		
		Optional<ProtectionsInVan> checkForExisting = protectionsInVanRepository.findById( protectionsInVanId );
		if( checkForExisting.isEmpty() ) {
			return null;
		}
		ProtectionsInVan updateProtectionsInVan = checkForExisting.get();
		
		if(updateProtectionsInVan.getCount() <= 1) {
			protectionsInVanRepository.delete(updateProtectionsInVan);
			updateProtectionsInVan.setCount(0);
			return updateProtectionsInVan;
		}
		
		updateProtectionsInVan.setCount( updateProtectionsInVan.getCount() - 1 );
		return protectionsInVanRepository.save( updateProtectionsInVan );
	}
	
	public BelayDevicesInVan addBelayDeviceToVan( Van updateVan, Long belayDeviceId ) {
		
		// search to see if same belayDevice is already in climbers van
		// if found, increment counter
		Optional<BelayDevicesInVan> checkForExisting = belayDevicesInVanRepository.findOneByBelayDeviceIdAndVanId(belayDeviceId, updateVan.getId() );
		if( checkForExisting.isPresent() ) {
			BelayDevicesInVan updateBelayDevicesInVan = checkForExisting.get();
			updateBelayDevicesInVan.setCount( updateBelayDevicesInVan.getCount() + 1 );
			return belayDevicesInVanRepository.save( updateBelayDevicesInVan );
		}
		
		// else if not existing
		// verify valid belayDevice id was given
		Optional<BelayDevice> checkForBelayDevice = belayDeviceRepository.findById( belayDeviceId );
		if( checkForBelayDevice.isEmpty() ) {
			return null;
		}

		BelayDevice addBelayDevice = checkForBelayDevice.get();

		// and create new entry for climbers van with selected belayDevice
		BelayDevicesInVan addBelayDevicesInVan = new BelayDevicesInVan();
		addBelayDevicesInVan.setBelayDevice(addBelayDevice);
		addBelayDevicesInVan.setVan(updateVan);
		
		return belayDevicesInVanRepository.save( addBelayDevicesInVan );
		
	}
	
	public BelayDevicesInVan removeBelayDeviceFromVan( Long belayDevicesInVanId ) {
		
		Optional<BelayDevicesInVan> checkForExisting = belayDevicesInVanRepository.findById( belayDevicesInVanId );
		if( checkForExisting.isEmpty() ) {
			return null;
		}
		BelayDevicesInVan updateBelayDevicesInVan = checkForExisting.get();
		
		if(updateBelayDevicesInVan.getCount() <= 1) {
			belayDevicesInVanRepository.delete(updateBelayDevicesInVan);
			updateBelayDevicesInVan.setCount(0);
			return updateBelayDevicesInVan;
		}
		
		updateBelayDevicesInVan.setCount( updateBelayDevicesInVan.getCount() - 1 );
		return belayDevicesInVanRepository.save( updateBelayDevicesInVan );
	}
	
	public DrawsInVan addDrawToVan( Van updateVan, Long drawId ) {
		
		// search to see if same piece of draw is already in climbers van
		// if found, increment counter
		Optional<DrawsInVan> checkForExisting = drawsInVanRepository.findOneByDrawIdAndVanId(drawId, updateVan.getId() );
		if( checkForExisting.isPresent() ) {
			DrawsInVan updateDrawsInVan = checkForExisting.get();
			updateDrawsInVan.setCount( updateDrawsInVan.getCount() + 1 );
			return drawsInVanRepository.save( updateDrawsInVan );
		}
		
		// else if not existing
		// verify valid draw id was given
		Optional<Draw> checkForDraw = drawRepository.findById( drawId );
		if( checkForDraw.isEmpty() ) {
			return null;
		}

		Draw addDraw = checkForDraw.get();

		// and create new entry for climbers van with selected draw
		DrawsInVan addDrawsInVan = new DrawsInVan();
		addDrawsInVan.setDraw(addDraw);
		addDrawsInVan.setVan(updateVan);
		
		return drawsInVanRepository.save( addDrawsInVan );
		
	}
	
	public DrawsInVan removeDrawFromVan( Long drawsInVanId ) {
		
		Optional<DrawsInVan> checkForExisting = drawsInVanRepository.findById( drawsInVanId );
		if( checkForExisting.isEmpty() ) {
			return null;
		}
		DrawsInVan updateDrawsInVan = checkForExisting.get();
		if(updateDrawsInVan.getCount() <= 1) {
			drawsInVanRepository.delete(updateDrawsInVan);
			updateDrawsInVan.setCount(0);
			return updateDrawsInVan;
		}
		
		updateDrawsInVan.setCount( updateDrawsInVan.getCount() - 1 );
		return drawsInVanRepository.save( updateDrawsInVan );
	}
}
