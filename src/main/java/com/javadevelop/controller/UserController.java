package com.javadevelop.controller;

import com.javadevelop.dto.BaseDTO;
import com.javadevelop.dto.UserDTO;
import com.javadevelop.jwt.JwtToken;
import com.javadevelop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user", produces = "application/json")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @GetMapping(value = "/logout")
    public ResponseEntity<String> logoutUser() {
        return ResponseEntity.status(HttpStatus.OK).body("logout: ");
    }

    @PostMapping(value = "/login")
    public String loginUser(@RequestBody UserDTO userDTO) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

        // Nếu không xảy ra exception => thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = jwtToken.generateToken((UserDTO) authentication.getPrincipal());
        return "token: " + jwt;
    }

    @GetMapping
    public BaseDTO<UserDTO> getUser(@RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "iteminpage", required = false) Integer iteminpage) {
        BaseDTO<UserDTO> userBaseDTO = new BaseDTO<>();
        if (page != null) {
            userBaseDTO.setPage(page);
            userBaseDTO.setTotalPage((int) Math.ceil(userService.totalItem() / iteminpage));
            Pageable pageable = new PageRequest(page - 1, iteminpage);
            userBaseDTO.setListResult(userService.findAll(pageable));
        } else {
            userBaseDTO.setListResult(userService.findAll());
        }
        return userBaseDTO;
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO model) {
        return userService.save(model);
    }

    @PutMapping(value = "/{id}")
    public UserDTO updateUser(@RequestBody UserDTO model, @PathVariable("id") long id) {
        model.setId(id);
        return userService.save(model);
    }

    @DeleteMapping()
    public void deleteUser(@RequestBody long[] ids) {
        userService.delete(ids);
    }
}
