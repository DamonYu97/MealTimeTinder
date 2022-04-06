package sta.cs5031p3.mealtimetinder.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * The Admin model is for administrator, which inherits features from the general user.
 * It refers to the admin table in database.
 */
@Entity
@Getter
@Setter
@Table(name = "restaurant")
@NoArgsConstructor
public class Restaurant extends User {

    public Restaurant(String username,String password,Status status,Role role,String address,String postcode,String description,List<Meal> meals){
        super(null, username, password, status,Role.RESTAURANT, address, postcode);
        this.description = description;
        this.meals = meals;
    }
    private String description;

    public void addMeal(Meal meal){
        this.meals.add(meal);
    }

    @ManyToMany(targetEntity = Meal.class)
    private List<Meal> meals;

}
