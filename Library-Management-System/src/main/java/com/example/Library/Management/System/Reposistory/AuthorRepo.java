package com.example.Library.Management.System.Reposistory;

import com.example.Library.Management.System.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Integer> {

}
