package cashier.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeFactory;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeTools {
	
	private TimeTools(){}
	
	public static Long iso8601StringToUnixTimestamp(String datetime){
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(datetime).toGregorianCalendar().getTime().getTime();
		} catch (Exception e) {}
		return (long) 0;
	}
	/**
	 * 
	 * @param unixTimestamp must be in milliseconds.
	 * @return
	 */
	public static String unixTimestampToiso8601String(Long unixTimestamp){
		Chronology chrono = ISOChronology.getInstance();
		DateTime dateTime = new DateTime(unixTimestamp, chrono);
        return dateTime.toString();
	}
	
	public static Long httpDateTimestampToUnixTimestamp(String timestamp){
		DateTimeFormatter df = DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss 'GMT'");
		return df.withOffsetParsed().parseDateTime(timestamp).getMillis();
	}
	
}
