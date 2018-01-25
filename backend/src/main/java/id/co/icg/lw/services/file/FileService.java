package id.co.icg.lw.services.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by alif on 5/19/17.
 */
public interface FileService {
    String upload(MultipartFile file);
    File download(String fileName);
}
