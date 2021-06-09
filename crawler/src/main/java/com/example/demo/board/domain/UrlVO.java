package com.example.demo.board.domain;

import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository
public class UrlVO {
	private int id;
	private String URL;
	private String pId;
	private int depth;
	private Date regi_date;
	
	public UrlVO() {}
	
	public UrlVO(int id, String uRL, String pId, int depth, Date regi_date) {
		super();
		this.id = id;
		URL = uRL;
		this.pId = pId;
		this.depth = depth;
		this.regi_date = regi_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public Date getRegi_date() {
		return regi_date;
	}
	public void setRegi_date(Date regi_date) {
		this.regi_date = regi_date;
	}
	
	
	
}
