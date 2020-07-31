package xby.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String dateToString(Date date,String patt)
    {
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }

    public static Date stringToDate(String str,String patt)throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date date = sdf.parse(str);
        return date;
    }
}
