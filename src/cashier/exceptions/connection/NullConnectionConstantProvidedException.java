/**
 * NullConnectionConstantProvidedException.java
 *
 * @author Andrew Koerner
 * @version 1.0
 */
package cashier.exceptions.connection;

public class NullConnectionConstantProvidedException  extends Exception {
	
	private static final long serialVersionUID = -1406826280511132882L;
	private String message;
	
	public NullConnectionConstantProvidedException(String rootUrl, String apiKey, String apiSecret, String token){
		super();
		String message = "Null connection constant provided conneciton constants must not be null. Please provide" +
				"not null values for the following constants: ";
		if(rootUrl == null){
			message += "rootUrl ";
		}
		if(apiKey == null){
			message += "apiKey ";
		}
		if(apiSecret == null){
			message += "apiSecret ";
		}
		if(token == null){
			message += "token";
		}
		
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
