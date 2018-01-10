package com.jieshao.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jieshao.Repository.BooksRepository;
import com.jieshao.Repository.LibraryCardRepository;
import com.jieshao.Repository.ManageRepository;
import com.jieshao.Repository.SysteminfoRepository;
import com.jieshao.data.Systeminfo;

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
	public String Index(HttpServletRequest request,HttpSession session) {
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
		//获得记录时间后一天的时间
        Calendar calendar= new GregorianCalendar();
        calendar.setTime(getsysteminfo.getDay());//设置为记录点的时间
        calendar.add(Calendar.DATE,1);//后一天
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date=calendar.getTime();//取得时间
		if (date.getTime()<now.getTime())
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
		if(session.getAttribute("card")!=null)
		{
			request.setAttribute("card", session.getAttribute("card"));
		}else {
			request.setAttribute("card", "游客");
		}
		return "index";
	}
}
