package com.example.Library.Management.System.Entity;

import com.example.Library.Management.System.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LibraryCard")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CardId;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private String CardHolderName;

    private int NoOfBookIssued;

    @OneToOne
    @JoinColumn
    private Student student;

    @OneToMany(mappedBy = "libraryCard",cascade = CascadeType.ALL)
    private List<Transaction> transactionList=new ArrayList<>();

}
