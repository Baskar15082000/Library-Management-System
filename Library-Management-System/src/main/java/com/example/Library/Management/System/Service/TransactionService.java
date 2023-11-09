package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entity.Book;
import com.example.Library.Management.System.Entity.LibraryCard;
import com.example.Library.Management.System.Entity.Transaction;
import com.example.Library.Management.System.Enum.CardStatus;
import com.example.Library.Management.System.Enum.TransactionStatus;
import com.example.Library.Management.System.Exception.BookNotFound;
import com.example.Library.Management.System.Exception.LibraryCardNotFound;
import com.example.Library.Management.System.Reposistory.BookRepo;
import com.example.Library.Management.System.Reposistory.LibraryCardRepo;
import com.example.Library.Management.System.Reposistory.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    TransactionRepo transactionRepo;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    LibraryCardRepo libraryCardRepo;
    static final private int MaxBookLimit = 5;
    static final private int FinePerDay = 5;
    public String Issue_book(int bookId,int cardId) throws Exception{
        Optional<Book>optionalBook=bookRepo.findById(bookId);
        Optional<LibraryCard>optionalLibraryCard=libraryCardRepo.findById(cardId);
        if(!optionalLibraryCard.isPresent()){
            throw  new LibraryCardNotFound("Invalid Library Card");
        }
        if (!optionalBook.isPresent()){
            throw  new BookNotFound("Invalid Book");
        }
        Book book=optionalBook.get();
        LibraryCard libraryCard=optionalLibraryCard.get();

        if(!book.isIsAvailable()){
            throw new BookNotFound("Book Is Currently UnAvailable");
        }

        if(!libraryCard.getCardStatus().equals(CardStatus.ACTIVE)){
            throw new LibraryCardNotFound("Your Card Is Not Activated");
        }
        if(libraryCard.getNoOfBookIssued()==MaxBookLimit){
            throw new LibraryCardNotFound("Already Max Limit Of Book Reached ( Max Limit = "+MaxBookLimit+" Books)");
        }
        Transaction transaction=new Transaction();

        transaction.setTransactionStatus(TransactionStatus.ISSUED);

        book.setIsAvailable(false);

        libraryCard.setNoOfBookIssued(libraryCard.getNoOfBookIssued()+1);

        transaction.setBook(book);
        transaction.setLibraryCard(libraryCard);

        book.getTransactionList().add(transaction);
        libraryCard.getTransactionList().add(transaction);

        transactionRepo.save(transaction);

        return "The Book "+book.getBookName()+" With Book Id "+book.getId()+" With Card Id "+libraryCard.getCardId()+" Issued Successful";
    }
    public String Return_Book_to_Library(int bookId,int cardId)throws Exception{
        Optional<Book>optionalBook=bookRepo.findById(bookId);
        Optional<LibraryCard>optionalLibraryCard=libraryCardRepo.findById(cardId);
        if(!optionalLibraryCard.isPresent()){
            throw  new LibraryCardNotFound("Invalid Library Card");
        }
        if (!optionalBook.isPresent()){
            throw  new BookNotFound("Invalid Book");
        }
        Book book=optionalBook.get();
        LibraryCard libraryCard=optionalLibraryCard.get();

        Transaction transaction=transactionRepo.findTransactionByBookAndLibraryCard(book.getId(),libraryCard.getCardId(),TransactionStatus.ISSUED).get();

        Date issueDate = transaction.getCreatedOn();

        long milliSeconds = Math.abs(System.currentTimeMillis()-issueDate.getTime());
        Long days = TimeUnit.DAYS.convert(milliSeconds,TimeUnit.MILLISECONDS);

        int fineAmount = 0;
        if(days>15){
            fineAmount = Math.toIntExact((days - 15) * FinePerDay);
        }
        Transaction newtransaction =new Transaction();
        newtransaction.setTransactionStatus(TransactionStatus.COMPLETED);
        newtransaction.setReturnDate(new Date());
        newtransaction.setFine(fineAmount);

        newtransaction.setBook(book);
        newtransaction.setLibraryCard(libraryCard);

        book.getTransactionList().add(newtransaction);
        libraryCard.getTransactionList().add(newtransaction);

        transactionRepo.save(newtransaction);
        return "Book Returned Successful";
    }
}
