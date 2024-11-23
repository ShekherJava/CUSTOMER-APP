package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.CustomerEntity;
import com.example.demo.entity.PlanEntity;
import com.example.demo.model.CustomerResponse;
import com.example.demo.model.LoginRequest;
import com.example.demo.service.CustomerService;

@RestController
public class CustomerController {
	
	private static String PLAN_URL = "http://localhost:8091/plans/{id}";
	private static String FRIEND_URL = "http://localhost:8092/friend/{phoneNumber}";
	
	@Autowired
	CustomerService  service;
	
	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping( value = "/customer/register")
	public boolean  addCustomer(@RequestBody CustomerEntity customer) {
		return service.registerCustomer(customer);
	}
	
	@PostMapping( value = "/customer/login")
	public  boolean  loginCustomer(@RequestBody LoginRequest loginRequest) {
		return  service.loginCustomer(loginRequest);
	}
	
	@GetMapping("/customer/profile/{phoneNumber}")
	public  CustomerResponse  showProfile(@PathVariable Long phoneNumber) {
		
		CustomerEntity  customerEntity=service.readCustomer(phoneNumber);
		CustomerResponse customerResponse = new CustomerResponse();
		
		BeanUtils.copyProperties(customerEntity, customerResponse);
			
		//calling plan microservice
		ResponseEntity<PlanEntity> re = restTemplate.getForEntity(PLAN_URL, PlanEntity.class, customerEntity.getPlanId());
		PlanEntity planEntity = re.getBody();
		BeanUtils.copyProperties(planEntity, customerResponse);
		
		//calling friend microservice
		List<Long> friendsContactNumbers = restTemplate.getForObject(FRIEND_URL, List.class, phoneNumber);
		customerResponse.setFriendsContactNumbers(friendsContactNumbers);
		
		return customerResponse;
	}
		

}
