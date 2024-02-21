package com.javadevelop.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {
	
	@Column
	private String code;
	
	@Column
	private String name;

	@ManyToMany(mappedBy = "roles")
	private List<UserEntity> users = new ArrayList<>();

}
