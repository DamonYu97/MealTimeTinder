package sta.cs5031p3.mealtimetinder.backend.model;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * Model for upload image.
 * @author 200011181
 */
@Getter
public class ImageUpload implements Serializable {
    private MultipartFile imageFile;
    private String fileName;
}
