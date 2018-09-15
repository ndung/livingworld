/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.icg.lw.manager;

import id.co.icg.lw.dao.util.BaseModel;

/**
 *
 * @author Hatta Palino
 */
public interface CacheManager {
    void setClass(String id, BaseModel clazz);
    void delClass(String id);
    BaseModel getClass(String id);
}
