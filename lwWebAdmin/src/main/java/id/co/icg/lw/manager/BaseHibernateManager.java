/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.icg.lw.manager;

import id.co.icg.lw.dao.util.CurrentPage;
import id.co.icg.lw.dao.util.ParameterDao;
import id.co.icg.lw.util.PaginatedListImpl;
import java.io.Serializable;
import java.util.List;
import org.displaytag.pagination.PaginatedList;

/**
 *
 * @author Hatta Palino
 */
public interface BaseHibernateManager {
    CurrentPage   getList(ParameterDao parameter);
    PaginatedList getList(ParameterDao parameter, PaginatedListImpl paginatedList);
    boolean       deleteList  (List list);
    boolean       addList     (List list);
    List          getHQLList  (String sql);
    List          getArrayList(ParameterDao parameter);
    Object        getObject   (Class clazz, Serializable id);
    boolean       updateObject(Object object);
    boolean       addObject(Object object);
    boolean       deleteObject(Object object);
}
