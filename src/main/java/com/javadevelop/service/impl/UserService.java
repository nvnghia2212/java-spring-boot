package com.javadevelop.service.impl;

import com.javadevelop.converter.UserConverter;
import com.javadevelop.dto.UserDTO;
import com.javadevelop.entity.UserEntity;
import com.javadevelop.repository.UserRepository;
import com.javadevelop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserConverter userConverter;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> findAll() {
        UserDTO userDTO = new UserDTO();
        List<UserDTO> arrList = new ArrayList<>();
        for(UserEntity userEntity : userRepository.findAll()) {
            userDTO = userConverter.toUserDTO(userEntity);
            arrList.add(userDTO);
        }
        return arrList;
    }

    @Override
    @Transactional
    public UserDTO save(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        if (userDTO.getId() == null) {
            userEntity = userConverter.toUserEntity(userDTO);
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        } else {
            UserEntity oldUserEntity = userRepository.findOne(userDTO.getId());
            userEntity = userConverter.toUserEntity(userDTO, oldUserEntity);
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        }
        userEntity = userRepository.save(userEntity);
        return userConverter.toUserDTO(userEntity);
    }

    @Override
    public void delete(long[] ids) {
        for (long item : ids) {
            userRepository.delete(item);
        }
    }

    @Override
    public List<UserDTO> findAll(Pageable pageable) {
        UserDTO userDTO = new UserDTO();
        List<UserDTO> arrList = new ArrayList<>();
        for(UserEntity userEntity : userRepository.findAll(pageable)) {
            userDTO = userConverter.toUserDTO(userEntity);
//			productDTO.setPage(pageable.getPageNumber() + 1);
            arrList.add(userDTO);
        }
        return arrList;
    }

    @Override
    public int totalItem() {
        return (int) userRepository.count();
    }

    @Override
    public UserDTO findOneById(Long userid) {
        UserEntity userEntity = userRepository.findOneById(userid);
        return userConverter.toUserDTO(userEntity);
    }

    @Override
    public UserDTO findOneByUserName(String userName) {
        UserEntity userEntity = userRepository.findOneByUserName(userName);
        return userConverter.toUserDTO(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDTO userDTO = new UserDTO();
        UserEntity userEntity = userRepository.findOneByUserName(userName);
        if (userEntity == null){
            throw new UsernameNotFoundException(userName);
        }else {
            userDTO = userConverter.toUserDTO(userEntity);
        }
        return userDTO;
    }
}
