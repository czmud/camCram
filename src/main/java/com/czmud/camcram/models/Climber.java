package com.czmud.camcram.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="climbers")
public class Climber {

	// ID & FOREIGN KEYS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "van_id", referencedColumnName = "id" )
	private Van van;
	
	@OneToMany(mappedBy="climber", fetch = FetchType.LAZY)
	private List<Rack> racks;
	
	@NotNull
	@NotBlank(message="First name must not be blank")
	@Size( min=2, max=45, message="First name must be between 2 and 45 characters" )
	private String firstName;
	@NotNull
	@NotBlank(message="Last name must not be blank")
	@Size( min=2, max=45, message="Last name must be between 2 and 45 characters" )
	private String lastName;
	@NotNull
	@NotBlank(message="email must not be blank")
	@Email( message="Must enter a valid email")
	private String email;
	
	@Transient
	@NotNull
	@NotBlank(message="password must not be blank")
	@Size( min=8, max=128, message="password must be at leaset 8 characters long")
	private String password;
	@Transient
	@NotNull
	@Size( min=8, max=128, message="password must be at leaset 8 characters long")
	private String confirmPassword;
	
	private String passwordHash;
	
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    public Climber() {}
    
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
    public String fullName() {
    	return String.format("%s %s", firstName, lastName);
    }

    // GETTERS & SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
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

	public Van getVan() {
		return van;
	}

	public void setVan(Van van) {
		this.van = van;
	}

	public List<Rack> getRacks() {
		return racks;
	}

	public void setRacks(List<Rack> racks) {
		this.racks = racks;
	}
	
}
