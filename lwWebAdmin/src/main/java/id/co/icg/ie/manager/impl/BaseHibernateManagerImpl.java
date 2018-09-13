/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.icg.ie.manager.impl;

import id.co.icg.ie.dao.util.CurrentPage;
import id.co.icg.ie.dao.util.ParameterDao;
import id.co.icg.ie.manager.BaseHibernateManager;
import id.co.icg.ie.util.PaginatedListImpl;
import javax.annotation.Resource;
import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.stereotype.Service;
import id.co.icg.ie.dao.DaoHibernate;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Hatta Palino
 */
@Service("baseHibernateManager")
public class BaseHibernateManagerImpl implements BaseHibernateManager {

    @Resource
    private DaoHibernate daoHibernate;
    
    @Override
    public CurrentPage getList(ParameterDao parameter) {
        return daoHibernate.getList(parameter);
    }

    @Override
    public List getArrayList(ParameterDao parameter) {
        parameter.setOffside(0);
        parameter.setMaxRows(0);
        return daoHibernate.getList(parameter).getPageItems();
    }

    @Override
    public PaginatedList getList(ParameterDao parameter, PaginatedListImpl paginated) {
        parameter.setOffside(paginated.getPageNumber());
        if (paginated.getSortCriterion() != null) {
            if (paginated.getSortDirection().equals(SortOrderEnum.ASCENDING)) {
                parameter.setAscOrders(paginated.getSortCriterion());
            } else if (paginated.getSortDirection().equals(SortOrderEnum.DESCENDING)) {
                parameter.setDescOrders(paginated.getSortCriterion());
            }
        } 
        
        CurrentPage cp = daoHibernate.getList(parameter);
        paginated.setList(cp.getPageItems());
        paginated.setTotalNumberOfRows(cp.getTotalSize());
        paginated.setPageSize(cp.getPageSize());
        return paginated;
    }

    @Override
    public boolean deleteList(List list) {
        return daoHibernate.deleteList(list);
    }
    
    @Override
    public boolean addList(List list) {
        return daoHibernate.addList(list);
    }

    @Override
    public Object getObject(Class clazz, Serializable id) {
        return daoHibernate.getObject(clazz, id);
    }

    @Override
    public boolean updateObject(Object object) {
        return daoHibernate.updateObject(object);
    }

    @Override
    public List getHQLList(String sql) {
        return daoHibernate.getHQL(sql);
    }

    @Override
    public boolean addObject(Object object) {
        return daoHibernate.addObject(object);
    }

    @Override
    public boolean deleteObject(Object object) {
        return daoHibernate.deleteObject(object);
    }
    
}
