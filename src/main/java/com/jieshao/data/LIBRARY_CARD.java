package com.jieshao.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
/**
 * 对应LIBRARY_CARD借书证表
 * @author CrazyHobo俊杰
 *
 */
public class LIBRARY_CARD {

	public LIBRARY_CARD() {
	}
	@Id
	@GeneratedValue
	private String CNO;//借书证ID（自增）
	public String getCNO() {
		return CNO;
	}
	public void setCNO(String cNO) {
		CNO = cNO;
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
	public Integer getTNO() {
		return TNO;
	}
	public void setTNO(Integer tNO) {
		TNO = tNO;
	}
	public Integer getCSTATUS() {
		return CSTATUS;
	}
	public void setCSTATUS(Integer cSTATUS) {
		CSTATUS = cSTATUS;
	}
	public String getCPSW() {
		return CPSW;
	}
	public void setCPSW(String cPSW) {
		CPSW = cPSW;
	}
	private String CNAME;//借书证拥有者名字
	private String CSEX;//借书证者的性别
	private Integer TNO;//借书证的类型，外键
	private Integer CSTATUS;//借书证状态，0禁用，1开启
	private String CPSW;//借书证密码
	
}
