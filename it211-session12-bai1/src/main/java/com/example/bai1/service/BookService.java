package com.example.bai1.service;

import com.example.bai1.model.Book;
import com.example.bai1.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book findByID(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book not found"));
    }

    public Book updateBook(Long id, Book book) {
        Book existingBook = findByID(id);
        existingBook.setName(book.getName());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPrice(book.getPrice());
        return bookRepository.save(book);
    }

    public Book deleteBook(Long id) {
        Book book = findByID(id);
        bookRepository.delete(book);
        return book;
    }
}
