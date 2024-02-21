package com.javadevelop.dto;

import lombok.Data;

@Data
public class ProductDTO extends BaseDTO<ProductDTO>{
	private String code;
	private String name;
	private String description;
	private String shortDescription;
	private String categoryCode;
	
}
