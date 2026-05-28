package com.example.bai1.controller;

import com.example.bai1.model.ApiDataResponse;
import com.example.bai1.model.Book;
import com.example.bai1.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Book>>> findAll() {
        return new ResponseEntity<>(new ApiDataResponse<>(true, "success", bookService.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Book>> createBook(@RequestBody Book book) {
        return new ResponseEntity<>(new ApiDataResponse<>(true, "success", bookService.createBook(book), HttpStatus.OK), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Book>> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return new ResponseEntity<>(new ApiDataResponse<>(true, "success", bookService.updateBook(id, book), HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Book>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
