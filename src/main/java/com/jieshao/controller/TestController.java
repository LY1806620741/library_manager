package com.jieshao.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jieshao.Repository.TestRepository;
import com.jieshao.data.Test;

@RestController
public class TestController {
	
	@Autowired
	private TestRepository testrepository;
	
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String testString() {
		return "这是个测试，成功了";
	}
	
	
	/**
	 * 添加一个test
	 * @param id
	 * @param Cname
	 * @return
	 */
	@PostMapping(value="/test")
	public Test testAdd(@RequestParam("id") Integer id,
			@RequestParam("Cname") String Cname) {
		Test test = new Test();
		test.setId(id);
		test.setCNAME(Cname);
		return testrepository.save(test);
	}
	
	/**
	 * 查询一个test
	 */
	@GetMapping(value="/test/{id}")
	public Test testfindone(@PathVariable("id") Integer id) {
		return testrepository.findOne(id);
	}
	/**
	 * 查询所有的行
	 * @return
	 */
	@GetMapping(value="/test/all")
	public List<Test> testList() {
		return testrepository.findAll();
	}
	
	/**
	 * 更新指定行
	 * @param id
	 * @param Cname
	 */
	@PutMapping(value = "/test/{id}")
	public void testUpdate(@PathVariable("id") Integer id,
			@RequestParam("Cname") String Cname) {
		Test test = new Test();
		test.setId(id);
		test.setCNAME(Cname);
		testrepository.save(test);
	}
	
	@DeleteMapping(value = "/test/{id}")
	public void testDelete(@PathVariable("id") Integer id) {
		testrepository.delete(id);
	}

}
