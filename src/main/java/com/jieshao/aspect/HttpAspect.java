package com.jieshao.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HttpAspect {

	@Before("execution(public * com.jieshao.controller.ManageControler.*(..))")
	public void log() {
		//System.out.println(11111);
	}

}
