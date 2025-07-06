package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;
    private Book book;

    // Constructor injection
    public BookService(Book book) {
        this.book = book;
    }

    // Setter injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void processLibrary() {
        System.out.println("BookService: Processing library...");
        book.showBookInfo();
        bookRepository.fetchBooks();
    }
}
