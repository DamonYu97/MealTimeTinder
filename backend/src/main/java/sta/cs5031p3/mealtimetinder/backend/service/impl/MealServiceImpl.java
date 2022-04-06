package sta.cs5031p3.mealtimetinder.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sta.cs5031p3.mealtimetinder.backend.model.Meal;
import sta.cs5031p3.mealtimetinder.backend.model.Recipe;
import sta.cs5031p3.mealtimetinder.backend.model.User;
import sta.cs5031p3.mealtimetinder.backend.repository.MealRepository;
import sta.cs5031p3.mealtimetinder.backend.repository.RecipeRepository;
import sta.cs5031p3.mealtimetinder.backend.service.MealService;

import java.util.List;
import java.util.Optional;

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

    public Meal saveMeal(Meal meal) {
        // Optional<Meal> existingMeal = userRepository.findMealByName
        Optional<Meal> existingMeal = mealRepository.findMealByName(meal.getName());

        if (existingMeal.isPresent()) {
            throw new IllegalArgumentException("Meal with this name already exists");
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

}
