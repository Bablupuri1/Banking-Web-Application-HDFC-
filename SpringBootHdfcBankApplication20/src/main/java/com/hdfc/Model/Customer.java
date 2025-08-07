package com.hdfc.Model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * This class represents a Customer. It contains personal details like name,
 * email, and address. It also has a one-to-one relationship with Account.
 */

@Entity
@Data
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Primary key, auto-incremented

	private String name; // Customer's full name (required)
	private String email; // Customer's email address
	private String phone; // Phone number
	private String gender; // Male, Female, or Other
	private LocalDate dob; // Date of birth
	private String address; // Home address

	private String customerId; // Unique customer ID generated elsewhere
	private String password; // Generated password stored here

	// One-to-one relationship with Account (Customer owns an account)
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	Account account;

	// Getters and setters below




}
