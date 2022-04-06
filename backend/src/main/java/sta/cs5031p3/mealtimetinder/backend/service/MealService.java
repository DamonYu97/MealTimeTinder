package sta.cs5031p3.mealtimetinder.backend.service;

import sta.cs5031p3.mealtimetinder.backend.model.Meal;
import sta.cs5031p3.mealtimetinder.backend.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public interface MealService {

    /**
     * Get random meal.
     * @return
     */
    Meal getRandomMeal();

    Meal getSpecificMeal(String mealName);

    Meal saveMeal(Meal meal);

    Recipe saveRecipe(Recipe recipe);

    Meal addRecipeToMeal(Meal meal,Recipe recipe);

    List<Recipe> getAllRecipesForMeal(Meal meal);

}
