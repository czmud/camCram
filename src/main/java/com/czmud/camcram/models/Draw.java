package com.czmud.camcram.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.czmud.camcram.abstractModels.Gear;

@Entity
@Table(name="draws")
public class Draw extends Gear {
	private boolean isQuickDraw;
	
	private double strength;
	
	public Draw() {
		super();
	}

	public boolean isQuickDraw() {
		return isQuickDraw;
	}

	public void setQuickDraw(boolean isQuickDraw) {
		this.isQuickDraw = isQuickDraw;
	}

	public double getStrength() {
		return strength;
	}

	public void setStrength(double strength) {
		this.strength = strength;
	}
	
}
