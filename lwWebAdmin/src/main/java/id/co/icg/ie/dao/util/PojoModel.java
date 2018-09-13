/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.co.icg.ie.dao.util;

import java.io.Serializable;

/**
 *
 * @author Hatta Palino
 */
public class PojoModel implements BaseModel, Serializable {
    
    protected Serializable id;

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }
    
}
