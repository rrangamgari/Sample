package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.User;
import com.example.demo.web.UserRestController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleApplicationTests {

	@Autowired
	UserRestController userRestController;

	// ~ Methods
	// ----------------------------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 */
	@Test
	public void contextLoads() {
	}

	// ~
	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 */
	@Test
	public void getUserTest() {
		ResponseEntity<?> user1 = userRestController.getUsers(1L);
		System.out.println(user1.getBody());
		assertNotNull(user1);
	}

	// ~
	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 */
	@Test
	public void registration() {
		User user = new User();
		user.setUserName("Test");
		user.setPassword("Test");
		userRestController.registration(user);
		ResponseEntity<?> user1 = userRestController.getUsers(1L);
		assertNotNull(user1);
	}

	@Test
	public void updateRegistration() {
		User user = new User();
		user.setUserName("Test1");
		user.setPassword("Test1");
		user.setId(1L);
		userRestController.updateUsers(user, 1L);
		ResponseEntity<?> user1 = userRestController.getUsers(1L);
		assertNotNull(user1);
	}
}
