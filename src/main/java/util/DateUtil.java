package util;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class DateUtil {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    public static Date toSqlDate(String str) {
        try {
            java.util.Date date = dateFormat.parse(str);
            return new Date(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return new Date(new java.util.Date().getTime());
        }
    }

    public static Time toSqlTime(String str) {
        try {
            java.util.Date date = timeFormat.parse(str);
            return new Time(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return new Time(new java.util.Date().getTime());
        }
    }
}
