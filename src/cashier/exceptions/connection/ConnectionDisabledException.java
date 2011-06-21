/**
 * ConnectionDisabledException.java
 *
 * @author Andrew Koerner
 * @version 1.0
 */
package cashier.exceptions.connection;

public class ConnectionDisabledException extends Exception {
	
	private static final long serialVersionUID = -3780776957220902327L;
	private String message;

	public ConnectionDisabledException(String message) {
		super(message);
		message += "Connection is disabled possible reasons:  Connection " +
				"was manually disabled, Connection constants not set.  " +
				"Please review the Cashier  API documentation.";
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
