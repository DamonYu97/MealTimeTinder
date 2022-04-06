package sta.cs5031p3.mealtimetinder.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Hunter model is for administrator, which inherits features from the general user.
 * It refers to the hunter table in database.
 * @author 200011181
 */
@Entity
@Getter
@Setter

@Table(name = "hunter")
public class Hunter extends User {
    public Hunter(){}



    public Hunter(String username,String password,Status status,Role role,String address,String postcode){
        super(username,password,status,role,address,postcode);
    }
}
