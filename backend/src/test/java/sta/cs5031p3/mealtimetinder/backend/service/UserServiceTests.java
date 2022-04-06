package sta.cs5031p3.mealtimetinder.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import sta.cs5031p3.mealtimetinder.backend.model.*;
import sta.cs5031p3.mealtimetinder.backend.repository.MealRepository;
import sta.cs5031p3.mealtimetinder.backend.repository.UserRepository;
import sta.cs5031p3.mealtimetinder.backend.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllUserTest() {
        List<User> users = new ArrayList<>();
        users.add(new Admin("conor", "120",
                User.Status.REGISTERED, "St Andrews", "KY16"));
        users.add(new Admin("cono", "120",
                User.Status.REGISTERED, "St Andrews", "KY16"));

        when(userRepository.findAll()).thenReturn(users);

        assertEquals(2, userRepository.findAll().size());

    }

    @Test
    public void getAllAdminUserTest2() {

        List<User> users = new ArrayList<>();
        users.add(new Admin("conor", "120",
                User.Status.REGISTERED, "St Andrews", "KY16"));
        users.add(new Admin("cono", "120",
                User.Status.REGISTERED, "St Andrews", "KY16"));
        users.add(new Restaurant("Ziggys", "123",
                User.Status.REGISTERED, "St Andrews", "KY16", "American Food", null));

        //mocked removal of restaurant
        users.remove(users.size()-1);

        when(userRepository.getAllByRole(User.Role.ADMIN)).thenReturn(users);
        assertEquals(userRepository.getAllByRole(User.Role.ADMIN).size(),2);
    }
    @Test
    public void duplicateUserThrowExceptionTest(){
        User testuser = new Admin("conor", "120",
                User.Status.REGISTERED, "St Andrews", "KY16");
        User testuser2 = new Admin("conor", "120",
                User.Status.REGISTERED, "St Andrews", "KY16");
        userService.saveUser(testuser);
        when(userRepository.findUserByUsernameAndRoleAndStatus(testuser.getUsername(),testuser.getRole(),testuser.getStatus())).thenReturn(Optional.of(testuser));
        when(userRepository.save(testuser2)).thenReturn(testuser2);
        assertThrows(IllegalArgumentException.class,() -> userService.saveUser(testuser2));
    }
}

