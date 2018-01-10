package com.jieshao.dataview;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VW_ROOMINFO")
public class vwRoomInfo {
		@Id
		private Integer lno;//图书室id
		public Integer getLno() {
			return lno;
		}
		public void setLno(Integer lno) {
			this.lno = lno;
		}
		public String getLname() {
			return lname;
		}
		public void setLname(String lname) {
			this.lname = lname;
		}
		public String getLposition() {
			return lposition;
		}
		public void setLposition(String lposition) {
			this.lposition = lposition;
		}
		public String getLimg() {
			return limg;
		}
		public void setLimg(String limg) {
			this.limg = limg;
		}
		public Integer getMno() {
			return mno;
		}
		public void setMno(Integer mno) {
			this.mno = mno;
		}
		public String getMrealname() {
			return mrealname;
		}
		public void setMrealname(String mrealname) {
			this.mrealname = mrealname;
		}
		private String lname;//图书室名字
		private String lposition;//图书室位置
		private String limg;//图书室图片地址
		private Integer mno;//图书室的管理员，可以为空
		private String mrealname;//管理员真名
		
}
