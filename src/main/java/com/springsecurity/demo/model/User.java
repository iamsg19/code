package com.springsecurity.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users", schema = "CM")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	
	@Column(name = "emailId")
	private String emailId;
	
	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	private boolean isEnabled;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles", schema = "CM",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    private Set<Role> roles = new HashSet<>();
	
	public User(long id, String emailId, String password, String name, boolean isEnabled) {
		super();
		this.id = id;
		this.emailId = emailId;
		this.password = password;
		this.name = name;
		this.isEnabled = isEnabled;
	}

	public User(User user) {

		this.isEnabled = user.isEnabled();
		this.id = user.getId();
		this.emailId = user.getEmailId();
		this.name = user.getName();
		this.password = user.getPassword();
	}

	public User() {
		super();
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", emailId=" + emailId + ", password=" + password + ", name=" + name + ", isEnabled="
				+ isEnabled + "]";
	}

}
