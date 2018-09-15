package id.co.icg.lw.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLInitializationException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author ICS Team
 */
public class HttpTransport {

    private HttpClient httpclient = null;
    private static Logger logger = Logger.getLogger(HttpTransport.class);
    static int socketTimeout = 10;

    public HttpTransport(int socketTimeout) {
        HttpTransport.socketTimeout = socketTimeout;
    }

    private void init() {
        httpclient = new HttpClient();
        httpclient.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
        httpclient.getParams().setParameter("http.socket.timeout", socketTimeout * 1000);
        httpclient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
    }

    public static String submit(String url, Map<String, String> queryString) {
        HttpClient httpclient = new HttpClient();

        httpclient.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
        httpclient.getParams().setParameter("http.socket.timeout", socketTimeout * 1000);
        httpclient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(5, false));
        GetMethod method = new GetMethod(url);

        if (queryString != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (String key : queryString.keySet()) {
                list.add(new NameValuePair(key, queryString.get(key)));
            }
            method.setQueryString(list.toArray(new NameValuePair[list.size()]));
        }

        String response = "";
        try {
            int statusCode = httpclient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                logger.error("Method failed: " + method.getStatusLine());
                response = "Error";
            } else {
                response = method.getResponseBodyAsString();
            }
            if (url!="https://roads.googleapis.com/v1/snapToRoads") System.out.println("RESP : " + response);
        } catch (Exception ex) {
            logger.error(ex);
        } finally {
            method.releaseConnection();
        }
        return response;
    }
        
    public String submitHttps(String url, Map<String, String> queryString){
        Boolean disableSsl = true;
        SSLConnectionSocketFactory sslSocketFactory;
        if (disableSsl) {
            SSLContext ctx;
            try {
                X509TrustManager x509TrustManager = new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    }
                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    }
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                };
                ctx = SSLContext.getInstance("TLS");
                ctx.init(new KeyManager[0], new TrustManager[]{x509TrustManager}, new SecureRandom());
            } catch (NoSuchAlgorithmException | KeyManagementException ex) {
                throw new SSLInitializationException(ex.getMessage(), ex);
            }
            sslSocketFactory = new SSLConnectionSocketFactory(
                    ctx,
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER
            );
        } else {
            sslSocketFactory = SSLConnectionSocketFactory.getSocketFactory();
        }
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslSocketFactory)
                .build();

        RequestConfig.Builder requestBuilder = RequestConfig.custom();
        requestBuilder = requestBuilder.setConnectTimeout(socketTimeout*1000);
        requestBuilder = requestBuilder.setConnectionRequestTimeout(socketTimeout*1000);

        CloseableHttpClient httpsclient = HttpClientBuilder.create()
                .setConnectionManager(new PoolingHttpClientConnectionManager(registry))
                .setDefaultRequestConfig(requestBuilder.build())
                .build();
        
        URIBuilder builder = new URIBuilder();
        String scheme;
        if(url.contains("https://")) {
            scheme="https";
        } else {
            scheme="http";
        }
        builder.setScheme(scheme).setHost(url.replace("https://","").replace("http://",""));
        if (queryString != null) {
            for (String key : queryString.keySet()) {
                builder.addParameter(key, queryString.get(key));
            }
        }
        String finalUrl=builder.toString();
        String tmpUrl=finalUrl;
        if(tmpUrl.indexOf("?", tmpUrl.indexOf("?")+1)>0){
            finalUrl = tmpUrl.substring(0,tmpUrl.indexOf("?", tmpUrl.indexOf("?")+1)) + '&' + tmpUrl.substring(tmpUrl.indexOf("?", tmpUrl.indexOf("?")+1)+1,tmpUrl.length());
        }
//        logger.info("URL:" + finalUrl);
        HttpPost httpPost = new HttpPost(finalUrl);
        HttpEntity entity;
        CloseableHttpResponse resp;
        String response = "";
        try {
            resp = httpsclient.execute(httpPost);
            if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                logger.error("Method failed (" + builder.toString() + "): " + resp.getStatusLine());
                response = "Error";
            } else {
                entity = resp.getEntity();
                response = EntityUtils.toString(entity, "UTF-8");
            }
//            logger.info("RESP : " + response);
        } catch (IOException ex) {
            logger.error(ex);
        } finally {
            try {
                httpsclient.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(HttpTransport.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return response;
    }
    
    public static void main(String[] args) {
    }
}