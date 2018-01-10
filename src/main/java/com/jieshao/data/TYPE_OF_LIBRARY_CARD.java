package com.jieshao.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
/**
 * 对应数据库中的TYPE_OF_LIBRARY_CARD（借书证类型表）
 * @author CrazyHobo俊杰
 *
 */
public class TYPE_OF_LIBRARY_CARD {

	public TYPE_OF_LIBRARY_CARD() {
		
	}
		public TYPE_OF_LIBRARY_CARD(String tNAME, Integer tMAX, Integer tLONG) {
		TNAME = tNAME;
		TMAX = tMAX;
		TLONG = tLONG;
	}
		@Id
		@GeneratedValue
		private Integer TNO;//借书证类型ID
		public Integer getTNO() {
			return TNO;
		}
		public void setTNO(Integer tNO) {
			TNO = tNO;
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
		public Integer getTLONG() {
			return TLONG;
		}
		public void setTLONG(Integer tLONG) {
			TLONG = tLONG;
		}
		private String TNAME;//借书证类型名
		private Integer TMAX;//最大借阅数，单位本
		private Integer TLONG;//最长借阅时间，单位天
		private Integer TMAXARREARS;//最大欠费（元）
		public Integer getTMAXARREARS() {
			return TMAXARREARS;
		}
		public void setTMAXARREARS(Integer tMAXARREARS) {
			TMAXARREARS = tMAXARREARS;
		}
		
}
