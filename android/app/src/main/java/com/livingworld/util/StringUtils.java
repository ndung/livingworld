package com.livingworld.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dizzay on 12/28/2017.
 */

public class StringUtils {

    public static final boolean isNullOrEmpty(String value){
        if(value == null || value.isEmpty() || value.equals("") || value.equals("-")){
            return true;
        }else {
            return false;
        }
    }


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isEmailValid(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public static boolean isPasswordValid(String password, boolean isDigit, boolean isCase, int minLength, int maxLength) {

        String PASSWORD_PATTERN = "";

        if (isDigit) PASSWORD_PATTERN += "(?=.*\\d)";
        if (isCase) PASSWORD_PATTERN += "(?=.*[a-z])";
        PASSWORD_PATTERN += ".{" + minLength + "," + maxLength + "}";
        PASSWORD_PATTERN = "(" + PASSWORD_PATTERN + ")";

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static final List<String> PHONE_NUMBER_PREFIXES = Arrays.asList("0811", "0812", "0813", "0821", "0822", "0823", "0851", "0852", "0853",
            "0855", "0856", "0857", "0858", "0814", "0815", "0816",
            "0817", "0818", "0819", "0859", "0877", "0878", "0831", "0832", "0838",
            "0895", "0896", "0897", "0898", "0899",
            "0881", "0882", "0883", "0884", "0885", "0886", "0887", "0888", "0889");

    public static boolean isMobilePhoneNumberValid(String phoneNumber){
        return PHONE_NUMBER_PREFIXES.contains(phoneNumber.substring(0,4));
    }
}
