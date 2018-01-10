package com.jieshao.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jieshao.data.Borrow;
import com.jieshao.dataview.vwCardAndType;

public interface BorrowRepository extends JpaRepository<Borrow, Integer>{
	
	@Query("select b from Borrow b where b.cno=?1")
	public List<Borrow> searchonehistory(Integer cno);
	@Query("select l from vwCardAndType l where l.CNO=?1")
	public vwCardAndType searchOneDate(Integer cno);
	@Query("select l from Borrow l where l.cno=?1 and l.returntime is null")
	public List<Borrow> searchUnreturn(Integer cno);
}