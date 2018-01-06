package com.jieshao.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
/**
 * 管理员表
 * @author CrazyHobo俊杰
 *
 */
public class Manage {
	@Id
	@GeneratedValue
	private Integer mno;//管理员ID
	public Integer getMno() {
		return mno;
	}
	public void setMno(Integer mno) {
		this.mno = mno;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMpsw() {
		return mpsw;
	}
	public void setMpsw(String mpsw) {
		this.mpsw = mpsw;
	}
	public String getMrealname() {
		return mrealname;
	}
	public void setMrealname(String mrealname) {
		this.mrealname = mrealname;
	}
	public Integer getMpower() {
		return mpower;
	}
	public void setMpower(Integer mpower) {
		this.mpower = mpower;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getLogintime() {
		return logintime;
	}
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}
	private String mname;//管理员账号
	private String mpsw;//管理员密码
	private String mrealname;//管理员真名
	private Integer mpower;//权限
	private Date createtime;//添加管理员的时间
	private Date logintime;//登陆的时间

}
