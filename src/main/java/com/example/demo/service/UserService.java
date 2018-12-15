package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
public interface UserService {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param  id  DOCUMENT ME!
   */
  void delete(Long id);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param   id  DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  User findById(Long id);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param   username  DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  User findByUsername(String username);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param  user  DOCUMENT ME!
   */
  void save(User user);

	List<User> findAll();

} // end interface UserService
