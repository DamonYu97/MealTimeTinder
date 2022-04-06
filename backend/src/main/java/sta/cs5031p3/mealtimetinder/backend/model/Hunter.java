package sta.cs5031p3.mealtimetinder.backend.model;

import lombok.Getter;

import javax.persistence.*;

/**
 * The Hunter model is for administrator, which inherits features from the general user.
 * It refers to the hunter table in database.
 * @author 200011181
 */
@Entity
@Getter
@Setter
@Table(name = "hunter")
@NoArgsConstructor
public class Hunter extends User {

    public Hunter(String username,String password,Status status,String address,String postcode){
        super(null, username,password,status,Role.HUNTER ,address,postcode);
    }
}
