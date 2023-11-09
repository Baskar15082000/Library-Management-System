package com.example.Library.Management.System.Entity;

import com.example.Library.Management.System.Enum.CardStatus;
import com.example.Library.Management.System.Enum.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "Transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int TransactionId;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    private int Fine;
    private Date ReturnDate;

    @CreationTimestamp
    private Date CreatedOn;

    @UpdateTimestamp
    private Date LostModifiedOn;

    @ManyToOne
    private Book book;

    @ManyToOne
    private LibraryCard libraryCard;

}
