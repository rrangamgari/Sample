package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_role1")
@Data
@Getter
@Setter
public class UserRole {
	@Id
	@GenericGenerator(name="kaugen" , strategy="increment")
	@GeneratedValue(generator="kaugen")
	@Column(name="user_role_id")
	private Long userRoleId;
	@Column(nullable = false, name = "user_id")
	private Long userId;
	@Column(nullable = false, name = "role_id")
	private Long roleId;

}
