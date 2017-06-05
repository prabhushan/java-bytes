import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import junit.framework.TestCase;

public class UnitTestcase extends TestCase {
	@Test
	public void testTimeZonesWithCalendar() throws ParseException {
	    assertEquals(0L, newCalendarInstanceMillis("GMT").getTimeInMillis());
	    System.out.println(newCalendarInstanceMillis("GMT").getTimeInMillis());
	    assertEquals(TimeUnit.HOURS.toMillis(-9), newCalendarInstanceMillis("Japan").getTimeInMillis());
	    assertEquals(TimeUnit.HOURS.toMillis(10), newCalendarInstanceMillis("Pacific/Honolulu").getTimeInMillis());
	    System.out.println(newCalendarInstanceMillis("Japan").getTimeInMillis());
	    System.out.println(newCalendarInstanceMillis("Pacific/Honolulu").getTimeInMillis());
	    Calendar epoch = newCalendarInstanceMillis("GMT");
	    System.out.println("Epoch + GMT timezone change-->"+epoch.getTime().toGMTString());

	    Calendar epoch1 = newCalendarInstanceMillis("GMT");
	    epoch1.setTimeZone(TimeZone.getTimeZone("Japan"));
	    assertEquals(TimeUnit.HOURS.toMillis(-9), epoch1.getTimeInMillis());
	    System.out.println("Epoch1 + Japan timezone change-->"+epoch1.getTime().toGMTString());
	}
	 
	private Calendar newCalendarInstanceMillis(String timeZoneId) {
	    Calendar calendar = new GregorianCalendar();
	    calendar.set(Calendar.YEAR, 1970);
	    calendar.set(Calendar.MONTH, 0);
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    calendar.setTimeZone(TimeZone.getTimeZone(timeZoneId));
	    return calendar;
	}
}
