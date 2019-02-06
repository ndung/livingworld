package id.co.icg.lw.dao.util;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterDao implements Serializable {

    public static String ORDER_ASC  = "ORDER_ASC";
    public static String ORDER_DESC = "ORDER_DESC";
    public static String EQUAL      = "EQUAL";
    public static String GRATHER    = "GRATHER";
    public static String GRATHER_EQ = "GRATHER_EQ";
    public static String LESS       = "LESS";
    public static String LESS_EQ    = "LESS_EQ";
    public static String LIKE       = "LIKE";
    public static String OR         = "OR";
    public static String IN         = "IN";
    public static String NOT_EQUAL  = "NOT_EQUAL";
    public static String NOT_LIKE   = "NOT_LIKE";
    public static String NOT_IN     = "NOT_IN";
    public static String BETWEEN    = "BETWEEN";
    public static String NULL       = "NULL";
    public static String NOT_NULL   = "NOT_NULL";

    private final Map<Integer, Object[]> maps;
    private Integer                      offside; 
    private Integer                      maxRows;
    private Class                        clazz;
    private int                          number;
    private boolean                      showList;
    private String                       alias;

    public ParameterDao(Class clazz) {
        maps          = new HashMap<>();
        number        = 0;
        maxRows       = 0;
        this.clazz    = clazz;
        this.showList = true;
    }

    public Map<Integer, Object[]> getMaps() {
        return maps;
    }

    public void setEqualsOrLikes(String fieldClass, Object value) {
        String str  = String.valueOf(value);
        String type = EQUAL;
        if(str.startsWith("%") || str.endsWith("%")) type = LIKE;

        Object[] obj = new Object[]{type, fieldClass, value};
        maps.put(number++, obj);
    }

    public void setOrs(String fieldClass, List value) {
        if(value == null || value.isEmpty()) showList = false;
        
        Object[] obj = new Object[]{OR, fieldClass, value};
        maps.put(number++, obj);
    }

    public void setNotEqualsOrLikes(String fieldClass, Object value) {
        String str  = String.valueOf(value);
        String type = NOT_EQUAL;
        if(str.startsWith("%") || str.endsWith("%")) type = NOT_LIKE;

        Object[] obj = new Object[]{type, fieldClass, value};
        maps.put(number++, obj);
    }

    public void setIn(String fieldClass, List value) {
        if(value == null || value.isEmpty()) showList = false;
        Object[] obj = new Object[]{IN, fieldClass, value};
        maps.put(number++, obj);
    }

    public void setNotIn(String fieldClass, List value) {
        if(value == null || value.isEmpty()) showList = false;
        Object[] obj = new Object[]{NOT_IN, fieldClass, value};
        maps.put(number++, obj);
    }

    public void setBetween(String fieldClass, Object startValue, Object endValue) {
        Object[] obj = new Object[]{BETWEEN, fieldClass, startValue, endValue};
        maps.put(number++, obj);
    }

    public Integer getOffside() {
        return offside;
    }

    public void setOffside(Integer offside) {
        this.offside = offside;
    }

    public Integer getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(Integer maxRows) {
        this.maxRows = maxRows;
    }

    public void setGratherThan(String fieldClass, Object value) {
        Object[] obj = new Object[]{GRATHER, fieldClass, value};
        maps.put(number++, obj);
    }

    public void setLessThan(String fieldClass, Object value) {
        Object[] obj = new Object[]{LESS, fieldClass, value};
        maps.put(number++, obj);
    }

    public void setGratherEqualsThan(String fieldClass, Object value) {
        Object[] obj = new Object[]{GRATHER_EQ, fieldClass, value};
        maps.put(number++, obj);
    }

    public void setLessEqualsThan(String fieldClass, Object value) {
        Object[] obj = new Object[]{LESS_EQ, fieldClass, value};
        maps.put(number++, obj);
    }

    public void setAscOrders(String fieldClass) {
        Object[] obj = new Object[]{ORDER_ASC, fieldClass};
        maps.put(number++, obj);
    }

    public void setDescOrders(String fieldClass) {
        Object[] obj = new Object[]{ORDER_DESC, fieldClass};
        maps.put(number++, obj);
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public void setIsNull(String fieldClass) {
        Object[] obj = new Object[]{NULL, fieldClass};
        maps.put(number++, obj);
    }

    public void setIsNotNull(String fieldClass) {
        Object[] obj = new Object[]{NOT_NULL, fieldClass};
        maps.put(number++, obj);
    }

    public boolean isShowList() {
        return showList;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}