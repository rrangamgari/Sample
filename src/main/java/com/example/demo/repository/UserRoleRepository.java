package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Account;
import com.example.demo.model.UserRole;



/**
 * DOCUMENT ME!
 *
 * @author   Ravi
 * @version  $Revision$, $Date$
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param   username  DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
	//Optional<UserRole> findById(Long id);
}
