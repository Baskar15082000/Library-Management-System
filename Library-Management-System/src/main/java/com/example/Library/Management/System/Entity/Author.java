package com.example.Library.Management.System.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AuthorId;
    private String AuthorName;
    private int Age;
    private int Rating;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    List<Book> bookList = new ArrayList<>();

}
