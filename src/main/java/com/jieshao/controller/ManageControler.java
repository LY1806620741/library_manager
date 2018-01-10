package com.jieshao.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jieshao.Repository.ManageRepository;
import com.jieshao.data.Manage;
import com.jieshao.json.Result;

@Controller
//@SessionAttributes({"user"})//使用session user@SessionAttribute("user") String user
public class ManageControler {
	
	Logger logger = LoggerFactory.getLogger(ManageControler.class);
	@Autowired
	private ManageRepository managerepository;
	/**
	 * 管理员管理页面
	 * 权限：管理员
	 * @throws IOException 
	 */
	@GetMapping(value="/admin")
	public String adminView(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
		if(session.getAttribute("user")==null)
		{
			response.sendRedirect(request.getContextPath()+"/admin/login");
			return  null;
		}
		request.setAttribute("user", session.getAttribute("user"));
		
		return "index-admin";
	}
	/**
	 * 登陆页面显示
	 * 权限：所有人
	 * @return
	 */
	@GetMapping(value="/admin/login")
	public String adminloginView() {
			return "login-admin";
	}
	/**
	 * 注销管理员处理
	 * 权限：管理员
	 * @param session
	 * @return
	 */
	@GetMapping(value="/admin/loginout")
	public  @ResponseBody Result adminloginout(HttpSession session) {
		if(session.getAttribute("user") != null){
			session.removeAttribute("user");
			session.removeAttribute("power");
        }
		Result json = new Result("0","注销成功");
		return json;
	}
	/**
	 * 管理员登陆处理
	 * 权限：所有人
	 * @param manage
	 * @param session
	 * @return
	 */
	@PostMapping(value="/admin")
	public @ResponseBody Result adminloginAction(Manage manage, HttpSession session) {
		if (manage.getMname()==null||manage.getMpsw()==null)
		{
			Result json = new Result("-1","账号密码不为空");
			return json;
		}
		
		try{
			Manage manageuser =managerepository.findByMname(manage.getMname());
			if(manageuser.getMpsw().equals(MD5(manage.getMpsw())))//密码正确
			{
				session.setAttribute("user",manage.getMname() );//设置session
				session.setAttribute("power",manageuser.getMpower() );
				//logger.info((String) session.getAttribute("power"));
				//model.addAttribute("user",manage.getMname());//存session
				//记录登陆时间
				managerepository.updatelogintime(manageuser.getMno());
				
				Result json = new Result("0","密码正确");
				return json;
			}else {
				Result json = new Result("-1","密码错误");
				return json;
			}
		}
		catch (NullPointerException e) {
			if (managerepository.count()==0)
			{
				Manage admin = new Manage("admin","E10ADC3949BA59ABBE56E057F20F883E","初始管理员",1);
				managerepository.save(admin);
			}
			Result json = new Result("-1","管理员不存在");
			return json;
		}
	}
	/**
	 * md5加密算法
	 * @param s
	 * @return
	 */
	private String MD5(String s) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] bytes = md.digest(s.getBytes("utf-8"));
	        return toHex(bytes);
	    }
	    catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

	/**
	 * hex算法
	 * @param bytes
	 * @return
	 */
	private static String toHex(byte[] bytes) {

	    final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
	    StringBuilder ret = new StringBuilder(bytes.length * 2);
	    for (int i=0; i<bytes.length; i++) {
	        ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
	        ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
	    }
	    return ret.toString();
	}
	/**
	 * 查找管理员
	 * 权限：超级管理员
	 * @param session
	 * @param rolename
	 * @return
	 */
	@GetMapping(value="/admin/action/manage/search/{rolename}")
	public @ResponseBody List<Manage> Managesearch(HttpSession session,@PathVariable("rolename") String rolename) {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		if(session.getAttribute("power")==null||!session.getAttribute("power").toString().equals("1"))
		{
			return null;
		}
		return managerepository.search(rolename);
		
	}
	/**
	 * 找一个管理员
	 * 权限：管理员
	 * @param session
	 * @param id
	 * @return
	 */
	@GetMapping(value="/admin/action/manage/findone/{id}")
	public @ResponseBody Manage Cardfindone(HttpSession session,@PathVariable("id") Integer id) {
		if(session.getAttribute("user")==null)
		{
			//logger.info("/admin/action/cardtype post请求 用户未登录");
			return  null;
		}
		return managerepository.findOne(id);
		
	}
	/**
	 * 查找全部管理员
	 * 权限：管理员
	 * @param session
	 * @return
	 */
	@GetMapping(value="/admin/action/manage/findall")
	public @ResponseBody List<Manage> Managefindall(HttpSession session) {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		return managerepository.findAll();
	}
	/**
	 * 查看一页页的数据
	 * 权限：超级管理员
	 * @param session
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(value="/admin/action/manage/findpage/{page}/{size}")
	public @ResponseBody Page<Manage> Managefindpage(HttpSession session,@PathVariable Integer page,@PathVariable Integer size) {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		if(session.getAttribute("power")==null||!session.getAttribute("power").toString().equals("1"))
		{
			return null;
		}
		Sort sort=new Sort(Direction.DESC,"mno");//坑5,不报错，要记得排序字段
		Pageable pageable=new PageRequest(page,size,sort);
		return managerepository.findAll(pageable);
		
	}
	/**
	 * 删除一个类型
	 * 权限：超级管理员
	 * @param session
	 * @param id
	 * @return
	 */
	@GetMapping(value="/admin/action/manage/del/{id}")
	public @ResponseBody Result DelManage(HttpSession session,@PathVariable("id") Integer id) {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		if(session.getAttribute("power")==null||!session.getAttribute("power").toString().equals("1"))
		{
			return null;
		}
		managerepository.delete(id);
		return new Result("0","删除成功");
		
	}
	/**
	 * 新建类型
	 * 权限：超级管理员
	 * 约束：权限
	 * @param session
	 * @param cardtype
	 * @return
	 * @throws IOException 
	 */
	@PostMapping(value= "/admin/action/manage/add")
	public @ResponseBody String Manageadd(HttpSession session,HttpServletRequest request,HttpServletResponse response,Manage manage) throws IOException {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		if(session.getAttribute("power")==null||!session.getAttribute("power").toString().equals("1"))
		{
			return "<a href='javascript:history.go(-1);'>没有权限</a>";
		}
		if (manage.getMname()!=null&&manage.getMrealname()!=null&&manage.getMpower()!=null)
		{
			if (manage.getMpsw()!=null&&!manage.getMpsw().equals(""))//密码不为空
			{
				try {
					if (!manage.getMpower().toString().equals("1")&&!manage.getMpower().toString().equals("0")) {//约束权限只有两种
						return "<a href='javascript:history.go(-1);'>tips:管理员的权限设置有问题，点击这里返回</a>";
					}
					manage.setMpsw(MD5(manage.getMpsw()));
					managerepository.save(manage);
					response.sendRedirect(request.getContextPath()+"../../User/index.html");
				}catch (Exception e) {
					return "<a href='javascript:history.go(-1);'>tips:管理员的账号不能重复，点击这里返回</a>";
				}
			}else if(manage.getMno()!=null&&!manage.getMno().toString().equals("")){
				try {
					managerepository.update(manage);//不修改密码
					response.sendRedirect(request.getContextPath()+"../../User/index.html");
				}catch (Exception e) {
					return "<a href='javascript:history.go(-1);'>tips:管理员的账号不能重复，点击这里返回</a>";
				}
			}
		}
		return  "参数不全";
		
	}

}
