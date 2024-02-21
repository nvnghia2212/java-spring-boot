package com.javadevelop.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.javadevelop.dto.ProductDTO;
import com.javadevelop.entity.ProductEntity;

@Component
public class ProductConverter {

	//insert
	public ProductEntity toProductEntity(ProductDTO productDTO) {
		ProductEntity entity = new ProductEntity();
		entity.setCode(productDTO.getCode());
		entity.setDescription(productDTO.getName());
		entity.setShortDescription(productDTO.getShortDescription());
		entity.setName(productDTO.getDescription());
		return entity;
	}

	//get
	public ProductDTO toProductDTO(ProductEntity productEntity) {
		ProductDTO productDTO = new ProductDTO();
		if(productEntity.getId() != null) {
			productDTO.setId(productEntity.getId());
		}
		productDTO.setCode(productEntity.getCode());
		productDTO.setName(productEntity.getName());
		productDTO.setShortDescription(productEntity.getShortDescription());
		productDTO.setDescription(productEntity.getDescription());
		productDTO.setCategoryCode(productEntity.getCategory().getCode());
//		productDTO.setCreatedBy(productEntity.getCreatedBy());
//		productDTO.setCreatedDate(productEntity.getCreatedDate());
//		productDTO.setModifiedBy(productEntity.getModifiedBy());
//		productDTO.setModifiefdDate(productEntity.getModifiefdDate());
		return productDTO;
	}

	//update
	public ProductEntity toProductEntity(ProductDTO productDTO, ProductEntity entity) {
		entity.setCode(productDTO.getCode());
		entity.setDescription(productDTO.getName());
		entity.setShortDescription(productDTO.getShortDescription());
		entity.setName(productDTO.getDescription());
		return entity;
	}
	
	public List<ProductDTO> toProductDTO(List<ProductEntity> productEntities) {
		ProductDTO productDTO = new ProductDTO();
		List<ProductDTO> arrayList = new ArrayList<>();
		for(ProductEntity newEntity : productEntities) {
			if(newEntity.getId() != null) {
				productDTO.setId(newEntity.getId());
			}
			productDTO.setCode(newEntity.getCode());
			productDTO.setName(newEntity.getDescription());
			productDTO.setShortDescription(newEntity.getShortDescription());
			productDTO.setDescription(newEntity.getName());
//			productDTO.setCreatedBy(newEntity.getCreatedBy());
//			productDTO.setCreatedDate(newEntity.getCreatedDate());
//			productDTO.setModifiedBy(newEntity.getModifiedBy());
//			productDTO.setModifiefdDate(newEntity.getModifiefdDate());
			arrayList.add(productDTO);
		}
		return arrayList;
	}
		
	
}
