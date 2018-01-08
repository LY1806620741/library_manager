package com.jieshao.controller;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.jieshao.Repository.TypeOfLibraryCardRepository;
import com.jieshao.data.TYPE_OF_LIBRARY_CARD;
import com.jieshao.json.Result;

@RestController
public class CardTypeController {
	
	Logger logger = LoggerFactory.getLogger(CardTypeController.class);
	@Autowired
	private TypeOfLibraryCardRepository typeoflibrarycardrepository;

	/**
	 * 查找全部
	 * @param session
	 * @return
	 */
	@GetMapping(value="/admin/action/cardtype/findall")
	public List<TYPE_OF_LIBRARY_CARD> Cardfindall(HttpSession session) {
		if(session.getAttribute("user")==null)
		{
			//logger.info("/admin/action/cardtype post请求 用户未登录");
			return  null;
		}
		return typeoflibrarycardrepository.findAll();
		
	}
	@GetMapping(value="/admin/action/cardtype/findpage/{page}/{size}")
	public Page<TYPE_OF_LIBRARY_CARD> Cardfindpage(HttpSession session,@PathVariable Integer page,@PathVariable Integer size) {
		if(session.getAttribute("user")==null)
		{
			//logger.info("/admin/action/cardtype post请求 用户未登录");
			return  null;
		}
		Sort sort=new Sort(Direction.DESC,"TNO");//坑5,不报错，要记得排序字段
		Pageable pageable=new PageRequest(page,size,sort);
		return typeoflibrarycardrepository.findAll(pageable);
		
	}
	/**
	 * 找一个卡类型
	 * @param session
	 * @param id
	 * @return
	 */
	@GetMapping(value="/admin/action/cardtype/findone/{id}")
	public TYPE_OF_LIBRARY_CARD Cardfindone(HttpSession session,@PathVariable("id") Integer id) {
		if(session.getAttribute("user")==null)
		{
			//logger.info("/admin/action/cardtype post请求 用户未登录");
			return  null;
		}
		return typeoflibrarycardrepository.findOne(id);
		
	}
	@GetMapping(value="/admin/action/cardtype/del/{id}")
	public Result DelCard(HttpSession session,@PathVariable("id") Integer id) {
		if(session.getAttribute("user")==null)
		{
			//logger.info("/admin/action/cardtype post请求 用户未登录");
			return  null;
		}
		typeoflibrarycardrepository.delete(id);
		return new Result("0","删除成功");
		
	}
	/**
	 * 新建类型
	 * @param session
	 * @param cardtype
	 * @return
	 * @throws IOException 
	 */
	@PostMapping(value= "/admin/action/cardtype/add")
	public @ResponseBody String adminaction(HttpSession session,HttpServletRequest request,HttpServletResponse response,TYPE_OF_LIBRARY_CARD cardtype) throws IOException {
		if(session.getAttribute("user")==null)
		{
			//logger.info("/admin/action/cardtype post请求 用户未登录");
			return  null;
		}
		if(session.getAttribute("power")==null||"1".equals(session.getAttribute("power")))
		{
			return "没有权限";
		}
		if (cardtype.getTNAME()!=null&&cardtype.getTMAX()!=null&&cardtype.getTLONG()!=null)
		{
			try {
				typeoflibrarycardrepository.save(cardtype);
				response.sendRedirect(request.getContextPath()+"../../CardType/index.html");
			}catch (Exception e) {
				// TODO: handle exception
				return "<a href='javascript:history.go(-1);'>tips:类型的名字不能重复，点击这里返回</a>";
			}
			//logger.info("/admin/action/cardtype 保存了一个新类型");
		}
		return  "参数不全";
		
	}
	
	
}
