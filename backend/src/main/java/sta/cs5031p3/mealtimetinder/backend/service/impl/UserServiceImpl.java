package sta.cs5031p3.mealtimetinder.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sta.cs5031p3.mealtimetinder.backend.model.User;
import sta.cs5031p3.mealtimetinder.backend.model.UserLoginForm;
import sta.cs5031p3.mealtimetinder.backend.repository.UserRepository;
import sta.cs5031p3.mealtimetinder.backend.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(UserLoginForm loginForm) {
        //Validate login form
        //Encode password
        String encodePassword = loginForm.getPassword();
        if (userRepository == null) {
            log.error("User Repository not found!");
        }
        User adminEntity = userRepository.findUserByStatusAndUsernameAndPassword(
                User.Status.REGISTERED, loginForm.getUsername(), encodePassword)
                .orElseThrow();
        return adminEntity;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        //No such user before: username does not match any registered username in database.
        Optional<User> existingUser = userRepository.findUserByUsernameAndRoleAndStatus(user.getUsername(), user.getRole(), User.Status.REGISTERED);
        if (existingUser.isPresent()) {
            throw new RuntimeException("Username exists");
        }
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsernameAndRoleAndStatus(username, User.Role.HUNTER, User.Status.REGISTERED)
                .orElseThrow(() -> new UsernameNotFoundException("User not found in database"));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
