package sta.cs5031p3.mealtimetinder.backend.service;

import sta.cs5031p3.mealtimetinder.backend.model.User;
import sta.cs5031p3.mealtimetinder.backend.model.UserLoginForm;

public interface AdminService {
    User login(UserLoginForm loginForm);

    User getAdminById(long id);
}
