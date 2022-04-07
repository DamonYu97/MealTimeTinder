package sta.cs5031p3.mealtimetinder.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sta.cs5031p3.mealtimetinder.backend.model.Meal;
import sta.cs5031p3.mealtimetinder.backend.model.Recipe;
import sta.cs5031p3.mealtimetinder.backend.model.Restaurant;
import sta.cs5031p3.mealtimetinder.backend.repository.MealRepository;
import sta.cs5031p3.mealtimetinder.backend.repository.RecipeRepository;
import sta.cs5031p3.mealtimetinder.backend.service.MealService;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Meal getRandomMeal() {
        return null;
    }

    @Override
    public Meal getSpecificMeal(String mealName) {
        return null;
    }

    @Override
    public List<Meal> getRandom5Meals() {
        //Get a random page number
        int totalPage = (int) (mealRepository.count() / 5);
        Random random = new Random();
        int pageNum = totalPage == 0 ? 0 : random.nextInt(totalPage);
        return mealRepository.findAll(PageRequest.of(pageNum, 5)).toList();
    }

    public Meal saveMeal(Meal meal) {
        // Optional<Meal> existingMeal = userRepository.findMealByName
        Optional<Meal> existingMeal = mealRepository.findMealByName(meal.getName());

        if (existingMeal.isPresent()) {
            //throw new IllegalArgumentException("Meal with this name already exists");
        }

        return mealRepository.save(meal);
    }

    public Recipe saveRecipe(Recipe recipe){

        String recipeMealName = recipe.getMeal().getName();

        Optional<Meal> existingMeal = mealRepository.findMealByName(recipeMealName);

        if(!existingMeal.isPresent()){
            throw new IllegalArgumentException("Recipe Must have a valid meal");
        }

        return recipeRepository.save(recipe);
    }




    @Override
    public Meal addRecipeToMeal(Meal meal,Recipe recipe){
        List<Recipe> currentRecipes = meal.getRecipes();
        currentRecipes.add(recipe);
        meal.setRecipes(currentRecipes);

        return mealRepository.save(meal);
    }

    @Override
    public List<Recipe> getAllRecipesForMeal(Meal meal) {
        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurantForMeal(Meal meal) {
        return null;
    }

    public Meal getMealById(Long id){
        Optional<Meal> meal =mealRepository.getMealById(id);

        if(!meal.isPresent()){
            throw new IllegalArgumentException("Meal does not exist");
        }

        return mealRepository.getMealById(id).orElseThrow();
    }

    @Override
    public List<Meal> getMealsForRestaurant(Restaurant restaurant) {
        return null;
    }

    @Override
    public void addMealToRestaurant(Restaurant restaurant, Meal meal) {

    }

}
