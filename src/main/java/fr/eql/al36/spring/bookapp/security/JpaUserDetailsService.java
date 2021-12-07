package fr.eql.al36.spring.bookapp.security;

import fr.eql.al36.spring.bookapp.domain.Author;
import fr.eql.al36.spring.bookapp.domain.Authority;
import fr.eql.al36.spring.bookapp.domain.User;
import fr.eql.al36.spring.bookapp.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow( () -> {
            return new UsernameNotFoundException("User name : " + username + " not found");
        });
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),
                true, true, true,
                true, convertTospringAuthorities(user.getAuthorities()));
    }

    private Collection<SimpleGrantedAuthority> convertTospringAuthorities(Set<Authority> authorities) {
        if (authorities != null && authorities.size() > 0) {
            return authorities.stream()
                    .map(Authority::getRole)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        } else {
            return new HashSet<>();
        }
    }
}
