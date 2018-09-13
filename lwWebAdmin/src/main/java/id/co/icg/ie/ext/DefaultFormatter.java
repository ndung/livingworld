/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.icg.ie.ext;

import id.co.icg.ie.dao.util.PojoModel;
import id.co.icg.ie.manager.CacheManager;
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.format.Formatter;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Hatta Palino
 */
public class DefaultFormatter implements Formatter<PojoModel> {

    @SpringBean
    private CacheManager cacheManager;

    @Override
    public void setFormatType(String formatType) {
    }

    @Override
    public void setFormatPattern(String formatPattern) {
    }

    @Override
    public void setLocale(Locale locale) {
    }

    @Override
    public void init() {
    }

    @Override
    public String format(PojoModel input) {
        String key = new CacheModel(input).hash();
        //System.out.println("key " + new CacheModel(input).getKey() + " : " + key);
        cacheManager.setClass(key, input);
        return key;
    }

}

class CacheModel {

    private final Serializable id;
    private final String clazzName;

    public CacheModel(PojoModel pojoModel) {
        this.id = pojoModel.getId();
        this.clazzName = pojoModel.getClass().getCanonicalName();
    }

    public String getKey() {
        ObjectMapper obj = new ObjectMapper();
        try {
            return obj.writeValueAsString(id) + "#$#" + clazzName;
        } catch (IOException e) {
            return null;
        }
    }

    public String hash() {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(getKey().getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CacheModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
