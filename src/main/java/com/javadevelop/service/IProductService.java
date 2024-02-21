package com.javadevelop.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.javadevelop.dto.ProductDTO;

public interface IProductService {

	List<ProductDTO> findAll();
	ProductDTO save(ProductDTO productDTO);
	void delete(long[] ids);
	
	List<ProductDTO> findAll(Pageable pageable);
	int totalItem();
}
