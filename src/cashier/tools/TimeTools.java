package cashier.tools;

import javax.xml.datatype.DatatypeFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeTools {
	
	private TimeTools(){}
	
	/**
	 * Provided a iso 8601 standard string the equivalent unix timestamp is returned.
	 * 
	 * <p>
	 * Precondition: The argument string must be a standardized iso 8601 time string.<br /> 
	 * Postcondition: None
	 * </p>
	 * 
	 * @param iso8601String a standardized iso8601 time formatted string.
	 * @return Long equivalent representation in milliseconds of the provided iso 8601 time string.
	 */
	public static Long iso8601StringToUnixTimestamp(String iso8601String){
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(iso8601String).toGregorianCalendar().getTime().getTime();
		} catch (Exception e) {}
		return (long) 0;
	}
	
	/**
	 * Converts the provided unix timestamp to an equivalent iso 8601 formatted string.
	 * 
	 * <p>
	 * Precondition: None<br /> 
	 * Postcondition: None
	 * </p>
	 * 
	 * @param unixTimestamp a unix timestamp to be converted to iso 8601 format
	 * @return String equivalent representation unix timestamp in iso 8601 time formatted string.
	 */
	public static String unixTimestampToiso8601String(Long unixTimestamp){
	
        return (new DateTime(unixTimestamp)).toDateTimeISO().toString();
	}
	
	/**
	 * Converts a HTTP response Date header formatted timestamp to an equivalent representation in unix time.
	 * 
	 * <p>
	 * Precondition: The argument string must be a an HTTP Date header formatted timestamp.<br /> 
	 * Postcondition: None
	 * </p>
	 * 
	 * @param httpDateTimestamp http Date header response string
	 * @return Long equivalent representation unix timestamp in milliseconds of the provided Http Date header string.
	 */
	public static Long httpDateTimestampToUnixTimestamp(String httpDateTimestamp){
		DateTimeFormatter df = DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss 'GMT'");
		return df.withOffsetParsed().parseDateTime(httpDateTimestamp).getMillis();
	}
	
}
