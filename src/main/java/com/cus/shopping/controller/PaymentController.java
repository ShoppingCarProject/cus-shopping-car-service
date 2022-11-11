package com.cus.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cus.shopping.model.PaymentRequest;
import com.cus.shopping.service.PaymentService;

@RestController
@RequestMapping("payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/generate")
	public ResponseEntity<?> generateOrder(@RequestHeader("Authorization") String bearerToken , @RequestBody PaymentRequest paymentRequest){	
		return paymentService.doPayment(bearerToken , paymentRequest);		 
	}
	
	
	@GetMapping("/amountdue/{id}")
	public ResponseEntity<?> getAmountdue(@RequestHeader("Authorization") String bearerToken ,@PathVariable("id") Integer idOrder ){
		return paymentService.getAmountDue(bearerToken , idOrder);		
	}
	
	@GetMapping
	public ResponseEntity<?> getMyPayments(@RequestHeader("Authorization") String bearerToken){
		return paymentService.getMyPayments(bearerToken);		
	}
	
}
