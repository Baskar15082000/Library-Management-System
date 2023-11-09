package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Dtos.studentDto;
import com.example.Library.Management.System.Entity.Student;
import com.example.Library.Management.System.Reposistory.StudenrRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudenrRepo studenrRepo;
    public String add_student(studentDto studentDto){
        Student student=Student.builder().StudentName(studentDto.getStudentName()).StudentEmail(studentDto.getStudentEmail()).StudentMobile(studentDto.getStudentMobile()).age(studentDto.getAge()).build();
        studenrRepo.save(student);
        return "Student added to DB";
    }
}
