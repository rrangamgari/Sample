package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Account;
import com.example.demo.model.UserActivity;



/**
 * DOCUMENT ME!
 *
 * @author   Ravi
 * @version  $Revision$, $Date$
 */
public interface UserActivityRepository extends CrudRepository<UserActivity, Long> {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  
}
