package com.jieshao.controller;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jieshao.Repository.LibraryCardRepository;
import com.jieshao.Repository.ManageRepository;
import com.jieshao.Repository.TypeOfLibraryCardRepository;
import com.jieshao.aspect.HttpAspect;
import com.jieshao.data.Manage;
import com.jieshao.data.TYPE_OF_LIBRARY_CARD;
import com.jieshao.json.Result;

@Controller
//@SessionAttributes({"user"})//使用session user@SessionAttribute("user") String user
public class ManageControler {
	
	Logger logger = LoggerFactory.getLogger(ManageControler.class);
	@Autowired
	private ManageRepository managerepository;
	private LibraryCardRepository librarycardrepository;
	private TypeOfLibraryCardRepository typeoflibrarycardrepository;
	/**
	 * 管理员管理页面
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
	 * @return
	 */
	@GetMapping(value="/admin/login")
	public String adminloginView() {
			return "login-admin";
	}
	/**
	 * 注销处理
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
			if(managerepository.findByMname(manage.getMname()).getMpsw().equals(MD5(manage.getMpsw())))//密码正确
			{
				session.setAttribute("user",manage.getMname() );//设置session
				session.setAttribute("power",managerepository.findByMname(manage.getMname()).getMpower() );
				//logger.info((String) session.getAttribute("power"));
				//model.addAttribute("user",manage.getMname());//存session
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

}
