package com.jieshao.Repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jieshao.data.Books;
import com.jieshao.dataview.vwBookInfo;

public interface BooksRepository extends JpaRepository<Books, Integer> {
	@Query("SELECT b FROM vwBookInfo b where b.bname LIKE %?1% or b.bauthor LIKE %?1% or b.bpress LIKE %?1% or b.bisbn LIKE %?1%")
	public List<vwBookInfo> search(String key);
	@Query("SELECT v FROM vwBookInfo v")
	public Page<vwBookInfo> Vwfindall(Pageable pageable);

}
