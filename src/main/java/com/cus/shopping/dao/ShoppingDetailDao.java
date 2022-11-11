package com.cus.shopping.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cus.shopping.model.ShoppingDetail;
import com.cus.shopping.model.User;

@Repository
public interface ShoppingDetailDao extends CrudRepository<ShoppingDetail, Integer>{

	@Query("SELECT e FROM ShoppingDetail e WHERE e.idorders.idorders = :idorders and e.idorders.userIduser = :user")
	List<ShoppingDetail> getDatailByOrder(@Param("idorders") Integer idorders , @Param("user") User user);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE s FROM shoppingdetail s INNER JOIN orders o ON o.idorders=s.idorders where o.user_iduser = :iduser and s.iddetail = :detail and s.paid = 0" , nativeQuery = true )
	Integer removeProductOnDetail(@Param("iduser") Integer iduser , @Param("detail") Integer detail);

}
