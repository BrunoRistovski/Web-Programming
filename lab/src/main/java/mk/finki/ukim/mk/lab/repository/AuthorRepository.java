package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {
    public static List<Author> authors=null;

    @PostConstruct
    public void init(){
        authors=new ArrayList<>();
        authors.add(new Author(1L,"Petar","Petrevski","Najak"));
        authors.add(new Author(2L,"Mile","Milevski","Najslab"));
        authors.add(new Author(3L,"Ile","Ilievski","Prosecen"));
        authors.add(new Author(4L,"Marko","Markovski","Dobar"));
        authors.add(new Author(5L,"Stefan","Stefanovski","Najak"));
    }
    public List<Author> findAll(){
        return authors;
    }
    public Optional<Author> findById(Long id){
        return authors.stream().filter(a->a.getId().equals(id)).findFirst();
    }
}
