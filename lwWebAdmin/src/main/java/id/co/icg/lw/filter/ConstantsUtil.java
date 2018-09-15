/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.co.icg.lw.filter;

/**
 *
 * @author Fauzi Marjalih
 */
public class ConstantsUtil {
    public static String MENU_LOGIN    = "MENU_LOGIN";
    public static String MENU_UNSECURE = "MENU_UNSECURE";
    public static String BACK_URL_PAGE = "BACK_URL_PAGE";
    
    public static String SESSION_USER  = "SESSION_USER";
    public static String SESSION_LINK  = "SESSION_LINK";

    public static int RS_TYPE_RESELLER  = 0;
    public static int RS_TYPE_SALES_OFFLINE  = 1;
    public static int RS_TYPE_SALES_FREELANCE  = 2;
    public static int RS_TYPE_SALES_ONLINE  = 3;
    
    public static int RS_STATUS_NOT_LOGIN_YET  = -1;
    public static int RS_STATUS_NOT_ACTIVATED_YET  = 0;
    public static int RS_STATUS_ACTIVE  = 1;
    public static int RS_STATUS_DEPOSIT_REFUND_PROBLEM  = 2;
    public static int RS_STATUS_WRONG_PASSWORD  = 3;
    public static int RS_STATUS_OVERDUE_LIABILITIES = 4;
    public static int RS_STATUS_NOT_CHANGE_PASSWORD = 5;
    public static int RS_STATUS_INACTIVE_9 = 9;
    public static int RS_STATUS_INACTIVE_10 = 10;

    public static int LOGIN_STATUS_ACTIVE  = 1;
    public static int LOGIN_STATUS_DEACTIVE  = 0;
    public static int LOGIN_STATUS_FORGOT_PASSWORD  = 3;

    public static String PATH_UPLOAD_SUB_IRELOADENGINE = "/ireloadengine";
    public static String PATH_UPLOAD_SUB_MESSENGER = "/messenger";
    public static String PATH_UPLOAD_SUB_ADVERTISING = "/advertising";
    public static String PATH_UPLOAD_SUB_IRELOADNET = "/ireloadnet";
}
