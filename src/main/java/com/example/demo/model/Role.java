package com.example.demo.model;

import java.util.Set;

import javax.persistence.*;

import lombok.Data;

/**
 * DOCUMENT ME!
 *
 * @author Ravi
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

	public Role(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Role() {
		super();
	}

} // end class Role
