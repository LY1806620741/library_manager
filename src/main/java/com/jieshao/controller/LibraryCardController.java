package com.jieshao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jieshao.Repository.LibraryCardRepository;
import com.jieshao.data.LIBRARY_CARD;

import java.util.List;

public class LibraryCardController {

	@Autowired
	private LibraryCardRepository librarycardRepository;
	
	/**
	 * 用户登陆
	 * @return
	 */
	@GetMapping(value="/login")
	public String CardloginView() {
		return "login";
	}
	
	@PostMapping(value="/login")
	public String Cardlogin(@RequestParam("username") Integer id,
			@RequestParam("password") String Cname) {
		
		return "";
	}
	
	@GetMapping(value="/LibraryCard")
	public List<LIBRARY_CARD> librarycardList(){
		return librarycardRepository.findAll();
	}
}
