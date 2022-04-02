package sta.cs5031p3.mealtimetinder.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sta.cs5031p3.mealtimetinder.backend.model.User;
import sta.cs5031p3.mealtimetinder.backend.model.UserLoginForm;
import sta.cs5031p3.mealtimetinder.backend.repository.UserRepository;
import sta.cs5031p3.mealtimetinder.backend.service.AdminService;

import java.util.logging.Logger;

@Service
public class AdminServiceImpl implements AdminService {
    Logger logger = Logger.getLogger(AdminService.class.getName());

    @Autowired
    private UserRepository userRepository;


    @Override
    public User login(UserLoginForm loginForm) {
        //Validate login form
        //Encode password
        String encodePassword = loginForm.getPassword();
        if (userRepository == null) {
            logger.severe("User Repository not found!");
        }
        User adminEntity = userRepository.findUserByStatusAndUsernameAndPassword(
                User.Status.REGISTERED, loginForm.getUsername(), encodePassword)
                .orElseThrow();
        return adminEntity;
    }

    @Override
    public User getAdminById(long id) {
        return userRepository.findById(id).orElseThrow();
    }


}
