package com.javadevelop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javadevelop.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

	CategoryEntity findOneByCode(String code);
}
