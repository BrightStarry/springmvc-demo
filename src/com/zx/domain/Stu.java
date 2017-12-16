package com.zx.domain;

public class Stu {
	private String stuId;
	private String stuName;
	private Cla cla;
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public Cla getCla() {
		return cla;
	}
	public void setCla(Cla cla) {
		this.cla = cla;
	}
	public Stu(String stuId, String stuName, Cla cla) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.cla = cla;
	}
	public Stu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
