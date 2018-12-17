package com.example.demo.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_activity")
@Data
@Getter
@Setter
@NoArgsConstructor
public class UserActivity {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	@Column(nullable = false)
	private Long createdBy;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@Column
	private String activitySummary;
	@Column
	private String activityDetail;

}
