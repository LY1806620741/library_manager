package com.jieshao.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jieshao.Repository.LibraryRepository;
import com.jieshao.data.Library;
import com.jieshao.dataview.vwRoomInfo;
import com.jieshao.json.Result;

@RestController
public class RoomController {
	Logger logger = LoggerFactory.getLogger(RoomController.class);
	@Autowired
	private LibraryRepository libraryrepository;

	/**
	 * 查找全部图书室
	 * 权限：管理员
	 * @param session
	 * @return
	 */
	@GetMapping(value="/admin/action/room/findall")
	public List<Library> Cardfindall(HttpSession session) {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		return libraryrepository.findAll();
		
	}
	/**
	 * 查找房间名
	 * 权限：管理员
	 * @param session
	 * @param rolename
	 * @return
	 */
	@GetMapping(value="/admin/action/room/search/{rolename}")
	public List<Library> roomsearch(HttpSession session,@PathVariable("rolename") String rolename) {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		return libraryrepository.search(rolename);
		
	}
	/**
	 * 查看一页页的数据
	 * 权限：管理员
	 * @param session
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(value="/admin/action/room/findpage/{page}/{size}")
	public Page<vwRoomInfo> Cardfindpage(HttpSession session,@PathVariable Integer page,@PathVariable Integer size) {
		if(session.getAttribute("user")==null)
		{
			//logger.info("/admin/action/room post请求 用户未登录");
			return  null;
		}
		Sort sort=new Sort(Direction.DESC,"lno");//坑5,不报错，要记得排序字段
		Pageable pageable=new PageRequest(page,size,sort);
		return libraryrepository.Vwfindall(pageable);
	}
	/**
	 * 找一个图书室id
	 * 权限：管理员
	 * @param session
	 * @param id
	 * @return
	 */
	@GetMapping(value="/admin/action/room/findone/{id}")
	public Library Cardfindone(HttpSession session,@PathVariable("id") Integer id) {
		if(session.getAttribute("user")==null)
		{
			//logger.info("/admin/action/room post请求 用户未登录");
			return  null;
		}
		return libraryrepository.findOne(id);
		
	}
	/**
	 * 删除一个图书室
	 * 权限：超级管理员
	 * @param session
	 * @param id
	 * @return
	 */
	@GetMapping(value="/admin/action/room/del/{id}")
	public Result DelCard(HttpSession session,@PathVariable("id") Integer id) {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		if(session.getAttribute("power")==null||!session.getAttribute("power").toString().equals("1"))
		{
			return null;
		}
		libraryrepository.delete(id);
		return new Result("0","删除成功");
		
	}
	/**
	 * 新建类型
	 * 权限：超级管理员
	 * @param session
	 * @param room
	 * @return
	 * @throws IOException 
	 */
	@PostMapping(value= "/admin/action/room/add")
	public @ResponseBody String adminaction(HttpSession session,HttpServletRequest request,HttpServletResponse response,Library room) throws IOException {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		if(session.getAttribute("power")==null||!session.getAttribute("power").toString().equals("1"))
		{
			return "<a href='javascript:history.go(-1);'>没有权限</a>";
		}
		if (room.getLname()!=null&&room.getLposition()!=null)
		{
			try {
				libraryrepository.save(room);
				response.sendRedirect(request.getContextPath()+"../../room/index.html");
			}catch (Exception e) {
				return "<a href='javascript:history.go(-1);'>tips:位置不能重复，点击这里返回</a>";
			}
		}
		return  "参数不全";
		
	}
}
