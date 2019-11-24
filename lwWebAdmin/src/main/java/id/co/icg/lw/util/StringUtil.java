package id.co.icg.lw.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nokieng17.emvcoqr.*;
import com.nokieng17.emvcoqr.encoding.PayloadEncoding;
import com.nokieng17.emvcoqr.iso.Iso3166Countries;
import com.nokieng17.emvcoqr.iso.Iso4217Currency;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StringUtil {

    public static String ALIGN_RIGHT = "right";
    public static String ALIGN_LEFT = "left";

    public static String padding(String s, int length, String alignment) {
        if (alignment.equalsIgnoreCase("right")) {
            return String.format("%1$"  + length + "s", s);
        } else {
            return String.format("%1$-" + length + "s", s);
        }
    }

    public static String addLeadingZeroes(int i, int length) {
        return String.format("%0" + length + "d", i);
    }

    public static String addLeadingZeroes(double i, int length) {
        return addLeadingZeroes(doubleToString(i), length);
    }
    
    public static String hiddenText(String x) {
        if(x != null) {
            String a = "";
            for (int i = 0; i < x.length(); i++) {
                if(!x.substring(i,i+1).equals(" ")) a += "*";
                else a += " ";
            }
            return a;
        } else return null;
    }

    public static String forPln(String i) {
        String nol = "";
        for (int j = 0; j < i.length(); j++) {
            nol += i.substring(j, j + 1);
            if (((j + 1) % 4 == 0 && (j + 1) < i.length())) {
                nol += " ";
            }
        }
        return nol;
    }

    public static String addLeadingZeroes(String i, int length) {
        if(i == null) return "";
        String nol = "";
        for (int j = 0; j < length - i.length(); j++) {
            nol = nol + "0";
        }
        return nol + i;
    }

    public static String addHiddenValue(String i, int length) {
        String nol = "";
        for (int j = 0; j < length - i.length(); j++) {
            nol = nol + "X";
        }
        return i + nol;
    }

    public static String numberFormat(double i) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator('.');
        dfs.setDecimalSeparator(',');
        formatter.setDecimalFormatSymbols(dfs);
        return formatter.format(i);
    }

    public static String numberFormat2Decimal(double i, int decimal) {
    	String x = "";
        if (decimal > 0) {
            for (int j = 0; j < decimal; j++) {
                x += "0";
            }
            x = "." + x;
        }

        DecimalFormat formatter = new DecimalFormat("###,###,##0" + x);
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator('.');
        dfs.setDecimalSeparator(',');
        formatter.setDecimalFormatSymbols(dfs);
        String result = formatter.format(i);
//        while ((result.endsWith("0")&& result.contains(",")) ||result.endsWith(",")){
//            result = result.substring(0,result.length()-1);
//        }
        return result;
    }

    public static String doubleToString(double i) {
        NumberFormat formatter = new DecimalFormat("#########");
        return formatter.format(i);
    }

    public static String numberFormat(String i) {
        try {
            if(i == null) i = "0";
            Double value = Double.parseDouble(i);
            return numberFormat(value);
        } catch (NumberFormatException e) {
            return i;
        }
        
        
    }

    public static String numberFormat(Object i) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator('.');
        dfs.setDecimalSeparator(',');
        formatter.setDecimalFormatSymbols(dfs);
        return formatter.format(i);
    }
    
    public static String numberFormatPajak1(Object i) {
        DecimalFormat formatter = new DecimalFormat("#########.###########");
        String x = formatter.format(i);
        String[] xs = x.split("\\.");
        return xs[0];
    }
    
    public static String numberFormatPajak2(Object i) {
        DecimalFormat formatter = new DecimalFormat("#########.####");
        return formatter.format(i);
    }
    
    static char[] hexChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'a', 'b', 'c', 'd', 'e', 'f'};

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            // look up high nibble char
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);

            // look up low nibble char
            sb.append(hexChar[b[i] & 0x0f]);
        }

        return sb.toString().toUpperCase();
    }
    
    public static String terbilang(double angka) {
        
        String[] nomina = {"","satu","dua","tiga","empat","lima","enam","tujuh","delapan","sembilan","sepuluh","sebelas"};
        
        if(angka<12)
        {
          return nomina[(int)angka];
        }
        
        if(angka>=12 && angka <=19)
        {
            return nomina[(int)angka%10] +" belas ";
        }
        
        if(angka>=20 && angka <=99)
        {
            return nomina[(int)angka/10] +" puluh "+nomina[(int)angka%10];
        }
        
        if(angka>=100 && angka <=199)
        {
            return "seratus "+ terbilang(angka%100);
        }
        
        if(angka>=200 && angka <=999)
        {
            return nomina[(int)angka/100]+" ratus "+terbilang(angka%100);
        }
        
        if(angka>=1000 && angka <=1999)
        {
            return "seribu "+ terbilang(angka%1000);
        }
        
        if(angka >= 2000 && angka <=999999)
        {
            return terbilang((int)angka/1000)+" ribu "+ terbilang(angka%1000);
        }
        
        if(angka >= 1000000 && angka <=999999999)
        {
            return terbilang((int)angka/1000000)+" juta "+ terbilang(angka%1000000);
        }
        
        if(angka >= 1000000000)
        {
            return terbilang((int)angka/1000000000)+" milyar "+ terbilang(angka%1000000000);
        }
        
        return "";
    }    
    
    public final static String jSon(List lists) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object[] array = lists.toArray(new Object[lists.size()]);
            return mapper.writeValueAsString(array);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(StringUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public final static String jSon(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(StringUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void main(String[] args) throws IllegalAccessException {

        String origin = "00020101021229300012D156000000000510A93FO3230Q31280012D15600000001030812345678520441115802CN5914BEST TRANSPORT6007BEIJING64200002ZH0104最佳运输0202北京540523.7253031565502016233030412340603***0708A60086670902ME91320016A0112233449988770708123456786304A13A";

        MerchantPayload payload = MerchantPayloadFunc.CreateStatic(
                new MerchantAccountInformationMap()
                        .add(26, new MerchantAccountInformation()
                                .setGlobalUniqueIdentifier("ID.CO.LIVINGWORLD.WWW"))
                        //.addPaymentNetworkSpecifics(1, "936005030000003081")
                        //.addPaymentNetworkSpecifics(2, "08200000000227")
                        //.addPaymentNetworkSpecifics(3, "UBE"))
                        .add(50, new MerchantAccountInformation()
                                .setGlobalUniqueIdentifier("123"))
                        .add(51, new MerchantAccountInformation()
                                .setGlobalUniqueIdentifier("ID.CO.QRIS.WWW")),
                //.addPaymentNetworkSpecifics(2, "ID8200000000227")
                //.addPaymentNetworkSpecifics(3, "UBE")),
                5499,
                new Iso4217Currency.Indonesiaextends().getNumericCode(),
                Iso3166Countries.INDONESIA ,
                "one to three",
                "Tangerang Selatan","15325");
        payload.setTransactionCurrency(new Iso4217Currency.Indonesiaextends().getNumericCode());
        /**payload.setAdditionalData(
         new MerchantAdditionalData()
         .setReferenceLabel("cibinong")
         .setCustomerLabel("478408")
         .setTerminalLabel("A01"));*/
        PayloadEncoding encode = new PayloadEncoding();
        String qr = encode.GeneratePayload(payload);

        System.out.println(qr);
        System.out.println(origin);

    }
}
