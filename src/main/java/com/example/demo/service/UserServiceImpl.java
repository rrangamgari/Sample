package com.example.demo.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.api.model.ApiAccount;
import com.example.demo.api.model.ApiModelPageAndSort;
import com.example.demo.api.model.ApiUsers;
import com.example.demo.model.Account;
import com.example.demo.model.Role;
import com.example.demo.model.UserRole;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.util.AuditTrail;
import com.example.demo.util.AuditTrailUtil;
import com.example.demo.util.SampleUtil;

/**
 * DOCUMENT ME!
 *
 * @author Ravi
 * @version $Revision$, $Date$
 */
@Service
public class UserServiceImpl implements UserService {
	// ~ Instance fields
	// --------------------------------------------------------------------------------------------------

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	// ~ Methods
	// ----------------------------------------------------------------------------------------------------------

	/**
	 * @see UserService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id, Long user) {
		userRepository.deleteById(id);
		AuditTrailUtil.audit(AuditTrail.DELETE.toString(), "Delete the ID :" + id, user);
	}

	// ~
	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * @see UserService#findById(java.lang.Long)
	 */
	@Override
	public Account findById(Long id) {
		logger.info("id : " + id);
		Optional<Account> user = userRepository.findById(id);
		// if (user != null)
		// user.get().setPassword(SampleUtil.decrypt(user.get().getPassword()));
		return user.get();
	}

	// ~
	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * @see UserService#findByUsername(java.lang.String)
	 */
	@Override
	public Account findByUsername(String username) {
		return userRepository.findByUserName(username);
	}

	// ~
	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * @see UserService#save(Account)
	 */
	@Override
	public void save(Account user, Long userId) {
		// user.setPassword(SampleUtil.encrypt(user.getPassword()));
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setCreatedDate(new Date());
		user.setUpdatedDate(new Date());
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		System.out.println("roles : "+user.getRoles());
		userRepository.save(user);
		AuditTrailUtil.audit(AuditTrail.POST.toString(), "Save :" + user.getId(), userId);
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public void updateUser(Account user, Long id) {
		Account user1 = findById(id);
		user.setPassword(SampleUtil.encrypt(user.getPassword()));
		user.setUpdatedDate(new Date());
		user.setCreatedDate(user1.getCreatedDate());
		user.setId(id);
		userRepository.save(user);
	}

	@Override
	public void updateRoles() {
		Role role = new Role(1L, "ADMIN");
		//roleRepository.saveAndFlush(role);
		roleRepository.save(role);
		/*role = new Role(2L, "USER");
		roleRepository.save(role);*/
		Account user = new Account();
		user.setCredentialsExpired(false);
		user.setUserName("Test");
		user.setFirstName("First Name");
		user.setLastName("last name");
		user.setPassword(SampleUtil.encrypt("Test"));
		user.setEmailAddress("test@test.com");
		user.setEnabled(true);
		user.setExpired(false);
		user.setCreatedBy("System");
		userRepository.save(user);
	}

	@Override
	public List<UserRole> getRoles() {
		// TODO Auto-generated method stub
		return userRoleRepository.findAll();
	}

	@Override
	public ApiUsers getUsers(Specification<Account> spec, PageRequest pageRequest) {

		Validate.notNull(spec, "Specification can't be null");
		Validate.notNull(pageRequest, "pageRequest can't be null");

		ApiUsers usersResponse = new ApiUsers();

		// Get Page info from usersRepository
		Page<Account> usersPage = userRepository.findAll(spec, pageRequest);

		ApiModelPageAndSort pagingResponse = new ApiModelPageAndSort();

		// Set the flag to indicate next page exists
		pagingResponse.setHasNextPage(usersPage.hasNext());

		// Set the flag to indicate previous page exists
		pagingResponse.setHasPreviousPage(usersPage.hasPrevious());

		// Set the total number of records for the given Filter Specification
		pagingResponse.setTotalNumberOfRecords(usersPage.getTotalElements());

		// Set the total number of pages for the given filter specification and
		// pagerequests
		pagingResponse.setTotalNumberOfPages(usersPage.getTotalPages());

		// Page numbers are indexed from 0 but to the consume we follow start index as 1
		pagingResponse.setPageNumber(pageRequest.getPageNumber() + 1);

		// Number of records per page
		pagingResponse.setPageSize(pageRequest.getPageSize());

		usersResponse.setPaging(pagingResponse);

		// Get the Employee List from the Page
		List<Account> users = usersPage.getContent();

		// Map the data to ApiModelEmployeeResource using lambda function
		usersResponse.setData(users.stream().map(this::getAccount).collect(Collectors.toList()));

		return usersResponse;

	}
	private ApiAccount getAccount(Account account) {
		ApiAccount res = new ApiAccount();

		res.setContactPhone(account.getContactPhone());
		res.setFirstName(account.getFirstName());
		res.setLastName(account.getLastName());
		res.setEmailAddress(account.getEmailAddress());
		res.setUserName(account.getUserName());
		res.setCreatedDate(account.getCreatedDate());
		res.setCreatedBy(account.getCreatedBy());
		return res;

	}
} // end class UserServiceImpl
