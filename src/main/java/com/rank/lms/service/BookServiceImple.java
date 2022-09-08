package com.rank.lms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rank.lms.DTO.BookDTO;
import com.rank.lms.Repository.BookRepository;
import com.rank.lms.Repository.StudentRepository;
import com.rank.lms.entity.Book;
import com.rank.lms.entity.Student;
import com.rank.lms.exception.ErrorCodeHelper;
import com.rank.lms.exception.ErrorConstant;
import com.rank.lms.exception.ErrorInfo;
import com.rank.lms.exception.ServiceException;

@Service
public class BookServiceImple implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ErrorCodeHelper errorCodeHelper;

	// add book
	@Override
	public BookDTO addBook(BookDTO bookDTO) {
		Book book = dtoToEntity(bookDTO);
		Book savedBook = bookRepository.save(book);
		return entityToDTO(savedBook);
	}

	// get book list
	@Override
	public List<BookDTO> getBooks() {
		List<BookDTO> bookDTOs = bookRepository.findAll().stream().map(book -> entityToDTO(book))
				.collect(Collectors.toList());
		return bookDTOs;
	}

	// get book by bookId
	@Override
	public BookDTO getBookById(Long bookId) {
		Optional<Book> bookById = bookRepository.findById(bookId);
		if (bookById.isEmpty()) {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(ErrorConstant.E1011_ERROR_CODE, ErrorConstant.E1011_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo, HttpStatus.NOT_FOUND);
		} else {
			return entityToDTO(bookById.get());
		}

	}

	@Override
	public BookDTO updateBook(BookDTO bookDTO, Long bookId) {
		Optional<Book> bookById = bookRepository.findById(bookId);
		if (bookById.isEmpty()) {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(ErrorConstant.E1011_ERROR_CODE, ErrorConstant.E1011_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo, HttpStatus.NOT_FOUND);
		}
		Book book = bookById.get();
		book.setCategory(bookDTO.getCategory());
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setIsbn(bookDTO.getIsbn());
		book.setPublisher(bookDTO.getPublisher());
		Book updatedbook = bookRepository.save(book);
		return entityToDTO(updatedbook);
	}

	@Override
	public void deleteBook(Long bookId) {
		Optional<Book> bookById = bookRepository.findById(bookId);
		if(bookById.isPresent()) {
			bookRepository.deleteById(bookId);
		}
		else {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(ErrorConstant.E1011_ERROR_CODE,
					ErrorConstant.E1011_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo, HttpStatus.NOT_FOUND);
		}

	}
	
	// issue book @Override
	public Book issueBook(Book book ,Long bookId, Long studentId) {
		Optional<Book> bookById = bookRepository.findById(bookId);
		if (bookById.isEmpty()|| book.isAvailable() == false) {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(ErrorConstant.E1011_ERROR_CODE, ErrorConstant.E1011_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo, HttpStatus.NOT_FOUND);
		} 
		Optional<Student> studentfk = studentRepository.findById(studentId);
		book.setAvailable(false);
		book.setStudent(studentfk.get());
		bookRepository.save(book);
		return book;
	}
	

	// convert dto to entity
	public Book dtoToEntity(BookDTO bookDTO) {
		Book book = modelMapper.map(bookDTO, Book.class);
		return book;
	}

	// convert entity to dto
	public BookDTO entityToDTO(Book book) {
		BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
		return bookDTO;
	}

}
