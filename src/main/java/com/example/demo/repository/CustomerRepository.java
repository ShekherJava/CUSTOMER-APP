package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
	
	@Query(value="SELECT  COUNT(*) FROM  CUSTOMER  WHERE  PHONE_NUMBER=?  AND  PASSWORD=?", nativeQuery=true)
	Integer  checkLogin(Long phoneNumber, String password);

}
