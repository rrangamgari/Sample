package com.example.demo.api.model;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * DOCUMENT ME!
 *
 * @author Ravi
 * @version $Revision$, $Date$
 */

@Data
@Getter
@Setter

public class ApiAccount {

	private Long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String midddleName;
	private String contactPhone;
	private String emailAddress;
	private String createdBy;
	private Date createdDate;
	private Date updatedDate;
	private boolean expired;
	private boolean locked;
	private boolean credentialsExpired;
	private boolean enabled;

} // end class User
