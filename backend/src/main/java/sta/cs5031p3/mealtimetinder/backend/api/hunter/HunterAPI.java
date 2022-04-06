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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sta.cs5031p3.mealtimetinder.backend.model.*;
import sta.cs5031p3.mealtimetinder.backend.security.JWTProvider;
import sta.cs5031p3.mealtimetinder.backend.service.MealService;
import sta.cs5031p3.mealtimetinder.backend.service.UserService;

@CrossOrigin
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
    private UserService hunterService;

    @Autowired
    private MealService mealService;

    @PostMapping("/login")
    @Operation(summary = "Hunter Login",
            description = "Hunter submit login form to log in")
    public ResponseEntity<JWTResponse> login(@RequestBody UserLoginForm loginForm) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Hunter Login....... \n username: {} \t password {}", loginForm.getUsername(), loginForm.getPassword());
        String accessToken = JWTProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTResponse(accessToken));
    }

    @GetMapping("/{id}/profile")
    @Operation(security = {
            @SecurityRequirement(name = "HunterBearerAuth")},
            summary = "Get Hunter Profile detail",
            description = "Hunter request profile information")
    public @ResponseBody
    User getProfile(@PathVariable long id) {
        return hunterService.getUserById(id);
    }


    public Meal getMeal() {
        Meal meal = mealService.getRandomMeal();
        return meal;
    }

    public Cookbook getCookbook() {
        //get user info
        //validate
        return null;
    }

    public boolean addMealToCookbook(long id) {
        return false;
    }

    public boolean removeMealFromCookbook(long id) {
        return false;
    }

}
