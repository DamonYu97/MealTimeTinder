package sta.cs5031p3.mealtimetinder.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cookbook")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cookbook {

    @Id
    @Column(name = "hunter_id")
    private Long id;

    @OneToOne()
    @MapsId
    @JoinColumn(name = "hunter_id")
    private Hunter hunter;

    @ManyToMany
    @JoinTable(name = "cookbook_meal", joinColumns = @JoinColumn(name = "cook_id", referencedColumnName = "hunter_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id", referencedColumnName = "id"))
    private List<Meal> meals;
}
