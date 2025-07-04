package com.pageport.pageport.controller;

import com.pageport.pageport.dto.BookDto;
import com.pageport.pageport.dto.BookSimplifyDto;
import com.pageport.pageport.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookSimplifyDto> getBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable UUID id){
        return bookService.getBook(id);
    }

    @GetMapping("/search")
    public List<BookSimplifyDto> getFilteredBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam(required = false) String genre){
        return bookService.getFilteredBooks(title, author, genre);
    }
}
