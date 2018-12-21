package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Account;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$, $Date$
 */
public interface UserService {

	void delete(Long id, Long user);

	Account findById(Long id);

	Account findByUsername(String username);

	void save(Account user);

	List<Account> findAll();

	void updateUser(Account user, Long id);

	void updateRoles();

} // end interface UserService
