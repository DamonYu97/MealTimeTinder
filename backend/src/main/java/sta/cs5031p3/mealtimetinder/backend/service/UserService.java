package sta.cs5031p3.mealtimetinder.backend.service;

import sta.cs5031p3.mealtimetinder.backend.model.User;
import sta.cs5031p3.mealtimetinder.backend.model.UserLoginForm;

public interface UserService {
    User login(UserLoginForm loginForm);

    User getUserById(long id);

    Iterable<User> getAllUsers();

    User saveUser(User user);
}
