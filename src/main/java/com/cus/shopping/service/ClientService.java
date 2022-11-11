package com.cus.shopping.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cus.shopping.dao.UserRepository;
import com.cus.shopping.model.Response;
import com.cus.shopping.model.User;
/**
 * Class works, for doing transactions if clients.
 * 
 * @author Isaias
 *
 */
@Service
public class ClientService {

	Logger logger = LoggerFactory.getLogger(ClientService.class);
	
	@Autowired
	private AutenticationService auth;
	
	@Autowired
	private UserRepository userRepository;
	/**
	 * Get info of user owner of token.
	 * 
	 * @param bearerToken
	 * @return
	 */
	public ResponseEntity<?> getMyInfo(String bearerToken){
		try {
			User user = auth.autenticate(bearerToken);
			if(user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			user.setPassword(null);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Response>(new Response("500" , e.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	/**
	 * Get all clients on database
	 * 
	 * @param bearerToken
	 * @return
	 */
	public ResponseEntity<?> getAllClients(String bearerToken){
		try {
			User user = auth.autenticate(bearerToken);
			//Could be better if i have roles in te user for get All data.
			if(user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			List<User> users = (List<User>) userRepository.findAll();
			if(users != null && users.size() > 0) {
				users.forEach(usr -> usr.setPassword(null));
				return new ResponseEntity<List<User>>(users , HttpStatus.OK);
			}
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Response>(new Response("500" , e.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
}
