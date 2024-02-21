package com.javadevelop.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BaseDTO<T> {
	private Long id;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiefdDate;
	private int page;
	private int totalPage;
	private List<T> listResult = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiefdDate() {
		return modifiefdDate;
	}
	public void setModifiefdDate(Date modifiefdDate) {
		this.modifiefdDate = modifiefdDate;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getListResult() {
		return listResult;
	}
	public void setListResult(List<T> listResult) {
		this.listResult = listResult;
	}
	
}
