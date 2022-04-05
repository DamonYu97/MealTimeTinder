package sta.cs5031p3.mealtimetinder.backend.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Admin model is for administrator, which inherits features from the general user.
 * It refers to the admin table in database.
 */
@Entity
@Getter
@Table(name = "restaurant")
public class Restaurant extends User {
    private String description;

}
