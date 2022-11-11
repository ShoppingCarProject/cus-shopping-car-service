package com.cus.shopping.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.cus.shopping.model.User;

/**
 * To do authentications on the microservice: cus-security-service
 * 
 * this is the most important class, if this doesn't work the project will not work
 * 
 * @author Isaias
 *
 */
@Service
public class AutenticationService {
	
	Logger logger = LoggerFactory.getLogger(AutenticationService.class);
	
	private final static String HEADER_TOKEN="Authorization";
	
	@Value("${api.params.autentication}")
	private String autenticationUrl;
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * I Will the validation of token on the microservice
	 * cus-security-service
	 * 
	 * @param token
	 * @return
	 */
	public User autenticate(String token){
		try {
			logger.info("aqui");
			HttpHeaders headers = new HttpHeaders();
			headers.set(HEADER_TOKEN, token);
			HttpEntity<String> request = new HttpEntity<String>(headers);
			ResponseEntity<User> user = restTemplate.postForEntity(autenticationUrl, request,  User.class);
			logger.info("Status : " + user.getStatusCodeValue());
			if(user.getStatusCodeValue() == 200) {
				return user.getBody();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;	
	}
	
}
