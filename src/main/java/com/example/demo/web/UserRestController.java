package com.example.demo.web;

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

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

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

	private static final String key = "aesEncryptionKey";
	private static final String initVector = "encryptionIntVec";

	// ~ Instance fields
	// --------------------------------------------------------------------------------------------------

	/** DOCUMENT ME! */
	Logger logger = LoggerFactory.getLogger(UserRestController.class);

	/** DOCUMENT ME! */

	@Autowired
	private UserService userService;

	// ~ Methods
	// ----------------------------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param encrypted
	 *            DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String decrypt(String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	// ~
	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param value
	 *            DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String encrypt(String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());

			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	// ~
	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param id
	 *            DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public boolean deleteUser(@PathVariable("id") Long id) {
		// model.addAttribute("userForm", new User());
		logger.info("id : " + id);

		userService.delete(id);

		return true;
	}

	// ~
	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param id
	 *            DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User getUsers(@PathVariable("id") Long id) {
		// model.addAttribute("userForm", new User());
		logger.info("id : " + id);
		User user = null;
		try {
			user = userService.findById(id);
			if (user != null)
				user.setPassword(decrypt(user.getPassword()));
		} catch (NoSuchElementException e) {
			// TODO: handle exception
		}
		return user;
	}

	// ~
	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param user
	 *            DOCUMENT ME!
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
	public void save(@RequestBody User user) {
		logger.info(user.getPassword());
		user.setPassword(encrypt(user.getPassword()));

		// user.setRoles(new HashSet<>(roleRepository.findAll()));
		userService.save(user);
	}

	// ~
	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param id
	 *            DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public User updateUsers(@PathVariable("id") Long id) {
		// model.addAttribute("userForm", new User());
		logger.info("id : " + id);

		User user = userService.findById(id);

		return user;
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
				user.setPassword(decrypt(user.getPassword()));
		} catch (NoSuchElementException e) {
			// TODO: handle exception
		}
		return user;
	}
} // end class UserRestController
