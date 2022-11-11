package com.cus.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.cus.shopping.constans.OrdersStatusConstans;
import com.cus.shopping.dao.OrdersDao;
import com.cus.shopping.dao.ProductsCarDao;
import com.cus.shopping.model.Orders;
import com.cus.shopping.model.ProductsCar;
import com.cus.shopping.model.Response;
import com.cus.shopping.model.User;

@Service
public class OrdersService {

	@Autowired 
	private OrdersDao ordersDao;
	
	@Autowired 
	private ProductsCarDao productsCarDao;
	
	@Autowired
	private ShoppingDetailService shoppingDetailService;

	@Autowired
	private AutenticationService auth;
	
	public ResponseEntity<?> getMyOrders(String token){
		try {
			User user = auth.autenticate(token);
			if(user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			List<Orders> orders = ordersDao.getMyOrders(user);
			if(orders != null && orders.size() > 0) {
				return new ResponseEntity<List<Orders>>(orders ,HttpStatus.OK);
			}else {
				return new ResponseEntity<Response>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("500" , e.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	public ResponseEntity<?> getMyOrdersDetail(String token , Integer id){
		try {
			User user = auth.autenticate(token);
			if(user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			return shoppingDetailService.getDetailByOrder(user, id);		
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("500" , e.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	public ResponseEntity<?> getMyOrdersById(String token , Integer id){
		try {
			if(id == null || (id < 1  || id > 3)) {
				return new ResponseEntity<Response>(new Response("400 " , "Allowed values are {1 = COMPLETE, 2 = IN PROCESS , 3 = CANCELED}") , HttpStatus.BAD_REQUEST);
			}
			User user = auth.autenticate(token);
			if(user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			List<Orders> orders = ordersDao.getMyOrdersById(user , id);
			if(orders != null && orders.size() > 0) {
				return new ResponseEntity<List<Orders>>(orders ,HttpStatus.OK);
			}else {
				return new ResponseEntity<Response>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("500" , e.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	
	
	public ResponseEntity<?> generateOrder(String token){
		try {
			User user = auth.autenticate(token);
			if(user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			Orders order = new Orders();
			order.setUserIduser(user);
			order.setIdstatus(OrdersStatusConstans.IN_PROCESS);
			ordersDao.save(order);
			order.getUserIduser().setPassword(null);
			if(generateShoppingDetail(user,order)) {
				return ResponseEntity.ok(order);
			}else {
				ordersDao.delete(order);
				return new ResponseEntity<Response>(new Response("400" , "We could not found products on your shopping car.") , HttpStatus.BAD_REQUEST);
			}		
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("500" , e.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	private Boolean generateShoppingDetail(User user ,Orders order ) {
		try {
			List<ProductsCar> products = productsCarDao.getProductsByUser(user);	
			return shoppingDetailService.generatoDetailOfCar(products, order);
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}
}
