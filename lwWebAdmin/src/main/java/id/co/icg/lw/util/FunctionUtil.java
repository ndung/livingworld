/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.icg.lw.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author ICG-PRG01
 */
public class FunctionUtil {

    public final static Date stringToDate(String date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date d;
        try {
            d = formatter.parse(date);
        } catch (ParseException ex) {
            System.out.println("Error stringToDate:" + ex.getMessage());
            return null;
        }
        return d;
    }

    public final static Date stringToDate(String date) {
        Date d = null;
        if (date != null) {
            if (date.length() == 10) {
                d = stringToDate(date, "yyyy-MM-dd");
            } else if (date.length() == 19) {
                d = stringToDate(date, "yyyy-MM-dd HH:mm:ss");
            }
        }
        return d;
    }

    public final static String dateTimeFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            return sdf.format(date);
        } else {
            return "-";
        }
    }

    public final static String jSon(List lists) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object[] array = lists.toArray(new Object[lists.size()]);
            return mapper.writeValueAsString(array);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public final static String jSon(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static Map<String, String> jsonMaps(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, Map.class);
        } catch (IOException ex) {
            return null;
        }
    }
    
    public final static String numberToAccounting(Object num) {
        return num == null ? "-" : NumberFormat.getNumberInstance(Locale.ITALY).format(num);
    }

    public final static String numberToString(Object num) {
        return num == null ? "" : num.toString();
    }

    public final static String formatSeparator(String text, String insert, int period) {
        Pattern p = Pattern.compile("(.{" + period + "})", Pattern.DOTALL);
        Matcher m = p.matcher(text);
        return m.replaceAll("$1" + insert);
    }

}
