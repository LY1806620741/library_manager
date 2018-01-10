package com.jieshao.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.jieshao.controller.ManageControler;

/**
 * 对/admin路径的简单测试
 * @author CrazyHobo俊杰
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional//事务回滚
public class ManageControlerTest {

	@Autowired
	private MockMvc mvc;
	private ManageControler managecontroler;
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	/**
	 * 未登录admin访问
	 * 结果跳转到/admin/login
	 * @throws Exception
	 */
	@Test
	public void adminViewNoLogin() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/admin")).andExpect(MockMvcResultMatchers.redirectedUrl("/admin/login"));
	}
	/**
	 * 未登录login访问
	 * admin登陆
	 * @throws Exception
	 */
	@Test
	@Rollback(false)
	public void adminloginView() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/admin/login")).andExpect(MockMvcResultMatchers.status().isOk());//能访问
		mvc.perform((post("/admin")).param("mname", "admin").param("mpsw", "123456").accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk());
		//mvc.perform(get("/admin/action/cardtype/findpage/0/10")).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(content().json("{'first':'true'}"));
	}
	/**
	 * 身份admin
	 * 获取图书卡类型页面
	 * @throws Exception
	 */
	@Test
	public void findpageByCardType() throws Exception{
		mvc.perform(get("/admin/action/cardtype/findpage/0/10")).andExpect(status().isOk());
	}
	/**
	 * 身份admin
	 * 更新一个用户的信息
	 * @throws Exception
	 */
	@Test
	@Rollback(true)
	public void changeManageView() throws Exception{
		mvc.perform((post("/admin/action/manage/add")).param("mno", "142").param("mname", "1").param("mrealname", "1333").param("mpower", "0").accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk());
	}
	
	private HttpSession getLoginSession() throws Exception{ 
        MvcResult result = mvc 
                                .perform((get("/admin")))  
                                .andExpect(status().isOk())  
                                .andReturn();  
        return result.getRequest().getSession();  
    }  
}
