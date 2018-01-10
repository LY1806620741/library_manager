package com.jieshao.json;

import java.util.Date;

import com.jieshao.data.Books;
import com.jieshao.data.Borrow;
import com.jieshao.data.LIBRARY_CARD;

public class AdminHistory {
	private Integer id;//记录id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private Date begintime = new Date();//借书开始时间
	private Date returntime;//还书时间
	private Integer BARREARS=0;//欠费金额
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	public Date getReturntime() {
		return returntime;
	}
	public void setReturntime(Date returntime) {
		this.returntime = returntime;
	}
	public Integer getBARREARS() {
		return BARREARS;
	}
	public void setBARREARS(Integer bARREARS) {
		BARREARS = bARREARS;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	private String bname;//图书名字
	private String cname;//图书名字
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public AdminHistory(Borrow b,Books book,LIBRARY_CARD l) {
		this.id=b.getId();
		this.begintime=b.getBegintime();
		this.returntime=b.getReturntime();
		this.BARREARS=b.getBARREARS();
		this.bname=book.getBname();
		this.cname=l.getCNAME();
	}
}
