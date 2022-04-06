package sta.cs5031p3.mealtimetinder.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sta.cs5031p3.mealtimetinder.backend.model.User;
import sta.cs5031p3.mealtimetinder.backend.model.UserLoginForm;
import sta.cs5031p3.mealtimetinder.backend.repository.UserRepository;
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
            throw new RuntimeException("Username exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User getRegisteredAdminByUsername(String username) {
        return userRepository.findUserByUsernameAndRoleAndStatus(username, User.Role.ADMIN, User.Status.REGISTERED).orElseThrow();
    }

}
