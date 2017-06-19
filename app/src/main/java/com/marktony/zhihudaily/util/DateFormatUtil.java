package com.marktony.zhihudaily.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public class DateFormatUtil {

    public static String ZhihuDailyDateFormat(long date) {
        String sDate;
        Date d = new Date(date + 24*60*60*1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        sDate = format.format(d);

        return sDate;
    }

    public static String DoubanDateFormat(long date){
        String sDate;
        Date d = new Date(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        sDate = format.format(d);

        return sDate;
    }

}
