package sta.cs5031p3.mealtimetinder.backend.service;

import sta.cs5031p3.mealtimetinder.backend.model.Meal;

public interface MealService {

    /**
     * Get random meal.
     * @return
     */
    Meal getRandomMeal();

}
