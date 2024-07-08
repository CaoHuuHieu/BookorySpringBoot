package com.bookory.controller;


import com.bookory.controller.constant.ApiConstant;
import com.bookory.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.API_USER)
public class UserController {

    @GetMapping("list")
    public ResponseEntity<List<User>> getUserList(Pageable pageable, @RequestParam String name){
        return null;
    }
}
