/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.icg.ie.manager.impl;

import id.co.icg.ie.dao.util.BaseModel;
import id.co.icg.ie.manager.CacheManager;
import javax.annotation.Resource;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hatta Palino
 */
@Service("cacheManager")
public class CacheManagerImpl implements CacheManager {

    @Resource(name="ehCacheCacheManager")
    private EhCacheCacheManager ehCacheCacheManager;
    
    private static final String CLASS_CHACHE = "classCache";

    @Override
    public void setClass(String id, BaseModel clazz) {
        Cache cache = ehCacheCacheManager.getCache(CLASS_CHACHE);
        cache.put(id, clazz);
    }

    @Override
    public void delClass(String id) {
        Cache cache = ehCacheCacheManager.getCache(CLASS_CHACHE);
        cache.evict(id);
    }

    @Override
    public BaseModel getClass(String id) {
        Cache cache = ehCacheCacheManager.getCache(CLASS_CHACHE);
        return (BaseModel) cache.get(id).get();
    }

}