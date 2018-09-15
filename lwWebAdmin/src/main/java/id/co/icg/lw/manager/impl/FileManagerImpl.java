package id.co.icg.lw.manager.impl;

import id.co.icg.lw.manager.FileManager;
import net.sourceforge.stripes.action.FileBean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

@Service("fileManager")
public class FileManagerImpl implements FileManager {
    @Override
    public String saveFile(FileBean fileBean) {
        try {
            String uuid = UUID.randomUUID().toString();
            fileBean.save(new File("C:\\Workplace\\IE\\tomcat7\\webapps\\image\\" + uuid));
            return fileBean.getFileName();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
