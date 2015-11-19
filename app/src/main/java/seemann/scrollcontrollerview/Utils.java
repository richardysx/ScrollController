package seemann.scrollcontrollerview;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangchao on 15-11-19.
 */
public class Utils {
    /**
     * get the begin and end of a week between the given start and end time
     **/
    public static ArrayList<List> getWeekBeginlist(Calendar c_begin, Calendar c_end) {
        ArrayList<List> weeklist = new ArrayList<>();
        Log.e("c_begin", c_begin.toString() + " " + c_end.toString());
        int count = 1;
        List weekbegin = new ArrayList();
        List weekend = new ArrayList();
        while (c_begin.before(c_end)) {
            if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                weekbegin.add(new java.sql.Date(c_begin.getTime().getTime()).toString().substring(5, 7)
                        + "/" + new java.sql.Date(c_begin.getTime().getTime()).toString().substring(8, 10));
            } else if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                weekend.add(new java.sql.Date(c_begin.getTime().getTime()).toString().substring(5, 7)
                        + "/" + new java.sql.Date(c_begin.getTime().getTime()).toString().substring(8, 10));
            }
//            if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//                count++;
//            }
            c_begin.add(Calendar.DAY_OF_YEAR, 1);
        }
        weeklist.add(weekbegin);
        weeklist.add(weekend);
        return weeklist;
    }

    /**
     * get every single day from begin of the year until today
     **/
    public List<Map> getDayList() {
        List<Map> dayList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.DAY_OF_YEAR, 1);
        long todayTimeInMillis = cal.getTimeInMillis();
        int dayOfYear = cal.get(Calendar.DAY_OF_YEAR) - 1;
        long beginOfYear = todayTimeInMillis - 24L * 60 * 60 * 1000 * dayOfYear;
        for (int i = 0; i < dayOfYear + 3; i++) {
            SimpleDateFormat format = new SimpleDateFormat("MM-dd");
            String d = format.format(beginOfYear + i * (24L * 60 * 60 * 1000));
            HashMap<String, Object> map = new HashMap<String, Object>();
            if (i == dayOfYear) {
                map.put("text", "Today");
            } else {
                map.put("text", d);
            }
            dayList.add(map);
        }
        return dayList;
    }

}
