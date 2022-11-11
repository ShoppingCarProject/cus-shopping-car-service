package com.cus.shopping.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cus.shopping.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

}
