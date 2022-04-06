package sta.cs5031p3.mealtimetinder.backend;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sta.cs5031p3.mealtimetinder.backend.model.*;
import sta.cs5031p3.mealtimetinder.backend.service.MealService;
import sta.cs5031p3.mealtimetinder.backend.service.UserService;

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
                //MEALS
                Meal burger = new Meal(null,"Burger","meals/burger.jpg",null,null);
                mealService.saveMeal(burger);
                Meal chicken = new Meal(null,"Grilled Chicken","meals/chicken.jpg",null,null);
                mealService.saveMeal(chicken);
                Meal pizza = new Meal(null,"Pizza","meals/pizza.jpg",null,null);
                mealService.saveMeal(pizza);
                Meal indian = new Meal(null,"Tikka Masala","meals/Tikka-masala.jpg",null,null);
                mealService.saveMeal(indian);
                Meal pakora = new Meal(null,"Pakora","meals/pakora.jpg",null,null);
                mealService.saveMeal(pakora);
                Meal fish = new Meal(null,"Fish and Chips","meals/fish.jpg",null,null);
                mealService.saveMeal(fish);
                Meal scampi = new Meal(null,"Scampi","meals/scampi.jpg",null,null);
                mealService.saveMeal(scampi);

                //RECIPES
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
                Recipe fishn = new Recipe(null,"Fish and Chips","Fish, Batter, Chips, Peas",true,fish);
                mealService.saveRecipe(fishn);
                Recipe chickenPakora = new Recipe(null,"Chicken Pakora","Place the garlic, ginger, green chillies, lemon juice, garam masala, cumin seeds, chilli powder, dried fenugreek leaves into a pestle and mortar and blend to a rough paste.",true,pakora);
                mealService.saveRecipe(chickenPakora);
                Recipe vegetablePakora = new Recipe(null,"Vegetable Pakora","Heat up the oil in a karahi or wok to a medium heat",false,pakora);
                mealService.saveRecipe(vegetablePakora);


                //USERS
                ArrayList<User> users= new ArrayList<User>();

                User conor = new Admin("conor",passwordEncoder().encode("1204578615"),
                        User.Status.REGISTERED, "St Andrews","KY16");
                users.add(conor);


                User damon = new Admin("damon",passwordEncoder().encode("1204578614"),
                        User.Status.REGISTERED, "St Andrews","KY16");
                users.add(damon);


                ArrayList<Meal> a = new ArrayList<Meal>();
                a.add(burger); a.add(chicken); a.add(pizza);


                User ziggy = new Restaurant("Ziggys",passwordEncoder().encode("1204578612"),
                        User.Status.REGISTERED, "St Andrews","KY16","American Food", a);
                users.add(ziggy);

                ArrayList<Meal> b = new ArrayList<Meal>();
                b.add(chicken);

                User nandos =new Restaurant("Nandos",passwordEncoder().encode("1204578611"),
                        User.Status.REGISTERED,"St Andrews","KY16","Grilled Chicken",b);

                users.add(nandos);


                users.add(new Restaurant("Paesano",passwordEncoder().encode("1204578611"),
                        User.Status.PENDING,"Glasgow","G64 123","Italian Food",null));
                ArrayList<Meal> c = new ArrayList<Meal>();
                c.add(pizza); c.add(indian); c.add(pakora);

                users.add(new Restaurant("Tulsi",passwordEncoder().encode("1204578610"),
                        User.Status.REGISTERED,"St Andrews","G64 124","Indian Food",c));

                users.add(new Hunter("Damon",passwordEncoder().encode("1204578609"),
                        User.Status.REGISTERED,"St Andrews","G64 125"));

                users.add(new Hunter("Michael",passwordEncoder().encode("1204578608"),
                        User.Status.REGISTERED, "St Andrews","G64 126"));

                users.add(new Hunter("Jonny",passwordEncoder().encode("1204578607"),
                        User.Status.PENDING, "St Andrews","G64 127"));

                users.add(new Hunter("Conor",passwordEncoder().encode("1204578606"),
                        User.Status.REGISTERED, "St Andrews","G64 128"));

                for(User user: users){
                    userService.saveUser(user);
                }



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
