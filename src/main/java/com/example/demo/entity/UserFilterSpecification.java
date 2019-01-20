package com.example.demo.entity;

import java.time.chrono.ChronoLocalDate;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.filter.Converters;
import com.example.demo.filter.FilterCriteria;
import com.example.demo.filter.FilterSpecifications;
import com.example.demo.model.Account;


@Service
public class UserFilterSpecification {

	
	
	/**
	 * {@link FilterSpecifications} for Entity {@link Employee} and Field type {@link ChronoLocalDate} (LocalDate)
	 */
	@Autowired
	private FilterSpecifications<Account, ChronoLocalDate> dateTypeSpecifications;

	/**
	 * {@link FilterSpecifications} for Entity {@link Account} and Field type {@link String}
	 */
	@Autowired
	private FilterSpecifications<Account, String> stringTypeSpecifications;

	/**
	 * {@link FilterSpecifications} for Entity {@link Account} and Field type {@link Integer}
	 * 
	 */
	@Autowired
	private FilterSpecifications<Account, Integer> integerTypeSpecifications;

	/**
	 * {@link FilterSpecifications} for Entity {@link Account} and Field type {@link Long}
	 */
	@Autowired
	private FilterSpecifications<Account, Long> longTypeSpecifications;
	
	
	/**
	 * Converter Functions
	 */
	@Autowired
	private Converters converters;
	
	
	/**
	 * Returns the Specification for Entity {@link Account} for the given fieldName and filterValue for the field type Date
	 * 
	 * @param fieldName
	 * @param filterValue
	 * @return
	 */
	public Specification<Account> getDateTypeSpecification(String fieldName, String filterValue) {
		return getSpecification(fieldName, filterValue, converters.getFunction(ChronoLocalDate.class), dateTypeSpecifications);
	}

	/**
	 * Returns the Specification for Entity {@link Account} for the given fieldName and filterValue for the field type String
	 * @param fieldName
	 * @param filterValue
	 * @return
	 */
	public Specification<Account> getStringTypeSpecification(String fieldName, String filterValue) {
		return getSpecification(fieldName, filterValue, converters.getFunction(String.class), stringTypeSpecifications);
	}
	
	/**
	 * Returns the Specification for Entity {@link Account} for the given fieldName and filterValue for the field type Long
	 * 
	 * @param fieldName
	 * @param filterValue
	 * @return
	 */
	public Specification<Account> getLongTypeSpecification(String fieldName, String filterValue) {
		return getSpecification(fieldName, filterValue, converters.getFunction(Long.class), longTypeSpecifications);
	}
	
	
	/**
	 * Returns the Specification for Entity {@link Account} for the given fieldName and filterValue for the field type Integer
	 * 
	 * @param fieldName
	 * @param filterValue
	 * @return
	 */
	public Specification<Account> getIntegerTypeSpecification(String fieldName, String filterValue) {
		return getSpecification(fieldName, filterValue, converters.getFunction(Integer.class), integerTypeSpecifications);
	}

	/**
	 * Generic method to return {@link Specification} for Entity {@link Account}
	 * 
	 * @param fieldName
	 * @param filterValue
	 * @param converter
	 * @param specifications
	 * @return
	 */
	private <T extends Comparable<T>> Specification<Account> getSpecification(String fieldName,
			String filterValue, Function<String, T> converter, FilterSpecifications<Account, T> specifications) {

		if (StringUtils.isNotBlank(filterValue)) {
			
			//Form the filter Criteria
			FilterCriteria<T> criteria = new FilterCriteria<>(fieldName, filterValue, converter);
			return specifications.getSpecification(criteria.getOperation()).apply(criteria);
		}

		return null;

	}
	

}
