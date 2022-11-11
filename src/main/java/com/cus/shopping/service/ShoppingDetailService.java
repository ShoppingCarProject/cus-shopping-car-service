package com.cus.shopping.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cus.shopping.dao.ProductsCarDao;
import com.cus.shopping.dao.ShoppingDetailDao;
import com.cus.shopping.model.Orders;
import com.cus.shopping.model.ProductsCar;
import com.cus.shopping.model.Response;
import com.cus.shopping.model.ShoppingDetail;
import com.cus.shopping.model.User;

/**
 * 
 * @author Isaias
 *
 */
@Service
public class ShoppingDetailService {

	Logger logger = LoggerFactory.getLogger(ShoppingDetailService.class);

	@Autowired
	private ShoppingDetailDao shoppingDetailDao;

	@Autowired
	private AutenticationService auth;

	@Autowired
	private ProductsCarDao productsCarDao;

	/**
	 * we generated a detail of my products on my car to shopping detail service
	 * 
	 * @param product
	 * @param order
	 * @return
	 */
	public Boolean generatoDetailOfCar(List<ProductsCar> product, Orders order) {
		try {
			List<ShoppingDetail> shoppingDataParse = shoppingDetail(product, order);
			if (shoppingDataParse != null) {
				List<ShoppingDetail> shopSave = (List<ShoppingDetail>) shoppingDetailDao.saveAll(shoppingDataParse);
				if (shopSave != null && shopSave.size() > 0) {
					try {
						productsCarDao.deleteAll(product);
						return Boolean.TRUE;
					} catch (Exception e) {
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return Boolean.FALSE;
	}

	/**
	 * we are going to get detail of my order
	 * 
	 * @param user
	 * @param idOrder
	 * @return
	 */
	public ResponseEntity<?> getDetailByOrder(User user, Integer idOrder) {
		try {
			List<ShoppingDetail> detail = shoppingDetailDao.getDatailByOrder(idOrder, user);
			if (detail != null && detail.size() > 0) {
				return new ResponseEntity<List<ShoppingDetail>>(detail, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Response>(new Response("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Get amount paid of my order
	 * 
	 * @param order
	 * @param user
	 * @return
	 */
	public Double getAmountPaid(Integer order, User user) {
		try {
			Double amount = shoppingDetailDao.getAmountPaid(order, user);
			return amount != null && amount > 0 ? amount : null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * Remove a product on my shoppingDetail
	 * 
	 * @param token
	 * @param idOrder
	 * @return
	 */
	public ResponseEntity<?> removeProductOnDetail(String token, Integer idOrder) {
		try {
			User user = auth.autenticate(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			Integer rowRemove = shoppingDetailDao.removeProductOnDetail(user.getIduser(), idOrder);
			return new ResponseEntity<Response>(
					new Response("200", String.format("Shoppingdetails removed has been : %s", rowRemove), rowRemove),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Response>(new Response("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Parse order to product we get.
	 * 
	 * @param product
	 * @param order
	 * @return
	 */
	private List<ShoppingDetail> shoppingDetail(List<ProductsCar> product, Orders order) {
		List<ShoppingDetail> finalDetal = null;
		if (product != null && product.size() > 0 && order != null) {
			finalDetal = product.stream().map(p -> {
				ShoppingDetail detail = new ShoppingDetail();
				detail.setImage(p.getImage());
				detail.setPaid(Boolean.FALSE);
				detail.setPrice(p.getPrice());
				detail.setProductid(p.getIdapi());
				detail.setTitle(p.getTitle());
				detail.setIdorders(order);
				return detail;
			}).collect(Collectors.toList());
		}
		return finalDetal;
	}
}
