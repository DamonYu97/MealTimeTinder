package sta.cs5031p3.mealtimetinder.backend.repository;

import org.springframework.data.repository.CrudRepository;
import sta.cs5031p3.mealtimetinder.backend.model.Meal;
import sta.cs5031p3.mealtimetinder.backend.model.User;

import java.util.Optional;

/**
 * Repository for all types of user information.
 * Extend CrudRepository allows it to use built in implementation of Spring Data JPA
 * with right method names or customised query.
 * @author 200011181
 */
public interface MealRepository extends CrudRepository<Meal, Long> {

    Optional<Meal> findMealByName(String name);

    // Optional<Meal> findMealbyName()
}