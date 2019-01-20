package com.example.demo.model;

import java.util.List;

import lombok.Data;

@Data
public class Search {
	private List<Filter> filter;
	private String orderBy;
	private String sort;

}
