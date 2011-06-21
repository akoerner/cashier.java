package cashier.net;

import java.net.ProtocolException;

import cashier.exceptions.connection.ConnectionDisabledException;
import cashier.exceptions.request.InvalidRequestException;

/**
 *
 * Cashier Request objects allow for easy formation of a valid HTTP/1.1 request.  The Request object is aware of the Cashier Connection class
 * and subsequently a Cashier Request knows how to submit itself by calling the submit() method.  Once submitted a Cashier Response will be returned
 * All data fields must be set before the object is submitted to the Cashier system otherwise a  InvalidRequestException will be thrown. This class works 
 * side by side with the Cashier Connection class, Resource class and Response class.
 * 
 * @author Andrew Koerner
 * @version 1.0
 * 
 */
public class Request{
	
	/**
	 * HTTP/1.1 GET constant
	 */
	public static final String GET = "GET";
	/**
	 * HTTP/1.1 POST constant
	 */
	public static final String POST = "POST";
	/**
	 * HTTP/1.1 PUT constant
	 */
	public static final String PUT = "PUT";
	/**
	 * HTTP/1.1 DELETE constant
	 */
	public static final String DELETE = "DELETE";
	
	/**
	 * The Cashier Resource database resource constant.
	 */
	private String resource;
	/**
	 * The Cashier Resource database table constant.
	 */
	private String table;
	/**
	 * The Request body, any data to submit via HTTP/1.1 in the body to the Cashier system
	 */
	private String body = "";
	/**
	 * Partial url pointing to the location to make the request e.g., "customers" or "transactions/1" or customers/query please refer
	 * to the Cashier API documentation.
	 */
	private String resourceAddress;
	/**
	 * HTTP/1.1 compliant method e.g., GET, POST, PUT, DELETE
	 */
	private String method;
	
	public Request(String resource, String table){
		this.resource = resource;
		this.table = table;
		
	}
	
	/**
	 * <p>
	 * Finds persistent resource data within the Cashier system.
	 * </p>
	 * <p>
	 * Precondition: The connection must be enabled otherwise a 
	 * ConnectionDisabledExcepiton will be thrown.<br />
	 * Postcondition: If a resource is found matching the unique id 
	 * provided then a singular resource will be returned else null
	 * will be returned.
	 * </p>
	 * @param id the unique id of the item to be found.
	 * @param resource the cashier resource being utilized in the search.
	 * @param table the cashier table being utilized in the search.
	 *
	 * @return Resource singular Cashier resource that matches the id provided, 
	 * a resource or if nothing is found will be null.
	 * @throws ConnectionDisabledException 
	 *
	 */
	public Response submit() throws ProtocolException, ConnectionDisabledException, InvalidRequestException{
		if(this.isValid()){
			return Connection.submitRequest(this);	
		}else{
			throw new InvalidRequestException(this);
		}
		
	}
	
	/**
	 * <p>
	 * Verify if this is a valid request.
	 * </p>
	 * <p>
	 * Precondition: None <br />
	 * Postcondition: If this is a valid request then a boolean true will be returned else
	 * if some of the required data is missing false will be returned.
	 * </p>
	 * @return boolean if the request is a valid Cashier request then true will be returned 
	 * else false will be returned.  Valid meaning all data within the request is not
	 * null.  This DOES NOT verify if the body data is in a valid format or if the resource
	 * address is correct the method is verified as HTTP/1.1 compliant at the time of setting
	 * the only methods the Cashier system utilizes are: GET, PUT, POST and DELETE.
	 * @throws ConnectionDisabledException 
	 *
	 */
	public boolean isValid(){
		
		if(this.resource == null){
			return false;
		}
		if(this.table == null){
			return false;
		}
		if(this.body == null){
			return false;
		}
		if(this.resourceAddress == null){
			return false;
		}
		if(this.method == null){
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Request [resource=" + resource + ", table=" + table + ", body="
				+ body + ", requestURL=" + resourceAddress + ", method=" + method
				+ "]  isValid=" + this.isValid();
	}
	
    ///////////////////////
	//Getters and Setters//
	///////////////////////
	/**
	 * <p>
	 * accessor/getter Returns the resource
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: none
	 *</p>
	 * @return String resource
	 *
	 */
	public String getResource() {
		return this.resource;
	}
	
	/**
	 * <p>
	 * accessor/getter Returns the table
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: none
	 *</p>
	 * @return String table
	 *
	 */
	public String getTable(){
		return this.table;
	}
	
	/**
	 * <p>
	 * accessor/getter Returns the body
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: none
	 *</p>
	 * @return String body
	 *
	 */
	public String getBody() {
		return this.body;
	}
	
	/**
	 * <p>
	 * accessor/getter Returns the resourceAddress
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: none
	 *</p>
	 * @return String resourceAddress
	 *
	 */
	public String getResourceAddress(){
		return this.resourceAddress;
	}
	
	/**
	 * <p>
	 * accessor/getter Returns the method
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: none
	 *</p>
	 * @return String method
	 *
	 */
	public String getMethod() {
		return this.method;
	}

	/**
	 * <p>
	 * setter/mutator for the the resource
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: resource is set to the argument value provided
	 *</p>
	 * @param resource
	 *
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}
	
	/**
	 * <p>
	 * setter/mutator for the the table
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: table is set to the argument value provided
	 *</p>
	 * @param table
	 *
	 */
	public void setTable(String table){
		this.table = table;
	}

	/**
	 * <p>
	 * setter/mutator for the the body.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: body is set to the argument value provided
	 *</p>
	 * @param body
	 *
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
	/**
	 * <p>
	 * setter/mutator for the the resourceAddress.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: resourceAddress is set to the argument value provided
	 *</p>
	 * @param resourceAddress
	 *
	 */
	public void setResourceAddress(String resourceAddress){
		this.resourceAddress = resourceAddress;
	}
	
	/**
	 * Finds persistent resource data within the Cashier system.
	 * 
	 * Precondition: The method must be a valid HTTP/1.1 Cashier accepted 
	 * method.
	 * Postcondition: The request method will be set.
	 * 
	 * @throws ProtocolException this will be thrown if the provided method not HTTP/1.1 compliant and of one
	 * of the accepted methods that the Cashier system utilizes which are: GET, PUT, POST and DELETE
	 *
	 */
	public void setMethod(String method) throws ProtocolException{
		if(method == Request.GET || method == Request.POST || method == Request.PUT || method == Request.DELETE){
			this.method = method;
		}else{
			throw new ProtocolException();
		}
	}

}