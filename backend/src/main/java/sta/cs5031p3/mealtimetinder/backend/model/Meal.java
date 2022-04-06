package sta.cs5031p3.mealtimetinder.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "meal")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Meal {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    String name;

    String imagePath;

    @OneToMany(mappedBy = "meal")
    List<Recipe> recipes;

    @ManyToMany
    @JoinTable(name = "meal_restaurant", joinColumns = @JoinColumn(name = "meal_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id", referencedColumnName = "id"))
    List<Restaurant> restaurants;
}
