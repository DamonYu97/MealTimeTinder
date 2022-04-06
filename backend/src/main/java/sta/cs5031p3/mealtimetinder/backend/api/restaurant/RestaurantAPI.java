package sta.cs5031p3.mealtimetinder.backend.api.restaurant;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sta.cs5031p3.mealtimetinder.backend.model.JWTResponse;
import sta.cs5031p3.mealtimetinder.backend.model.Meal;
import sta.cs5031p3.mealtimetinder.backend.model.User;
import sta.cs5031p3.mealtimetinder.backend.model.UserLoginForm;
import sta.cs5031p3.mealtimetinder.backend.security.JWTProvider;
import sta.cs5031p3.mealtimetinder.backend.service.UserService;

import java.util.List;

@CrossOrigin
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

    @PostMapping("/login")
    @Operation(summary = "Restaurant Login",
            description = "Restaurant submit login form to log in")
    public ResponseEntity<JWTResponse> login(@RequestBody UserLoginForm loginForm) {
        String accessToken = userService.login(loginForm, User.Role.ADMIN, authenticationManager);
        return ResponseEntity.ok(new JWTResponse(accessToken));
    }

    public List<Meal> searchMeal(String mealName) {
        return null;
    }

    public boolean addRestaurantToMeal(long meal_id) {
        //restaurant id
        return false;
    }

    public boolean removeRestaurantToMeal(long meal_id) {
        //restaurant id
        return false;
    }

    public List<Meal> checkOwnMeal() {
        return null;
    }

    @GetMapping("/getSpecificMeal/{mealName}")
    public Meal getSpecificMeal(
        @PathVariable("mealName") String meaName
    ){

        try {
            Meal meal = getSpecificMeal(meaName);
            return meal;
        } catch(Exception e){
            return null;
        }

    }
}
