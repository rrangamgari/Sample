package com.example.demo.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$, $Date$
 */
@Entity
@Table(name = "user")
@Data
@Getter
@Setter
@NoArgsConstructor
public class User {
	// ~ Instance fields
	// --------------------------------------------------------------------------------------------------
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	@Column(nullable = false)
	private String password;
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@ManyToMany
	private Set<Role> roles;
	@Column(nullable = false, unique = true)
	private String userName;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column
	private String midddleName;
	@Column
	private String contactPhone;
	@Column(nullable = false, unique = true)
	private String emailAddress;
	@Column(nullable = false)
	private String createdBy;

	private Date createdDate;

	private Date updatedDate;

} // end class User
