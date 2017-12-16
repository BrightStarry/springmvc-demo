package com.zx.domain;

import java.util.List;

public class Cla {
	private String claId;
	private String claName;
	private List<Stu> stuList;
	
	public List<Stu> getStuList() {
		return stuList;
	}
	public void setStuList(List<Stu> stuList) {
		this.stuList = stuList;
	}
	public String getClaId() {
		return claId;
	}
	public void setClaId(String claId) {
		this.claId = claId;
	}
	public String getClaName() {
		return claName;
	}
	public void setClaName(String claName) {
		this.claName = claName;
	}
	public Cla(String claId, String claName) {
		super();
		this.claId = claId;
		this.claName = claName;
	}
	public Cla() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cla(String claId, String claName, List<Stu> stuList) {
		super();
		this.claId = claId;
		this.claName = claName;
		this.stuList = stuList;
	}
	
	
}
