package com.javadevelop.repository;

import com.javadevelop.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findOneByCode(String code);
}
