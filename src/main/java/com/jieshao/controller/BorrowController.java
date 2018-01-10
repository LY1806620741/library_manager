package com.jieshao.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jieshao.Repository.BooksRepository;
import com.jieshao.Repository.BorrowRepository;
import com.jieshao.Repository.LibraryCardRepository;
import com.jieshao.Repository.TypeOfLibraryCardRepository;
import com.jieshao.data.Books;
import com.jieshao.data.Borrow;
import com.jieshao.data.LIBRARY_CARD;
import com.jieshao.data.TYPE_OF_LIBRARY_CARD;
import com.jieshao.json.AdminHistory;
import com.jieshao.json.Cardinfo;
import com.jieshao.json.History;
import com.jieshao.json.Result;


@RestController
public class BorrowController {
	
	Logger logger = LoggerFactory.getLogger(BorrowController.class);
	@Autowired
	private BorrowRepository borrowrepository;
	@Autowired//每一个都要加，神坑查了半天，眼都花
	private BooksRepository booksrepository;
	@Autowired
	private TypeOfLibraryCardRepository typeoflibrarycardrepository;
	@Autowired
	private LibraryCardRepository librarycardrepository;
	
	/**
	 * 查询有没有权限
	 * 权限：借书卡
	 * @param session
	 * @return
	 */
	@GetMapping(value="/action/borrow/havepower")
	public Result havepower(HttpSession session) {
		if(session.getAttribute("card")==null)
		{
			return  new Result("-1","没有权限");
		}
		return new Result("0",session.getAttribute("card").toString());
	}
	/**
	 * 查找全部借阅记录
	 * 权限：管理员
	 * @param session
	 * @return
	 */
	@GetMapping(value="/admin/action/borrow/findall")
	public List<AdminHistory> Cardfindall(HttpSession session) {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		List<Borrow> borrow = borrowrepository.findAll();
		List<AdminHistory> adminhistory = new ArrayList<AdminHistory>();
		for (Borrow key : borrow) {
			adminhistory.add(new AdminHistory(key, booksrepository.findOne(key.getBno()),librarycardrepository.findOne(key.getCno())));
		}
		return adminhistory;
	}
	/**
	 * 查找一个人的历史记录
	 * @param session
	 * @return
	 */
	@GetMapping(value="/action/borrow/history")
	public List<History> searchonehistory(HttpSession session) {
		if(session.getAttribute("cno")==null)
		{
			return  null;
		}
		List<Borrow> borrow=borrowrepository.searchonehistory((Integer)session.getAttribute("cno"));
		List<History> history = new ArrayList<History>();
		for (Borrow key : borrow) {
			history.add(new History(key, booksrepository.findOne(key.getBno())));
		}
		return history;
	}
	/**
	 * 查找一个人的未还历史
	 * @param session
	 * @return
	 */
	@GetMapping(value="/action/borrow/unreturn")
	public List<Borrow> searchUnreturn(HttpSession session) {
		if(session.getAttribute("cno")==null)
		{
			return  null;
		}
		return borrowrepository.searchUnreturn((Integer)session.getAttribute("cno"));
	}
	/**
	 * 查找一个人的信息
	 * @param session
	 * @return
	 */
	@GetMapping(value="/action/borrow/info/")
	public Cardinfo searchoneinfo(HttpSession session) {
		if(session.getAttribute("cno")==null)
		{
			return  null;
		}
		LIBRARY_CARD library_card=librarycardrepository.findOne((Integer)session.getAttribute("cno"));//查询用户信息
		TYPE_OF_LIBRARY_CARD type_of_library_card = typeoflibrarycardrepository.findOne(library_card.getTNO());//查询类型
		Cardinfo cardinfo = new Cardinfo(library_card,type_of_library_card);
		cardinfo.setBNOW(borrowrepository.searchUnreturn((Integer)session.getAttribute("cno")).size());//已借数
		//查询未还书数
		return cardinfo;
	}
	/**
	 * 借书
	 * 权限：借书卡
	 * @param session
	 * @param id
	 * @return
	 */
	@GetMapping(value="/action/borrow/borrow/{id}")
	@Transactional//事务
	public Result Borrowone(HttpSession session,@PathVariable("id") Integer id) {
		if(session.getAttribute("cno")==null)
		{
			return  new Result("-1","游客不能借阅哦，是会员就赶快登陆吧");
		}
		LIBRARY_CARD library_card=librarycardrepository.findOne((Integer)session.getAttribute("cno"));//查询用户信息
		Cardinfo cardinfo=searchoneinfo(session);//查询信息
//		if (cardinfo.getCARREARS()>=cardinfo.getTMAXARREARS()) {//如果欠费超了
//			library_card.setCSTATUS(0);//停机
//			librarycardrepository.save(library_card);
//		}
		if (cardinfo.getBNOW()>=cardinfo.getTMAX()) {//借书到达类型极限
			return new Result("-1","你已经借了你的借书证类型能借的最大数目的书了");
		}
		if (library_card.getCSTATUS()!=1) {//禁用
			 return new Result("-1","你的帐号已被禁用，请及时缴费或者联系管理员");
			}
		TYPE_OF_LIBRARY_CARD type_of_library_card = typeoflibrarycardrepository.findOne(library_card.getTNO());//查询类型
		if (library_card.getCARREARS()>=type_of_library_card.getTMAXARREARS())//已欠费
		{
			return new Result("-1","您已欠费停借书卡，请及时充值和归还到期图书");//借书
		}
		Borrow borrow =new Borrow();
		borrow.setCno((Integer)session.getAttribute("cno"));
		borrow.setBno(id);
		borrowrepository.save(borrow);
		Books books=booksrepository.findOne(id);
		books.setBinventory(books.getBinventory()-1);//库存-1
		booksrepository.save(books);
		return new Result("0","借书成功");//借书
	}
	/**
	 * 还书
	 * 权限：借书卡
	 * @param session
	 * @param id
	 * @return
	 */
	@GetMapping(value="/action/borrow/return/{id}")
	@Transactional//事务
	public Result Retrunone(HttpSession session,@PathVariable("id") Integer id) {
		if(session.getAttribute("cno")==null)
		{
			return  null;
		}
//		LIBRARY_CARD library_card=librarycardrepository.findOne((Integer)session.getAttribute("cno"));//查询用户信息
//		if (library_card.getCSTATUS()!=1) {//禁用 //禁用也能还
//			 return new Result("-1","你的帐号已被禁用，请及时缴费或者联系管理员");
//			}
		//查询记录
		try {
		Borrow borrows =borrowrepository.findOne(id);
		//if (borrows.getCno().equals(session.getAttribute("cno").toString())){//如果是这个人的
			Date now=new Date();//获取时间
			Cardinfo cardinfo=searchoneinfo(session);
			List<LIBRARY_CARD> card=librarycardrepository.search(session.getAttribute("cno").toString());
			int days=differentDaysByMillisecond(borrows.getBegintime(),now);//求相差的天数
			if(days>cardinfo.getTLONG()) {//超时
				int fine=days-cardinfo.getTLONG();//罚款数
				card.get(0).setCARREARS(card.get(0).getCARREARS()+fine);//设置罚款
				librarycardrepository.save(card.get(0));//完成罚款
				borrows.setBARREARS(fine);//记录罚金
				borrows.setReturntime(now);//还书时间
				borrowrepository.save(borrows);//保存修改
			}else {//没超时
				borrows.setReturntime(now);//还书时间
				borrowrepository.save(borrows);//保存修改
			}
//			borrows.setReturntime();
		//}
//		booksrepository.save(borrowrepository.findOne(id).setReturntime(new Date()));//
		Books books=booksrepository.findOne(borrows.getBno());
		logger.info("ok");
		int i=(books.getBinventory()+1);
		System.out.println(i);
		books.setBinventory(books.getBinventory()+1);//库存+1
		booksrepository.save(books);
		return new Result("0","还书成功");//还书
		}catch (Exception e) {
			// TODO: handle exception
			return new Result("-1","还书失败");//还书
		}
	}
	/**
	 * 球两个date之间的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public int differentDaysByMillisecond(Date date1,Date date2) {
		int days = (int) (date2.getTime() -date1.getTime()) / (1000*3600*24);
		return days;
	}
	/**
	 * 查看一页页的数据
	 * 权限：管理员
	 * @param session
	 * @param page
	 * @param size
	 * @return
	 */
	/**@GetMapping(value="/admin/action/borrow/findpage/{page}/{size}")
	public Page<vwBookInfo> Cardfindpage(HttpSession session,@PathVariable Integer page,@PathVariable Integer size) {
		if(session.getAttribute("user")==null)
		{
			return  null;
		}
		Sort sort=new Sort(Direction.DESC,"lno");//坑5,不报错，要记得排序字段
		Pageable pageable=new PageRequest(page,size,sort);
		return borrowrepository.Vwfindall(pageable);
	}*/
	
}
