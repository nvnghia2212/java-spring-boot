package com.javadevelop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.javadevelop.dto.BaseDTO;
import com.javadevelop.dto.ProductDTO;
import com.javadevelop.service.IProductService;

@RestController
@RequestMapping(value = "product", produces = "application/json")
public class ProductController {
	
	@Autowired
	private IProductService productService;

	@GetMapping
	public BaseDTO<ProductDTO> getProduct(@RequestParam(value = "page", required = false) Integer page,
									  @RequestParam(value = "iteminpage", required = false) Integer iteminpage ){
		BaseDTO<ProductDTO> productBaseDTO = new BaseDTO<>();
		if (page != null) {
			productBaseDTO.setPage(page);
			productBaseDTO.setTotalPage((int)Math.ceil(productService.totalItem()/iteminpage));
			Pageable pageable = new PageRequest(page - 1, iteminpage);
			productBaseDTO.setListResult(productService.findAll(pageable));
		}else {
			productBaseDTO.setListResult(productService.findAll());
		}
		return productBaseDTO;
	}
	
	@PostMapping
	public ProductDTO createProduct(@RequestBody ProductDTO model ) {
		return productService.save(model);
	}
	
	@PutMapping(value="/{id}")
	public ProductDTO insertProduct(@RequestBody ProductDTO model, @PathVariable("id") long id ) {
		model.setId(id);
		return productService.save(model);
	}
	
	@DeleteMapping
	public void deleteProduct(@RequestBody long[] ids ) {
		productService.delete(ids);
	}
}
