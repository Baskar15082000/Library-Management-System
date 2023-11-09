package com.example.Library.Management.System.Reposistory;

import com.example.Library.Management.System.Entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryCardRepo extends JpaRepository<LibraryCard,Integer> {
}
