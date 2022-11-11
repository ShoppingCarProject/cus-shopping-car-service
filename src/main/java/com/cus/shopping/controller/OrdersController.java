package com.cus.shopping.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrdersController {

	public ResponseEntity<?> generateOrder(){
		return ResponseEntity.ok("");
	}
	
}
