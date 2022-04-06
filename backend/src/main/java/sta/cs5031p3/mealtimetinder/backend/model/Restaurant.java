package sta.cs5031p3.mealtimetinder.backend.model;

import lombok.Getter;

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
@Table(name = "restaurant")
public class Restaurant extends User {
    public Restaurant(){}

    public Restaurant(String username,String password,Status status,Role role,String address,String postcode,String description){
        super(username,password,status,role,address,postcode);
        this.description=description;
    }
    private String description;

    @ManyToMany(targetEntity = Meal.class)
    private List<Meal> meals;

}
