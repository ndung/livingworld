package id.co.icg.lw.services.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * Created by alif on 5/19/17.
 */
@Service
public class FileServiceBean implements FileService {

    @Value("${file.location}")
    private String location;


    @Override
    public String upload(MultipartFile file) {

        try {
            byte[] bytes = file.getBytes();
            String ext = file.getOriginalFilename().split("\\.")[1];
            String fileName = UUID.randomUUID().toString() + "." + ext;
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(location + fileName)));
            stream.write(bytes);
            stream.close();
            return fileName;
        } catch (Exception e) {
            return "";
        }


    }

    @Override
    public File download(String fileName) {
        return null;
    }
}
