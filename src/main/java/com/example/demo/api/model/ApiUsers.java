package com.example.demo.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class ApiUsers {
	@JsonProperty("data")
	@Valid
	private List<ApiAccount> data = null;

	@JsonProperty("paging")
	private ApiModelPageAndSort paging = null;

	public ApiUsers data(List<ApiAccount> data) {
		this.data = data;
		return this;
	}

	public ApiUsers addDataItem(ApiAccount dataItem) {
		if (this.data == null) {
			this.data = new ArrayList<>();
		}
		this.data.add(dataItem);
		return this;
	}

	/**
	 * Get data
	 * 
	 * @return data
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public List<ApiAccount> getData() {
		return data;
	}

	public void setData(List<ApiAccount> data) {
		this.data = data;
	}

	public ApiUsers paging(ApiModelPageAndSort paging) {
		this.paging = paging;
		return this;
	}

	/**
	 * Get paging
	 * 
	 * @return paging
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public ApiModelPageAndSort getPaging() {
		return paging;
	}

	public void setPaging(ApiModelPageAndSort paging) {
		this.paging = paging;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ApiUsers employees = (ApiUsers) o;
		return Objects.equals(this.data, employees.data) && Objects.equals(this.paging, employees.paging);
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, paging);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ApiUsers {\n");

		sb.append("    data: ").append(toIndentedString(data)).append("\n");
		sb.append("    paging: ").append(toIndentedString(paging)).append("\n");
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
