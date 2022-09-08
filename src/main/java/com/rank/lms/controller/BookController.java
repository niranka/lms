package com.rank.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rank.lms.DTO.BookDTO;
import com.rank.lms.DTO.ResponseJson;
import com.rank.lms.Utils.CommonConstant;
import com.rank.lms.Utils.MappingConstant;
import com.rank.lms.entity.Book;
import com.rank.lms.service.BookService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(MappingConstant.LMS_API)
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private Environment enviornmnet;

	// save book
	@ApiOperation(value = "save book", tags = "Book Information")
	@PostMapping(MappingConstant.ADD_BOOK)
	public ResponseEntity<ResponseJson> addBook(@RequestBody BookDTO bookDTO) {
		ResponseJson responseJson = new ResponseJson(enviornmnet);
		responseJson.setResponse(bookService.addBook(bookDTO));
		responseJson.setResponseDescription(CommonConstant.S0002_SUCCESS_DESCRIPTION);
		return ResponseEntity.ok(responseJson);
	}
	
	// get book list
	@ApiOperation(value = "get all books list", tags = "Book Information")
	@GetMapping(MappingConstant.BOOK_LIST)
	public ResponseEntity<ResponseJson> getBooksList() {
		ResponseJson responseJson = new ResponseJson(enviornmnet);
		responseJson.setResponse(bookService.getBooks());
		responseJson.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		return ResponseEntity.ok(responseJson);
	}
	
	// get book by bookId
	@ApiOperation(value = "get book by bookId", tags = "Book Information")
	@GetMapping(MappingConstant.BOOK_BY_ID)
	public ResponseEntity<ResponseJson> getBookById(@PathVariable("bId") Long bookId) {
		ResponseJson responseJson = new ResponseJson(enviornmnet);
		responseJson.setResponse(bookService.getBookById(bookId));
		responseJson.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		return ResponseEntity.ok(responseJson);
	}
	
	// update book information
		@ApiOperation(value = "update book information by bookId", tags = "Book Information")
		@PutMapping(MappingConstant.UPDATE_BOOK)
		public ResponseEntity<ResponseJson> updateBook(@RequestBody BookDTO bookDTO,
				@PathVariable("bId") Long bookId) {
			ResponseJson responseJson = new ResponseJson(enviornmnet);
			responseJson.setResponse(bookService.updateBook(bookDTO, bookId));
			responseJson.setResponseDescription(CommonConstant.S0003_SUCCESS_DESCRIPTION);
			return ResponseEntity.ok(responseJson);
		}
		
		// delete book by id
		@ApiOperation(value = "delete book by bookId", tags = "Book Information")
		@DeleteMapping(MappingConstant.DELETE_BOOK)
		public ResponseEntity<ResponseJson> deleteBook(@PathVariable("bId") Long bookId){
			ResponseJson responseJson = new ResponseJson(enviornmnet);
			bookService.deleteBook(bookId);
			responseJson.setResponseDescription(CommonConstant.S0004_SUCCESS_DESCRIPTION);
			return ResponseEntity.ok(responseJson);
		}
		
		// issue book 
		@ApiOperation(value = "issue book by bookId", tags = "Book Issue Information")
		@PutMapping(MappingConstant.ISSUE_BOOK)
		public ResponseEntity<ResponseJson> issueById(@RequestBody Book book ,@PathVariable("bId") Long bookId,@PathVariable("sId") Long studentId) {
			ResponseJson responseJson = new ResponseJson(enviornmnet);
			responseJson.setResponse(bookService.issueBook(book,bookId, studentId));
			responseJson.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
			return ResponseEntity.ok(responseJson);
		}
	
}
