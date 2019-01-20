package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Account;

/**
 * DOCUMENT ME!
 *
 * @author Ravi
 * @version $Revision$, $Date$
 */
public interface UserRepository extends CrudRepository<Account, Long>, JpaSpecificationExecutor<Account> {
	// ~ Methods
	// ----------------------------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param username
	 *            DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	Account findByUserName(String userName);

	List<Account> findAll();
}
