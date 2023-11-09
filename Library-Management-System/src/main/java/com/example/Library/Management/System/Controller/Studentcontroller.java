package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Dtos.studentDto;
import com.example.Library.Management.System.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class Studentcontroller {
    @Autowired
    StudentService studentService;
    @PostMapping("add student")
    public ResponseEntity add_student(@RequestBody studentDto studentDto){
        return new ResponseEntity<>(studentService.add_student(studentDto), HttpStatus.ACCEPTED);
    }

}
