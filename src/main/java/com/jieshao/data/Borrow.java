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
	public String getBisbn() {
		return bisbn;
	}
	public void setBisbn(String bisbn) {
		this.bisbn = bisbn;
	}
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	public Integer getReturnbook() {
		return returnbook;
	}
	public void setReturnbook(Integer returnbook) {
		this.returnbook = returnbook;
	}
	private Integer cno;//外键借书证号
	private String bisbn;//外键图书isbn
	//@Column(nullable=false,columnDefinition="timestamp default current_timestamp")//插入的时候去的当前时间
	private Date begintime = new Date();//借书开始时间
	private Integer returnbook;//是否归还了书0未还1还了
	
}
