package fr.eql.al36.spring.bookapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;

    @ManyToMany(mappedBy="authorities")
    private Set<User> users;
}
