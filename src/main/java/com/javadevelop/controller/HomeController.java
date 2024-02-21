package com.javadevelop.controller;

import com.javadevelop.dto.BaseDTO;
import com.javadevelop.dto.ProductDTO;
import com.javadevelop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/")
    public String getProduct(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "iteminpage", required = false) Integer iteminpage, Model model){
        BaseDTO<ProductDTO> productBaseDTO = new BaseDTO<>();
        if (page != null) {
            productBaseDTO.setPage(page);
            productBaseDTO.setTotalPage((int)Math.ceil(productService.totalItem()/iteminpage));
            Pageable pageable = new PageRequest(page - 1, iteminpage);
            productBaseDTO.setListResult(productService.findAll(pageable));
        }else {
            productBaseDTO.setListResult(productService.findAll());
        }
        model.addAttribute("productBaseDTO", productBaseDTO);
        return "listProduct";
    }

    @RequestMapping("/listUser")
    public String getUser(){
        return "listUser";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/hazelcast")
    public String index(HttpSession httpSession) {
        Integer hits = (Integer) httpSession.getAttribute("hits");
        if (hits == null) {
            hits = 0;
        }
        httpSession.setAttribute("hits", ++hits);

        return "welcomePage";
    }
}
