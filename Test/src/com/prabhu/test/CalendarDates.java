package com.prabhu.test;

import java.util.*;
public class CalendarDates
{
  public static void main(String argv[]) {
    TimeZone tz = TimeZone.getTimeZone("GMT");
    //my locate is Asia/Shanghai(GMT+8:00), so the following code set the cal to
    //20030301T160000Z
    Calendar cal = new GregorianCalendar(2003,2,2,0,0,0);
    //cal.setTimeZone(tz);  //now the timezone change from Aisa/Shanghai to GMT
    //cal.set(Calendar.HOUR_OF_DAY,0);  //in GMT, set the hour to 0
    //so the cal should be now 20030301T000000Z
    System.out.println(     cal.get(Calendar.DAY_OF_MONTH) + "--" + cal.get(Calendar.HOUR_OF_DAY));
  }
}
