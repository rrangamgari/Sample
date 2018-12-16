package com.example.demo.web;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.websocket.server.PathParam;

import org.apache.tomcat.util.codec.binary.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.HttpResponseErrors;
import com.example.demo.util.ErrorMessages;
import com.example.demo.util.SampleUtil;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$, $Date$
 */
@RequestMapping("/sample")
@RestController
public class UserRestController {
	// ~ Static fields/initializers
	// ---------------------------------------------------------------------------------------

	// ~ Instance fields
	// --------------------------------------------------------------------------------------------------

	/** DOCUMENT ME! */
	Logger logger = LoggerFactory.getLogger(UserRestController.class);

	/** DOCUMENT ME! */

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		// model.addAttribute("userForm", new User());
		logger.info("id : " + id);
		try {
			userService.delete(id);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			HttpResponseErrors errorMessage = new HttpResponseErrors();
			errorMessage.setErrorCode(1);
			errorMessage.setErrorMessage(ErrorMessages.USER_NOT_FOUND);
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUsers(@PathVariable("id") Long id) {
		// model.addAttribute("userForm", new User());
		logger.info("id : " + id);
		User user = null;
		try {
			user = userService.findById(id);
			if (user != null)
				user.setPassword(SampleUtil.decrypt(user.getPassword()));
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			HttpResponseErrors errorMessage = new HttpResponseErrors();
			errorMessage.setErrorCode(1);
			errorMessage.setErrorMessage(ErrorMessages.USER_NOT_FOUND);
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody User user) {
		// logger.info(user.getPassword());
		try {
			user.setPassword(SampleUtil.encrypt(user.getPassword()));
			userService.save(user);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			HttpResponseErrors errorMessage = new HttpResponseErrors();
			errorMessage.setErrorCode(2);
			errorMessage.setErrorMessage(ErrorMessages.FAILED_TO_SAVE);
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		}
		// user.setRoles(new HashSet<>(roleRepository.findAll()));

		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getUsers() {
		// model.addAttribute("userForm", new User());
		logger.info("id : ");

		List<User> user = userService.findAll();

		return user;
	}

	@RequestMapping(value = "/users/search", method = RequestMethod.GET)
	public User searchUsers(@RequestParam("q") String query, @RequestParam("v") String value) {
		// model.addAttribute("userForm", new User());
		logger.info("query : " + query);
		logger.info("value : " + value);
		User user = null;
		try {
			// user = userService.findById(id);
			if (user != null)
				user.setPassword(SampleUtil.decrypt(user.getPassword()));
		} catch (NoSuchElementException e) {
			// TODO: handle exception
		}
		return user;
	}
	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param user
	 *            DOCUMENT ME!
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody User user) {
		// logger.info(user.getPassword());
		try {
			user.setPassword(SampleUtil.encrypt(user.getPassword()));
			user.setCreatedDate(new Date());
			user.setUpdatedDate(new Date());
			// user.setRoles(new HashSet<>(roleRepository.findAll()));
			userService.save(user);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			HttpResponseErrors errorMessage = new HttpResponseErrors();
			errorMessage.setErrorCode(5);
			errorMessage.setErrorMessage(ErrorMessages.FAILED_TO_SAVE);
			return new ResponseEntity<>(errorMessage, HttpStatus.ALREADY_REPORTED);
		}

	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUsers(@RequestBody User user, @PathVariable Long id) {
		// logger.info(user.getPassword());
		try {
			if (id == null) {
				HttpResponseErrors errorMessage = new HttpResponseErrors();
				errorMessage.setErrorCode(3);
				errorMessage.setErrorMessage(ErrorMessages.USER_ID_NOT_FOUND);
				return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
			}
			User user1 = userService.findById(id);
			user.setPassword(SampleUtil.encrypt(user.getPassword()));
			user.setUpdatedDate(new Date());
			user.setCreatedDate(user1.getCreatedDate());
			user.setId(id);
			userService.save(user);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			HttpResponseErrors errorMessage = new HttpResponseErrors();
			errorMessage.setErrorCode(6);
			errorMessage.setErrorMessage(ErrorMessages.FAILED_TO_SAVE);
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_GATEWAY);
		}

	}
} // end class UserRestController
