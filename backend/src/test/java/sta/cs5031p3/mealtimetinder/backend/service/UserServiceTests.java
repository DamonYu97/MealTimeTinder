package sta.cs5031p3.mealtimetinder.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sta.cs5031p3.mealtimetinder.backend.model.Admin;
import sta.cs5031p3.mealtimetinder.backend.model.User;
import sta.cs5031p3.mealtimetinder.backend.repository.UserRepository;
import sta.cs5031p3.mealtimetinder.backend.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        users.add(new Admin("conor","120",
                User.Status.REGISTERED,"St Andrews","KY16"));
        users.add(new Admin("cono","120",
                User.Status.REGISTERED,"St Andrews","KY16"));

        when(userRepository.findAll()).thenReturn(users);

        assertEquals(2,userRepository.count());

    }

}