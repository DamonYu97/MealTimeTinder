package sta.cs5031p3.mealtimetinder.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @Setter
    @OneToMany(mappedBy = "meal")
    List<Recipe> recipes;

    @ManyToMany(targetEntity = Restaurant.class, mappedBy = "meals")
    List<Restaurant> restaurants;
}
