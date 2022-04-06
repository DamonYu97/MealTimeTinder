package sta.cs5031p3.mealtimetinder.backend.api.hunter;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sta.cs5031p3.mealtimetinder.backend.model.*;
import sta.cs5031p3.mealtimetinder.backend.service.MealService;
import sta.cs5031p3.mealtimetinder.backend.service.UserService;
import java.util.List;

import java.util.ArrayList;

import java.util.List;

@RestController
@OpenAPIDefinition(info = @Info(title = "Hunter Account API",
        description = "This documents Restful APIs for Hunter's Account",
        contact = @Contact(name = "CS5031 P3 Group B",
                url = "https://gitlab.cs.st-andrews.ac.uk/cs5031groupb/project-code")
))
@Slf4j
public class HunterAPI {

    @Qualifier("hunterAuthManager")
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private MealService mealService;

    @PostMapping("/login")
    @Operation(summary = "Hunter Login",
            description = "Hunter submit login form to log in")
    public ResponseEntity<JWTResponse> login(@RequestBody UserLoginForm loginForm) {
        String accessToken = userService.login(loginForm, User.Role.ADMIN, authenticationManager);
        ;
        return ResponseEntity.ok(new JWTResponse(accessToken));
    }

    @GetMapping("/profile")
    @Operation(security = {
            @SecurityRequirement(name = "HunterBearerAuth")},
            summary = "Get Hunter Profile detail",
            description = "Hunter request profile information")
    public @ResponseBody
    User getProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getRegisteredHunterByUsername(username);
    }

    @GetMapping("/meals")
    public List<Meal> getMeal() {
        log.info("meals");
        return mealService.getRandom5Meals();
    }

   /* public Cookbook getCookbook() {
        //get user info
        //validate
        return null;
    }*/

    @PostMapping("/getRecipesForMeal/{meal}")
    public List<Recipe> getRecipesFromMeal(
            @PathVariable("meal") Meal meal
    ) {
        try {
            return getRecipesFromMeal(meal);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/getRestaurantFromMeal/{meal}")
    public List<Restaurant> getRestaurantFromMeal(
            @PathVariable("meal") Meal meal
    ){
        try {
            return getRestaurantFromMeal(meal);
        } catch (Exception e){
            return null;
        }
    }

    @PostMapping("/createNewHunterAccount/{password}/{username}")
    public boolean addAccount(
            @PathVariable String password,
            @PathVariable String username
    ) {
        try {
            userService.saveUser(new User(null, username, password, User.Status.REGISTERED, User.Role.HUNTER, null, null));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @PostMapping("/addMealToCookbook/{mealID}")
    public boolean removeMealFromCookbook(
            @PathVariable ("mealID") int mealID
    ) {
        try {
            removeMealFromCookbook(mealID);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
