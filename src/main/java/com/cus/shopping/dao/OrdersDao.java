package com.cus.shopping.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cus.shopping.model.Orders;

@Repository
public interface OrdersDao extends CrudRepository<Orders, Integer>{

}
