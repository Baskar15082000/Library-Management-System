package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entity.LibraryCard;
import com.example.Library.Management.System.Entity.Student;
import com.example.Library.Management.System.Enum.CardStatus;
import com.example.Library.Management.System.Exception.LibraryCardNotFound;
import com.example.Library.Management.System.Exception.StudentNotFound;
import com.example.Library.Management.System.Reposistory.LibraryCardRepo;
import com.example.Library.Management.System.Reposistory.StudenrRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    LibraryCardRepo libraryCardRepo;
    @Autowired
    StudenrRepo studenrRepo;
    @Autowired
    private JavaMailSender mailSender;

    public String generate_empty_card(){
        LibraryCard libraryCard=new LibraryCard();
        libraryCard.setCardStatus(CardStatus.NEW);
        libraryCardRepo.save(libraryCard);
        return "New card created with card Id = "+libraryCard.getCardId();
    }

    public String generate_empty_card(int studentId, int cardId)throws Exception{
        Optional<Student> optionalStudent=studenrRepo.findById(studentId);
        Optional<LibraryCard> optionalLibraryCard=libraryCardRepo.findById(cardId);
        if(!optionalLibraryCard.isPresent()){
            throw new LibraryCardNotFound("Invalid Library Card");

        }
        if(!optionalStudent.isPresent()){
            throw new StudentNotFound("Invalid Student");
        }

        Student student=optionalStudent.get();
        LibraryCard libraryCard=optionalLibraryCard.get();

        if(student.getLibraryCard()!=null) return "Student Already Add to "+student.getLibraryCard().getCardId();
        if(libraryCard.getStudent()!=null) return "Card Already Add to "+libraryCard.getStudent().getStudentId();

        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setCardHolderName(student.getStudentName());
        libraryCard.setStudent(student);
        student.setLibraryCard(libraryCard);
        studenrRepo.save(student);

        SimpleMailMessage mailMessage=new SimpleMailMessage();
        String body ="Hi "+student.getStudentName()+" Your Card successfully Linked to Library Card Id = "+libraryCard.getCardId();
        mailMessage.setFrom("indianlibrary0001@gmail.com");
        mailMessage.setTo(student.getStudentEmail());
        mailMessage.setSubject("WelCome to the Indian Library");
        mailMessage.setText(body);
        mailSender.send(mailMessage);


        return "CARD ASSIGNED TO STUDENT ID = "+student.getStudentId()+" WITH CARD ID = "+libraryCard.getCardId();

    }

}
