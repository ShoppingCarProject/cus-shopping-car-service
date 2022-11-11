package com.cus.shopping.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.cus.shopping.api.model.Product;
import com.cus.shopping.dao.ProductsCarDao;
import com.cus.shopping.model.ProductsCar;
import com.cus.shopping.model.Response;
import com.cus.shopping.model.User;

@Service
public class ProductsCarService {
	
	Logger logger = LoggerFactory.getLogger(ProductsCarService.class);
	
	@Value("${api.params.url}")
	private String url;
	
	@Autowired
	private ProductsCarDao productsService;

	@Autowired
	private AutenticationService auth;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ResponseEntity<?> saveOnCar(Integer id , String token) {
		try {			
			User user = auth.autenticate(token);
			if(user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}			
			String finalUrl = url.concat("products/").concat(id.toString());
			Product products = restTemplate.getForObject(finalUrl, Product.class);
			productsService.save(toParseProduct(products, user , id));
			logger.info(products.toString());
			return new ResponseEntity<Product>( products, HttpStatus.CREATED);
		}catch(ResourceAccessException ex) {
			return new ResponseEntity<Response>(new Response("500" ,"Resource don't found " + ex.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		catch (Exception e) {
			return new ResponseEntity<Response>(new Response("500" , e.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	public  ResponseEntity<?> getMyProducts(String token){
		try {
			User user = auth.autenticate(token);
			if(user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			List<ProductsCar> productos = productsService.getProductsByUser(user);
			if(productos != null && productos.size() > 0) {
				productos.forEach(e-> e.getIduser().setPassword(null));
				return  new ResponseEntity<List<ProductsCar>>(productos, HttpStatus.OK);
			}else {
				return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}		
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("500" , e.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public  ResponseEntity<?> getCountOFMyProducts(String token){
		try {
			User user = auth.autenticate(token);
			if(user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			Integer productos = productsService.getCountOfProductsByUser(user);
			if(productos != null && productos.intValue()> 0) {
				return new ResponseEntity<Response>(new Response("200" , String.format("It user have %s products on the car", productos) , productos) , HttpStatus.OK);
				
			}else {
				return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}		
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("500" , e.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public  ResponseEntity<?> getRemoveProducts(String token , Integer idProduct){
		try {
			User user = auth.autenticate(token);
			if(user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}		
			Integer productos = productsService.deleteById(user , idProduct);
				return new ResponseEntity<Response>(new Response("200" , String.format("Products removed has been : %s", productos) , productos) , HttpStatus.OK);		
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("500" , e.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	private ProductsCar toParseProduct(Product products , User user , Integer id) {
		ProductsCar newProduct = new ProductsCar();
		newProduct.setCategory(products.getCategory());
		newProduct.setDescription(products.getDescription());
		newProduct.setTitle(products.getTitle());
		newProduct.setPrice(products.getPrice());
		newProduct.setImage(products.getImage());
		newProduct.setIdapi(id);
		newProduct.setIduser(user);
		return newProduct;
	}
}
