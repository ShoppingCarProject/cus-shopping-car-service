package com.cus.shopping.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cus.shopping.model.ProductsCar;
import com.cus.shopping.model.User;

@Repository
public interface ProductsCarDao  extends CrudRepository<ProductsCar, Integer>{
	
	@Query("SELECT e FROM ProductsCar e WHERE e.iduser = :id")
	List<ProductsCar> getProductsByUser(@Param("id") User id);
	
	@Query("SELECT COUNT(e.idproductscar) FROM ProductsCar e WHERE e.iduser = :id")
	Integer getCountOfProductsByUser(@Param("id") User id);

	@Modifying
	@Transactional
	@Query("DELETE FROM ProductsCar p WHERE p.iduser = :id and p.idproductscar = :idProduct")
	Integer deleteById(@Param("id") User id , @Param("idProduct") Integer idProduct);
}
