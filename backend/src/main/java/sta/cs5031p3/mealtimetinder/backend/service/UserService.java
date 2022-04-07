package sta.cs5031p3.mealtimetinder.backend.service;

import org.springframework.security.authentication.AuthenticationManager;
import sta.cs5031p3.mealtimetinder.backend.model.Hunter;
import sta.cs5031p3.mealtimetinder.backend.model.Meal;
import sta.cs5031p3.mealtimetinder.backend.model.User;
import sta.cs5031p3.mealtimetinder.backend.model.UserLoginForm;

import javax.management.relation.Role;
import java.util.List;

public interface UserService {
    /**
     * Different role of User login with username and password.
     * @param loginForm includes username and password
     * @param role
     * @param authenticationManager manage the authentication logic, can get from individual security configs.
     * @return a String type access token.
     */
    String login(UserLoginForm loginForm, User.Role role, AuthenticationManager authenticationManager);

    User getUserById(long id);

    List<User> getAllUsers();

    User getRegisteredHunterByUsername(String username);

    User saveUser(User user);

    User getRegisteredAdminByUsername(String username);

    List<User> getAllByRole(User.Role role);

 }
