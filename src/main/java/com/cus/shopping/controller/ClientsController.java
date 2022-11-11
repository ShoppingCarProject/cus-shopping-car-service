package com.cus.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cus.shopping.service.ClientService;

@RestController
@RequestMapping("client")
public class ClientsController {

	@Autowired
	private ClientService service;
	
	@GetMapping
	public ResponseEntity<?> getMyInfo(@RequestHeader("Authorization") String bearerToken){
		return service.getMyInfo(bearerToken);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getMyAllClientes(@RequestHeader("Authorization") String bearerToken){
		return service.getAllClients(bearerToken);
	}
}
