package com.czmud.camcram.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Cam extends Protection {

	private double minRangeSafetyFactor;
	
	private double maxRangeSafetyFactor;
	
	public Cam() {
		super();
	}
	
	
	// SUPERCLASS METHODS
	@Override
	public double getSafeMinRange() {
		return this.getMinRange() * this.minRangeSafetyFactor;
	}
	@Override
	public double getSafeMaxRange() {
		return this.getMaxRange() * this.maxRangeSafetyFactor;
	}
	
	
	// GETTERS AND SETTERS
	public double getMinRangeSafetyFactor() {
		return minRangeSafetyFactor;
	}

	public void setMinRangeSafetyFactor(double minRangeSafetyFactor) {
		this.minRangeSafetyFactor = minRangeSafetyFactor;
	}

	public double getMaxRangeSafetyFactor() {
		return maxRangeSafetyFactor;
	}

	public void setMaxRangeSafetyFactor(double maxRangeSafetyFactor) {
		this.maxRangeSafetyFactor = maxRangeSafetyFactor;
	}

}
