package sta.cs5031p3.mealtimetinder.backend.api.restaurant;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sta.cs5031p3.mealtimetinder.backend.model.*;
import sta.cs5031p3.mealtimetinder.backend.security.JWTProvider;
import sta.cs5031p3.mealtimetinder.backend.service.MealService;
import sta.cs5031p3.mealtimetinder.backend.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@OpenAPIDefinition(info = @Info(title = "Restaurant API",
        description = "This documents Restful APIs for Restaurant",

        contact = @Contact(name = "CS5031 P3 Group B",
                url = "https://gitlab.cs.st-andrews.ac.uk/cs5031groupb/project-code")
))
@Slf4j
public class RestaurantAPI {

    @Qualifier("restaurantAuthManager")
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private MealService mealService;

    @PostMapping("/register")
    @Operation(summary = "Restaurant register",
            description = "Restaurant submit username password description to register an account")
    public boolean addAccount(@RequestBody RestaurantCreation creation) {
        try {
            userService.saveUser(new Restaurant(creation.getUsername(), creation.getPassword(),
                    sta.cs5031p3.mealtimetinder.backend.model.User.Status.REGISTERED, creation.getAddress(), creation.getPostcode(),
                    creation.getDescription(), null));
            return true;
        } catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Restaurant Login",
            description = "Restaurant submit login form to log in")
    public ResponseEntity<JWTResponse> login(@RequestBody UserLoginForm loginForm) {
        String accessToken = userService.login(loginForm, User.Role.RESTAURANT, authenticationManager);
        return ResponseEntity.ok(new JWTResponse(accessToken));
    }
    // This is now redundant?
    public List<Meal> searchMeal(String mealName) {
        return null;
    }

    @GetMapping("/allMeals")
    @Operation(security = {
                @SecurityRequirement(name = "RestaurantBearerAuth")
    })
    public ResponseEntity<Iterable<Meal>> getAllMeals() {
        return ResponseEntity.ok().body(mealService.getAllMeals());
    }


    @PostMapping("/addMealToRestaurant/{restaurant}/{meal}")
    public boolean addMealToRestaurant(
            @PathVariable ("restaurant") Restaurant restaurant,
            @PathVariable ("meal") Meal meal
    ) {
        try {
            mealService.addMealToRestaurantImpl(restaurant, meal);
            return true;

        } catch (Exception e){
            return false;
        }
    }

    @PostMapping ("/removeMealFromRestaurant/{restaurant}/{meal}")
    public boolean  removeMealFromRestaurant(
            @PathVariable ("restaurant") Restaurant restaurant,
            @PathVariable ("meal") Meal meal
    ){
        try {
            mealService.removeMealFromRestaurantImpl(restaurant, meal);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @PostMapping("/addRestaurantToMeal/{restaurant}/{meal}")
    public boolean addRestaurantToMeal(
            @PathVariable ("restaurant") Restaurant restaurant,
            @PathVariable ("meal") Meal meal
    ) {
        try {
            mealService.addRestaurantToMealImpl(restaurant, meal);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    @PostMapping ("/removeRestaurantFromMeal/{restaurant}/{meal}")
    public boolean removeRestaurantToMeal(
            @PathVariable ("restaurant") Restaurant restaurant,
            @PathVariable ("meal") Meal meal
    ) {
        try{
            mealService.removeRestaurantFromMealImpl(restaurant, meal);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/getRestaurantMeals/{restaurant}")
    public List<Meal> checkOwnMeal(
            @PathVariable("restaurant")Restaurant restaurant
            ) {
        try {
            List<Meal> meals = mealService.getMealsForRestaurant(restaurant);
            return meals;
        } catch (Exception e){
            return null;
        }
    }

    @GetMapping("/getSpecificMeal/{mealName}")
    public Meal getSpecificMeal(
        @PathVariable("mealName") String meaName
    ){

        try {
            return mealService.getSpecificMeal(meaName);
        } catch(Exception e){
            return null;
        }

    }

}
