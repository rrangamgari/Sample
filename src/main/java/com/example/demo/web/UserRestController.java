package com.example.demo.web;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
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

import com.example.demo.api.model.ApiUsers;
import com.example.demo.entity.UserFilterSpecification;
import com.example.demo.model.Account;
import com.example.demo.model.Search;
import com.example.demo.model.UserRole;
import com.example.demo.page.PageRequestBuilder;
import com.example.demo.service.UserService;
import com.example.demo.util.HttpResponseErrors;
import com.example.demo.util.MyHttpResponse;
import com.example.demo.util.Messages;
import com.example.demo.util.SampleUtil;

import io.swagger.annotations.ApiParam;

/**
 * DOCUMENT ME!
 *
 * @author Ravi
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
	protected UserFilterSpecification userFilterSpecification;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/api/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id, @RequestParam("user") Long userId,
			HttpServletRequest request, HttpSession httpSession) {
		// model.addAttribute("userForm", new User());
		logger.info("id : " + id);
		MyHttpResponse response = new MyHttpResponse();
		try {
			userService.delete(id, userId);
			response.setStatus("Success");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			response = addErrorMessages(response, 1);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
	public ResponseEntity<?> save(@RequestBody Account user, @RequestParam("user") Long userId,
			@PathParam("id") Long id, HttpServletRequest request, HttpSession httpSession) {
		// logger.info(user.getPassword());
		MyHttpResponse response = new MyHttpResponse();
		try {
			user.setId(id);
			userService.save(user, userId);
			response.setStatus("success");
		} catch (NoSuchElementException e) {
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

	@RequestMapping(value = "/users/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchUsers(
			@ApiParam(value = "Query param for 'firstName' filter") @Valid @RequestParam(value = "firstName", required = false) String firstName,
			@ApiParam(value = "Query param for 'lastName' filter") @Valid @RequestParam(value = "lastName", required = false) String lastName,
			@ApiParam(value = "Query param for 'midddleName' filter") @Valid @RequestParam(value = "midddleName", required = false) String midddleName,
			@ApiParam(value = "Query param for 'userName' filter") @Valid @RequestParam(value = "userName", required = false) String userName,
			@ApiParam(value = "Query param for 'contactPhone' filter") @Valid @RequestParam(value = "contactPhone", required = false) String contactPhone,
			@ApiParam(value = "Query param for 'emailAddress' filter") @Valid @RequestParam(value = "emailAddress", required = false) String emailAddress,
			@ApiParam(value = "Query param for 'createdBy' filter") @Valid @RequestParam(value = "createdBy", required = false) String createdBy,
			@ApiParam(value = "Query param for 'createdDate' filter") @Valid @RequestParam(value = "createdDate", required = false) String createdDate,
			@ApiParam(value = "Query param for 'updatedDate' filter") @Valid @RequestParam(value = "updatedDate", required = false) String updatedDate,
			@ApiParam(value = "Query param for 'enabled' filter") @Valid @RequestParam(value = "enabled", required = false) String enabled,
			@ApiParam(value = "Query param for 'locked'") @Valid @RequestParam(value = "locked", required = false) String locked,
			@ApiParam(value = "Query param for 'pageNumber'") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@ApiParam(value = "Query param for 'pageSize'") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,
			@ApiParam(value = "Query param for 'sort' criteria") @Valid @RequestParam(value = "sort", required = false) String sort,
			HttpServletRequest request, HttpSession httpSession) {
		logger.info("query : ");

		Account user = null;
		MyHttpResponse response = new MyHttpResponse();
		try {
			Specification<Account> specs = Specifications
					// Exposed attributes in API swagger spec doesn't need to be same as Database
					// table column names.
					.where(userFilterSpecification.getStringTypeSpecification("firstName", firstName))
					.and(userFilterSpecification.getStringTypeSpecification("lastName", lastName))
					;

			// This represents the Page config with sorting
			PageRequest pageRequest = PageRequestBuilder.getPageRequest(pageSize, pageNumber, sort);

			// Call the DAO with specifications and pagerequest
			ApiUsers users = userService.getUsers(specs, pageRequest);
			System.out.println(sort);
			// Return the sorting criteria back so that the consumer can pass the same
			// sorting or of different sorting based on the usecases.
			//users.getPaging().setSortingCriteria(sort);

			response.setStatus("success");
			response.setData(users);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NoSuchElementException e) {
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
	@RequestMapping(value = "/registration/{user}", method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody Account user, @PathVariable("user") Long userId,
			HttpServletRequest request, HttpSession httpSession) {
		logger.info("registration");
		MyHttpResponse response = new MyHttpResponse();
		try {
			userService.save(user, userId);
			response.setStatus("success");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			response = addErrorMessages(response, 5);
			return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
		}

	}

	@RequestMapping(value = "/addroles", method = RequestMethod.GET)
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

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ResponseEntity<?> getRoles(HttpServletRequest request, HttpSession httpSession) {
		logger.info("getRoles");
		MyHttpResponse response = new MyHttpResponse();
		try {
			List<UserRole> role = userService.getRoles();
			response.setStatus("success");
			response.setData(role);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = addErrorMessages(response, 5);
			return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
		}

	}
} // end class UserRestController
