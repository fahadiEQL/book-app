package fr.eql.al36.spring.bookapp.repository;

import fr.eql.al36.spring.bookapp.domain.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
}
