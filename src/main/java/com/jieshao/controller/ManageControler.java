package com.jieshao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jieshao.Repository.ManageRepository;
import com.jieshao.data.Manage;
import com.jieshao.json.Result;

@Controller
public class ManageControler {
	@Autowired
	private ManageRepository managerepository;
	/**
	 * 管理员登陆
	 */
	@GetMapping(value="/admin")
	public String adminloginView() {
		return "login-admin";
	}
	
	@PostMapping(value="/admin")
	public @ResponseBody Result adminloginAction(Manage manage) {
		if(managerepository.findByMname(manage.getMname()).getMpsw()==manage.getMpsw())//密码正确
		{
			
			
		}
		Result json = new Result(manage.getMname(),manage.getMpsw());
		return json;
	}

}
