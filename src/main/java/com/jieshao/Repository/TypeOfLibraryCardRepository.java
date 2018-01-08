package com.jieshao.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jieshao.data.TYPE_OF_LIBRARY_CARD;


public interface TypeOfLibraryCardRepository extends JpaRepository<TYPE_OF_LIBRARY_CARD,Integer>{
	@Query("SELECT v FROM TYPE_OF_LIBRARY_CARD v where v.TNAME LIKE %?1%")
	public List<TYPE_OF_LIBRARY_CARD> search(String key);
}
