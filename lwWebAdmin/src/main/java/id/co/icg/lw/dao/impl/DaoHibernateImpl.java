/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.icg.lw.dao.impl;

import id.co.icg.lw.dao.util.CurrentPage;
import id.co.icg.lw.dao.util.ParameterDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import id.co.icg.lw.dao.DaoHibernate;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Hatta Palino
 */
@Transactional
@Repository("daoHibernate")
public class DaoHibernateImpl extends HibernateDaoSupport implements DaoHibernate {

    @Autowired
    public void anyMethodName(SessionFactory sessionFactory)
    {
        setSessionFactory(sessionFactory);
    }
    
    @Override
    public CurrentPage getList(ParameterDao parameter) {
        DetachedCriteria criteria1 = DetachedCriteria.forClass(parameter.getClazz());
        DetachedCriteria criteria2 = DetachedCriteria.forClass(parameter.getClazz());
        
        for (Object[] obj : parameter.getMaps().values()) {
            Criterion criterion = null;
            Order     order   = null;
            
            if(obj[0].equals(ParameterDao.EQUAL))           criterion = Restrictions.eq((String) obj[1], obj[2]);
            else if(obj[0].equals(ParameterDao.NOT_EQUAL))  criterion = Restrictions.not(Restrictions.eq((String) obj[1], obj[2]));
            else if(obj[0].equals(ParameterDao.LIKE))       criterion = Restrictions.like((String) obj[1], obj[2]);
            else if(obj[0].equals(ParameterDao.NOT_LIKE))   criterion = Restrictions.not(Restrictions.like((String) obj[1], obj[2]));
            else if(obj[0].equals(ParameterDao.NULL))       criterion = Restrictions.isNull((String) obj[1]);
            else if(obj[0].equals(ParameterDao.NOT_NULL))   criterion = Restrictions.isNotNull((String) obj[1]);
            else if(obj[0].equals(ParameterDao.IN))         criterion = Restrictions.in((String) obj[1], (List) obj[2]);
            else if(obj[0].equals(ParameterDao.NOT_IN))     criterion = Restrictions.not(Restrictions.in((String) obj[1], (List) obj[2]));
            else if(obj[0].equals(ParameterDao.GRATHER))    criterion = Restrictions.gt((String) obj[1], obj[2]);
            else if(obj[0].equals(ParameterDao.GRATHER_EQ)) criterion = Restrictions.ge((String) obj[1], obj[2]);
            else if(obj[0].equals(ParameterDao.LESS))       criterion = Restrictions.lt((String) obj[1], obj[2]);
            else if(obj[0].equals(ParameterDao.LESS_EQ))    criterion = Restrictions.le((String) obj[1], obj[2]);
            else if(obj[0].equals(ParameterDao.BETWEEN))    criterion = Restrictions.between((String) obj[1], obj[2], obj[3]);
            else if(obj[0].equals(ParameterDao.ORDER_ASC))  order     = Order.asc((String) obj[1]);
            else if(obj[0].equals(ParameterDao.ORDER_DESC)) order     = Order.desc((String) obj[1]);
            else if(obj[0].equals(ParameterDao.OR)) {
                Criterion exp1 = null;
		Criterion exp2;
		for (Object object : (List) obj[2]) {
                    if(object == null) exp2 = Restrictions.isNull((String) obj[1]);
                    else               exp2 = Restrictions.eq((String) obj[1], object);
                    if (exp1 == null) {
                    	exp1 = exp2;
                    } else {
                    	exp1 = Restrictions.or(exp2, exp1);
                    }
		}
                criterion = exp1;
            }
            
            if(criterion != null) {
                criteria1.add(criterion);
                criteria2.add(criterion);
            }
            
            if(order != null) criteria1.addOrder(order);
            
        }
        
        List list = new ArrayList(); 
        int  avai = 0;
        int  page = 0;
        int  size = 0; 
        int  max  = 0;
        
        if(parameter.isShowList()) {
            if(parameter.getMaxRows() != null && parameter.getMaxRows() > 0 && parameter.getOffside() != null) {
            
                ProjectionList projectionList = Projections.projectionList();
                projectionList.add(Projections.rowCount());

                criteria2.setProjection(projectionList);

                size = ((Long) getHibernateTemplate().findByCriteria(criteria2).get(0)).intValue();
                max  = parameter.getMaxRows();
                page = parameter.getOffside();

                if(page < 1) page = 1;

                if(size > 0 && max > 0) {
                    avai = Math.round(size / parameter.getMaxRows());
                    int start = parameter.getMaxRows() * (page - 1);
                    if (start > size) start = size;

                    list = getHibernateTemplate().findByCriteria(criteria1, start, parameter.getMaxRows());
                } else {
                    max  = 0;
                    avai = 0;
                }
            } else {
                list = getHibernateTemplate().findByCriteria(criteria1);
                size = list.size();
                max  = list.size();
            }
        }
        
        CurrentPage cp = new CurrentPage();
        cp.setPageItems(list);
        cp.setPageNumber(page);
        cp.setPagesAvailable(avai);
        cp.setTotalSize(size);
        cp.setPageSize(parameter.getMaxRows());

        return cp;
    }

    @Override
    public Object getObject(ParameterDao parameter) {
        List list = getArrayList(parameter, 1);
        if(list != null && !list.isEmpty()) return list.get(0);
        else                                return null; 
    }
    
    public List getArrayList(ParameterDao parameter) {
       return getArrayList(parameter, 0);
    }
    
    public List getArrayList(ParameterDao parameter, int maxList) {
        parameter.setOffside(0);
        parameter.setMaxRows(maxList);
        return getList(parameter).getPageItems();
    }

    @Override
    public List getList(Class clazz) {
        try {
            DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
            return getHibernateTemplate().findByCriteria(criteria);
        } catch (DataAccessException e) {
            logger.error(e);
            return null;
        }

    }

    @Override
    public boolean deleteList(List list) {
        getHibernateTemplate().deleteAll(list);
        return true;
    }

    @Override
    public boolean addList(List list) {
        for (Object object : list) getHibernateTemplate().saveOrUpdate(object);
        return true;
    }

    @Override
    public Object getObject(Class clazz, Serializable id) {
        return getHibernateTemplate().get(clazz, id);
    }

    @Override
    public boolean updateObject(Object object) {
        try {
            getHibernateTemplate().update(object);
            return true;
        } catch(Exception e) {
            logger.error(e);
            return false;
        }
    }

    @Override
    public List getHQL(String hql) {
        return getHQL(hql, null); 
    }
    
    @Override
    public List getHQL(String hql, Object[] objects) {
        if(objects == null) objects = new Object[]{};
        
        return getHibernateTemplate().find(hql, objects); 
    }

    @Override
    public boolean addObject(Object object) {
        try {
            getHibernateTemplate().saveOrUpdate(object);
            return true;
        } catch(Exception e) {
            logger.error(e);
            return false;
        }
    }
    
    @Override
    public Serializable addOnlyObject(Object object) {
        try {
            return getHibernateTemplate().save(object);
        } catch(Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public boolean deleteObject(Object object) {
        try {
            getHibernateTemplate().delete(object);
            return true;
        } catch(Exception e) {
            logger.error(e);
            return false;
        }
    }
}
