package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.User;
import com.example.demo.web.UserRestController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleApplicationTests {

	
	@Autowired UserRestController userRestController;

	  //~ Methods ----------------------------------------------------------------------------------------------------------

	  /**
	   * DOCUMENT ME!
	   */
	  @Test public void contextLoads() { }

	  //~ ------------------------------------------------------------------------------------------------------------------

	  /**
	   * DOCUMENT ME!
	   */
	  @Test public void getUserTest() {
	    User user = new User();
	    user.setUsername("Test");
	    user.setPassword("Test");

	    User user1 = userRestController.getUsers(1L);
	    assertEquals(user.getUsername(), user1.getUsername());
	  }

	  //~ ------------------------------------------------------------------------------------------------------------------

	  /**
	   * DOCUMENT ME!
	   */
	  @Test public void registration() {
	    User user = new User();
	    user.setUsername("Test");
	    user.setPassword("Test");
	    userRestController.save(user);
	    user = userRestController.getUsers(1L);
	    assertNotNull(user);
	  }
}

