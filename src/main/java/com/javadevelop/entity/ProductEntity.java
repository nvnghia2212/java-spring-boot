package com.javadevelop.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {
	
	@Column
	private String code;
	
	@Column
	private String name;
	
	@Column(name = "shortdescription")
	private String shortDescription;
	
	@Column(columnDefinition = "Text")
	private String description;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	private CategoryEntity category;

}
