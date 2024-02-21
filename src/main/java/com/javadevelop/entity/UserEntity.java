package com.javadevelop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
	
	@Column(name="username")
	private String userName;
	
	@Column
	private String password;
	
	@Column(name="fullname")
	private String fullName;
	
	@Column
	private Integer status;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="userRole", joinColumns = @JoinColumn(name = "userId"),
								inverseJoinColumns = @JoinColumn(name = "roleId"))
	private List<RoleEntity> roles = new ArrayList<>();
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public UserEntity() {
	}
}
