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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    public void addMealWithoutRecipesTest() {
        //add test meal
        Meal test = new Meal(null, "testmeal", null, null, null, null);
        //mock repositories
        when(mealRepository.findMealByName("testmeal")).thenReturn(Optional.empty());
        when(mealRepository.save(test)).thenReturn(test);
        //test the service
        Meal meal = mealService.saveMeal(test);
        assertEquals(test.getName(), meal.getName());
        assertNull(meal.getRecipes());
    }

    @Test
    public void addNewMealWithValidImagePathAndNameRecipesTest() {
        //add test recipes
        List<Recipe> recipes = new ArrayList<>();
        Recipe r1 = new Recipe(null, "r1", "ds", true, null);
        recipes.add(r1);
        Recipe r2 = new Recipe(null, "r2", "ds", false, null);
        recipes.add(r2);
        //add test meal
        Meal test = new Meal(null, "testmeal", "meals/burger.jpg", recipes, null, null);
        //mock repositories
        when(mealRepository.findMealByName("testmeal")).thenReturn(Optional.empty());
        when(mealRepository.save(test)).thenReturn(test);
        when(recipeRepository.saveAll(recipes)).thenReturn(recipes);
        //test the service
        mealService.saveMeal(test);
        Meal meal = mealService.saveMeal(test);
        assertEquals(test.getName(), meal.getName());
        assertEquals(test.getImagePath(), meal.getImagePath());
        assertEquals(r1, meal.getRecipes().get(0));
        assertEquals(meal.getRecipes().get(0).getMeal(), meal);
    }

    @Test
    public void addNewMealWithoutNameThrowExceptionTest() {
        //add test meal
        Meal test = new Meal(null, null , "meals/burger.jpg", null, null, null);
        //test the service
        mealService.saveMeal(test);
        assertThrows(IllegalArgumentException.class, () -> mealService.saveMeal(test));
    }

    @Test
    public void addNewMealWithoutImagePathWillGetDefaultPathTest() {
        //add test meal
        Meal test = new Meal(null, "test" , null, null, null, null);
        //mock
        when(mealRepository.findMealByName("test")).thenReturn(Optional.empty());
        when(mealRepository.save(test)).thenReturn(test);
        //test the service
        Meal meal = mealService.saveMeal(test);
        assertEquals(test.getName(), meal.getName());
        assertEquals("/meals/default.jpg", meal.getImagePath());
    }

    @Test
    public void addMealWithInValidImagePathThrowExceptionTest() {
        //add test meal
        Meal test = new Meal(null, "testmeal", "wrongPath", null, null, null);
        //mock repositories
        when(mealRepository.findMealByName("testmeal")).thenReturn(Optional.empty());
        //test the service
        mealService.saveMeal(test);
        assertThrows(IllegalArgumentException.class, () -> mealService.saveMeal(test));
    }

    @Test
    public void addOneRecipeToExistingMealTest() {
        Meal testMeal = new Meal((long)1, "testmeal", "meals/burger.jpg", null, null, null);
        Recipe recipe = new Recipe(null, "Vegetable Pakora", "Heat up the oil in a karahi or wok to a medium heat", false, testMeal);
        //mock repositories
        when(mealRepository.findMealByName("testmeal")).thenReturn(Optional.of(testMeal));
        when(recipeRepository.save(recipe)).thenReturn(recipe);
        //test
        Recipe savedRecipe = mealService.saveRecipe(recipe);
       assertEquals(savedRecipe.getMeal(), testMeal);
    }

    @Test
    public void addOneRecipeToNonExistingMealTest() {
        Meal testMeal = new Meal((long)1, "testmeal", "meals/burger.jpg", null, null, null);
        Recipe recipe = new Recipe(null, "Vegetable Pakora", "Heat up the oil in a karahi or wok to a medium heat", false, testMeal);
        //mock repositories
        when(mealRepository.findMealByName("testmeal")).thenReturn(Optional.empty());
        //test
        assertThrows(IllegalArgumentException.class, () -> mealService.saveRecipe(recipe));
    }

    @Test
    public void recipeMustHaveMeal() {

        Meal test = new Meal(null, "Burger Test", "meals/burger.jpg", null, null, null);

        //Meal hasnt been added to mealRepo
        Recipe testrecipe = new Recipe(null, "Vegetable Pakora", "Heat up the oil in a karahi or wok to a medium heat", false, test);

        assertThrows(IllegalArgumentException.class, () -> mealService.saveRecipe(testrecipe));
    }

    @Test
    public void mealCanHaveMultipleRecipe() {

        Meal test = new Meal(null, "Burger Test 2", "meals/burger.jpg", null, null, null);

        mealService.saveMeal(test);
        //Meal hasnt been added to mealRepo
        Recipe testrecipe = new Recipe(null, "Vegetable Pakora", "Heat up the oil in a karahi or wok to a medium heat", false, test);
        Recipe testrecipe2 = new Recipe(null, "Vegetable Test", "Heat up the oil in a karahi or wok to a medium test", false, test);
        when(mealRepository.findMealByName(testrecipe.getMeal().getName())).thenReturn(Optional.of(test));
        mealService.saveRecipe(testrecipe);
        mealService.saveRecipe(testrecipe2);


        assertEquals(2,recipeRepository.findAll().size());

    }

    @Test
    public void addRepeatMealThrowExceptionTest() {

        Meal test = new Meal(null, "Burger Test", "meals/burger.jpg", null, null, null);
        Meal test2 = new Meal(null, "Burger Test", "meals/burger.jpg", null, null, null);
        mealService.saveMeal(test);
        when(mealRepository.findMealByName(test.getName())).thenReturn(Optional.of(test));
        when(mealRepository.save(test2)).thenReturn(test2);
        assertThrows(IllegalArgumentException.class, () -> mealService.saveMeal(test2));
    }

}
