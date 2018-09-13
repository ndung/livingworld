/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.icg.ie.dao;

import id.co.icg.ie.dao.util.CurrentPage;
import id.co.icg.ie.dao.util.ParameterDao;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Hatta Palino
 */
public interface DaoHibernate {
    CurrentPage getList   (ParameterDao parameter);
    List        getList   (Class clazz);
    Object      getObject (ParameterDao parameter);
    Object      getObject (Class clazz, Serializable id);
    boolean     deleteList(List list);
    boolean     addList   (List list);
    boolean     addObject (Object object);
    boolean     addOnlyObject (Object object);
    boolean     deleteObject (Object object);
    List        getHQL    (String hql);
    List        getHQL    (String hql, Object[] objects);
    
    boolean     updateObject(Object object);
}
