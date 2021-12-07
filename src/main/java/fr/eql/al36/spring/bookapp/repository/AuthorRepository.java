package fr.eql.al36.spring.bookapp.repository;

import fr.eql.al36.spring.bookapp.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
