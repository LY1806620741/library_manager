package com.jieshao.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.jieshao.data.Books;

public interface BooksRepository extends JpaRepository<Books, String> {

}
