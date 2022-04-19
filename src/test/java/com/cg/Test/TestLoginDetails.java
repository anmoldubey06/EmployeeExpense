package com.cg.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.entity.LoginDetails;
import com.cg.repository.ExpenseRepository;
import com.cg.repository.LoginDetailsRepository;
import com.cg.service.ExpenseService;
import com.cg.service.LoginDetailsService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestLoginDetails {
	
	@Autowired
	LoginDetailsService loginDetailsService;
	
	@Autowired
	LoginDetailsRepository loginDetailsRepo;
	
	@Test
	@Order(1)
	public void testAddDetails() {
		LoginDetails loginDetails= new LoginDetails();
		loginDetails.setlUserName("anmol");
		loginDetails.setlPassword("@nmolDubey1");
		loginDetails.setlRole("Developer");
		loginDetailsRepo.save(loginDetails);
		assertEquals(1, loginDetailsRepo.count());
	}
	
	@Test
	@Order(2)
	public void testValidateUser() {
		assertEquals("anmol", loginDetailsRepo.getById(1).getlUserName());
	}
	
	@Test
	@Order(3)
	public void testLogout() {
		assertEquals("User: anmol has logged out successfully!", 
					 "User: " + loginDetailsRepo.getById(1).getlUserName() + " has logged out successfully!");
	}
	
	@Test
	@Order(4)
	public void testDeleteDetailsById() {
		loginDetailsRepo.deleteAll();
		assertEquals(0, loginDetailsRepo.count());
	}
}
