package sta.cs5031p3.mealtimetinder.backend.api.admin;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sta.cs5031p3.mealtimetinder.backend.model.User;
import sta.cs5031p3.mealtimetinder.backend.model.UserLoginForm;
import sta.cs5031p3.mealtimetinder.backend.service.AdminService;
import sta.cs5031p3.mealtimetinder.backend.service.FileService;

import java.io.IOException;

@CrossOrigin
@RestController
@OpenAPIDefinition(info = @Info(title = "Admin Account API",
        description = "This documents Restful APIs for Administrator's Account",
        contact = @Contact(name = "CS5031 P3 Group B",
                url = "https://gitlab.cs.st-andrews.ac.uk/cs5031groupb/project-code")
))
public class AdminAccountAPI {

    @Autowired
    private AdminService adminService;

    @Autowired
    private FileService fileService;

    @PostMapping("/admin/login")
    @Operation(summary = "Admin Login",
            description = "Administrator submit login form to log into Admin Interface")
    public @ResponseBody
    User login(@RequestBody UserLoginForm loginForm) {
        //Validate login form
        return adminService.login(loginForm);
    }

    @GetMapping("/admin/{id}/profile/")
    @Operation(summary = "Get Admin Profile detail",
            description = "Administrator request profile information")
    public @ResponseBody
    User getProfile(@PathVariable long id) {
        return adminService.getAdminById(id);
    }

    @PostMapping(value = "/admin/meal/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestBody MultipartFile multipartFile) throws IOException {
        return fileService.upload(multipartFile, "meals");
    }

}
