package com.czmud.camcram.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vans")
public class Van {

	// ID & FOREIGN KEYS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@OneToOne(mappedBy = "van")
	private Climber climber;

	@OneToMany(mappedBy = "van")
	private List<ProtectionsInVan> protectionsInVans;
	
	@OneToMany(mappedBy = "van")
	private List<BelayDevicesInVan> belayDevicesInVans;
	
	@OneToMany(mappedBy = "van")
	private List<DrawsInVan> drawsInVans;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	public Van() {
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
		this.updatedAt = this.createdAt;
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	// UNIQUE METHODS
	public double getProtectionsWeight() {
		double weightSum = 0;
		for( ProtectionsInVan oneProtectionsInVan : this.protectionsInVans ) {
			weightSum = weightSum + oneProtectionsInVan.getProtection().getWeight() * oneProtectionsInVan.getCount();
		}
		return weightSum;
	}
	
	public String displayProtectionsWeight() {
		double weightSum = this.getProtectionsWeight();
		if( weightSum > 1000 ) {
			weightSum = weightSum / 1000;
			return String.format("%.2f kg", weightSum );
		}
		return String.format("%.0f g", weightSum );
	}
	
	public double getBelayDevicesWeight() {
		double weightSum = 0;
		for( BelayDevicesInVan oneBelayDevicessInVan : this.belayDevicesInVans ) {
			weightSum = weightSum + oneBelayDevicessInVan.getBelayDevice().getWeight() * oneBelayDevicessInVan.getCount();
		}
		return weightSum;
	}
	
	public String displayBelayDevicesWeight() {
		double weightSum = this.getBelayDevicesWeight();
		if( weightSum > 1000 ) {
			weightSum = weightSum / 1000;
			return String.format("%.2f kg", weightSum );
		}
		return String.format("%.0f g", weightSum );
	}
	
	public double getDrawsWeight() {
		double weightSum = 0;
		for( DrawsInVan oneDrawsInVan : this.drawsInVans ) {
			weightSum = weightSum + oneDrawsInVan.getDraw().getWeight() * oneDrawsInVan.getCount();
		}
		return weightSum;
	}
	
	public String displayDrawsWeight() {
		double weightSum = this.getDrawsWeight();
		if( weightSum > 1000 ) {
			weightSum = weightSum / 1000;
			return String.format("%.2f kg", weightSum );
		}
		return String.format("%.0f g", weightSum );
	}
	
	public double getTotalWeight() {
		return this.getProtectionsWeight() + this.getDrawsWeight() + this.getBelayDevicesWeight();
	}
	
	public String displayTotalWeight() {
		double weightSum = this.getTotalWeight();
		if( weightSum > 1000 ) {
			weightSum = weightSum / 1000;
			return String.format("%.2f kg", weightSum );
		}
		return String.format("%.0f g", weightSum );
	}
	
	public String displayTotalWeightImperial() {
		double weightGrams = this.getTotalWeight();
		Long pounds = Math.round(weightGrams / 453.5924);
		double weightGramsRemainder = weightGrams % 453.5924;
		Long oz = Math.round(weightGramsRemainder / 28.3495);
		
		if( pounds > 0 ) {
			return String.format("%d lb %d oz", pounds, oz);
		}
		return String.format("%d oz", oz);
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Climber getClimber() {
		return climber;
	}

	public void setClimber(Climber climber) {
		this.climber = climber;
	}

	public List<ProtectionsInVan> getProtectionsInVans() {
		return protectionsInVans;
	}

	public void setProtectionsInVans(List<ProtectionsInVan> protectionsInVans) {
		this.protectionsInVans = protectionsInVans;
	}
	
	public List<BelayDevicesInVan> getBelayDevicesInVans() {
		return belayDevicesInVans;
	}

	public void setBelayDevicesInVans(List<BelayDevicesInVan> belayDevicesInVans) {
		this.belayDevicesInVans = belayDevicesInVans;
	}

	public List<DrawsInVan> getDrawsInVans() {
		return drawsInVans;
	}

	public void setDrawsInVans(List<DrawsInVan> drawsInVans) {
		this.drawsInVans = drawsInVans;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
