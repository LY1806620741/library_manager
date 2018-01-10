package com.jieshao.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jieshao.Repository.LibraryCardRepository;
import com.jieshao.data.LIBRARY_CARD;
import com.jieshao.dataview.vwCardAndType;
import com.jieshao.json.Result;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LibraryCardController {

	Logger logger = LoggerFactory.getLogger(LibraryCardController.class);
	@Autowired
	private LibraryCardRepository librarycardRepository;
	
	/**
	 * 用户登陆界面（调用模板）
	 * 权限：所有人
	 * @return
	 * @throws IOException 
	 */
	@GetMapping(value="/login")
	public String CardloginView(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws IOException {
		if(session.getAttribute("card")!=null)
		{
			response.sendRedirect(request.getContextPath()+"/");
			return  null;
		}
		return "login";
	}
	@GetMapping(value="/loginout")
	public @ResponseBody Result Cardloginout(HttpSession session) {
		if(session.getAttribute("card") != null){
			session.removeAttribute("card");
			session.removeAttribute("cno");
        }
		Result json = new Result("0","注销成功");
		return json;
	}
	/**
	 * 用借书卡登陆
	 * @param session
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping(value="/login")
	public @ResponseBody Result Cardlogin(@RequestParam("username") String username,
			@RequestParam("password") String password,HttpSession session) {
			if (username==null||password==null)
			{
				Result json = new Result("-1","账号密码不为空");
				return json;
			}
			
			try{
				LIBRARY_CARD carduser =librarycardRepository.findByCAN(username);
				if(carduser.getCPSW().equals(password))//密码正确不用加密
				{
					session.setAttribute("card",carduser.getCNAME());//设置sessionc
					session.setAttribute("cno",carduser.getCNO());
					Result json = new Result("0","密码正确");
					return json;
				}else {
					Result json = new Result("-1","密码错误");
					return json;
				}
			}
			catch (NullPointerException e) {
				Result json = new Result("-1","账号或密码错误");
				return json;
			}
	}
	
	/**
	 * 查找全部
	 * 权限：管理员
	 * @param session
	 * @return
	 */
	@GetMapping(value="/admin/action/librarycard/")
	public @ResponseBody List<LIBRARY_CARD> Cardfindall(HttpSession session) {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		return librarycardRepository.findAll();
		
	}
	/**
	 * 读取卡的信息
	 * 权限：管理员
	 * @param session
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(value="/admin/action/librarycard/findpage/{page}/{size}")
	public @ResponseBody Page<vwCardAndType> Cardfindpage(HttpSession session,@PathVariable Integer page,@PathVariable Integer size) {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		Sort sort=new Sort(Direction.DESC,"CNO");//坑5,不报错，要记得排序字段
		Pageable pageable=new PageRequest(page,size,sort);
		return librarycardRepository.Vwfindall(pageable);
	}
	/**
	 * 找一个卡类型
	 * 权限：管理员
	 * 没用到
	 * @param session
	 * @param id
	 * @return
	 */
	@GetMapping(value="/admin/action/librarycard/findone/{id}")
	public @ResponseBody LIBRARY_CARD Cardfindone(HttpSession session,@PathVariable("id") Integer id) {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		return librarycardRepository.findOne(id);
		
	}
	/**
	 * 查找账号
	 * 权限：管理员
	 * @param session
	 * @param rolename
	 * @return
	 */
	@GetMapping(value="/admin/action/librarycard/search/{rolename}")
	public @ResponseBody List<LIBRARY_CARD> Cardsearch(HttpSession session,@PathVariable("rolename") String rolename) {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		return librarycardRepository.search(rolename);
		
	}
	/**
	 * 删除
	 * 权限：管理员
	 * @param session
	 * @param id
	 * @return
	 */
	@GetMapping(value="/admin/action/librarycard/del/{id}")
	public @ResponseBody Result DelCard(HttpSession session,@PathVariable("id") Integer id) {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		if(session.getAttribute("power")==null||!session.getAttribute("power").toString().equals("1"))
		{
			return null;
		}
		librarycardRepository.delete(id);
		return new Result("0","删除成功");
		
	}
	/**
	 * 新建类型
	 * 权限：超级管理员
	 * @param session
	 * @param cardtype
	 * @return
	 * @throws IOException 
	 */
	@PostMapping(value= "/admin/action/librarycard/add")
	public @ResponseBody String adminaction(HttpSession session,HttpServletRequest request,HttpServletResponse response,LIBRARY_CARD card) throws IOException {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		if(session.getAttribute("power")==null||!session.getAttribute("power").toString().equals("1"))
		{
			return "<a href='javascript:history.go(-1);'>没有权限</a>";
		}
		//if (card.getCNO()!=null&&cardtype.getTMAX()!=null&&cardtype.getTLONG()!=null)
		//{
			try {
				if (card.getCAN()!=null&&card.getCPSW()!=null&&card.getCNAME()!=null&&card.getCSEX()!=null&&card.getTNO()!=null&&card.getCSTATUS()!=null) {
					if(card.getCARREARS()!=null&&card.getCARREARS()<0)
					{
						return "<a href='javascript:history.go(-1);'>tips:怎么能欠费负数，送钱的意思么，点击返回</a>";
					}
					if(card.getCARREARS()==null)
					{
						card.setCARREARS(0);
					}
					librarycardRepository.save(card);
					response.sendRedirect(request.getContextPath()+"../../Card/index.html");
				}else {
					return "<a href='javascript:history.go(-1);'>tips:参数不为空</a>";
				}
			}catch (Exception e) {
				// TODO: handle exception
				return "<a href='javascript:history.go(-1);'>tips:账号"+card.getCAN()+"已经存在了，或者违反约束条件，刷新看看</a>";
			}
		//}
		return  "参数不全";
		
	}
}
