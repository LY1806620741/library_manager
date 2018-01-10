package com.jieshao.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jieshao.data.Library;
import com.jieshao.dataview.vwRoomInfo;

public interface LibraryRepository extends JpaRepository<Library, Integer>{
	@Query("SELECT l FROM Library l where l.lname LIKE %?1% or l.lposition LIKE %?1%")
	public List<Library> search(String key);
	@Query("SELECT l FROM Library l where l.lname=?1 or l.lposition=?2")
	public Library check(String name,String lposition);
	@Query("SELECT v FROM vwRoomInfo v")
	public Page<vwRoomInfo> Vwfindall(Pageable pageable);

}
