package com.jieshao.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jieshao.data.Manage;


public interface ManageRepository extends JpaRepository<Manage, Integer>{
	
	public Manage findByMname(String name);
	@Query("SELECT m FROM Manage m where m.mname LIKE %?1%")
	public List<Manage> search(String key);
	@Transactional
	@Modifying(clearAutomatically = true)//更新实体对象
	@Query("Update Manage m set m.mname=:#{#manage.mname},m.mrealname=:#{#manage.mrealname},m.mpower=:#{#manage.mpower} where m.mno=:#{#manage.mno}")
	public void update(@Param("manage") Manage manage);
	@Transactional
	@Modifying(clearAutomatically = true)//更新实体对象
	@Query(value="Update Manage set logintime=SYSDATE where mno=?1", nativeQuery = true)//更新登陆时间 原生sql
	public void updatelogintime(Integer mno);
	
	
}
