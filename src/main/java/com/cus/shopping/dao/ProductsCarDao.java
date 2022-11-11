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
/**
 * Repository of ProductsCar
 * @author Isaias
 *
 */
@Repository
public interface ProductsCarDao  extends CrudRepository<ProductsCar, Integer>{
	
	/**
	 * Get products on the shopping car by user.
	 * @param id
	 * @return
	 */
	@Query("SELECT e FROM ProductsCar e WHERE e.iduser = :id")
	List<ProductsCar> getProductsByUser(@Param("id") User id);
	
	/**
	 * Count products in my shopping car.
	 * @param id
	 * @return
	 */
	@Query("SELECT COUNT(e.idproductscar) FROM ProductsCar e WHERE e.iduser = :id")
	Integer getCountOfProductsByUser(@Param("id") User id);

	/**
	 * Delete a product.
	 * @param id
	 * @param idProduct
	 * @return
	 */
	@Modifying
	@Transactional
	@Query("DELETE FROM ProductsCar p WHERE p.iduser = :id and p.idproductscar = :idProduct")
	Integer deleteById(@Param("id") User id , @Param("idProduct") Integer idProduct);
}
