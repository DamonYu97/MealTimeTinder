package sta.cs5031p3.mealtimetinder.backend.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sta.cs5031p3.mealtimetinder.backend.service.FileService;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    /**
     * Customized folder for image files.
     */
    @Value("#{'${spring.web.resources.static-locations}'.substring(5)}")
    private String imageFileLocation;

    @Override
    public String upload(MultipartFile multipartFile, String to) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String relativePath = "/" + to + "/" + fileName;
        File image = new File(imageFileLocation + relativePath);
        multipartFile.transferTo(image);
        return relativePath;
    }
}
