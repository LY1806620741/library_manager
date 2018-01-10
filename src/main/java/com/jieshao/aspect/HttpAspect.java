package com.jieshao.aspect;


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
