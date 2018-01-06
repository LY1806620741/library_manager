package com.jieshao.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
/**
 * 图书信息表
 * @author CrazyHobo俊杰
 *
 */
public class Books {

	public Books() {
	}
	@Id
	@GeneratedValue
	private String bisbn;//图书isbn
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
	public Integer getLno() {
		return lno;
	}
	public void setLno(Integer lno) {
		this.lno = lno;
	}
	public String getBimg() {
		return bimg;
	}
	public void setBimg(String bimg) {
		this.bimg = bimg;
	}
	private String bname;//图书名字
	private String bauthor;//图书作者
	private String bpress;//出版社
	private Integer bprice;//价格
	private Integer bcopy;//副本量
	private Integer binventory;//库存量
	private String bimg;//图书图片
	private Integer lno;//外键，图书室id

}
