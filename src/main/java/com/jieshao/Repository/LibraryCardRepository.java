package com.jieshao.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jieshao.data.LIBRARY_CARD;
import com.jieshao.dataview.vwCardAndType;

public interface LibraryCardRepository extends JpaRepository<LIBRARY_CARD, Integer>{
	
	@Query("SELECT l FROM LIBRARY_CARD l where l.CAN=?1")
	public LIBRARY_CARD findByCAN(String CAN);
	@Query("SELECT v FROM vwCardAndType v")
	public Page<vwCardAndType> Vwfindall(Pageable pageable);
	@Query("SELECT v FROM vwCardAndType v where v.CAN LIKE %?1%")
	public List<LIBRARY_CARD> search(String key);
	@Query("SELECT l FROM LIBRARY_CARD l where l.CNO=?1")
	public LIBRARY_CARD findByCNO(Integer CNO);
}
