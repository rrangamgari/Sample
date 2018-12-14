package com.example.demo.service;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;



/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
@Service public class UserServiceImpl implements UserService {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private RoleRepository roleRepository;
  @Autowired private UserRepository userRepository;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * @see  UserService#delete(java.lang.Long)
   */
  @Override public void delete(Long id) {
    userRepository.deleteById(id);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  UserService#findById(java.lang.Long)
   */
  @Override public User findById(Long id) {
    Optional<User> user = userRepository.findById(id);

    return user.get();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  UserService#findByUsername(java.lang.String)
   */
  @Override public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  UserService#save(User)
   */
  @Override public void save(User user) {
    user.setPassword(user.getPassword());
    user.setRoles(new HashSet<>(roleRepository.findAll()));
    userRepository.save(user);
  }




} // end class UserServiceImpl
