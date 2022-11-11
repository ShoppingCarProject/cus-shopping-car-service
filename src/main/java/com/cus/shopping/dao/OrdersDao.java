package com.cus.shopping.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cus.shopping.model.Orders;
import com.cus.shopping.model.User;

/**
 * Repository for Orders
 * 
 * @author Isaias
 *
 */
@Repository
public interface OrdersDao extends CrudRepository<Orders, Integer>{
	
	@Query("SELECT e FROM Orders e WHERE e.userIduser = :user")
	List<Orders> getMyOrders(@Param("user") User user);
	
	
	@Query("SELECT e FROM Orders e WHERE e.userIduser = :user and e.idstatus.idstatus = :status ")
	List<Orders> getMyOrdersById(@Param("user") User user , @Param("status") Integer status);
}
