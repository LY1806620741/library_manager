package com.jieshao.aspect;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory; 
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class HttpAspect {
	Logger logger = LoggerFactory.getLogger(HttpAspect.class);

	@Before("execution(public * com.jieshao.controller.ManageControler.*(..))")
	public void log() {
		
		//System.out.println(11111);
	}

}
