package com.jieshao.dataview;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VW_BOOKINFO")
public class vwBookInfo {
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getBisbn() {
		return bisbn;
	}
	public void setBisbn(String bisbn) {
		this.bisbn = bisbn;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getBauthor() {
		return bauthor;
	}
	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}
	public String getBpress() {
		return bpress;
	}
	public void setBpress(String bpress) {
		this.bpress = bpress;
	}
	public Integer getBprice() {
		return bprice;
	}
	public void setBprice(Integer bprice) {
		this.bprice = bprice;
	}
	public Integer getBcopy() {
		return bcopy;
	}
	public void setBcopy(Integer bcopy) {
		this.bcopy = bcopy;
	}
	public Integer getBinventory() {
		return binventory;
	}
	public void setBinventory(Integer binventory) {
		this.binventory = binventory;
	}
	public String getBimg() {
		return bimg;
	}
	public void setBimg(String bimg) {
		this.bimg = bimg;
	}
	public Integer getLno() {
		return lno;
	}
	public void setLno(Integer lno) {
		this.lno = lno;
	}
	public String getLposition() {
		return lposition;
	}
	public void setLposition(String lposition) {
		this.lposition = lposition;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	@Id
	private Integer bno;//id
	private String bisbn;//图书isbn
	private String bname;//图书名字
	private String bauthor;//图书作者
	private String bpress;//出版社
	private Integer bprice;//价格
	private Integer bcopy;//副本量
	private Integer binventory;//库存量
	private String bimg;//图书图片
	private Integer lno;//图书室id
	private String lname;//图书室名字
	private String lposition;//图书室位置
}
