package com.javadevelop.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Data
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

}
