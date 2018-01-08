package com.jieshao.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jieshao.data.LIBRARY_CARD;
import com.jieshao.dataview.vm_CardAndType;

public interface LibraryCardRepository extends JpaRepository<LIBRARY_CARD, Integer>{
	//@Query("select t from vm_CardAndType t")
	//public Page<vm_CardAndType> Vmfindall(Pageable pageable);
}
