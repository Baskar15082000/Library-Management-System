package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Reposistory.LibraryCardRepo;
import com.example.Library.Management.System.Service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/LibraryCard")
public class Librarycardcontroller {
    @Autowired
    LibraryService libraryService;
    @PostMapping("/generate empty card")
    public ResponseEntity generate_empty_card(){
        return new ResponseEntity<>(libraryService.generate_empty_card(), HttpStatus.CREATED);
    }
    @PutMapping("/Associate Card with Student")
    public ResponseEntity associate_card_with_student(@RequestParam("Student Id") int studentId,@RequestParam("LibraryCard Id") int cardId){
        try {
            String str=libraryService.generate_empty_card(studentId,cardId);
            return new ResponseEntity<>(str,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
