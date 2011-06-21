/**
 * InvalidRequestException.java
 *
 * @author Andrew Koerner
 * @version 1.0
 */
package cashier.exceptions.request;

import cashier.net.Request;

public class InvalidRequestException extends Exception {
	
	private static final long serialVersionUID = -3780776957220902327L;
	private String message = "";

	public InvalidRequestException(Request request) {
		super("");
		String message;
		message = "The Request was could not be submited because it was incomplete possible reasons include: " +
				"No method provided or set, No request url provided, No resource or table provided or set. "+request;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
