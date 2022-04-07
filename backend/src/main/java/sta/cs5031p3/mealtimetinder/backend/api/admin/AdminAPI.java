package sta.cs5031p3.mealtimetinder.backend.api.admin;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sta.cs5031p3.mealtimetinder.backend.model.*;
import sta.cs5031p3.mealtimetinder.backend.service.MealService;
import sta.cs5031p3.mealtimetinder.backend.service.UserService;
import sta.cs5031p3.mealtimetinder.backend.service.ImageFileService;

import java.io.IOException;

@RestController
@RequestMapping("/admin")
@OpenAPIDefinition(info = @Info(title = "Admin Account API",
        description = "This documents Restful APIs for Administrator's Account",

        contact = @Contact(name = "CS5031 P3 Group B",
                url = "https://gitlab.cs.st-andrews.ac.uk/cs5031groupb/project-code")
))
@Slf4j
public class AdminAPI {

    @Qualifier("adminAuthManager")
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private MealService mealService;


    @Autowired
    private ImageFileService fileService;

    @PostMapping("/login")
    @Operation(summary = "Admin Login",
            description = "Administrator submit login form to log into Admin Interface")
    public ResponseEntity<JWTResponse> login(@RequestBody UserLoginForm loginForm) {
        String accessToken = userService.login(loginForm, User.Role.ADMIN, authenticationManager);
        return ResponseEntity.ok(new JWTResponse(accessToken));
    }

    @GetMapping("/allUsers")
    @Operation(security = {
          @SecurityRequirement(name = "AdminBearerAuth")
    })
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/allMeals")
    @Operation(security = {
            @SecurityRequirement(name = "AdminBearerAuth")
    })
    public ResponseEntity<Iterable<Meal>> getAllMeals() {
        return ResponseEntity.ok().body(mealService.getAllMeals());
    }

    @GetMapping("/profile")
    @Operation(security = {
            @SecurityRequirement(name = "AdminBearerAuth")},
            summary = "Get Admin Profile detail",
            description = "Administrator request profile information")
    public @ResponseBody
    User getProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getRegisteredAdminByUsername(username);
    }

    @PostMapping(value = "/meal/uploadMealImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(security = {
            @SecurityRequirement(name = "AdminBearerAuth")
    })
    public String uploadImage(@RequestBody ImageUpload imageUpload) throws IOException {
        return fileService.upload(imageUpload, "meals");
    }

    @PostMapping("/addMeal/{image_path}/{name}")
    @Operation(security = {
            @SecurityRequirement(name = "AdminBearerAuth")
    })
    public boolean addMeal(
            @PathVariable String image_path,
            @PathVariable String name
    )  {
        try{
            mealService.saveMeal(new Meal(null,name,image_path,null,null,null));
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
