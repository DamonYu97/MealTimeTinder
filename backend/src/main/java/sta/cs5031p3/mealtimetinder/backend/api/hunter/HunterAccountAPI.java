package sta.cs5031p3.mealtimetinder.backend.api.hunter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sta.cs5031p3.mealtimetinder.backend.model.User;
import sta.cs5031p3.mealtimetinder.backend.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/hunter")
@OpenAPIDefinition(info = @Info(title = "Hunter Account API",
        description = "This documents Restful APIs for Hunter's Account",
        contact = @Contact(name = "CS5031 P3 Group B",
                url = "https://gitlab.cs.st-andrews.ac.uk/cs5031groupb/project-code")
))
public class HunterAccountAPI {
    @Autowired
    private UserService hunterService;

    @GetMapping("/{id}/profile")
    @Operation(security = {
            @SecurityRequirement(name = "HunterBearerAuth")},
            summary = "Get Hunter Profile detail",
            description = "Hunter request profile information")
    public @ResponseBody
    User getProfile(@PathVariable long id) {
        return hunterService.getUserById(id);
    }
    // to do - login - inputs: credentials, output boolean
    // to do - logout
    // to do - getNextMeal (void method, return
    // to do - getMealRecipes
    // to do - get meal restaurants
    // to do - view cook book (saved or favourites recipes)
    // to do - "swipe right" and get screen to be presented with cook or order screen for a meal
    // to do - view default recipe and options for other user entered recipes
    // to do - view other recipes
    // to do - view local restaurants that can cook a meal

}
