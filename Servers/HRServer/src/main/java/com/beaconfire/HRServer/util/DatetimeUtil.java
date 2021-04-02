package com.beaconfire.HRServer.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class DatetimeUtil {
    public static SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    public static SimpleDateFormat fileExtensionFormatter = new SimpleDateFormat("MM-dd-yyyy_HH:mm:ss");

    public static String getCurrentDateTime(){
        return formatter.format(new Date());
    }


    public static String get3hrAddedDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 3);
        Date newDate = calendar.getTime();
        return formatter.format(newDate);
    }

    public static String getCurrentDateTimeExtension() {
        return fileExtensionFormatter.format(new Date());
    }

    public static boolean isBeforeCurrentDate(String dateStr){
        try {
            Date dateInQuestion = formatter.parse(dateStr);
            Date currentDate = new Date();
            return dateInQuestion.before(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static int compareTimes(String date1, String date2) {
        try {
            Date d1 = formatter.parse(date1);
            Date d2 = formatter.parse(date2);
            return d1.before(d2)? -1 : (d2.before(d1)? 1 : 0);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String covert(Date date){
        return "";
    }


}
