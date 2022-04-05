package sta.cs5031p3.mealtimetinder.backend;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sta.cs5031p3.mealtimetinder.backend.model.Hunter;
import sta.cs5031p3.mealtimetinder.backend.model.Meal;
import sta.cs5031p3.mealtimetinder.backend.model.Recipe;
import sta.cs5031p3.mealtimetinder.backend.model.User;
import sta.cs5031p3.mealtimetinder.backend.repository.MealRepository;
import sta.cs5031p3.mealtimetinder.backend.repository.UserRepository;
import sta.cs5031p3.mealtimetinder.backend.service.MealService;
import sta.cs5031p3.mealtimetinder.backend.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

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

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(BackendApplication.class);

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
    @Transactional
    @Bean
    CommandLineRunner run(UserService userService, MealService mealService) {
        return args -> {
            try {
                Meal burger = new Meal(null,"Burger","meals/burger.jpg",null,null);
                Recipe cheeseburger = new Recipe(null,"Cheeseburger recipe","Bun, Meat, Lettuce, Tomato, Cheese",true, burger);
                mealService.addRecipeToMeal(burger,cheeseburger);


                mealService.saveMeal(burger);
                mealService.saveRecipe(new Recipe(null,"Cheeseburger recipe","Bun, Meat, Lettuce, Tomato, Cheese",true, burger));

                mealService.saveMeal(new Meal(null,"Burger","meals/burger.jpg",null,null));

                userService.saveUser(new User(null, "david", passwordEncoder().encode("1204578615"),
                        User.Status.REGISTERED, User.Role.ADMIN, null, null));
                userService.saveUser(new User(null,"conor", passwordEncoder().encode("98765432"),
                        User.Status.REGISTERED,User.Role.RESTAURANT,null,null));
                userService.saveUser(new User(null, "damon", passwordEncoder().encode("12345678"),
                        User.Status.REGISTERED, User.Role.HUNTER, null, null));
                userService.saveUser(new User(null, "damon", passwordEncoder().encode("1204578616"),
                        User.Status.REGISTERED, User.Role.ADMIN, null, null));


            } catch (Exception e) {
                e.getStackTrace();
            }
        };
    }


   /* @Bean
    CommandLineRunner runMeal(MealService mealService) {
        return args -> {
            try {
                mealService.saveUser(new User(null, "damon", passwordEncoder().encode("12345678"),
                        User.Status.REGISTERED, User.Role.HUNTER, null, null));

            } catch (Exception e) {
                e.getStackTrace();
            }
        };
    }*/

}
