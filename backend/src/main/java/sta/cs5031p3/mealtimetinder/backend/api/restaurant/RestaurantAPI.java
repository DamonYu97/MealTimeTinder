package sta.cs5031p3.mealtimetinder.backend.api.restaurant;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sta.cs5031p3.mealtimetinder.backend.model.Meal;
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
public class RestaurantAPI {

    @Autowired
    private UserService restaurantService;

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

}
