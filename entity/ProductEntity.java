package com.sathya.mvcproject.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity


public class ProductEntity {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	
	private String name;
	private double price;
	private int quantity;
	private String madeIn;
	private String brand;
	private double totalAmount;
	private double taxAmount;
	private LocalDateTime cretedAt;
	private String createdBy;

}
