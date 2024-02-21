package com.javadevelop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javadevelop.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
