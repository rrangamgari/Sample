package com.example.demo.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Account;
import com.example.demo.model.Role;
import com.example.demo.model.UserActivity;
import com.example.demo.model.UserRole;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserActivityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.util.AuditTrail;
import com.example.demo.util.AuditTrailUtil;
import com.example.demo.util.SampleUtil;
import com.example.demo.web.UserRestController;

/**
 * DOCUMENT ME!
 *
 * @author $author$
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
	public void save(Account user) {
		// user.setPassword(SampleUtil.encrypt(user.getPassword()));
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setCreatedDate(new Date());
		user.setUpdatedDate(new Date());
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		userRepository.save(user);
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
		roleRepository.saveAndFlush(role);
		// role = new Role(2L, "USER");
		// roleRepository.save(role);
	}

	@Override
	public List<UserRole> getRoles() {
		// TODO Auto-generated method stub
		return userRoleRepository.findAll();
	}

} // end class UserServiceImpl
