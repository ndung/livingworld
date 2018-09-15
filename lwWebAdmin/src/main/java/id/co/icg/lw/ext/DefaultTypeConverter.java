/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.co.icg.lw.ext;

import id.co.icg.lw.dao.util.BaseModel;
import id.co.icg.lw.dao.util.PojoModel;
import id.co.icg.lw.manager.CacheManager;
import java.util.Collection;
import java.util.Locale;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.util.CryptoUtil;
import net.sourceforge.stripes.validation.TypeConverter;
import net.sourceforge.stripes.validation.ValidationError;

/**
 *
 * @author Hatta Palino
 */
public class DefaultTypeConverter implements TypeConverter<BaseModel> {

    @SpringBean
    private CacheManager cacheManager;

    @Override
    public void setLocale(Locale locale) {}

    @Override
    public PojoModel convert(String input, Class<? extends BaseModel> targetType, Collection<ValidationError> errors) {
        //input = CryptoUtil.decrypt(input);
        return (PojoModel) cacheManager.getClass(input);
    }
    
}