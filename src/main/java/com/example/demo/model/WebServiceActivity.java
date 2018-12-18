package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "webservice_activity")
public class WebServiceActivity {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long webServiceId;
}
