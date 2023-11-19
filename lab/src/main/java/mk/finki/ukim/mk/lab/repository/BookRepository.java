package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    public static List<Book> books=null;

    @PostConstruct
    public void init(){
        books=new ArrayList<>();

        List<Author>pom1=new ArrayList<>();
        pom1.add(new Author(1L,"Petar","Petrevski","Najak"));
        books.add(new Book("11","Crna","Omraza",2000,pom1));

        List<Author>pom2=new ArrayList<>();
        pom2.add(new Author(2L,"Mile","Milevski","Najslab"));
        books.add(new Book("12","Crvena","Ljubov",2001,pom2));

        List<Author>pom3=new ArrayList<>();
        pom3.add(new Author(3L,"Ile","Ilievski","Prosecen"));
        books.add(new Book("13","Plava","Horor",2002,pom3));

        List<Author>pom4=new ArrayList<>();
        pom4.add(new Author(4L,"Marko","Markovski","Dobar"));
        books.add(new Book("14","Violetova","Begalka",2003,pom4));

        List<Author>pom5=new ArrayList<>();
        pom5.add(new Author(5L,"Stefan","Stefanovski","Najak"));
        books.add(new Book("15","Zolta","Kralska",2004,pom5));
    }
    public List<Book> findAll(){
        return books;
    }
    public Optional<Book> findByIsbn(String isbn){
        return books.stream().filter(b->b.getIsbn().equals(isbn)).findFirst();
    }
    public Author addAuthorToBook(Author author, Book book){
        Book tmp=books.stream().filter(b->b.getIsbn().equals(book.getIsbn())).findFirst().get();
        tmp.getAuthors().removeIf(a->a.getId().equals(author.getId()));
        tmp.getAuthors().add(author);
        books.removeIf(b->b.getIsbn().equals(book.getIsbn()));
        books.add(tmp);

        return author;
    }
    public Optional<Book>findByGenre(String genre){
        return books.stream().filter(b->b.getGenre().equals(genre)).findAny();
    }
}
