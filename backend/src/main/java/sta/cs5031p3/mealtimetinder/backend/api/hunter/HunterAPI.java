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
import sta.cs5031p3.mealtimetinder.backend.repository.UserRepository;
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

    @PostMapping("/getRecipesForMeal/{id}")
    @Operation(security = {
            @SecurityRequirement(name = "HunterBearerAuth")
    })
    public List<Recipe> getRecipesFromMeal(
            @PathVariable("id") long id
    ) {
        try {
            Meal meal = mealService.getMealById(id);
            return mealService.getAllRecipesForMeal(meal);
        } catch (Exception e) {
            return null;
        }
    }


    //@PostMapping("/AddRecipeForMeal/{id}/{description}/{is_default}/{name}")
    @PostMapping("/AddRecipeForMeal/{id}/{description}/{name}")
    @Operation(security = {
            @SecurityRequirement(name = "HunterBearerAuth")
    })
    public void addRecipeForMeal(
            @PathVariable("id") long id,
            @PathVariable("description") String description,
            @PathVariable("name") String name
            //@PathVariable("is_default") Boolean defaulted,
            //@PathVariable("name") String name

    ) {
        try {
            Meal meal = mealService.getMealById(id);
            //mealService.saveRecipe(new Recipe(null,name,description,defaulted,meal));
            mealService.saveRecipe(new Recipe(null,name,description,false,meal));
            //check for invalid recipe (no meal)
        } catch (Exception e) {

        }
    }

    @PostMapping("/getSpecificMeal/{id}")
    @Operation(security = {
            @SecurityRequirement(name = "HunterBearerAuth")
    })
    public Meal getMeal(
            @PathVariable("id") long id
    ) {
        try {
            return mealService.getMealById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/getRestaurantsForMeal/{id}")
    @Operation(security = {
            @SecurityRequirement(name = "HunterBearerAuth")
    })
    public List<Restaurant> getRestaurantFromMeal(
            @PathVariable("id") long id
    ){
        try {
            Meal meal = mealService.getMealById(id);
            return mealService.getAllRestaurantForMeal(meal);
        } catch (Exception e){
            return null;
        }
    }

    @PostMapping("/register")
    public boolean addAccount(@RequestBody HunterCreation creation) {
        try {
            userService.saveUser(new Hunter(creation.getUsername(), creation.getPassword(), User.Status.REGISTERED
                    , creation.getAddress(), creation.getPostcode(), null));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @PostMapping("/viewFavourites")
    @Operation(security = {
            @SecurityRequirement(name = "HunterBearerAuth")
    })
    public List<Meal> viewFavourites() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Hunter h = (Hunter) userService.getRegisteredHunterByUsername(username);
            return userService.getFavourites(h);
        } catch (Exception e){
            return null;
        }
    }

    @PostMapping("/addToFavourites/{mealId}")
    @Operation(security = {
            @SecurityRequirement(name = "HunterBearerAuth")
    })
    public Boolean addToFavourites(
            @PathVariable ("mealId") long mealId
    ) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Hunter h = (Hunter) userService.getRegisteredHunterByUsername(username);

            Meal m = mealService.getMealById(mealId);
            userService.addToFavourites(h,m);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @PostMapping("/removeFromFavourites/{mealId}")
    @Operation(security = {
            @SecurityRequirement(name = "HunterBearerAuth")
    })
    public Boolean removeFromFavourites(
            @PathVariable ("mealId") long mealId
    ) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Hunter h = (Hunter) userService.getRegisteredHunterByUsername(username);

            Meal m = mealService.getMealById(mealId);
            userService.removeFromFavourites(h,m);
            return true;
        } catch (Exception e){
            return false;
        }
    }


}
