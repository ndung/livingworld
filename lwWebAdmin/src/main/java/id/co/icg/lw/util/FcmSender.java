/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.icg.lw.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import id.co.icg.lw.dao.model.Message;
import org.apache.log4j.Logger;

/**
 *
 * @author Hatta Palino
 */
public class FcmSender {

    private final Logger logger = Logger.getLogger(this.getClass());

    private final String url = "https://fcm.googleapis.com/fcm/send";
    private final String googleKeyApi = "AIzaSyA_C6xcAZnbBFPD1KiUNGie9VKqM6nYHa4";

    public String sendMessage(String title, String message, String receiver) {
        Message msg = new Message();
        msg.setUser(receiver);
        msg.setTopicId(receiver);
        msg.setMessage(message);
        msg.setCreated(new Date());
        msg.setSelf(false);
        Map<String, Object> data2 = new LinkedHashMap<>();
        data2.put("title", title);
        data2.put("is_background", false);
        data2.put("flag", 1);
        data2.put("data", message);
        return sendReceiveFirebase(data2, receiver);
    }

    private synchronized String sendReceiveFirebase(Map<String, Object> data2, String topic) {
        try {

            Map<String, Object> data1 = new LinkedHashMap<>();
            data1.put("to", "/topics/" + topic);
            data1.put("data", data2);

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            String json = gson.toJson(data1);
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "key=" + googleKeyApi);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            conn.getOutputStream().write(json.getBytes("UTF-8"));
            conn.getInputStream();

            return conn.getResponseMessage();
        } catch (MalformedURLException ex) {
            logger.error("error", ex);
        } catch (IOException ex) {
            logger.error("error", ex);
        }
        return "ERROR";
    }

}
