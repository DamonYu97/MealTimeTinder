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

@RestController
@RequestMapping("/hunter")
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
        return mealService.getRecent5Meals();
    }

    public Cookbook getCookbook() {
        //get user info
        //validate
        return null;
    }

    @PostMapping("/addMealToFavourite/{id}")
    public boolean addMealToCookbook(long id) {
        //function MEAL = mealService.getMealFromID
        //add MEAL to cookbook;
        return false;
    }


    @PostMapping("/createNewHunterAccount/{address}/{password}/{username}")
    public boolean addAccount(
            @PathVariable String address,
            @PathVariable String password,
            @PathVariable String username
    ) {
        try {
            userService.saveUser(new User(null, username, password, User.Status.REGISTERED, User.Role.HUNTER, address, null));
            return true;
        }catch(Exception e){
            return false;
        }
    }


    public boolean removeMealFromCookbook(long id) {
        return false;
    }

}
