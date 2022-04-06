package sta.cs5031p3.mealtimetinder.backend.api.hunter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sta.cs5031p3.mealtimetinder.backend.model.Cookbook;
import sta.cs5031p3.mealtimetinder.backend.model.Meal;
import sta.cs5031p3.mealtimetinder.backend.model.User;
import sta.cs5031p3.mealtimetinder.backend.service.MealService;
import sta.cs5031p3.mealtimetinder.backend.service.UserService;

@CrossOrigin
@RestController
@OpenAPIDefinition(info = @Info(title = "Hunter Account API",
        description = "This documents Restful APIs for Hunter's Account",
        contact = @Contact(name = "CS5031 P3 Group B",
                url = "https://gitlab.cs.st-andrews.ac.uk/cs5031groupb/project-code")
))
public class HunterAPI {
    @Autowired
    private UserService hunterService;

    @Autowired
    private MealService mealService;

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
