package com.jieshao.dataview;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="vm_CardAndType")
public class vm_CardAndType {
	@Id
	private Integer CNO;//借书证ID（自增）
	private String CAN;//账号account num
	private String CPSW;//借书证密码
	private String CNAME;//借书证拥有者名字
	private String CSEX;//借书证者的性别
	private String TNAME;//借书证类型名
	private Integer CSTATUS;//借书证状态，0禁用，1开启
	public Integer getCNO() {
		return CNO;
	}
	public void setCNO(Integer cNO) {
		CNO = cNO;
	}
	public String getCAN() {
		return CAN;
	}
	public void setCAN(String cAN) {
		CAN = cAN;
	}
	public String getCPSW() {
		return CPSW;
	}
	public void setCPSW(String cPSW) {
		CPSW = cPSW;
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
	public String getTNAME() {
		return TNAME;
	}
	public void setTNAME(String tNAME) {
		TNAME = tNAME;
	}
	public Integer getCSTATUS() {
		return CSTATUS;
	}
	public void setCSTATUS(Integer cSTATUS) {
		CSTATUS = cSTATUS;
	}
}
