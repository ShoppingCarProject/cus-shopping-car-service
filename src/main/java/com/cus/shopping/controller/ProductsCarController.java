package com.cus.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cus.shopping.service.ProductsCarService;

@RestController
@RequestMapping("productscar")
public class ProductsCarController {

	@Autowired
	private ProductsCarService productsCarService;

	@PostMapping("/{id}")
	public ResponseEntity<?> saveOnCar(@PathVariable("id") Integer id,
			@RequestHeader("Authorization") String bearerToken) {
		return productsCarService.saveOnCar(id, bearerToken);
	}
	@GetMapping
	public ResponseEntity<?> getMyProducts(@RequestHeader("Authorization") String bearerToken) {
		return productsCarService.getMyProducts(bearerToken);
	}
	@GetMapping("/count")
	public ResponseEntity<?> getCountOfMyProducts(@RequestHeader("Authorization") String bearerToken) {
		return productsCarService.getCountOFMyProducts(bearerToken);
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> getRemoveProducts(@PathVariable("id") Integer id ,@RequestHeader("Authorization") String bearerToken) {
		return productsCarService.getRemoveProducts(bearerToken , id);
	}
	
}
