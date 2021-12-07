package fr.eql.al36.spring.bookapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@EqualsAndHashCode(exclude = "books")
@Builder
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    private String city;
    private String zipCode;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;

}
