package com.cus.shopping.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * 
 * @author Isaias
 *
 */
@Service
public class OrdersService {

	Logger logger = LoggerFactory.getLogger(OrdersService.class);
	
	@Autowired
	private OrdersDao ordersDao;

	@Autowired
	private ProductsCarDao productsCarDao;

	@Autowired
	private ShoppingDetailService shoppingDetailService;

	@Autowired
	private AutenticationService auth;

	/**
	 * with this method we are going to get orders of the owner token
	 * 
	 * @param token
	 * @return
	 */
	public ResponseEntity<?> getMyOrders(String token) {
		try {
			User user = auth.autenticate(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			List<Orders> orders = ordersDao.getMyOrders(user);
			if (orders != null && orders.size() > 0) {
				return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Response>(new Response("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * we are going to get a detail of my products on my order.
	 * 
	 * @param token
	 * @param id
	 * @return
	 */
	public ResponseEntity<?> getMyOrdersDetail(String token, Integer id) {
		try {
			User user = auth.autenticate(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			return shoppingDetailService.getDetailByOrder(user, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Response>(new Response("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * we are going to get orders by id {1 = COMPLETE, 2 = IN PROCESS , 3 =
	 * CANCELED}
	 * 
	 * @param token
	 * @param id
	 * @return
	 */
	public ResponseEntity<?> getMyOrdersById(String token, Integer id) {
		try {
			if (id == null || (id < 1 || id > 3)) {
				return new ResponseEntity<Response>(
						new Response("400 ", "Allowed values are {1 = COMPLETE, 2 = IN PROCESS , 3 = CANCELED}"),
						HttpStatus.BAD_REQUEST);
			}
			User user = auth.autenticate(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			List<Orders> orders = ordersDao.getMyOrdersById(user, id);
			if (orders != null && orders.size() > 0) {
				return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Response>(new Response("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * We are going to generate a order with this method for before do a payment
	 * 
	 * @param token
	 * @return
	 */
	public ResponseEntity<?> generateOrder(String token) {
		try {
			User user = auth.autenticate(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			Orders order = new Orders();
			order.setUserIduser(user);
			order.setIdstatus(OrdersStatusConstans.IN_PROCESS);
			ordersDao.save(order);
			order.getUserIduser().setPassword(null);
			if (generateShoppingDetail(user, order)) {
				return ResponseEntity.ok(order);
			} else {
				ordersDao.delete(order);
				return new ResponseEntity<Response>(
						new Response("400", "We could not found products on your shopping car."),
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Response>(new Response("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * generation of detail on may shopping car
	 * 
	 * @param user
	 * @param order
	 * @return
	 */
	private Boolean generateShoppingDetail(User user, Orders order) {
		try {
			List<ProductsCar> products = productsCarDao.getProductsByUser(user);
			return shoppingDetailService.generatoDetailOfCar(products, order);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Boolean.FALSE;
		}
	}
}
