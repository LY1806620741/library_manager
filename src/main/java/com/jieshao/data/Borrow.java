package com.jieshao.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
/**
 * 借阅表
 * @author CrazyHobo俊杰
 *
 */
public class Borrow {

	public Borrow() {
	}
	@Id
	@GeneratedValue
	private Integer id;//记录id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCno() {
		return cno;
	}
	public void setCno(Integer cno) {
		this.cno = cno;
	}
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	private Integer cno;//外键借书证号
	private Integer bno;//外键图书isbn
	//@Column(nullable=false,columnDefinition="timestamp default current_timestamp")//插入的时候去的当前时间
	private Date begintime = new Date();//借书开始时间
	private Date returntime;//还书时间
	private Integer BARREARS=0;//欠费金额
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
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
}
