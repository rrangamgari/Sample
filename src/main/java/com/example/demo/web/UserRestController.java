package com.example.demo.web;

import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Account;
import com.example.demo.service.UserService;
import com.example.demo.util.HttpResponseErrors;
import com.example.demo.util.MyHttpResponse;
import com.example.demo.util.Messages;
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
	protected AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id, HttpServletRequest request,
			HttpSession httpSession) {
		// model.addAttribute("userForm", new User());
		logger.info("id : " + id);
		MyHttpResponse response = new MyHttpResponse();
		try {
			userService.delete(id, (Long) httpSession.getAttribute("userId"));
			response.setStatus("Success");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			response = addErrorMessages(response, 1);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/users/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUsers(@PathVariable("id") Long id, HttpServletRequest request,
			HttpSession httpSession) {
		// model.addAttribute("userForm", new User());
		logger.info("id : " + id);
		Account user = null;
		MyHttpResponse response = new MyHttpResponse();
		try {
			user = userService.findById(id);

			response.setData(user);
			response.setStatus("success");
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			response = addErrorMessages(response, 1);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Account user, HttpServletRequest request, HttpSession httpSession) {
		// logger.info(user.getPassword());
		MyHttpResponse response = new MyHttpResponse();
		try {
			userService.save(user);
			response.setStatus("success");
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			response = addErrorMessages(response, 6);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		// user.setRoles(new HashSet<>(roleRepository.findAll()));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<?> getUsers(HttpServletRequest request, HttpSession httpSession) {
		logger.info("id : ");
		MyHttpResponse response = new MyHttpResponse();
		try {
			List<Account> user = userService.findAll();
			response.setStatus("success");
			response.setData(user);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = addErrorMessages(response, 1);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/users/search", method = RequestMethod.GET)
	public ResponseEntity<?> searchUsers(@RequestParam("q") String query, @RequestParam("v") String value,
			HttpServletRequest request, HttpSession httpSession) {
		logger.info("query : " + query);
		logger.info("value : " + value);
		Account user = null;
		MyHttpResponse response = new MyHttpResponse();
		try {
			// user = userService.findById(id);
			if (user != null)
				user.setPassword(SampleUtil.decrypt(user.getPassword()));
			response.setStatus("success");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			response = addErrorMessages(response, 4);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}
	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param user
	 *            DOCUMENT ME!
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody Account user, HttpServletRequest request,
			HttpSession httpSession) {
		logger.info("registration");
		MyHttpResponse response = new MyHttpResponse();
		try {
			userService.save(user);
			response.setStatus("success");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = addErrorMessages(response, 5);
			return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
		}

	}

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ResponseEntity<?> addRoles(HttpServletRequest request, HttpSession httpSession) {
		logger.info("registration");
		MyHttpResponse response = new MyHttpResponse();
		try {
			userService.updateRoles();
			response.setStatus("success");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = addErrorMessages(response, 5);
			return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
		}

	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUsers(@RequestBody Account user, @PathVariable Long id, HttpServletRequest request,
			HttpSession httpSession) {
		MyHttpResponse response = new MyHttpResponse();
		try {

			if (id == null) {
				response = addErrorMessages(response, 3);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			userService.updateUser(user, id);
			response.setStatus("success");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = addErrorMessages(response, 6);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	private MyHttpResponse addErrorMessages(MyHttpResponse response, int errorCode) {
		HttpResponseErrors errorMessage = new HttpResponseErrors();
		errorMessage.setErrorCode(errorCode);

		switch (errorCode) {
		case 1:
			errorMessage.setErrorMessage(Messages.USER_NOT_FOUND);
			break;
		case 2:
			errorMessage.setErrorMessage(Messages.FAILED_TO_SAVE);
			break;
		case 3:
			errorMessage.setErrorMessage(Messages.USER_ID_NOT_FOUND);
			break;
		case 4:
			errorMessage.setErrorMessage(Messages.FAILED_TO_SAVE);
			break;
		case 5:
			errorMessage.setErrorMessage(Messages.FAILED_TO_CREATE);
			break;
		case 6:
			errorMessage.setErrorMessage(Messages.FAILED_TO_SAVE);
			break;
		default:
			errorMessage.setErrorMessage(Messages.DEFAULT_ERROR_MESSAGE);
			break;
		}
		response.setMessage(errorMessage);
		response.setStatus("failure");
		return response;
	}
} // end class UserRestController
