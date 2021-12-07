package fr.eql.al36.spring.bookapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
@EqualsAndHashCode(exclude="books")
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String name;

    @OneToMany(mappedBy = "author")
    private Set<Book> books;

    public Author(String firstName, String name) {
        this.firstName = firstName;
        this.name = name;
    }
}
