package sta.cs5031p3.mealtimetinder.backend.service;

import sta.cs5031p3.mealtimetinder.backend.model.Meal;
import sta.cs5031p3.mealtimetinder.backend.model.Recipe;
import sta.cs5031p3.mealtimetinder.backend.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

public interface MealService {

    /**
     * Get random meal.
     * @return
     */
    Meal getRandomMeal();

    Meal getSpecificMeal(String mealName);

    public List<Meal> getRandom5Meals();

    Meal saveMeal(Meal meal);

    Recipe saveRecipe(Recipe recipe);

    Meal getMealById(Long id);

    public List<Meal> getAllMeals();

    List<Recipe> getAllRecipesForMeal(Meal meal);

    List <Restaurant> getAllRestaurantForMeal(Meal meal);

    List<Meal> getMealsForRestaurant(Restaurant restaurant);

    void addMealToRestaurantImpl(Restaurant restaurant, Meal meal);

    void removeMealFromRestaurantImpl(Restaurant restaurant, Meal meal);

    void addRestaurantToMealImpl(Restaurant restaurant, Meal meal);

    void removeRestaurantFromMealImpl(Restaurant restaurant, Meal meal);
}
