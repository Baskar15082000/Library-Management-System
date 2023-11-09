package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Dtos.BookDto;
import com.example.Library.Management.System.Dtos.RequestBook;
import com.example.Library.Management.System.Entity.Author;
import com.example.Library.Management.System.Entity.Book;
import com.example.Library.Management.System.Exception.AuthorNotFound;
import com.example.Library.Management.System.Exception.BookNotFound;
import com.example.Library.Management.System.Reposistory.AuthorRepo;
import com.example.Library.Management.System.Reposistory.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepo bookRepo;
    @Autowired
    AuthorRepo authorRepo;
    public String add_book(RequestBook requestBook, int author_id) throws AuthorNotFound {
        Optional<Author> optionalAuthor=authorRepo.findById(author_id);
        if(!optionalAuthor.isPresent()){
            throw new AuthorNotFound("autor not found");
        }
        Author author=optionalAuthor.get();
        Book book=Book.builder().BookName(requestBook.getBookName()).IsAvailable(requestBook.isIsAvailable()).genre(requestBook.getGenre()).Rating(requestBook.getRating()).Prize(requestBook.getPrize()).author(author).build();

        author.getBookList().add(book);

        authorRepo.save(author);
        return "book added successfully";

    }
    public List<BookDto> get_book_by_genre(String genre)throws BookNotFound {
        List<Book> books=bookRepo.findAll();
        List<BookDto> bookDtoList=new ArrayList<>();
        for(Book book:books){
            if((book.getGenre().toString()).equals(genre)){
                BookDto bookDto=BookDto.builder().BookName(book.getBookName()).genre(book.getGenre()).Rating(book.getRating()).Prize(book.getPrize()).AuthorName(book.getAuthor().getAuthorName()).IsAvailable(book.isIsAvailable()).build();
                bookDtoList.add(bookDto);
            }
        }
        if(bookDtoList.isEmpty()){
            throw new BookNotFound("Book Not Available");
        }
        return bookDtoList;
    }
   public List<String> show_all_books()throws BookNotFound{
        List<Book> books=bookRepo.findAll();
        if(books.isEmpty()){
            throw new BookNotFound("Books Not Available");
        }
        List<String> allbooks=new ArrayList<>();
        for(Book book:books){
            allbooks.add("Book ID = "+book.getId()+" ---> Book Name = : "+book.getBookName()+" Author Name : "+book.getAuthor().getAuthorName()+" Genre : "+book.getGenre());
        }
        return allbooks;
   }
   public List<String> get_book_by_author_name(String name)throws Exception{

       List<Author> authorList = authorRepo.findAll();
       Author author=null;
       for (Author author1:authorList){
           if(author1.getAuthorName().equals(name)){
               author=author1;
           }
       }
       if(author==null)throw new AuthorNotFound("Invalid author");
       List<Book> books=bookRepo.findAll();
       if(books.isEmpty()){
           throw new BookNotFound("Books Not Available");
       }
       List<String> allbooks=new ArrayList<>();
       for(Book book:books){
           if(book.getAuthor().getAuthorName().equals(name)) {

               allbooks.add(" -> Book Name = : " + book.getBookName() + " Author Name : " + book.getAuthor().getAuthorName() + " Genre : " + book.getGenre());
           }
       }
       return allbooks;
   }

}
