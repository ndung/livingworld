package id.co.icg.lw.manager.impl;

import id.co.icg.lw.manager.FileManager;
import net.sourceforge.stripes.action.FileBean;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

@Service("fileManager")
public class FileManagerImpl implements FileManager {
    @Override
    public String saveFile(FileBean fileBean) {
        try {
            String uuid = UUID.randomUUID().toString();
            String ext = FilenameUtils.getExtension(fileBean.getFileName());
            fileBean.save(new File("/opt/tomcat-mondelez/webapps-loyalty/images/" + uuid +"."+ext));
            return uuid+"."+ext;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
