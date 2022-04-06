package sta.cs5031p3.mealtimetinder.backend.service;

import sta.cs5031p3.mealtimetinder.backend.model.Meal;
import sta.cs5031p3.mealtimetinder.backend.model.Recipe;

import java.util.List;

public interface MealService {

    /**
     * Get random meal.
     * @return
     */
    Meal getRandomMeal();

    List<Meal> getRecent5Meals();

    public List<Meal> getRandom5Meals();

    Meal saveMeal(Meal meal);

    Recipe saveRecipe(Recipe recipe);

    Meal addRecipeToMeal(Meal meal,Recipe recipe);
}
