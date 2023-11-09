package com.example.Library.Management.System.Reposistory;

import com.example.Library.Management.System.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book,Integer> {
}
