package br.com.abasteceai.common.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        User user = new User();
        user.setLogin("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.getRoles().add(Roles.DETONATOR_ROLE);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User loggedInUser = findByLogin(login)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + login));

        List<SimpleGrantedAuthority> roles = loggedInUser.getRoles().stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(loggedInUser.getLogin(), loggedInUser.getPassword(), roles);
    }

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}