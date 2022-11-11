package com.cus.shopping.constans;

import com.cus.shopping.model.OrderStatus;

/**
 * Orders
 * 
 * @author Isaias
 *
 */
public class OrdersStatusConstans {

	public static final OrderStatus COMPLETE = new OrderStatus(1, "COMPLETE");
	
	public static final OrderStatus IN_PROCESS = new OrderStatus(2, "IN PROCESS");
	
	public static final OrderStatus CANCELED = new OrderStatus(3, "CANCELED");
	
}
