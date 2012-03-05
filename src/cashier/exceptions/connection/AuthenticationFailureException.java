/**
 * AuthenticationFailureException.java
 *
 * @author Andrew Koerner
 * @version 1.0
 */
package cashier.exceptions.connection;

public class AuthenticationFailureException extends Exception {
	
	private static final long serialVersionUID = -3780776957220902327L;
	private String message;

	public AuthenticationFailureException(String message) {
		super(message);
		message += "Falure to authenticate connection constants possible reasons:  invalid token " +
				"invalid api key, invalid api secret.  " +
				"Please review the Cashier  API documentation.";
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
