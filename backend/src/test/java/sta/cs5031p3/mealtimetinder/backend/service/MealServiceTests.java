package sta.cs5031p3.mealtimetinder.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import sta.cs5031p3.mealtimetinder.backend.model.*;
import sta.cs5031p3.mealtimetinder.backend.repository.MealRepository;
import sta.cs5031p3.mealtimetinder.backend.repository.RecipeRepository;
import sta.cs5031p3.mealtimetinder.backend.repository.UserRepository;
import sta.cs5031p3.mealtimetinder.backend.service.impl.MealServiceImpl;
import sta.cs5031p3.mealtimetinder.backend.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class MealServiceTests {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private MealRepository mealRepository;

    @InjectMocks
    private MealService mealService = new MealServiceImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void recipeMustHaveMeal() {

        Meal test = new Meal(null, "Burger Test", "meals/burger.jpg", null, null);
        Recipe testrecipe = new Recipe(null, "Vegetable Pakora", "Heat up the oil in a karahi or wok to a medium heat", false, null);

        assertThrows(IllegalArgumentException.class, () -> mealService.saveRecipe(testrecipe));

    }


}
