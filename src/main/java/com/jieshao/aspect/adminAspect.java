package com.jieshao.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *admin的拦截器
 * @author CrazyHobo俊杰
 *
 */
public class adminAspect implements HandlerInterceptor{
	Logger logger = LoggerFactory.getLogger(adminAspect.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//获取session
        HttpSession session = request.getSession(true);
        if(session.getAttribute("user") == null)
        {
        	response.sendRedirect(request.getContextPath()+"/admin/login");
        	logger.info("未登录，跳转到登陆页");
            return false;
      }else {
    	  logger.info("已登录");
          return true;
      }

  }

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
