package com.javadevelop.converter;

import com.javadevelop.dto.RoleDTO;
import com.javadevelop.dto.UserDTO;
import com.javadevelop.entity.RoleEntity;
import com.javadevelop.entity.UserEntity;
import com.javadevelop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    @Autowired
    RoleRepository roleRepository;

    // get
    public UserDTO toUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        if(userEntity.getId() != null) {
            userDTO.setId(userEntity.getId());
        }
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setFullName(userEntity.getFullName());
        userDTO.setStatus(userEntity.getStatus());
//        userDTO.setCreatedBy(userEntity.getCreatedBy());
//        userDTO.setCreatedDate(userEntity.getCreatedDate());
//        userDTO.setModifiedBy(userEntity.getModifiedBy());
//        userDTO.setModifiefdDate(userEntity.getModifiefdDate());

        userDTO.setRoles(addListRoleDTO(userEntity));
        return userDTO;
    }

    // insert
    public UserEntity toUserEntity(UserDTO userDTO) {
        UserEntity entity = new UserEntity();
        entity.setPassword(userDTO.getPassword());
        entity.setUserName(userDTO.getUserName());
        entity.setFullName(userDTO.getFullName());
        entity.setStatus(userDTO.getStatus());

        entity.setRoles(addListRoleEntity(userDTO));
        return entity;
    }

    //update
    public UserEntity toUserEntity(UserDTO userDTO, UserEntity entity) {
        entity.setPassword(userDTO.getPassword());
        entity.setUserName(userDTO.getUserName());
        entity.setFullName(userDTO.getFullName());
        entity.setStatus(userDTO.getStatus());

        entity.setRoles(addListRoleEntity(userDTO));
        return entity;
    }

    private List<RoleEntity> addListRoleEntity (UserDTO userDTO){
        List<RoleEntity> listRoleEntity = new ArrayList<>();
        for (RoleDTO roleDTO : userDTO.getRoles()) {
            RoleEntity roleEntity = roleRepository.findOneByCode(roleDTO.getCode());
            listRoleEntity.add(roleEntity);
        }
        return listRoleEntity;
    }

    private List<RoleDTO> addListRoleDTO (UserEntity userEntity){
        List<RoleDTO> listRoleDTO = new ArrayList<>();
        for (RoleEntity roleEntity : userEntity.getRoles()) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(roleEntity.getId());
            roleDTO.setCode(roleEntity.getCode());
            roleDTO.setName(roleEntity.getName());

            listRoleDTO.add(roleDTO);
        }
        return listRoleDTO;
    }
}
