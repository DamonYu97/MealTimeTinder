package sta.cs5031p3.mealtimetinder.backend;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sta.cs5031p3.mealtimetinder.backend.model.User;
import sta.cs5031p3.mealtimetinder.backend.service.UserService;

@SecuritySchemes(value = {
        @SecurityScheme(
                name = "AdminBearerAuth",
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT",
                scheme = "bearer"
        ),
        @SecurityScheme(
                name = "HunterBearerAuth",
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT",
                scheme = "bearer"
        ),
        @SecurityScheme(
                name = "RestaurantBearerAuth",
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT",
                scheme = "bearer"
        )
})
@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Initialise data in database.
     * @param userService
     * @return
     */
    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            try {
                userService.saveUser(new User(null, "damon", passwordEncoder().encode("12345678"),
                        User.Status.REGISTERED, User.Role.HUNTER, null, null));
                userService.saveUser(new User(null, "damon", passwordEncoder().encode("1204578616"),
                        User.Status.REGISTERED, User.Role.ADMIN, null, null));
            } catch (Exception e) {
                e.getStackTrace();
            }
        };
    }

}
