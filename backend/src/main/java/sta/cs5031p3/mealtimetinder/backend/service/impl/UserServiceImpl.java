package sta.cs5031p3.mealtimetinder.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sta.cs5031p3.mealtimetinder.backend.model.User;
import sta.cs5031p3.mealtimetinder.backend.model.UserLoginForm;
import sta.cs5031p3.mealtimetinder.backend.repository.UserRepository;
import sta.cs5031p3.mealtimetinder.backend.security.JWTProvider;
import sta.cs5031p3.mealtimetinder.backend.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(UserLoginForm loginForm, User.Role role, AuthenticationManager authenticationManager) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Login....... \n username: {} \t password {}", loginForm.getUsername(), loginForm.getPassword());
        return JWTProvider.generateToken(authentication);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getRegisteredHunterByUsername(String username) {
        return userRepository.findUserByUsernameAndRoleAndStatus(username, User.Role.HUNTER, User.Status.REGISTERED).orElseThrow();
    }

    @Override
    public User saveUser(User user) {
        //No such user before: username does not match any registered username in database.
        Optional<User> existingUser = userRepository.findUserByUsernameAndRoleAndStatus(user.getUsername(), user.getRole(), User.Status.REGISTERED);
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User getRegisteredAdminByUsername(String username) {
        return userRepository.findUserByUsernameAndRoleAndStatus(username, User.Role.ADMIN, User.Status.REGISTERED).orElseThrow();
    }

    @Override
    public List<User> getAllByRole(User.Role role){
        return userRepository.getAllByRole(role);
    }

    @Override
    public void addMealToCookbook(int mealID){

    }
}
