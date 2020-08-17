package com.hygogg.cookbook.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Entity
@Table(name="users")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, message="Username must be 3 characters or longer!")
	@Size(max=200, message="Username must be shorter than 200 characters!")
	private String username;
	
	@Size(min=1, message="Email is required!")
	@Size(max=200, message="Email must be shorter than 200 characters!")
	@Email(message="Invalid Email!")
	private String email;
	
	@Size(min=8, message="Password must be 8 characters or longer!")
	@Size(max=200, message="Password must be shorter than 200 characters!")
	private String password;
	
    @Transient
	private String confirm;
	
	@Column(updatable=false)
    private Date createdAt;
	
    private Date updatedAt;

	public User(String username, String email, String password, String confirm) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
	}
	
	public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
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
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate(){
    	this.updatedAt = new Date();
    }
    
    
	
}
