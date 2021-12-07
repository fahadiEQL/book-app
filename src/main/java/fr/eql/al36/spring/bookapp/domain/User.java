package fr.eql.al36.spring.bookapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name="user_authority",
            joinColumns = { @JoinColumn (name="user_id", referencedColumnName = "id")},
            inverseJoinColumns = { @JoinColumn(name="authority_id", referencedColumnName = "id")})
    private Set<Authority> authorities;
}
