package sta.cs5031p3.mealtimetinder.backend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Schema(name = "User login form", description = "Store user login info")
public class UserLoginForm implements Serializable {
    @Schema(description="username", example = "damon", required = true)
    private String username;
    @Schema(description="password", example = "1204578616", required = true)
    private String password;
}
