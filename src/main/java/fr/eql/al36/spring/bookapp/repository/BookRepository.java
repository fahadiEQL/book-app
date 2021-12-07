package fr.eql.al36.spring.bookapp.repository;

import fr.eql.al36.spring.bookapp.domain.Author;
import fr.eql.al36.spring.bookapp.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> getBooksByAuthor(Author author);
}
