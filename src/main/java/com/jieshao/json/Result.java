package com.jieshao.json;

public class Result {
	private String errno;
	public String getErrno() {
		return errno;
	}
	public void setErrno(String errno) {
		this.errno = errno;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	private String msg;
	public Result() {
		
	}
	public Result(String errno,String msg) {
		this.errno=errno;
		this.msg=msg;
	}
}
