/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.co.icg.lw.util;

import java.io.Serializable;

/**
 *
 * @author Hatta Palino
 */
public class SelectValue implements Serializable {
    private Object value;
    private String label;
    private String group;

    public SelectValue(Object value, String label) {
        this.value = value;
        this.label = label;
    }
    
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    
}
