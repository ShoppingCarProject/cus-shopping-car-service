package com.cus.shopping.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cus.shopping.model.Payment;

@Repository
public interface PaymentDao extends CrudRepository<Payment, Integer>{

	@Query("SELECT e FROM Payment e WHERE e.user = :user ")
	List<Payment> getMyPayments(Integer user);
	
	
}
