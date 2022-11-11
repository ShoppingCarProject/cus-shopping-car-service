package com.cus.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cus.shopping.service.OrdersService;

@RestController
@RequestMapping("orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	
	@PostMapping("/generate")
	public ResponseEntity<?> generateOrder(@RequestHeader("Authorization") String bearerToken){
		return ordersService.generateOrder(bearerToken);		 
	}
	
	@GetMapping("/myorders")
	public ResponseEntity<?> getMyOrders(@RequestHeader("Authorization") String bearerToken){
		return ordersService.getMyOrders(bearerToken);		 
	}
	@GetMapping("/myorders/{id}")
	public ResponseEntity<?> getMyOrders(@RequestHeader("Authorization") String bearerToken , @PathVariable("id") Integer id){
		return ordersService.getMyOrdersById(bearerToken, id);		 
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> getMyOrderDetail(@RequestHeader("Authorization") String bearerToken , @PathVariable("id") Integer idOrder){
		return ordersService.getMyOrdersDetail(bearerToken, idOrder);		 
	}
		
}
