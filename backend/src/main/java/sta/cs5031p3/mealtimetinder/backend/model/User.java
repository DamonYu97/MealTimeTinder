package sta.cs5031p3.mealtimetinder.backend.model;

import lombok.AccessLevel;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String username;

    @Getter(AccessLevel.NONE)
    private String password;

    public enum Status {
        REGISTERED,
        PENDING,
        DELETED
    }

    @Enumerated(EnumType.STRING)
    private Status status;

    enum Role {
        ADMIN,
        HUNTER,
        RESTAURANT
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    private String address;

    private String postcode;
}
