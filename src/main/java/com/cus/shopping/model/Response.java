package com.cus.shopping.model;

public class Response {
	
	private String code;
	
	private String description;
	
	private Integer count;
	
	public Response(String code, String description, Integer count) {
		super();
		this.code = code;
		this.description = description;
		this.count = count;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Response(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
