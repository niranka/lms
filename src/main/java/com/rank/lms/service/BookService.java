package com.rank.lms.service;

import java.util.List;

import com.rank.lms.DTO.BookDTO;
import com.rank.lms.entity.Book;

public interface BookService {
	/**
	 * 
	 * @param bookDTO
	 * @return
	 */
	public BookDTO addBook(BookDTO bookDTO);
	/**
	 * 
	 * @return
	 */
	public List<BookDTO> getBooks();
	/**
	 * 
	 * @param Id
	 * @return
	 */
	public BookDTO getBookById(Long bookId);
	/**
	 * 
	 * @param bookDTO
	 * @param bookId
	 * @return
	 */
	public BookDTO updateBook(BookDTO bookDTO, Long bookId);
	/**
	 * 
	 * @param bookId
	 */
	public void deleteBook(Long bookId);
	/**
	 * 
	 * @param bookId
	 * @return
	 */
	public Book issueBook(Book book ,Long bookId, Long studentId); 

}
