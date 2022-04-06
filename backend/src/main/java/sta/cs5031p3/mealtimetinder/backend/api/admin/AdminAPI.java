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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sta.cs5031p3.mealtimetinder.backend.model.JWTResponse;
import sta.cs5031p3.mealtimetinder.backend.model.User;
import sta.cs5031p3.mealtimetinder.backend.model.UserLoginForm;
import sta.cs5031p3.mealtimetinder.backend.security.JWTProvider;
import sta.cs5031p3.mealtimetinder.backend.service.UserService;
import sta.cs5031p3.mealtimetinder.backend.service.FileService;

import java.io.IOException;

@CrossOrigin
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
    private UserService adminService;

    @Autowired
    private FileService fileService;

    @PostMapping("/login")
    @Operation(summary = "Admin Login",
            description = "Administrator submit login form to log into Admin Interface")
    public ResponseEntity<JWTResponse> login(@RequestBody UserLoginForm loginForm) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Login....... \n username: {} \t password {}", loginForm.getUsername(), loginForm.getPassword());
        String accessToken = JWTProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTResponse(accessToken));
    }

    @GetMapping("/allUsers")
    @Operation(security = {
          @SecurityRequirement(name = "AdminBearerAuth")
    })
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok().body(adminService.getAllUsers());
    }


    @GetMapping("/{id}/profile")
    @Operation(security = {
            @SecurityRequirement(name = "AdminBearerAuth")},
            summary = "Get Admin Profile detail",
            description = "Administrator request profile information")
    public @ResponseBody
    User getProfile(@PathVariable long id) {
        return adminService.getUserById(id);
    }

    @GetMapping("/profile")
    @Operation(security = {
            @SecurityRequirement(name = "AdminBearerAuth")},
            summary = "Get Admin Profile detail",
            description = "Administrator request profile information")
    public @ResponseBody
    User getProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return adminService.getRegisteredAdminByUsername(username);
    }

    @PostMapping(value = "/meal/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestBody MultipartFile multipartFile) throws IOException {
        return fileService.upload(multipartFile, "meals");
    }

}
