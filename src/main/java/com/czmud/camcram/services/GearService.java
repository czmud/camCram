package com.czmud.camcram.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czmud.camcram.models.BelayDevice;
import com.czmud.camcram.models.Draw;
import com.czmud.camcram.models.Protection;
import com.czmud.camcram.repositories.BelayDeviceRepository;
import com.czmud.camcram.repositories.DrawRepository;
import com.czmud.camcram.repositories.ProtectionRepository;

@Service
public class GearService {
	@Autowired
	private ProtectionRepository protectionRepository;
	@Autowired
	private BelayDeviceRepository belayDeviceRepository;
	@Autowired
	private DrawRepository drawRepository;
	
	public List<Protection> getAllProtections(){
		return protectionRepository.findAll();
	}
	public List<BelayDevice> getAllBelayDevices(){
		return belayDeviceRepository.findAll();
	}
	public List<Draw> getAllDraws(){
		return drawRepository.findAll();
	}
}
