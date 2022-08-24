package com.czmud.camcram.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.czmud.camcram.abstractModels.Gear;

@Entity
@Table(name="belay_devices")
public class BelayDevice extends Gear {
	
	private boolean isAssistedBreaking;
	
	public BelayDevice() {
		super();
	}

	public boolean isAssistedBreaking() {
		return isAssistedBreaking;
	}

	public void setAssistedBreaking(boolean isAssistedBreaking) {
		this.isAssistedBreaking = isAssistedBreaking;
	}

}
