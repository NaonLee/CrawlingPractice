package com.example.demo.board.domain;

import java.util.Date;

public class BoardVO {
	private String id;
	private String URL;
	private String parentId;
	private Date registeredDate;
	
	public BoardVO(String id, String URL, String parentId, Date registeredDate) {
		this.id = id;
		this.URL = URL;
		this.parentId = parentId;
		this.registeredDate = registeredDate;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	
	
}
