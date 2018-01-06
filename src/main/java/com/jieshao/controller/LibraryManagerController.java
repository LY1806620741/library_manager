package com.jieshao.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jieshao.Repository.BooksRepository;
import com.jieshao.Repository.LibraryCardRepository;
import com.jieshao.Repository.ManageRepository;
import com.jieshao.Repository.SysteminfoRepository;
import com.jieshao.data.LIBRARY_CARD;
import com.jieshao.data.Systeminfo;
import com.jieshao.json.Msg;

@Controller
public class LibraryManagerController {
	
	@Autowired
	private LibraryCardRepository librarycardrepository;//借书证
	@Autowired//重要，不报空指针
	private BooksRepository booksrepository;//书
	@Autowired
	private ManageRepository managerepository;//管理员
	@Autowired
	private SysteminfoRepository systeminforepository;//系统设置
	
//	@RequestMapping(value= {"/index","/"},method=RequestMethod.GET)
//	public void init() {
//		
//		
////		if (systeminfo.getDay().getDate())
//		
//	}
	/**
	 * 主页
	 * @param request
	 * @return
	 */
	@RequestMapping(value= {"/index","/"},method=RequestMethod.GET)
	public String Index(HttpServletRequest request) {
		if (!systeminforepository.exists(0))//如果没有系统设置，新建设置
		{
			Systeminfo systeminfo = new Systeminfo();
			systeminfo.setId(0);
			systeminfo.setIsopen(1);
			systeminfo.setDayview(0);
			systeminfo.setAllview(0);
			systeminfo.setStartday(new Date());
			systeminfo.setDay(new Date());
			systeminforepository.save(systeminfo);
		}
		Systeminfo getsysteminfo=systeminforepository.getOne(0);//取得系统配置
		//检查是不是新的一天
		Date now=new Date();
		if (getsysteminfo.getDay().getDay()<now.getDay()&&getsysteminfo.getDay().getMonth()<now.getMonth()&&getsysteminfo.getDay().getYear()<now.getYear())
		{
			getsysteminfo.setAllview(getsysteminfo.getDayview()+getsysteminfo.getAllview());//加到全部阅览量
			getsysteminfo.setDayview(0);//重置为0
			getsysteminfo.setDay(now);//重置时间
			getsysteminfo.setDayview(getsysteminfo.getDayview()+1);//更新主页浏览量
			systeminforepository.save(getsysteminfo);//更新
		}else {
			getsysteminfo.setDayview(getsysteminfo.getDayview()+1);
			systeminforepository.save(getsysteminfo);//更新
		}
		
		request.setAttribute("title", "图书管理系统");
		request.setAttribute("cardnum", librarycardrepository.count());
		request.setAttribute("booknum", booksrepository.count());
		request.setAttribute("managernum", managerepository.count());
		request.setAttribute("dayview", getsysteminfo.getDayview());
		return "index";
	}
}
