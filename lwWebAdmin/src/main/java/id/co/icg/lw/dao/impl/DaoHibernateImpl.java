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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import id.co.icg.lw.dao.DaoHibernate;
import org.springframework.dao.DataAccessException;

/**
 * @author Hatta Palino
 */
@Transactional
@Repository("daoHibernate")
public class DaoHibernateImpl extends HibernateDaoSupport implements DaoHibernate {

    @Autowired
    public void anyMethodName(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public CurrentPage getList(ParameterDao parameter) {
        DetachedCriteria criteria1 = DetachedCriteria.forClass(parameter.getClazz());
        DetachedCriteria criteria2 = DetachedCriteria.forClass(parameter.getClazz());

        for (Object[] obj : parameter.getMaps().values()) {
            Criterion criterion = null;
            Order order = null;

            if (obj[0].equals(ParameterDao.EQUAL)) criterion = Restrictions.eq((String) obj[1], obj[2]);
            else if (obj[0].equals(ParameterDao.NOT_EQUAL))
                criterion = Restrictions.not(Restrictions.eq((String) obj[1], obj[2]));
            else if (obj[0].equals(ParameterDao.LIKE)) criterion = Restrictions.like((String) obj[1], obj[2]);
            else if (obj[0].equals(ParameterDao.NOT_LIKE))
                criterion = Restrictions.not(Restrictions.like((String) obj[1], obj[2]));
            else if (obj[0].equals(ParameterDao.NULL)) criterion = Restrictions.isNull((String) obj[1]);
            else if (obj[0].equals(ParameterDao.NOT_NULL)) criterion = Restrictions.isNotNull((String) obj[1]);
            else if (obj[0].equals(ParameterDao.IN)) criterion = Restrictions.in((String) obj[1], (List) obj[2]);
            else if (obj[0].equals(ParameterDao.NOT_IN))
                criterion = Restrictions.not(Restrictions.in((String) obj[1], (List) obj[2]));
            else if (obj[0].equals(ParameterDao.GRATHER)) criterion = Restrictions.gt((String) obj[1], obj[2]);
            else if (obj[0].equals(ParameterDao.GRATHER_EQ)) criterion = Restrictions.ge((String) obj[1], obj[2]);
            else if (obj[0].equals(ParameterDao.LESS)) criterion = Restrictions.lt((String) obj[1], obj[2]);
            else if (obj[0].equals(ParameterDao.LESS_EQ)) criterion = Restrictions.le((String) obj[1], obj[2]);
            else if (obj[0].equals(ParameterDao.BETWEEN))
                criterion = Restrictions.between((String) obj[1], obj[2], obj[3]);
            else if (obj[0].equals(ParameterDao.ORDER_ASC)) order = Order.asc((String) obj[1]);
            else if (obj[0].equals(ParameterDao.ORDER_DESC)) order = Order.desc((String) obj[1]);
            else if (obj[0].equals(ParameterDao.OR)) {
                Criterion exp1 = null;
                Criterion exp2;
                for (Object object : (List) obj[2]) {
                    if (object == null) exp2 = Restrictions.isNull((String) obj[1]);
                    else exp2 = Restrictions.eq((String) obj[1], object);
                    if (exp1 == null) {
                        exp1 = exp2;
                    } else {
                        exp1 = Restrictions.or(exp2, exp1);
                    }
                }
                criterion = exp1;
            }

            if (criterion != null) {
                criteria1.add(criterion);
                criteria2.add(criterion);
            }

            if (order != null) criteria1.addOrder(order);

        }

        List list = new ArrayList();
        int avai = 0;
        int page = 0;
        int size = 0;
        int max = 0;

        if (parameter.isShowList()) {
            if (parameter.getMaxRows() != null && parameter.getMaxRows() > 0 && parameter.getOffside() != null) {

                ProjectionList projectionList = Projections.projectionList();
                projectionList.add(Projections.rowCount());

                criteria2.setProjection(projectionList);

                size = ((Long) getHibernateTemplate().findByCriteria(criteria2).get(0)).intValue();
                max = parameter.getMaxRows();
                page = parameter.getOffside();

                if (page < 1) page = 1;

                if (size > 0 && max > 0) {
                    avai = Math.round(size / parameter.getMaxRows());
                    int start = parameter.getMaxRows() * (page - 1);
                    if (start > size) start = size;

                    list = getHibernateTemplate().findByCriteria(criteria1, start, parameter.getMaxRows());
                } else {
                    max = 0;
                    avai = 0;
                }
            } else {
                list = getHibernateTemplate().findByCriteria(criteria1);
                size = list.size();
                max = list.size();
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

    public CurrentPage getList2(ParameterDao parameter) {
        Criteria criteria = currentSession().createCriteria(parameter.getClazz(), parameter.getAlias());

        Map<String, Criteria> map = new HashMap();
        for (Object[] obj : parameter.getMaps().values()) {
            String key = (String) obj[1];
            if (key.indexOf(".") > -1) {
                String[] arr = key.split("\\.");
                Criteria criteria1 = null;
                if (map.containsKey(arr[0])) {
                    criteria1 = (Criteria) map.get(arr[0]);
                } else {
                    criteria1 = criteria.createCriteria(arr[0], arr[0]);
                    map.put(arr[0], criteria1);
                }
                if (arr.length == 2) {
                    addCriteria(arr[1], obj, criteria1);
                } else {
                    Criteria criteria2 = null;
                    if (map.containsKey(arr[0] + "." + arr[1])) {
                        criteria2 = (Criteria) map.get(arr[0] + "." + arr[1]);
                    } else {
                        criteria2 = criteria1.createCriteria(arr[1], arr[1]);
                        map.put(arr[0] + "." + arr[1], criteria2);
                    }
                    if (arr.length == 3) {
                        addCriteria(arr[2], obj, criteria2);
                    } else {
                        Criteria criteria3 = null;
                        if (map.containsKey(arr[0] + "." + arr[1] + "." + arr[2])) {
                            criteria3 = (Criteria) map.get(arr[0] + "." + arr[1] + "." + arr[2]);
                        } else {
                            criteria3 = criteria2.createCriteria(arr[2], arr[2]);
                            map.put(arr[0] + "." + arr[1] + "." + arr[2], criteria3);
                        }
                        if (arr.length == 4) {
                            addCriteria(arr[3], obj, criteria3);
                        }
                    }
                }
            } else {
                addCriteria(key, obj, criteria);
            }
        }
        this.logger.info("criteria:" + criteria.toString());

        Object list = new ArrayList();
        int avai = 0;
        int page = 0;
        int size = 0;
        int max = 0;
        if (parameter.isShowList()) {
            if ((parameter.getMaxRows() != null) && (parameter.getMaxRows().intValue() > 0) && (parameter.getOffside() != null)) {
                ProjectionList projectionList = Projections.projectionList();
                projectionList.add(Projections.rowCount());

                criteria.setProjection(projectionList);

                size = ((Long) criteria.uniqueResult()).intValue();
                max = parameter.getMaxRows().intValue();
                page = parameter.getOffside().intValue();

                this.logger.info("size:" + size);

                criteria.setProjection(null);
                criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
                if (page < 1) {
                    page = 1;
                }
                if ((size > 0) && (max > 0)) {
                    avai = Math.round(size / parameter.getMaxRows().intValue());
                    int start = parameter.getMaxRows().intValue() * (page - 1);
                    if (start > size) {
                        start = size;
                    }
                    criteria.setFirstResult(start);
                    criteria.setMaxResults(parameter.getMaxRows().intValue());
                    list = criteria.list();
                } else {
                    max = 0;
                    avai = 0;
                }
            } else {
                list = criteria.list();
                size = ((List) list).size();
                max = ((List) list).size();
            }
        }
        this.logger.info("list:" + list);

        CurrentPage cp = new CurrentPage();
        cp.setPageItems((List) list);
        cp.setPageNumber(page);
        cp.setPagesAvailable(avai);
        cp.setTotalSize(size);
        cp.setPageSize(parameter.getMaxRows().intValue());

        return cp;
    }

    private void addCriteria(String key, Object[] obj, Criteria criteria1) {
        Criterion criterion = null;
        Order order = null;
        if (obj[0].equals(ParameterDao.EQUAL)) {
            criteria1.add(Restrictions.eq(key, obj[2]));
        } else if (obj[0].equals(ParameterDao.NOT_EQUAL)) {
            criteria1.add(Restrictions.not(Restrictions.eq(key, obj[2])));
        } else if (obj[0].equals(ParameterDao.LIKE)) {
            criteria1.add(Restrictions.like(key, obj[2]));
        } else if (obj[0].equals(ParameterDao.NOT_LIKE)) {
            criteria1.add(Restrictions.not(Restrictions.like(key, obj[2])));
        } else if (obj[0].equals(ParameterDao.NULL)) {
            criteria1.add(Restrictions.isNull(key));
        } else if (obj[0].equals(ParameterDao.NOT_NULL)) {
            criteria1.add(Restrictions.isNotNull(key));
        } else if (obj[0].equals(ParameterDao.IN)) {
            criteria1.add(Restrictions.in(key, (List) obj[2]));
        } else if (obj[0].equals(ParameterDao.NOT_IN)) {
            criteria1.add(Restrictions.not(Restrictions.in(key, (List) obj[2])));
        } else if (obj[0].equals(ParameterDao.GRATHER)) {
            criteria1.add(Restrictions.gt(key, obj[2]));
        } else if (obj[0].equals(ParameterDao.GRATHER_EQ)) {
            criteria1.add(Restrictions.ge(key, obj[2]));
        } else if (obj[0].equals(ParameterDao.LESS)) {
            criteria1.add(Restrictions.lt(key, obj[2]));
        } else if (obj[0].equals(ParameterDao.LESS_EQ)) {
            criteria1.add(Restrictions.le(key, obj[2]));
        } else if (obj[0].equals(ParameterDao.BETWEEN)) {
            criteria1.add(Restrictions.between(key, obj[2], obj[3]));
        } else if (obj[0].equals(ParameterDao.ORDER_ASC)) {
            order = Order.asc(key);
        } else if (obj[0].equals(ParameterDao.ORDER_DESC)) {
            order = Order.desc(key);
        } else if (obj[0].equals(ParameterDao.OR)) {
            Criterion exp1 = null;
            for (Object object : (List) obj[2]) {
                Criterion exp2;
                if (object == null) {
                    exp2 = Restrictions.isNull(key);
                } else {
                    exp2 = Restrictions.eq(key, object);
                }
                if (exp1 == null) {
                    exp1 = exp2;
                } else {
                    exp1 = Restrictions.or(exp2, exp1);
                }
            }
            criterion = exp1;
        }
        if (criterion != null) {
            criteria1.add(criterion);
        }
        if (order != null) {
            criteria1.addOrder(order);
        }
    }

    @Override
    public Object getObject(ParameterDao parameter) {
        List list = getArrayList(parameter, 1);
        if (list != null && !list.isEmpty()) return list.get(0);
        else return null;
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
        } catch (Exception e) {
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
        if (objects == null) objects = new Object[]{};

        return getHibernateTemplate().find(hql, objects);
    }

    @Override
    public boolean addObject(Object object) {
        try {
            getHibernateTemplate().saveOrUpdate(object);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    @Override
    public Serializable addOnlyObject(Object object) {
        try {
            return getHibernateTemplate().save(object);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public boolean deleteObject(Object object) {
        try {
            getHibernateTemplate().delete(object);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }
}
