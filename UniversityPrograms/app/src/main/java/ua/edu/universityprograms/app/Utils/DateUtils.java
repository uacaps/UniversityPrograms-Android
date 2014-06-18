package ua.edu.universityprograms.app.Utils;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;

/**
 * Created by vcaciuc on 6/18/2014.
 */
public class DateUtils {
    public static String timeUntil(String start, String end){
        String timeUntil = "";
        DateTime dt = new DateTime();
        DateTime st = new DateTime(start);
        DateTime et = new DateTime(end);
        int days = Days.daysBetween(dt, st).getDays();
        int hours = Hours.hoursBetween(dt, st).getHours();
        int min = Minutes.minutesBetween(dt, st).getMinutes();
        int daysLeft = Days.daysBetween(dt, et).getDays();
        int hoursLeft = Hours.hoursBetween(dt, et).getHours();
        int minLeft = Minutes.minutesBetween(dt, et).getMinutes();
        if(days > 0 ){
            timeUntil = timeUntil + "in " + days + " d";
            return timeUntil;
        }else if(hours > 0){
            timeUntil = timeUntil + "in " + hours + " h";
            return timeUntil;
        }else if(min > 0){
            timeUntil = timeUntil + "in " + min + " m";
            return timeUntil;
        }else if(daysLeft > 0 || hoursLeft > 0 || minLeft > 0){
            timeUntil = timeUntil + "now";
            return timeUntil;
        }else {
            timeUntil = timeUntil + "ended";
            return timeUntil;
        }
    }
    public static String timeUntilLongFormat(String start, String end){
        String timeUntil = "";
        DateTime dt = new DateTime();
        DateTime st = new DateTime(start);
        DateTime et = new DateTime(end);
        int days = Days.daysBetween(dt, st).getDays();
        int hours = Hours.hoursBetween(dt, st).getHours();
        int min = Minutes.minutesBetween(dt, st).getMinutes();
        int daysLeft = Days.daysBetween(dt, et).getDays();
        int hoursLeft = Hours.hoursBetween(dt, et).getHours();
        int minLeft = Minutes.minutesBetween(dt, et).getMinutes();
        if(days > 0 ){
            timeUntil = timeUntil + days + " days from now";
            return timeUntil;
        }else if(hours > 0){
            timeUntil = timeUntil + hours + " hours from now";
            return timeUntil;
        }else if(min > 0){
            timeUntil = timeUntil + min + " min from now";
            return timeUntil;
        }else if(daysLeft > 0 || hoursLeft > 0 || minLeft > 0){
            timeUntil = timeUntil + "now";
            return timeUntil;
        }else {
            timeUntil = timeUntil + "ended";
            return timeUntil;
        }
    }
}
