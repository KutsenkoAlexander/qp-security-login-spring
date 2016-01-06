package ua.vza.kay.qp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Aleksandr on 21.07.15.
 */
public class UnixTimeConvert {
    //Convert unixtime to dd.MM.yyyy
    public static String unixTimeToData(long unixTime) {
        Date date = new Date(unixTime * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Kyiv"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
}
