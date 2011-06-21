package cashier.net;

/**
*
* Cashier Response objects are formulated when an HTTP Casher Request is submitted.  The response will contain the message data from the server and the
* HyperText Transfer Protocol (HTTP/1.1) response status code. This class works side by side with the Cashier Connection class, Request class and Resource class.
* 
* @author <a href="mailto:andrewkoerner.b@gmail.com"> Andrew Koerner</a>
* @version 1.0
* 
*/
public class Response {

	/**
	 * The Response body, any data received from Cashier system
	 */
	private String body;
	/**
	 * HyperText Transfer Protocol (HTTP/1.1) response status code.  This is the status code returned by the Cashier server.
	 */
	private String statusCode;
	
	/* -- Constructors -- */
	 
	/**
	  * default, Creates a new <code>Response</code> instance.
	  **/
	public Response(){}
	
	/**
	  * Creates a new <code>Response</code> instance.
      * @param body The Response body, any data received from Cashier system
	  * @param statusCode HyperText Transfer Protocol (HTTP/1.1) response status code.  This is the status code returned by the Cashier server.
	  **/
	public Response(String body, String statusCode) {
		this.body = body;
		this.statusCode = statusCode;
	}
	
	/**
	 * <p>
	 * Simple check to verify that the body has content.  If the body has content
	 * then true is returned else if the body is empty or has no object then false 
	 * is returned.
	 * </p>
	 * <p>
	 * Precondition: None <br />
	 * Postcondition: None
	 * </p>
	 * @return Boolean if the body has content then true is returned.
	 * 
	 */
	public boolean bodyIsEmpty(){
		if(body == null){
			return true;
		}
		if(body.length() < 2){
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Response [body=" + body + ", statusCode=" + statusCode + "]";
	}
	
    ///////////////////////
	//Getters and Setters//
	///////////////////////
	/**
	 * <p>
	 * accessor/getter Returns body.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: none
	 *</p>
	 * @return body
	 *
	 */
	public String getBody() {
		return this.body;
	}
	
	/**
	 * <p>
	 * accessor/getter Returns statusCode
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: none
	 *</p>
	 * @return statusCode
	 *
	 */
	public String getStatusCode() {
		return this.statusCode;
	}
	
	/**
	 * <p>
	 * setter/mutator for body.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: body will be set to the argument provided
	 *</p>
	 * @param body
	 *
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
	/**
	 * <p>
	 * setter/mutator for statusCode
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: statusCode is set to the argument value provided
	 *</p>
	 * @param statusCode
	 *
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}	
	
}
