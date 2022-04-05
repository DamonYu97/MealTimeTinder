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
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Hunter hunter;

    @ManyToMany
    private List<Meal> meals;
}
