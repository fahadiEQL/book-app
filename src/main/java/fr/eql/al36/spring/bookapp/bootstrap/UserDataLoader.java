package fr.eql.al36.spring.bookapp.bootstrap;

import fr.eql.al36.spring.bookapp.domain.Authority;
import fr.eql.al36.spring.bookapp.domain.User;
import fr.eql.al36.spring.bookapp.repository.AuthorityRepository;
import fr.eql.al36.spring.bookapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDataLoader implements CommandLineRunner {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private PasswordEncoder passwordEncoder; //Bean de MySecurityEncoder

    //Injection de dépendance via le constructeur :
    public UserDataLoader(UserRepository userRepository,
                          AuthorityRepository authorityRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        Authority adminRole = authorityRepository.save(Authority.builder().role("ROLE_ADMIN").build());
        Authority userRole = authorityRepository.save(Authority.builder().role("ROLE_USER").build());

        Set<Authority> adminSet = new HashSet<>();
        adminSet.add(adminRole);

        Set<Authority> userSet = new HashSet<>();
        userSet.add(userRole);

        Set<Authority> allRolesSet = new HashSet<>();
        allRolesSet.add(adminRole);
        allRolesSet.add(userRole);

        userRepository.save(User.builder()
                .username("toto")
                .password(passwordEncoder.encode("pwdtoto"))
                .authorities(adminSet)
                .build());

        userRepository.save(User.builder()
                .username("titi")
                .password(passwordEncoder.encode("pwdtiti"))
                .authorities(userSet)
                .build());

        userRepository.save(User.builder()
                .username("admin")
                .password(passwordEncoder.encode("pwdadmin"))
                .authorities(allRolesSet)
                .build());
    }
}
