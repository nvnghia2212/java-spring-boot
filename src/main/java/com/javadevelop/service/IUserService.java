package com.javadevelop.service;

import com.javadevelop.dto.ProductDTO;
import com.javadevelop.dto.UserDTO;
import com.javadevelop.entity.UserEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    List<UserDTO> findAll();

    UserDTO save(UserDTO userDTO);

    void delete(long[] ids);

    List<UserDTO> findAll(Pageable pageable);

    int totalItem();

    UserDTO findOneById(Long userid);
    UserDTO findOneByUserName(String userName);
}
