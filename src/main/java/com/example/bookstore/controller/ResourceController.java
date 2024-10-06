package com.example.bookstore.controller;

import com.example.bookstore.Entity.Book;
import com.example.bookstore.model.BookDTO;
import com.example.bookstore.model.GenericResponse;
import com.example.bookstore.model.SellDTO;
import com.example.bookstore.service.BookStoreService;
import jakarta.persistence.Id;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class ResourceController {
    @Autowired
    private BookStoreService bookStoreService;

    @RequestMapping("/book-list")
    public GenericResponse getBookList(){
        GenericResponse response = new GenericResponse();
        if (bookStoreService.getAllBooks() == null){
            response.setStatus("Error");
            response.setErrorMessage("Books not found");
        } else {
            response.setStatus("Success");
            response.setData(bookStoreService.getAllBooks());
        }
        return response;
    }

    @RequestMapping("book/{id}")
    public GenericResponse getBookById(@PathVariable Long id) {
        GenericResponse response = new GenericResponse();
        if(bookStoreService.getBookById(id).isEmpty()){
            response.setStatus("Error");
            response.setErrorMessage("Book with this ID was not found");
        } else {
            response.setStatus("Success");
            response.setData(bookStoreService.getBookById(id));
        }
        return response;
    }

    @RequestMapping("/number-of-books/{id}")
    public GenericResponse getNumberedBookById(@PathVariable Long id) {
        Optional<Book> bookDTO;
        bookDTO = bookStoreService.getBookById(id);
        int numberOfBooks = bookDTO.map(Book::getTotalCount).orElse(0);
        GenericResponse response = new GenericResponse();
        if(numberOfBooks <= 0){
            response.setStatus("Error");
            response.setErrorMessage("Book count was not found");
        } else {
            response.setData(numberOfBooks);
            response.setStatus("Success");
        }
        return response;
    }

    @RequestMapping("/add-new-book")
    public void addNewBook(@RequestBody BookDTO [] book){
        bookStoreService.addNewBookService(book);
    }

    @RequestMapping("/add-book")
    public void addBook(@RequestBody BookDTO bookDTO ){
        bookStoreService.addBook(bookDTO);
    }

    @RequestMapping("sell-book/{id}")
    public void sellBookById(@PathVariable Long id) {
        bookStoreService.sellBookService(id);
    }

    @RequestMapping("sell-books")
    public void sellBookByList(@RequestBody SellDTO sellDTO){
        bookStoreService.sellBookByListService(sellDTO);
    }

    @RequestMapping("books")
    public List<Book> getBookByCategory(@RequestParam(required = false) String keyword, @RequestParam(required = false) String category){
        return bookStoreService.getBooksByKeywordAndCategory(keyword,category);
    }

    @RequestMapping("number-of-books")
    public int getBooksSoldByKeywordAndCategory(@RequestParam(required = false) String keyword, @RequestParam(required = false) String category){
        return bookStoreService.getNumberOfBooksSoldByKeywordAndCategory(keyword,category);
    }




}
