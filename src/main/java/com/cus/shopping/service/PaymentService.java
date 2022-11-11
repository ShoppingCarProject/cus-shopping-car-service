package com.cus.shopping.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cus.shopping.constans.OrdersStatusConstans;
import com.cus.shopping.dao.OrdersDao;
import com.cus.shopping.dao.PaymentDao;
import com.cus.shopping.dao.ShoppingDetailDao;
import com.cus.shopping.model.AmountPaid;
import com.cus.shopping.model.Orders;
import com.cus.shopping.model.Payment;
import com.cus.shopping.model.PaymentRequest;
import com.cus.shopping.model.Response;
import com.cus.shopping.model.User;

/**
 * 
 * @author Isaias
 *
 */
@Service
public class PaymentService {

	
	Logger logger = LoggerFactory.getLogger(PaymentService.class);
	
	@Autowired
	private AutenticationService auth;

	@Autowired
	private ShoppingDetailService detailService;

	@Autowired
	private ShoppingDetailDao shoppingDetailDao;

	@Autowired
	private OrdersDao ordersDao;

	@Autowired
	private PaymentDao paymentDao;

	/**
	 * whith this method we do a payment with the amount paid en idorder
	 * 
	 * @param token
	 * @param paymentRequest
	 * @return
	 */
	public ResponseEntity<?> doPayment(String token, PaymentRequest paymentRequest) {
		try {
			User user = auth.autenticate(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			Double amountPaid = detailService.getAmountPaid(paymentRequest.getIdOrder(), user);
			if (amountPaid == null) {
				return new ResponseEntity<Response>(
						new Response("400", "Invalid order id ".concat(paymentRequest.getIdOrder().toString())),
						HttpStatus.BAD_REQUEST);
			}
			if (amountPaid.intValue() == paymentRequest.getAmountPaid().intValue()) {
				Integer updateToPaid = shoppingDetailDao.updateToPaid(paymentRequest.getIdOrder());
				if (updateToPaid > 0) {
					Payment payment = new Payment();
					payment.setCreated(new Date());
					payment.setDescription("Products has been payd with amount " + amountPaid + " the order id "
							+ paymentRequest.getIdOrder());
					payment.setOderid(paymentRequest.getIdOrder());
					payment.setTotal(amountPaid);
					payment.setUser(user.getIduser());
					paymentDao.save(payment);
					ordersDao.save(new Orders(paymentRequest.getIdOrder(), OrdersStatusConstans.COMPLETE, user));
					return ResponseEntity.ok(payment);
				}
				return new ResponseEntity<Response>(new Response("406", "no any entity has been updated"),
						HttpStatus.NOT_ACCEPTABLE);
			} else {
				return new ResponseEntity<Response>(
						new Response("406", "amountPaid should be $".concat(amountPaid.toString())),
						HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Response>(new Response("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * we will get the amount due
	 * 
	 * @param token
	 * @param idOrder
	 * @return
	 */
	public ResponseEntity<?> getAmountDue(String token, Integer idOrder) {
		try {
			User user = auth.autenticate(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			Double amountPaid = detailService.getAmountPaid(idOrder, user);
			if (amountPaid == null) {
				return new ResponseEntity<Response>(new Response("400", "Invalid order id ".concat(idOrder.toString())),
						HttpStatus.BAD_REQUEST);
			} else {
				return ResponseEntity.ok(new AmountPaid(amountPaid));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Response>(new Response("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * This is a detail of may payments.
	 * 
	 * @param token
	 * @return
	 */
	public ResponseEntity<?> getMyPayments(String token) {
		try {
			User user = auth.autenticate(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			List<Payment> listaPagos = paymentDao.getMyPayments(user.getIduser());
			if (listaPagos != null && listaPagos.size() > 0) {
				return ResponseEntity.ok(listaPagos);
			} else {
				return new ResponseEntity<Response>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Response>(new Response("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
