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
import sta.cs5031p3.mealtimetinder.backend.model.*;
import sta.cs5031p3.mealtimetinder.backend.repository.MealRepository;
import sta.cs5031p3.mealtimetinder.backend.repository.UserRepository;
import sta.cs5031p3.mealtimetinder.backend.service.MealService;
import sta.cs5031p3.mealtimetinder.backend.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.ArrayList;

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
                mealService.saveMeal(burger);
                Meal chicken = new Meal(null,"Grilled Chicken","meals/chicken.jpg",null,null);
                mealService.saveMeal(chicken);
                Meal pizza = new Meal(null,"Pizza","meals/pizza.jpg",null,null);
                mealService.saveMeal(pizza);
                Meal indian = new Meal(null,"Tikka Masala","meals/Tikka-masala.jpg",null,null);
                mealService.saveMeal(indian);

                Recipe cheeseburger = new Recipe(null,"Cheeseburger recipe","Bun, Beef, Lettuce, Tomato, Cheese",true, burger);
                mealService.saveRecipe(cheeseburger);
                Recipe baconburger = new Recipe(null,"Cheeseburger recipe","Bacon, Meat, Beef,Tomato, Cheese",false,burger);
                mealService.saveRecipe(baconburger);
                Recipe pirichicken = new Recipe(null,"Piri Chicken","Chicken breast, Piri spices, Oil, Rice",false,chicken);
                mealService.saveRecipe(pirichicken);
                Recipe peppizza = new Recipe(null,"Pepperoni Pizza","Dough, Tomato, Cheese, Pepperoni",true,pizza);
                mealService.saveRecipe(peppizza);
                Recipe tikka = new Recipe(null,"Tikka Masala","Chicken, Rice, Spices, Naan Bread",true,indian);
                mealService.saveRecipe(tikka);

                ArrayList<User> users= new ArrayList<User>();

                users.add(new Admin("conor",passwordEncoder().encode("1204578615"),
                        User.Status.REGISTERED, User.Role.ADMIN,"St Andrews","KY16"));
                users.add(new Admin("damon",passwordEncoder().encode("1204578614"),
                        User.Status.REGISTERED, User.Role.ADMIN,"St Andrews","KY16"));
                users.add(new Restaurant("Ziggys",passwordEncoder().encode("1204578612"),
                        User.Status.REGISTERED, User.Role.RESTAURANT,"St Andrews","KY16","American Food"));
                users.add(new Restaurant("Nandos",passwordEncoder().encode("1204578611"),
                        User.Status.REGISTERED, User.Role.RESTAURANT,"St Andrews","KY16","Grilled Chicken"));
                users.add(new Restaurant("Paesano",passwordEncoder().encode("1204578611"),
                        User.Status.PENDING, User.Role.RESTAURANT,"Glasgow","G64 123","Italian Food"));
                users.add(new Restaurant("Tulsi",passwordEncoder().encode("1204578610"),
                        User.Status.REGISTERED, User.Role.RESTAURANT,"St Andrews","G64 124","Indian Food"));
                users.add(new Hunter("Damon",passwordEncoder().encode("1204578609"),
                        User.Status.REGISTERED, User.Role.HUNTER,"St Andrews","G64 125"));
                users.add(new Hunter("Michael",passwordEncoder().encode("1204578608"),
                        User.Status.REGISTERED, User.Role.HUNTER,"St Andrews","G64 126"));
                users.add(new Hunter("Jonny",passwordEncoder().encode("1204578607"),
                        User.Status.PENDING, User.Role.HUNTER,"St Andrews","G64 127"));
                users.add(new Hunter("Conor",passwordEncoder().encode("1204578606"),
                        User.Status.REGISTERED, User.Role.HUNTER,"St Andrews","G64 128"));


                for(User user: users){
                    userService.saveUser(user);
                }



                mealService.saveRecipe(new Recipe(null,"Cheeseburger recipe","Bun, Meat, Lettuce, Tomato, Cheese",true, burger));

                mealService.saveMeal(new Meal(null,"Burger","meals/burger.jpg",null,null));



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
