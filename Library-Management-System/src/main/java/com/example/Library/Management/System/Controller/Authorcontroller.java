package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Dtos.AuthorDto;
import com.example.Library.Management.System.Entity.Author;
import com.example.Library.Management.System.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class Authorcontroller {
    @Autowired
    AuthorService authorService;
    @PostMapping("/add author")
    public ResponseEntity<String> add_author(@RequestBody AuthorDto authorDto){
        authorService.add_author(authorDto);

        return new ResponseEntity<>("Author added to db", HttpStatus.ACCEPTED);
    }
    @GetMapping("/find all author")
    public ResponseEntity<List> find_allauthor(){
        List<String > list =authorService.find_allauthor();
        return new ResponseEntity<>(list,HttpStatus.FOUND);
    }
    @GetMapping("/find author by id/{id}")
    public ResponseEntity find_author_by_id(@PathVariable("id") int id ){
        try{
            AuthorDto author=authorService.find_author_by_id(id);
            return new ResponseEntity<>(author,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }



}
