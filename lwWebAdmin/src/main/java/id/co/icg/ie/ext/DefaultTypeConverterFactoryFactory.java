/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.co.icg.ie.ext;

import id.co.icg.ie.dao.util.PojoModel;
import java.util.Locale;
import java.util.Set;
import javax.servlet.ServletContext;
import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.integration.spring.SpringHelper;
import net.sourceforge.stripes.util.ResolverUtil;
import net.sourceforge.stripes.validation.DefaultTypeConverterFactory;
import net.sourceforge.stripes.validation.TypeConverter;

/**
 *
 * @author Hatta Palino
 */
public class DefaultTypeConverterFactoryFactory extends DefaultTypeConverterFactory {

    @Override
    public void init(Configuration configuration) {
        super.init(configuration); //To change body of generated methods, choose Tools | Templates.
        
        ResolverUtil<PojoModel> resolver = new ResolverUtil<>();
        resolver.findImplementations(PojoModel.class, "id.co.icg.ie.dao.model");
        Set<Class<? extends PojoModel>> classes = resolver.getClasses();

        for (Class<? extends PojoModel> clazz : classes) {
            if(!clazz.isInterface()) {
                System.out.println("CLAZZ TYPE CONVERTER = " + clazz);
                add(clazz, DefaultTypeConverter.class);
            }            
        }
    }
    
    @Override
    public TypeConverter getInstance(Class<? extends TypeConverter> clazz, Locale locale) throws Exception {
        TypeConverter  tc = super.getInstance(clazz, locale);
        ServletContext sc = getConfiguration().getServletContext();
        SpringHelper.injectBeans(tc, sc);
        return tc;
    }
    
    
    
    
    
}
