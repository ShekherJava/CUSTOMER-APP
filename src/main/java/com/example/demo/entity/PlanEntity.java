package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table ( name = "PLANDETAILS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanEntity {
	
	@Id
	@Column( name = "PLANID")
	private String planId;
	
	@Column( name = "PLANNAME")
	private String planName;
	
	private String description;
	
	private String validity;
	
	

}
