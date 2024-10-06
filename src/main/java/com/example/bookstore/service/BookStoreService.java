package com.example.bookstore.service;

import com.example.bookstore.Entity.Book;
import com.example.bookstore.model.BookDTO;
import com.example.bookstore.model.SellDTO;
import com.example.bookstore.repository.BookStoreRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class BookStoreService {

    @Autowired
    private BookStoreRepository bookStoreRepository;

    public List<Book> getAllBooks(){
        return bookStoreRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookStoreRepository.findById(id);
    }

    public void addNewBookService(BookDTO [] book) {
        List<Book> bookList = new ArrayList<Book>();

        for(BookDTO books : book) {
            Book addBook = new Book();
            addBook.setAuthor(books.getAuthor());
            addBook.setTitle(books.getTitle());
            addBook.setCategory(books.getCategory());
            addBook.setPrice(books.getPrice());
            addBook.setTotalCount(books.getTotalCount());
            addBook.setSold(books.getSold());

            bookList.add(addBook);
        }

        bookStoreRepository.saveAll(bookList);
    }

    public void addBook(BookDTO bookDTO) {
        Optional<Book> existingBook = bookStoreRepository.findByTitle(bookDTO.getTitle());
        if (existingBook.isPresent()) {
            Book bookToUpdate = existingBook.get();
            bookToUpdate.setTotalCount(bookToUpdate.getTotalCount() + bookDTO.getTotalCount());
            bookStoreRepository.save(bookToUpdate);
        }
    }

    public void sellBookService(Long id){
        Optional<Book> existingBook = bookStoreRepository.findById(id);
        if(existingBook.isPresent()){
            Book bookToSell = existingBook.get();
            bookToSell.setSold(bookToSell.getSold() + 1);
            bookToSell.setTotalCount(bookToSell.getTotalCount() - 1);
            bookStoreRepository.save(bookToSell);
        }
    }

    public void sellBookByListService(SellDTO sellDTO){
        Long bookId = sellDTO.getBookId();
        Optional<Book> existingBook = bookStoreRepository.findById(bookId);
        if(existingBook.isPresent()){
            Book bookListToSell = existingBook.get();
            bookListToSell.setSold(bookListToSell.getSold() + sellDTO.getSellBookCount());
            bookListToSell.setTotalCount(bookListToSell.getTotalCount() - sellDTO.getSellBookCount());
            bookStoreRepository.save(bookListToSell);
        }
    }

    public List<Book> getBooksByKeywordAndCategory(String keyword, String category) {
        return bookStoreRepository.findByKeywordAndCategory(keyword, category);
    }

    public Integer getNumberOfBooksSoldByKeywordAndCategory(String keyword, String category) {
        return bookStoreRepository.getNumberOfBooksSoldByKeywordAndCategory(keyword, category);
    }

}
