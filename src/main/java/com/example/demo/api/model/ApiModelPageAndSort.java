package com.example.demo.api.model;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Paging and sorting information")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-20T03:45:45.424-05:00")

public class ApiModelPageAndSort implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("pageNumber")
	private Integer pageNumber = null;

	@JsonProperty("pageSize")
	private Integer pageSize = null;

	@JsonProperty("totalNumberOfRecords")
	private Long totalNumberOfRecords = null;

	@JsonProperty("totalNumberOfPages")
	private Integer totalNumberOfPages = null;

	@JsonProperty("hasNextPage")
	private Boolean hasNextPage = null;

	@JsonProperty("hasPreviousPage")
	private Boolean hasPreviousPage = null;

	@JsonProperty("sortingCriteria")
	private String sortingCriteria = null;

	public ApiModelPageAndSort pageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
		return this;
	}

	/**
	 * Get pageNumber
	 * 
	 * @return pageNumber
	 **/
	@ApiModelProperty(value = "")

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public ApiModelPageAndSort pageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	/**
	 * Get pageSize
	 * 
	 * @return pageSize
	 **/
	@ApiModelProperty(value = "")

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public ApiModelPageAndSort totalNumberOfRecords(Long totalNumberOfRecords) {
		this.totalNumberOfRecords = totalNumberOfRecords;
		return this;
	}

	/**
	 * Get totalNumberOfRecords
	 * 
	 * @return totalNumberOfRecords
	 **/
	@ApiModelProperty(value = "")

	public Long getTotalNumberOfRecords() {
		return totalNumberOfRecords;
	}

	public void setTotalNumberOfRecords(Long totalNumberOfRecords) {
		this.totalNumberOfRecords = totalNumberOfRecords;
	}

	public ApiModelPageAndSort totalNumberOfPages(Integer totalNumberOfPages) {
		this.totalNumberOfPages = totalNumberOfPages;
		return this;
	}

	/**
	 * Get totalNumberOfPages
	 * 
	 * @return totalNumberOfPages
	 **/
	@ApiModelProperty(value = "")

	public Integer getTotalNumberOfPages() {
		return totalNumberOfPages;
	}

	public void setTotalNumberOfPages(Integer totalNumberOfPages) {
		this.totalNumberOfPages = totalNumberOfPages;
	}

	public ApiModelPageAndSort hasNextPage(Boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
		return this;
	}

	/**
	 * Get hasNextPage
	 * 
	 * @return hasNextPage
	 **/
	@ApiModelProperty(value = "")

	public Boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(Boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public ApiModelPageAndSort hasPreviousPage(Boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
		return this;
	}

	/**
	 * Get hasPreviousPage
	 * 
	 * @return hasPreviousPage
	 **/
	@ApiModelProperty(value = "")

	public Boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(Boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public ApiModelPageAndSort sortingCriteria(String sortingCriteria) {
		this.sortingCriteria = sortingCriteria;
		return this;
	}

	/**
	 * Get sortingCriteria
	 * 
	 * @return sortingCriteria
	 **/
	@ApiModelProperty(value = "")

	public String getSortingCriteria() {
		return sortingCriteria;
	}

	public void setSortingCriteria(String sortingCriteria) {
		this.sortingCriteria = sortingCriteria;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ApiModelPageAndSort pageAndSort = (ApiModelPageAndSort) o;
		return Objects.equals(this.pageNumber, pageAndSort.pageNumber)
				&& Objects.equals(this.pageSize, pageAndSort.pageSize)
				&& Objects.equals(this.totalNumberOfRecords, pageAndSort.totalNumberOfRecords)
				&& Objects.equals(this.totalNumberOfPages, pageAndSort.totalNumberOfPages)
				&& Objects.equals(this.hasNextPage, pageAndSort.hasNextPage)
				&& Objects.equals(this.hasPreviousPage, pageAndSort.hasPreviousPage)
				&& Objects.equals(this.sortingCriteria, pageAndSort.sortingCriteria);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pageNumber, pageSize, totalNumberOfRecords, totalNumberOfPages, hasNextPage,
				hasPreviousPage, sortingCriteria);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ApiModelPageAndSort {\n");

		sb.append("    pageNumber: ").append(toIndentedString(pageNumber)).append("\n");
		sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
		sb.append("    totalNumberOfRecords: ").append(toIndentedString(totalNumberOfRecords)).append("\n");
		sb.append("    totalNumberOfPages: ").append(toIndentedString(totalNumberOfPages)).append("\n");
		sb.append("    hasNextPage: ").append(toIndentedString(hasNextPage)).append("\n");
		sb.append("    hasPreviousPage: ").append(toIndentedString(hasPreviousPage)).append("\n");
		sb.append("    sortingCriteria: ").append(toIndentedString(sortingCriteria)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
