package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Dtos.AuthorDto;
import com.example.Library.Management.System.Entity.Author;
import com.example.Library.Management.System.Exception.AuthorNotFound;
import com.example.Library.Management.System.Reposistory.AuthorRepo;
import jakarta.validation.constraints.NegativeOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepo authorRepo;
    public void add_author(AuthorDto authorDto){
        Author author=Author.builder().AuthorName(authorDto.getName()).Age(authorDto.getAge()).Rating(authorDto.getRating()).build();
        authorRepo.save(author);
    }
    public List find_allauthor(){
        List<Author> optionalAuthors=authorRepo.findAll();
       List<String > list=new ArrayList<>();
       for(Author author:optionalAuthors){
           list.add(author.getAuthorName());
       }
        return list;
    }
    public AuthorDto find_author_by_id(int id)throws AuthorNotFound{
        Optional<Author> optionalAuthor=authorRepo.findById(id);
        if(!optionalAuthor.isPresent()){
            throw  new AuthorNotFound("Invaid Author");
        }

        Author author=optionalAuthor.get();
        AuthorDto authorDto=AuthorDto.builder().age(author.getAge()).name(author.getAuthorName()).rating(author.getRating()).build();
        return authorDto;
    }
}
