package org.example.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String convertToDateFormate(Long milliseconds) {

        Date date = new Date(milliseconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }
}
