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

import com.jieshao.Repository.BooksRepository;
import com.jieshao.data.Books;
import com.jieshao.dataview.vwBookInfo;
import com.jieshao.json.Result;

@RestController
public class BookController {

		Logger logger = LoggerFactory.getLogger(BookController.class);
		@Autowired
		private BooksRepository booksrepository;

		/**
		 * 查找全部书籍
		 * 权限：管理员
		 * @param session
		 * @return
		 */
		@GetMapping(value="/admin/action/book/findall")
		public List<Books> Cardfindall(HttpSession session) {
			if(session.getAttribute("user")==null)
			{
				return  null;
			}
			return booksrepository.findAll();
			
		}
		/**
		 * 查找书籍名
		 * 权限：所有人
		 * @param session
		 * @param rolename
		 * @return
		 */
		@GetMapping(value="/admin/action/book/search/{rolename}")
		public List<vwBookInfo> booksearch(HttpSession session,@PathVariable("rolename") String rolename) {
			/**if(session.getAttribute("user")==null)
			{
				return  null;
			}*/
			return booksrepository.search(rolename);
			
		}
		/**
		 * 查看一页页的数据
		 * 权限：所有人
		 * @param session
		 * @param page
		 * @param size
		 * @return
		 */
		@GetMapping(value="/admin/action/book/findpage/{page}/{size}")
		public Page<vwBookInfo> Cardfindpage(HttpSession session,@PathVariable Integer page,@PathVariable Integer size) {
			/*if(session.getAttribute("user")==null)
			{
				//logger.info("/admin/action/book post请求 用户未登录");
				return  null;
			}*/
			Sort sort=new Sort(Direction.DESC,"lno");//坑5,不报错，要记得排序字段
			Pageable pageable=new PageRequest(page,size,sort);
			return booksrepository.Vwfindall(pageable);
		}
		/**
		 * 找一个图书id
		 * 权限：管理员
		 * @param session
		 * @param id
		 * @return
		 */
		@GetMapping(value="/admin/action/book/findone/{id}")
		public Books Cardfindone(HttpSession session,@PathVariable("id") Integer id) {
			if(session.getAttribute("user")==null)
			{
				//logger.info("/admin/action/book post请求 用户未登录");
				return  null;
			}
			return booksrepository.findOne(id);
			
		}
		/**
		 * 删除一个图书室
		 * 权限：超级管理员
		 * @param session
		 * @param id
		 * @return
		 */
		@GetMapping(value="/admin/action/book/del/{id}")
		public Result DelCard(HttpSession session,@PathVariable("id") Integer id) {
			if(session.getAttribute("user")==null)
			{
				return  null;
			}
			if(session.getAttribute("power")==null||!session.getAttribute("power").toString().equals("1"))
			{
				return null;
			}
			booksrepository.delete(id);
			return new Result("0","删除成功");
			
		}
		/**
		 * 新建类型
		 * 权限：超级管理员
		 * @param session
		 * @param book
		 * @return
		 * @throws IOException 
		 */
		@PostMapping(value= "/admin/action/book/add")
		public @ResponseBody String adminaction(HttpSession session,HttpServletRequest request,HttpServletResponse response,Books book) throws IOException {
			if(session.getAttribute("user")==null)
			{
				return  null;
			}
			if(session.getAttribute("power")==null||!session.getAttribute("power").toString().equals("1"))
			{
				return "<a href='javascript:history.go(-1);'>没有权限</a>";
			}
			if (book.getBprice()!=null&&book.getBpress()!=null&&book.getBname()!=null&&book.getBisbn()!=null&&book.getBinventory()!=null&&book.getBcopy()!=null&&book.getBauthor()!=null)
			{
				try {
					booksrepository.save(book);
					response.sendRedirect(request.getContextPath()+"../../book/index.html");
				}catch (Exception e) {
					return "<a href='javascript:history.go(-1);'>tips:保存出了问题，点击这里返回</a>"+e;
				}
			}
			return  "参数不全";
			
		}
}
