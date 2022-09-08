package com.rank.lms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rank.lms.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
