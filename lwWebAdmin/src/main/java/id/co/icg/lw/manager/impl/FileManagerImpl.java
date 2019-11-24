package id.co.icg.lw.manager.impl;

import com.squareup.okhttp.*;
import id.co.icg.lw.manager.FileManager;
import net.sourceforge.stripes.action.FileBean;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;

@Service("fileManager")
public class FileManagerImpl implements FileManager {

    //public static final String URL = "https://mobile.livingworld.co.id/lw/api/v1/upload/image";
    @Value("${app.file.upload.path}")
    String path;
    @Value("${app.file.upload.url}")
    String url;

    @Override
    public String saveFile(FileBean fileBean) {
        try {
            String uuid = UUID.randomUUID().toString();
            String ext = FilenameUtils.getExtension(fileBean.getFileName());
            File file = new File(path + uuid +"."+ext);
            fileBean.save(file);
            return uploadFile(file, ext);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public String uploadFile(File file, String extension) {
        try {
            final MediaType MEDIA_TYPE = extension.equalsIgnoreCase("png") ?
                    MediaType.parse("image/png") : MediaType.parse("image/jpeg");

            RequestBody requestBody = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addFormDataPart("image", file.getName(), RequestBody.create(MEDIA_TYPE, file))
                    .build();

            Request request = new Request.Builder()
                    .header("Authorization", "LivingWorld")
                    .url(url)
                    .post(requestBody)
                    .build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();

            return new JSONObject(response.body().string()).getString("message");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

}
