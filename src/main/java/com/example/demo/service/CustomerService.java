package com.example.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerEntity;
import com.example.demo.model.LoginRequest;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository repository;

	public boolean registerCustomer(CustomerEntity customer) {
		
		if(repository.existsById(customer.getPhoneNumber())==false) {
			repository.save(customer);
			return true;
		}
		else {
			return  false;
		}
	}

	public boolean loginCustomer(LoginRequest loginRequest) {
		
		if(repository.checkLogin(loginRequest.getPhoneNumber(), loginRequest.getPassword())==1) {
			return true;
		}
		else {
			return false;
		}
	}

	public CustomerEntity readCustomer(Long phoneNumber) {
		CustomerEntity customer = repository.findById(phoneNumber).get();
		return customer;
	}
}
