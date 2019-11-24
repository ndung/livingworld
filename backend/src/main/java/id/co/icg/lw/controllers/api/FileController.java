package id.co.icg.lw.controllers.api;

import id.co.icg.lw.Application;
import id.co.icg.lw.domain.Response;
import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.enums.RoleEnum;
import id.co.icg.lw.services.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by alif on 5/31/17.
 */

@RestController
@RequestMapping(Application.API_PATH)
public class FileController extends BaseController {
    @Value("${file.location}")
    private String location;

    @Autowired
    private FileService fileService;

    /**
     * @api {get} /files/{fileName} Get Images
     * @apiName Get Image
     * @apiGroup File
     */
    @RequestMapping(value = "/files/{fileName:.+}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable("fileName") String fileName) {
        HttpHeaders headers = new HttpHeaders();
        DataInputStream in;

        try {
            File file = new File(location + fileName);
            byte[] media = new byte[(int) file.length()];
            BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
            br.read(media);


            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
    }

    @RequestMapping(value = "/upload/image", method = RequestMethod.POST)
    public ResponseEntity<Response> uploadPhotoProfile(@RequestHeader(Application.AUTH) String token,
                                                       @RequestParam("image") MultipartFile image) {
        if (!token.equals("LivingWorld")) {
            return FORBIDDEN;
        }

        try {
            String fileName = fileService.upload(image);
            return getHttpStatus(new Response(fileName));
        } catch (Exception e) {
            e.printStackTrace();
            return getHttpStatus(new Response(e.getMessage()));
        }
    }
}
