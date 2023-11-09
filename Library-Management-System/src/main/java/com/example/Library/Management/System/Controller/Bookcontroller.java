package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Dtos.BookDto;
import com.example.Library.Management.System.Dtos.RequestBook;
import com.example.Library.Management.System.Entity.Book;
import com.example.Library.Management.System.Service.BookService;
import com.example.Library.Management.System.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class Bookcontroller {
    @Autowired
    BookService bookService;
    @PostMapping("/add book")
    public ResponseEntity add_book(@RequestBody RequestBook requestBook, @RequestParam("Author id") int author_id){
        try{
            String str= bookService.add_book(requestBook,author_id);
            return new ResponseEntity<>(str, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get book by genre")
    public ResponseEntity get_book_by_genre(@RequestParam("genre") String genre){
        try{
            List<BookDto> bookDtoList=bookService.get_book_by_genre(genre);
            return new ResponseEntity<>(bookDtoList,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("show all books")
    public ResponseEntity show_all_books(){
        try {
            List<String> books=bookService.show_all_books();
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("get book by author name")
    public ResponseEntity get_book_by_author_name(String authorname){
        try {
            List<String> books=bookService.get_book_by_author_name(authorname);
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
