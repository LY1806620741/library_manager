package com.jieshao;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.jieshao.data","com.jieshao.controller","com.jieshao.Repository"})//指定基础包
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})//阻断数据库
public class LibraryManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagerApplication.class, args);
	}
}
