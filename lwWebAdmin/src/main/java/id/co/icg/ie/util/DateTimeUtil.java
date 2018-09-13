package id.co.icg.ie.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeUtil {

    public static String DDMMYYYY = "ddMMyyyy";
    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static String YYMMDDHHMMSS = "yyMMddHHmmss";
    public static String DD_MMM_YYYY = "dd MMM yyyy";
    public static String DD_MMM_YY = "dd MMM yy";
    public static String DDMMMYY = "ddMMMyy";
    public static String YYYYMMDD = "yyyyMMdd";
    public static String YYMMDD = "yyMMdd";
    public static String DDMMYY = "ddMMyy";
    public static String HHMMSS = "HHmmss";
    public static String REPORTDATE = "dd/MM/yyyy HH:mm:ss";
    public static String REPORTDATE2 = "yyyy-MM-dd HH:mm:ss";
    public static String WEBDATE = "MM/dd/yyyy";
    public static String WEBDATEKAI = "dd/MM/yyyy";

    public static final Date convertStringToDate(String date) {
        Date d = null;
        String regex = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
        Pattern pattern = Pattern.compile(regex);
        if (date != null) {
            if (date.length() == 10) {
                Matcher matcher = pattern.matcher(date);
                if (matcher.matches()){
                    d = convertStringToDateCustomized(date, "MM/dd/yyyy");
                } else {
                    d = convertStringToDateCustomized(date, "yyyy-MM-dd");
                }
            } else if (date.length() == 19) {
                d = convertStringToDateCustomized(date, "yyyy-MM-dd HH:mm:ss");
            }
        }
        return d;
    }

    public static final Date convertStringToDateCustomized(String date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        Date result;

        if (date != null) {
            try {
                result = df.parse(date);
            } catch (ParseException e) {
                result = null;
                //e.printStackTrace();
            }
            return result;
        } else {
            return null;
        }
    }

    public static final String convertDateToStringCustomized(Date date, String pattern) {
        try {
            if (date != null) {
                String dateEng = new SimpleDateFormat(pattern).format(date).toUpperCase();
                dateEng = dateEng.replaceAll("JAN", "JANUARI");
                dateEng = dateEng.replaceAll("FEB", "FEBRUARI");
                dateEng = dateEng.replaceAll("APR", "APRIL");
                dateEng = dateEng.replaceAll("MAY", "MEI");
                dateEng = dateEng.replaceAll("JUN", "JUNI");
                dateEng = dateEng.replaceAll("JUL", "JULI");
                dateEng = dateEng.replaceAll("SEP", "SEPTEMBER");
                dateEng = dateEng.replaceAll("NOV", "NOVEMBER");
                dateEng = dateEng.replaceAll("AUG", "AGUSTUS");
                dateEng = dateEng.replaceAll("OCT", "OKTOBER");
                dateEng = dateEng.replaceAll("DEC", "DESEMBER");
                return dateEng;
            } else {
                return null;
            }
        } catch (Exception e) {
            return "";
        }
    }

    public static final String convertDateToStringCustomized(Date date, String pattern, int amount) {
        try {
            date = getCustomDate(date, amount);
            return convertDateToStringCustomized(date, pattern);
        } catch (Exception e) {
            return "";
        }
    }

    public static final Date maxDateWeb(String date) {
        return maxDateWeb(date, 0);
    }

    public static final Date maxDateWeb(Date date) {
        String dateStr = convertDateToStringCustomized(date, WEBDATE);
        return convertStringToDateCustomized(dateStr + " 235959", WEBDATE + " HHmmss");
    }

    public static final Date maxDateWeb(String date, int day) {
        Date datz = convertStringToDateCustomized(date + " 235959", WEBDATE + " HHmmss");
        if (day != 0) {
            return getCustomDate(datz, day);
        } else {
            return datz;
        }
    }

    public static final Date minDateWeb(String date) {
        return minDateWeb(date, 0);
    }

    public static final Date minDateWeb(String date, int day) {
        Date datz = convertStringToDateCustomized(date + " 000000", WEBDATE + " HHmmss");
        if (day != 0) {
            return getCustomDate(datz, day);
        } else {
            return datz;
        }
    }

    public static final Date minDateWeb(Date date) {
        String dateStr = convertDateToStringCustomized(date, WEBDATE);
        return convertStringToDateCustomized(dateStr + " 000000", WEBDATE + " HHmmss");
    }

    public static final String convertStringToStringFormaterCustomized(String date, String patternFrom, String patternTo) {
        try {
            Date date2 = convertStringToDateCustomized(date, patternFrom);
            return convertDateToStringCustomized(date2, patternTo);
        } catch (Exception e) {
            return "";
        }
    }

    public static final String convertIndoDate(Date date) {
        String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
        switch (dayOfWeek) {
            case "Monday":
                dayOfWeek = "Senin";
                break;
            case "Tuesday":
                dayOfWeek = "Selasa";
                break;
            case "Wednesday":
                dayOfWeek = "Rabu";
                break;
            case "Thursday":
                dayOfWeek = "Kamis";
                break;
            case "Friday":
                dayOfWeek = "Jum'at";
                break;
            case "Saturday":
                dayOfWeek = "Sabtu";
                break;
            case "Sunday":
                dayOfWeek = "Minggu";
                break;
            default:
                break;
        }

        String dat = convertDateToStringCustomized(date, "ddMMyyyy");
        switch (dat.substring(2, 4)) {
            case "01":
                dat = dat.substring(0, 2) + " Januari " + dat.substring(4);
                break;
            case "02":
                dat = dat.substring(0, 2) + " Februari " + dat.substring(4);
                break;
            case "03":
                dat = dat.substring(0, 2) + " Maret " + dat.substring(4);
                break;
            case "04":
                dat = dat.substring(0, 2) + " April " + dat.substring(4);
                break;
            case "05":
                dat = dat.substring(0, 2) + " Mei " + dat.substring(4);
                break;
            case "06":
                dat = dat.substring(0, 2) + " Juni " + dat.substring(4);
                break;
            case "07":
                dat = dat.substring(0, 2) + " Juli " + dat.substring(4);
                break;
            case "08":
                dat = dat.substring(0, 2) + " Agustus " + dat.substring(4);
                break;
            case "09":
                dat = dat.substring(0, 2) + " September " + dat.substring(4);
                break;
            case "10":
                dat = dat.substring(0, 2) + " Oktober " + dat.substring(4);
                break;
            case "11":
                dat = dat.substring(0, 2) + " November " + dat.substring(4);
                break;
            case "12":
                dat = dat.substring(0, 2) + " Desember " + dat.substring(4);
                break;
            default:
                break;
        }

        return dayOfWeek + ", " + dat;
    }

    public static final String convertMonth(String value) {
        try {
            String tahun = "201" + value.substring(0, 1);
            String bulan = value.substring(1);

            if (bulan.equals("01")) {
                return "Januari " + tahun;
            }
            if (bulan.equals("02")) {
                return "Januari " + tahun;
            }
            if (bulan.equals("03")) {
                return "Januari " + tahun;
            }
            if (bulan.equals("04")) {
                return "Januari " + tahun;
            }
            if (bulan.equals("05")) {
                return "Januari " + tahun;
            }
            if (bulan.equals("06")) {
                return "Januari " + tahun;
            }
            if (bulan.equals("07")) {
                return "Januari " + tahun;
            }
            if (bulan.equals("08")) {
                return "Januari " + tahun;
            }
            if (bulan.equals("09")) {
                return "Januari " + tahun;
            }
            if (bulan.equals("10")) {
                return "Januari " + tahun;
            }
            if (bulan.equals("11")) {
                return "Januari " + tahun;
            }
            if (bulan.equals("12")) {
                return "Januari " + tahun;
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    public static Date getCustomDate(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.DATE, amount);
        return calendar.getTime();
    }

    public static Date getCustomYears(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.YEAR, amount);
        return calendar.getTime();
    }

    public static Date getCustomSecond(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.SECOND, amount);
        return calendar.getTime();
    }

    public static Date getMaxDateInMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }

        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.set(Calendar.DATE, maxDay);

        return calendar.getTime();
    }

    public static Date getMinDateInMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }

        calendar.set(Calendar.DATE, 1);

        return calendar.getTime();
    }

    public static Date getPrevMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    public static Date getNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    public static Date getCostumMinuteDate(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.MINUTE, amount);
        return calendar.getTime();
    }

    public static Date getCostumHoursDate(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.HOUR, amount);
        return calendar.getTime();
    }

    public static Date getCostumSecondDate(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.SECOND, amount);
        return calendar.getTime();
    }

    public static Date getCostumHourDate(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.HOUR, amount);
        return calendar.getTime();
    }

    public static String hasilWaktu(Date waktubesar, Date waktukecil) {
        long second = selisihWaktu(waktubesar, waktukecil);
        long jam = second / 3600;
        long menit = (second % 3600) / 60;
        long detik = (second % 3600) % 60;
        return jam + " jam, " + menit + " menit, " + detik + " detik";
    }

    public static Long[] secondToDate(long second) {
        long jam = second / 3600;
        long menit = (second % 3600) / 60;
        long detik = (second % 3600) % 60;
        return new Long[]{jam, menit, detik};
    }

    public static long selisihWaktu(Date waktubesar, Date waktukecil) {
        Calendar dablek = Calendar.getInstance();
        dablek.setTime(waktubesar);
        Long i1 = dablek.getTimeInMillis();
        Calendar dablek2 = Calendar.getInstance();
        dablek2.setTime(waktukecil);
        Long i2 = dablek2.getTimeInMillis();
        long result = (int) ((i1 - i2) / 1000);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(convertDateToStringCustomized(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }
}
