package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/Issue Book")
    public ResponseEntity<String> Issue_book(@RequestParam("Book Id") int bookId,@RequestParam("Card Id") int cardId){
        try {
            String str=transactionService.Issue_book(bookId,cardId);
            return new ResponseEntity<>(str, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("Return Book to Library ")
    public ResponseEntity<String>Return_Book_to_Library(@RequestParam("Book Id") int bookId,@RequestParam("Card Id") int cardId ){
        try {
            String str = transactionService.Return_Book_to_Library(bookId,cardId);
            return new ResponseEntity<>(str,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
