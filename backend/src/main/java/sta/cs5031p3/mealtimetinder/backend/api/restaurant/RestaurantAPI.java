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

    @PostMapping("/login")
    @Operation(summary = "Restaurant Login",
            description = "Restaurant submit login form to log in")
    public ResponseEntity<JWTResponse> login(@RequestBody UserLoginForm loginForm) {
        String accessToken = userService.login(loginForm, User.Role.ADMIN, authenticationManager);
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
    //TODO : MANIPULATE MEALS LIST, EVERYTHING BELOW NEEDS CHECKING

    @PostMapping("/addMealToRestaurant/{mealId}")
    @Operation(security = {
            @SecurityRequirement(name = "RestaurantBearerAuth")
    })
    public boolean addMealToRestaurant(
            @PathVariable ("mealId") long mealId
    ) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Restaurant h = (Restaurant) userService.getRegisteredRestaurantByUsername(username);

            Meal m = mealService.getMealById(mealId);
            mealService.addMealToRestaurantImpl(h, m);
            return true;

        } catch (Exception e){
            return false;
        }
    }

    @PostMapping ("/removeMealFromRestaurant/{mealId}")
    @Operation(security = {
            @SecurityRequirement(name = "RestaurantBearerAuth")
    })
    public boolean  removeMealFromRestaurant(

            @PathVariable ("mealId") long mealId
    ){
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Restaurant h = (Restaurant) userService.getRegisteredRestaurantByUsername(username);

            Meal m = mealService.getMealById(mealId);
            mealService.removeRestaurantFromMealImpl(h, m);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @PostMapping("/addRestaurantToMeal/{mealId}")
    @Operation(security = {
            @SecurityRequirement(name = "RestaurantBearerAuth")
    })
    public boolean addRestaurantToMeal(
            @PathVariable ("mealId") long mealId
    ) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Restaurant h = (Restaurant) userService.getRegisteredRestaurantByUsername(username);

            Meal m = mealService.getMealById(mealId);
            mealService.addRestaurantToMealImpl(h, m);
            return true;
        } catch(Exception e){
            return false;
        }
    }

   /* @PostMapping ("/removeRestaurantFromMeal/{restaurant}/{meal}")
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
    }*/

    @PostMapping("/getRestaurantMeals")
    @Operation(security = {
            @SecurityRequirement(name = "RestaurantBearerAuth")
    })
    public List<Meal> checkOwnMeal() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Restaurant h = (Restaurant) userService.getRegisteredRestaurantByUsername(username);
            return userService.getServedMeals(h);
        } catch (Exception e){
            return null;
        }
    }


    @GetMapping("/getSpecificMeal/{mealId}")
    @Operation(security = {
            @SecurityRequirement(name = "RestaurantBearerAuth")
    })
    public Meal getSpecificMeal(
        @PathVariable("mealId") long mealId
    ){

        try {
            Meal m = mealService.getMealById(mealId);
            return m;
        } catch(Exception e){
            return null;
        }

    }
}
