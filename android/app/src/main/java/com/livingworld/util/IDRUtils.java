package com.livingworld.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by Dizzay on 12/28/2017.
 */

public class IDRUtils {

    private static final String CURRENCY_SYMBOL = "IDR";

    public static final String toRupiah(double harga){
        //DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormat kursIndonesia = new DecimalFormat("###,###,###");
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return CURRENCY_SYMBOL+". "+kursIndonesia.format(harga).replaceAll("\\,00", "");
    }

    public static final String toAccounting(double harga){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
//        DecimalFormat kursIndonesia = new DecimalFormat("#");
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return kursIndonesia.format(harga).replaceAll("\\,00", "");
    }
}
