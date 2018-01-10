package com.jieshao.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Library {

	public Library() {
	}
	@Id
	@GeneratedValue
	private Integer lno;//图书室id
	public Integer getLno() {
		return lno;
	}
	public void setLno(Integer lno) {
		this.lno = lno;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getLposition() {
		return lposition;
	}
	public void setLposition(String lposition) {
		this.lposition = lposition;
	}
	public String getLimg() {
		return limg;
	}
	public void setLimg(String limg) {
		this.limg = limg;
	}
	public Integer getMno() {
		return mno;
	}
	public void setMno(Integer mno) {
		this.mno = mno;
	}
	private String lname;//图书室名字
	@Column(unique=true)//唯一约束
	private String lposition;//图书室位置
	private String limg;//图书室图片地址
	private Integer mno;//图书室的管理员，可以为空
}
