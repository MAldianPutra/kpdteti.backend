package edu.kpdteti.backend.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

@Service
public class FileUploadUtil {

    public String uploadFile(String publicationId, MultipartFile multipartFile) throws IOException, URISyntaxException {
        try {
            URL modelURL = FileUploadUtil.class.getClassLoader().getResource("publication/");
            String publicationPath = Paths.get(modelURL.toURI()).toString() + "/" + publicationId + ".pdf";
            File file = new File(publicationPath);
            if (!file.exists()) {
                file.mkdirs();
            } else {
                file.delete();
            }
            multipartFile.transferTo(file);
            return file.getAbsolutePath();
        } catch (Exception e) {
            throw new IOException("File cannot be uploaded with id " + publicationId + ". Create a 'publication' folder in target/classes.");
        }
    }

}
