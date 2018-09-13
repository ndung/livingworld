/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.icg.ie.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

/**
 *
 * @author Hatta Palino
 */
public class XlsUtil {
    public static Workbook excelAvailability(Map beans, String fileName) {
        InputStream reportStream;
        try {
            String path  = StaticField.pathTemplate + fileName;
            reportStream = new FileInputStream(path);
            XLSTransformer transformer  = new XLSTransformer();
            return transformer.transformXLS(reportStream, beans);
        } catch (FileNotFoundException | ParsePropertyException | InvalidFormatException ex) {
            return null;
        } 
        
    }    
    
    public static List<Map<String, Object>> readFile(String configFile, InputStream file) {
        try {
            XLSReader   mainReader = ReaderBuilder.buildFromXML(new ByteArrayInputStream(configFile.getBytes()));
            InputStream inputXLS   = new BufferedInputStream(file);
            
            List<Map<String, Object>> maps = new ArrayList<>();
            Map beans = new HashMap();
            beans.put("datas", maps);
            XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
            if(readStatus.isStatusOK()) return maps;
            else                        return null;
        } catch (IOException | SAXException | InvalidFormatException e) {
            return null;
        }
        
    }
}
