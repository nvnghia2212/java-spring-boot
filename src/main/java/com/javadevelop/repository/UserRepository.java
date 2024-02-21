package com.javadevelop.repository;

import com.javadevelop.entity.RoleEntity;
import com.javadevelop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findOneById(Long id);

    UserEntity findOneByUserName(String userName);
}
