package com.jieshao.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
/**
 * 系统表
 * @author CrazyHobo俊杰
 *
 */
public class Systeminfo {

	public Systeminfo() {
	}
	@Id
	//@GeneratedValue不自增
	private Integer id;//主键id
	public Integer getDayview() {
		return dayview;
	}
	public void setDayview(Integer dayview) {
		this.dayview = dayview;
	}
	private Integer isopen;//是否打开
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIsopen() {
		return isopen;
	}
	public void setIsopen(Integer isopen) {
		this.isopen = isopen;
	}
	private Integer dayview;//日访问量
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public Date getStartday() {
		return startday;
	}
	public void setStartday(Date startday) {
		this.startday = startday;
	}
	public Integer getAllview() {
		return allview;
	}
	public void setAllview(Integer allview) {
		this.allview = allview;
	}
	private Date day;//系统刷新时间
	private Date startday;//运行系统时间
	private Integer allview;//所有的访问量
	
}
