package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.UserActivity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserActivityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.AuditTrail;
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
	private UserActivityRepository auditTrail;
	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	// ~ Methods
	// ----------------------------------------------------------------------------------------------------------

	/**
	 * @see UserService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	// ~
	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * @see UserService#findById(java.lang.Long)
	 */
	@Override
	public User findById(Long id) {
		logger.info("id : " + id);
		Optional<User> user = userRepository.findById(id);
		UserActivity userActivity= new UserActivity();
		userActivity.setActivitySummary(AuditTrail.GET.toString());
		auditTrail.save(userActivity);
		return user.get();
	}

	// ~
	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * @see UserService#findByUsername(java.lang.String)
	 */
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUserName(username);
	}

	// ~
	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * @see UserService#save(User)
	 */
	@Override
	public void save(User user) {
		user.setPassword(user.getPassword());
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

} // end class UserServiceImpl
