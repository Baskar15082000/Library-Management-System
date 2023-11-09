package com.example.Library.Management.System.Reposistory;

import com.example.Library.Management.System.Entity.Book;
import com.example.Library.Management.System.Entity.LibraryCard;
import com.example.Library.Management.System.Entity.Transaction;
import com.example.Library.Management.System.Enum.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Integer> {

    @Query("SELECT t FROM Transaction t WHERE t.book.id = :bookId AND t.libraryCard.CardId = :libraryCardId AND t.transactionStatus = :transactionStatus")
    Optional<Transaction>findTransactionByBookAndLibraryCard(
            @Param("bookId") int bookId,
            @Param("libraryCardId") int libraryCardId,
            @Param("transactionStatus") TransactionStatus transactionStatus
    );
}
