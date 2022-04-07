package sta.cs5031p3.mealtimetinder.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Setter
    @OneToMany(mappedBy = "meal")
    List<Recipe> recipes;

    @ManyToMany(mappedBy ="servedMeals")
    List<Restaurant> restaurants;

    @ManyToMany(mappedBy ="favouriteMeals")
    List<Hunter> likes;


}
