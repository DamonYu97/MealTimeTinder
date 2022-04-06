package sta.cs5031p3.mealtimetinder.backend.service;

import sta.cs5031p3.mealtimetinder.backend.model.User;
import sta.cs5031p3.mealtimetinder.backend.model.UserLoginForm;

import java.util.List;

public interface UserService {
    User login(UserLoginForm loginForm);

    User getUserById(long id);

    List<User> getAllUsers();

    User saveUser(User user);
}
