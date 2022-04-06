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
    public void addMeal() {
        List<Meal> meals = new ArrayList<>();
        Meal test = new Meal(null, "testmeal", "meals/burger.jpg", null, null);
        meals.add(test);
        mealService.saveMeal(test);
        //Meal hasnt been added to mealRepo
        when(mealRepository.findAll()).thenReturn(meals);
        assertEquals(mealRepository.findAll().size(),1);
    }

    @Test
    public void addRecipe() {

        Meal testmeal = new Meal(null, "testmeal", "meals/burger.jpg", null, null);
        mealRepository.save(testmeal);
        //Meal hasnt been added to mealRepo
        List<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(new Recipe(null, "Vegetable Pakora", "Heat up the oil in a karahi or wok to a medium heat", false, testmeal));

        when(recipeRepository.findAll()).thenReturn(recipes);
        assertEquals(recipeRepository.findAll().size(),1);
    }

    @Test
    public void recipeMustHaveMeal() {

        Meal test = new Meal(null, "Burger Test", "meals/burger.jpg", null, null);

        //Meal hasnt been added to mealRepo
        Recipe testrecipe = new Recipe(null, "Vegetable Pakora", "Heat up the oil in a karahi or wok to a medium heat", false, test);

        assertThrows(IllegalArgumentException.class, () -> mealService.saveRecipe(testrecipe));
    }

    @Test
    public void mealCanHaveMultipleRecipe() {

        Meal test = new Meal(null, "Burger Test 2", "meals/burger.jpg", null, null);

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

        Meal test = new Meal(null, "Burger Test", "meals/burger.jpg", null, null);
        Meal test2 = new Meal(null, "Burger Test", "meals/burger.jpg", null, null);
        mealService.saveMeal(test);
        when(mealRepository.findMealByName(test.getName())).thenReturn(Optional.of(test));
        when(mealRepository.save(test2)).thenReturn(test2);
        assertThrows(IllegalArgumentException.class, () -> mealService.saveMeal(test2));

    }

}
