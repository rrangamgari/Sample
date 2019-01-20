package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.api.model.ApiUsers;
import com.example.demo.model.Account;
import com.example.demo.model.UserRole;

/**
 * DOCUMENT ME!
 *
 * @author Ravi
 * @version $Revision$, $Date$
 */
public interface UserService {

	void delete(Long id, Long userId);

	Account findById(Long id);

	Account findByUsername(String username);

	List<Account> findAll();

	void updateUser(Account user, Long userId);

	void updateRoles();

	List<UserRole> getRoles();

	void save(Account user, Long userId);

	public ApiUsers getUsers(Specification<Account> spec, PageRequest pageRequest);

} // end interface UserService
