package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Account;
import com.example.demo.model.UserActivity;



/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  
}
