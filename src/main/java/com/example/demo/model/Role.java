package com.example.demo.model;

import java.util.Set;

import javax.persistence.*;

import lombok.Data;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$, $Date$
 */
@Entity
@Table(name = "role")
@Data
public class Role {
	// ~ Instance fields
	// --------------------------------------------------------------------------------------------------
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	@Column
	private String name;
	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

} // end class Role
