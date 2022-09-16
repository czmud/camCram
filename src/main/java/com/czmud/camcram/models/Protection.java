package com.czmud.camcram.models;

import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.czmud.camcram.abstractModels.Gear;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "protections")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "protection_type", discriminatorType = DiscriminatorType.INTEGER)
public class Protection extends Gear {

	@JsonIgnore
	@OneToMany(mappedBy = "protection")
	private List<ProtectionsInVan> protectionsInVans;

	@NotNull
	private double strength;

	@NotNull
	private double minRange;

	@NotNull
	private double maxRange;

	public Protection() {
		super();
	}

	// UNIQUE METHODS
	public double getSafeMinRange() {
		return this.minRange;
	}

	public double getSafeMaxRange() {
		return this.maxRange;
	}
	
	public double getNominalRange() {
		return (this.getSafeMaxRange() + this.getSafeMinRange()) / 2;
	}

	// GETTERS & SETTERS
	public double getMinRange() {
		return minRange;
	}

	public void setMinRange(double minRange) {
		this.minRange = minRange;
	}

	public double getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(double maxRange) {
		this.maxRange = maxRange;
	}

	public double getStrength() {
		return strength;
	}

	public void setStrength(double strength) {
		this.strength = strength;
	}

	public List<ProtectionsInVan> getProtectionsInVans() {
		return protectionsInVans;
	}

	public void setProtectionsInVans(List<ProtectionsInVan> protectionsInVans) {
		this.protectionsInVans = protectionsInVans;
	}

}
