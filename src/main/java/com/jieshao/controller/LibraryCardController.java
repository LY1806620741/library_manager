package com.jieshao.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.jieshao.Repository.LibraryCardRepository;
import com.jieshao.data.LIBRARY_CARD;
import com.jieshao.data.TYPE_OF_LIBRARY_CARD;
import com.jieshao.dataview.vwCardAndType;
import com.jieshao.json.Result;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class LibraryCardController {

	@Autowired
	private LibraryCardRepository librarycardRepository;
	
	/**
	 * 用户登陆界面（调用模板）
	 * 权限：所有人
	 * @return
	 */
	@GetMapping(value="/login")
	public String CardloginView() {
		return "login";
	}
	/**
	 * 废弃
	 * @param id
	 * @param Cname
	 * @return
	 */
	@PostMapping(value="/login")
	public String Cardlogin(@RequestParam("username") Integer id,
			@RequestParam("password") String Cname) {
		
		return "";
	}
	
	/**
	 * 查找全部
	 * 权限：管理员
	 * @param session
	 * @return
	 */
	@GetMapping(value="/admin/action/librarycard/")
	public List<LIBRARY_CARD> Cardfindall(HttpSession session) {
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
	public Page<vwCardAndType> Cardfindpage(HttpSession session,@PathVariable Integer page,@PathVariable Integer size) {
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
	public LIBRARY_CARD Cardfindone(HttpSession session,@PathVariable("id") Integer id) {
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
	public List<LIBRARY_CARD> Cardsearch(HttpSession session,@PathVariable("rolename") String rolename) {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		return librarycardRepository.search(rolename);
		
	}
	/**
	 * 删除
	 * 权限：超级管理员
	 * @param session
	 * @param id
	 * @return
	 */
	@GetMapping(value="/admin/action/librarycard/del/{id}")
	public Result DelCard(HttpSession session,@PathVariable("id") Integer id) {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		if(session.getAttribute("power")==null||"1".equals(session.getAttribute("power")))
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
		if(session.getAttribute("power")==null||"1".equals(session.getAttribute("power")))
		{
			return "没有权限";
		}
		//if (card.getCNO()!=null&&cardtype.getTMAX()!=null&&cardtype.getTLONG()!=null)
		//{
			try {
				librarycardRepository.save(card);
				response.sendRedirect(request.getContextPath()+"../../Card/index.html");
			}catch (Exception e) {
				// TODO: handle exception
				return "<a href='javascript:history.go(-1);'>tips:账号"+card.getCAN()+"已经存在了</a>";
			}
		//}
		return  "参数不全";
		
	}
}
