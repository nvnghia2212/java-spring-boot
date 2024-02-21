package com.javadevelop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javadevelop.converter.ProductConverter;
import com.javadevelop.dto.ProductDTO;
import com.javadevelop.entity.CategoryEntity;
import com.javadevelop.entity.ProductEntity;
import com.javadevelop.repository.CategoryRepository;
import com.javadevelop.repository.ProductRepository;
import com.javadevelop.service.IProductService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductConverter productConverter;

	@Override
	public List<ProductDTO> findAll() {
		ProductDTO productDTO = new ProductDTO();
		List<ProductDTO> arrList = new ArrayList<>();
		for(ProductEntity productEntity : productRepository.findAll()) {
			productDTO = productConverter.toProductDTO(productEntity);
			arrList.add(productDTO);
		}
		return arrList;
	}
	
	@Override
	@Transactional
	public ProductDTO save(ProductDTO productDTO) {

		ProductEntity newEntity = new ProductEntity();
		if (productDTO.getId() == null) {
			newEntity = productConverter.toProductEntity(productDTO);
		} else {
			ProductEntity oldNewEntity = productRepository.findOne(productDTO.getId());
			newEntity = productConverter.toProductEntity(productDTO, oldNewEntity);
		}
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(productDTO.getCategoryCode());
		newEntity.setCategory(categoryEntity);
		newEntity = productRepository.save(newEntity);
		return productConverter.toProductDTO(newEntity);
	}

	@Override
	public void delete(long[] ids) {
		for (long item : ids) {
			productRepository.delete(item);
		}
	}

	@Override
	public List<ProductDTO> findAll(Pageable pageable) {
		ProductDTO productDTO = new ProductDTO();
		List<ProductDTO> arrList = new ArrayList<>();
		for(ProductEntity newEntity : productRepository.findAll(pageable)) {
			productDTO = productConverter.toProductDTO(newEntity);
//			productDTO.setPage(pageable.getPageNumber() + 1);
			arrList.add(productDTO);
		}
		return arrList;
	}

	@Override
	public int totalItem() {
		return (int) productRepository.count();
	}
}
