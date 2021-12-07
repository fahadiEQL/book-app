package fr.eql.al36.spring.bookapp.repository;

import fr.eql.al36.spring.bookapp.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
