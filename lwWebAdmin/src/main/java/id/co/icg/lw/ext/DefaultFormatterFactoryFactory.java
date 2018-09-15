/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.co.icg.lw.ext;

import java.util.Locale;
import javax.servlet.ServletContext;
import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.format.DefaultFormatterFactory;
import net.sourceforge.stripes.format.Formatter;
import net.sourceforge.stripes.integration.spring.SpringHelper;

/**
 *
 * @author Hatta Palino
 */
public class DefaultFormatterFactoryFactory extends DefaultFormatterFactory {

    @Override
    public void init(Configuration configuration) throws Exception {
        super.init(configuration); //To change body of generated methods, choose Tools | Templates.
        
//        ResolverUtil<PojoModel> resolver = new ResolverUtil<PojoModel>();
//        resolver.findImplementations(PojoModel.class, PojoModel.class.getPackage().getName());
//        Set<Class<? extends PojoModel>> classes = resolver.getClasses();
//
//        System.out.println("LEWAT....");
//        System.out.println("HAHAHA : " + classes.size());
//        
//        for (Class<? extends PojoModel> clazz : classes) {
//            if(!clazz.isInterface()) {
//                System.out.println("CLAZZ FORMATTER = " + clazz);
//                add(clazz, DefaultFormatter.class);
//            }            
//        }
    
    }

    
    
    @Override
    public Formatter<?> getInstance(Class<? extends Formatter<?>> clazz, String formatType, String formatPattern, Locale locale) throws Exception {
        Formatter tc = super.getInstance(clazz, formatType, formatPattern, locale);
        ServletContext sc = getConfiguration().getServletContext();
        
        SpringHelper.injectBeans(tc, sc);
        
        return tc;
    }
    
    
    
}
