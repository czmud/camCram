package com.czmud.camcram.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="racks")
public class Rack {

	// ID & FOREIGN KEYS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn( name="climber_id" )
	private Climber climber;
	
	@NotNull
	@NotBlank(message="Route name must not be blank")
	private String route;
	
	
	@NotNull
	@NotBlank(message="Climbing Area must not be blank")
	private String area;
	
	@NotNull(message="Date of climb must not be blank")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateClimbed;
	
	private String notes;
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    public Rack() {}
    
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    
    // UNIQUE METHODS
    
    // GETTERS & SETTERS
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

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public Date getDateClimbed() {
		return dateClimbed;
	}

	public void setDateClimbed(Date dateClimbed) {
		this.dateClimbed = dateClimbed;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
    
}
