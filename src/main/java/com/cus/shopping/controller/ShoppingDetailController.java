package com.cus.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cus.shopping.service.ShoppingDetailService;

@RestController
@RequestMapping("/shopping")
public class ShoppingDetailController {
	
	@Autowired
	private ShoppingDetailService shoppingDetailService;
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> getRemoveProducts(@PathVariable("id") Integer id ,@RequestHeader("Authorization") String bearerToken) {
		return shoppingDetailService.removeProductOnDetail(bearerToken , id);
	}
}
