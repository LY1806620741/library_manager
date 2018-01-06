package com.jieshao.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jieshao.data.Manage;

import antlr.collections.List;

public interface ManageRepository extends JpaRepository<Manage, Integer>{
	
	public Manage findByMname(String name);
}
