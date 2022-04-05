package sta.cs5031p3.mealtimetinder.backend.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Hunter model is for administrator, which inherits features from the general user.
 * It refers to the hunter table in database.
 * @author 200011181
 */
@Entity
@Getter
@Table(name = "hunter")
public class Hunter extends User {

}
