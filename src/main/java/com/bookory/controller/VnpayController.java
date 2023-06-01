package com.bookory.controller;

import java.io.UnsupportedEncodingException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookory.dto.request.VnpayRequestDTO;
import com.bookory.object.ResponseObject;
import com.bookory.services.VnpayService;

import jakarta.servlet.http.HttpServletRequest;
@CrossOrigin
@RestController
public class VnpayController {
	@Autowired
	VnpayService vnpayService;
	@PostMapping("/vnpay/service")
	public  ResponseEntity<ResponseObject>  registerService(@RequestBody VnpayRequestDTO vnpay, HttpServletRequest request) {
		try {
			String result =  vnpayService.registerService(vnpay, request);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công", result));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện", ""));
		}
	    
	}
}
