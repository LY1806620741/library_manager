package com.jieshao.json;

import com.jieshao.data.LIBRARY_CARD;
import com.jieshao.data.TYPE_OF_LIBRARY_CARD;

public class Cardinfo {
	private String CAN;//账号account num
	private String CNAME;//借书证拥有者名字
	private String CSEX;//性别
	private Integer CSTATUS;//状态
	private Integer CARREARS;//欠费
	public String getCAN() {
		return CAN;
	}
	public void setCAN(String cAN) {
		CAN = cAN;
	}
	public String getCNAME() {
		return CNAME;
	}
	public void setCNAME(String cNAME) {
		CNAME = cNAME;
	}
	public String getCSEX() {
		return CSEX;
	}
	public void setCSEX(String cSEX) {
		CSEX = cSEX;
	}
	public Integer getCSTATUS() {
		return CSTATUS;
	}
	public void setCSTATUS(Integer cSTATUS) {
		CSTATUS = cSTATUS;
	}
	public Integer getCARREARS() {
		return CARREARS;
	}
	public void setCARREARS(Integer cARREARS) {
		CARREARS = cARREARS;
	}
	public String getTNAME() {
		return TNAME;
	}
	public void setTNAME(String tNAME) {
		TNAME = tNAME;
	}
	public Integer getTMAX() {
		return TMAX;
	}
	public void setTMAX(Integer tMAX) {
		TMAX = tMAX;
	}
	public Integer getBNOW() {
		return BNOW;
	}
	public void setBNOW(Integer bNOW) {
		BNOW = bNOW;
	}
	public Integer getTLONG() {
		return TLONG;
	}
	public void setTLONG(Integer tLONG) {
		TLONG = tLONG;
	}
	public Integer getTMAXARREARS() {
		return TMAXARREARS;
	}
	public void setTMAXARREARS(Integer tMAXARREARS) {
		TMAXARREARS = tMAXARREARS;
	}
	private String TNAME;//借书证类型名
	private Integer TMAX;//最大借阅数，单位本
	private Integer BNOW;//已借阅本书
	private Integer TLONG;//最长借阅时间，单位天
	private Integer TMAXARREARS;//最大欠费（元）
	public Cardinfo(LIBRARY_CARD card,TYPE_OF_LIBRARY_CARD type) {
		this.CAN=card.getCAN();
		this.CARREARS=card.getCARREARS();
		this.CNAME=card.getCNAME();
		this.CSEX=card.getCSEX();
		this.CSTATUS=card.getCSTATUS();
		this.TMAX=type.getTMAX();
		this.TLONG=type.getTLONG();
		this.TNAME=type.getTNAME();
		this.TMAXARREARS=type.getTMAXARREARS();
	}
}
