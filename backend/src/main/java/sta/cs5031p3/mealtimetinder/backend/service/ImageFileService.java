package sta.cs5031p3.mealtimetinder.backend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Provide services for files handling.
 * @author 200011181
 */
public interface ImageFileService {

    /**
     * Upload image to /<project-root-folder>/images/
     * @throws IOException
     */
    String upload(MultipartFile multipartFile, String to) throws IOException;
}
