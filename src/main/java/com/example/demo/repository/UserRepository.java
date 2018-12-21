package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Account;



/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
public interface UserRepository extends JpaRepository<Account, Long> {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param   username  DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  Account findByUserName(String userName);
}
