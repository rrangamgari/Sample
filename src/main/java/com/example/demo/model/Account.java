package com.example.demo.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.Generated;
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
@Table(name = "account")
@Data
@Getter
@Setter

public class Account {
	public Account() {
		super();
	}

	public Account(Account account) {
		this.id = account.getId();
		this.userName = account.getUserName();
		this.password = account.getPassword();
		this.enabled = account.isEnabled();
		this.credentialsExpired = account.isCredentialsExpired();
		this.locked = account.isExpired();
		this.roles = account.getRoles();
	}

	// ~ Instance fields
	// --------------------------------------------------------------------------------------------------
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id

	private Long id;
	@Column(nullable = false)
	private String password;
	// @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
	// inverseJoinColumns = @JoinColumn(name = "role_id"))
	// @ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
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
	private boolean expired;
	private boolean locked;
	private boolean credentialsExpired;
	private boolean enabled;

} // end class User
