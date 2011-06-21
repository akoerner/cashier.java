package cashier.net;

import java.io.IOException;
import java.util.ArrayList;
import cashier.exceptions.connection.ConnectionDisabledException;
import cashier.exceptions.request.InvalidRequestException;
import cashier.resource.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

/**
*
* This class is the base class for all Cashier database resources.  Methods to save, load objects from/to the Cashier database convert to/from JSON.
* This class works side by side with the Cashier Connection class, Request class and Response class.
* 
* @author <a href="mailto:andrewkoerner.b@gmail.com"> Andrew Koerner</a>
* @version 1.0
* 
*/
public abstract class Resource{ 

	@Expose(serialize = false, deserialize = false) protected String table;
	@Expose(serialize = false, deserialize = false) protected String resource;
	
	/* -- Constructors -- */
	 
	/**
	  * default, Creates a new <code>Resource</code> instance.
	  **/
	public Resource(){}

	/**
	 * <p>
	 * Submits/saves persistent resource data to the Cashier API
	 * </p>
	 * <p><br />
	 * Precondition: The connection must be enabled otherwise a 
	 * ConnectionDisabledExcepiton will be thrown.<br />
	 * Postcondition: Data present in the resource at the time of calling save
	 * will be persisted within the Cashier system.
	 * </p>
	 * @param resource the resource to be saved e.g., a Cashier Customer
	 * @throws ConnectionDisabled
	 *             The connection must be enabled by calling
	 *             Connection.setConnectionConstants.
	 */
	public static <T> Response save(T resource) throws ConnectionDisabledException{
		Request request = new Request(((Resource)resource).getResource(), ((Resource)resource).getTable());
		request.setBody(Resource.toJSON(resource));
		
		//this needs to be refactored
		String id;
		if(request.getBody().contains("\"id\":")){
			String[] tmpStr = (request.getBody().split("\"id\":"));
			id = (tmpStr[1].split(",")[0]);
		}else{
			id = "0";
		}
		/////////////////////////////
		try{
			if(id.equals("0")){
				request.setResourceAddress(request.getTable());
				request.setMethod(Request.POST);
			}else{
				request.setResourceAddress(request.getTable()+"/"+id);
				request.setMethod(Request.PUT);
				
				
			}
			try {
			
				return request.submit();
			} catch (InvalidRequestException invalidRequest) {
				System.out.println(invalidRequest.getMessage());
			}
		}catch(java.net.ProtocolException e) {}
		return new Response();
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
	 * 
	 * @throws ConnectionDisabledException 
	 *
	 */
	public static ArrayList<Resource> find(int id, String resource, String table) throws ConnectionDisabledException {
		Request request = new Request(resource, table);
		try {
			request.setMethod(Request.GET);
			request.setResourceAddress(request.getTable()+"/"+id);
			ArrayList<Resource> tempResource = Resource.fromJSON(request.submit().getBody());
			if(tempResource != null){
				if(tempResource.size() == 1){
					return tempResource;
				}
			}
			return null;
		}catch(InvalidRequestException invalidRequest){
			System.out.println(invalidRequest.getMessage());
		} catch (java.net.ProtocolException e) {}
		
		return null;
	}
	
	/**
	 * <p>
	 * Finds all persistent resource data within the Cashier system matching
	 * limit and offset criteria provided as parameters.
	 * </p>
	 * <p>
	 * Precondition: The connection must be enabled otherwise a 
	 * ConnectionDisabledExcepiton will be thrown. <br />
	 * Postcondition: If a resource is found matching the unique id 
	 * provided then a singular resource will be returned else null
	 * will be returned.
	 * </p>
	 * @param limit the number of resources to return.
	 * @param offset the starting resource to return
	 * @param resource the cashier resource being utilized in the search.
	 * @param table the cashier table being utilized in the search.
	 *
	 * @return ArrayList<Resource> an array list containing all resources persistent
	 * within the Cashier system will be returned matching the provided criteria of 
	 * limit and offset.
	 * 
	 * @throws ConnectionDisabledException 
	 *
	 */
	public static ArrayList<Resource> all(int limit, int offset, String resource, String table) throws ConnectionDisabledException {
		Request request = new Request(resource, table);
		try {
			request.setMethod(Request.GET);
			request.setResourceAddress(request.getTable()+"/?limit="+limit+"&offset="+offset);
			return Resource.fromJSON(request.submit().getBody());
		}catch(InvalidRequestException invalidRequest){
			System.out.println(invalidRequest.getMessage());
		} catch (java.net.ProtocolException e) {}
		return null;

	}

	/**
	 * <p>
	 * Finds all persistent resource data within the Cashier system.
	 * </p>
	 * <p>
	 * Precondition: The connection must be enabled otherwise a 
	 * ConnectionDisabledExcepiton will be thrown. <br />
	 * Postcondition: all persistent data matching the request resource will be
	 * provided in the form of an ArrayList containing all resources of the specified type.
	 * If none exist then null will be returned.
	 * </p>
	 * @param resource the cashier resource being utilized in the search.
	 * @param table the cashier table being utilized in the search.
	 *
	 * @return ArrayList<Resource> an array list containing all resources persistent
	 * within the Cashier or if none exist then null will be returned.
	 * 
	 * @throws ConnectionDisabledException -
	 *
	 */
	public static ArrayList<Resource> all(String resource, String table) throws ConnectionDisabledException {
		Request request = new Request(resource, table);
		try {
			request.setMethod(Request.GET);
			request.setResourceAddress(request.getTable());
			return Resource.fromJSON(request.submit().getBody());
		}catch(InvalidRequestException invalidRequest){
			System.out.println(invalidRequest.getMessage());
		} catch (java.net.ProtocolException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Finds all persistent resource data within the Cashier system fitting the provided Cashier Query.
	 * 
	 * Precondition: The connection must be enabled otherwise a 
	 * ConnectionDisabledExcepiton will be thrown and the query must be a valid query.
	 * Postcondition: all persistent data matching the request resource will be
	 * provided in the form of an ArrayList containing all resources of the specified type.
	 * If none exist then null will be returned.
	 * 
	 * @param query
	 * @param resource the cashier resource being utilized in the search.
	 * @param table the cashier table being utilized in the search.
	 *
	 * @return ArrayList<Resource> an array list containing all resources persistent
	 * within the Cashier or if none exist then null will be returned.
	 * 
	 * @throws ConnectionDisabledException 
	 *
	 */
	public static ArrayList<Resource> query(Query query , String resource, String table) throws ConnectionDisabledException {

		Request request = new Request(resource, table);
		StringBuilder stringBuilder = new StringBuilder("{\"query\":{\"statement\":\""+query.getSqlPreparedStatement()+"\",\"params\":[");
		if(query.hasNextPreparedValue()){
			stringBuilder.append("\""+query.getNextPreparedValue().getValue()+"\"");
		}
		
		while(query.hasNextPreparedValue()){
			stringBuilder.append(", \""+query.getNextPreparedValue().getValue()+"\"");
		}
		stringBuilder.append("],\"limit\":"+query.getLimit()+", \"offset\":"+query.getOffset()+"}}");
		System.out.println(stringBuilder.toString());
		
		
		try {
			System.out.println(query);
			request.setBody(stringBuilder.toString());
			request.setMethod(Request.POST);
			request.setResourceAddress(request.getTable()+"/query");
			return Resource.fromJSON(request.submit().getBody());
		}catch(InvalidRequestException invalidRequest){
			System.out.println(invalidRequest.getMessage());
		} catch(IOException ioe) {}
		
		return null;
	}

	/**
	 * <p>
	 * Destroys or deletes the persistent resource within the Cashier system
	 * matching the provided resource type and unique id.
	 * </p>
	 * <p>
	 * Precondition: The connection must be enabled otherwise a 
	 * ConnectionDisabledExcepiton will be thrown.<br />
	 * Postcondition: If a resource is found matching the unique id 
	 * provided then it will be deleted or destroyed and will no longer
	 * persist within the Cashier system.
	 * </p>
	 * @param id the unique id of the item to deleted or destroyed.
	 * @param resource the cashier resource being utilized in the search.
	 * @param table the cashier table being utilized in the search.
	 *
	 * @return Resource singular Cashier resource that matches the id provided, 
	 * a resource or if nothing is found will be null.
	 * 
	 * @throws ConnectionDisabledException 
	 *
	 */
	public static Response destroy(Integer id, String resource, String table) throws ConnectionDisabledException {
		Request request = new Request(resource, table);
		try {
			request.setMethod(Request.DELETE);
			request.setResourceAddress(request.getTable()+"/"+id);
			return request.submit();
		}catch(InvalidRequestException invalidRequest){
			System.out.println(invalidRequest.getMessage());
		} catch (java.net.ProtocolException e) {
		}
		return null;

	}

	/**
	 * <p>
	 * Takes a JSON string in the Cashier API format and maps it to
	 * its java equivalent Cashier API Resource data access objects.
	 * </p>
	 * <p>
	 * Precondition: The JSON string must be a singular or an array of 
	 * Javascript objects in a valid Cashier API resource format.<br />
	 * Postcondition: An ArrayList containing one or multiple Cashier Resource
	 * objects will be returned.
	 * </p>
	 * @param json A valid JSON, Cashier API Resource formatted string.
	 *
	 * @return ArrayList<Resource> An ArrayList of java Cashier API Resource 
	 * data access objects equivalent to the provided parameter JSON string.
	 *
	 */
	public static ArrayList<Resource> fromJSON(String json){
		
		if(json == null || json.equals("")){
			return new ArrayList<Resource>();
		}
		Gson gson = new Gson();
		ArrayList<Resource> resources = new ArrayList<Resource>();
		Resource[] resourceArray = null;
		String resource = "";
		if(json.contains(":")){
			resource = json.split(":")[0];
		}
		
		if(!((json.indexOf("[") == 0))){
			json = "[" + json + "]";
		}
		
		if(resource.contains(Customer.RESOURCE)){
			resourceArray = gson.fromJson(json, Customer[].class);
		}else if(resource.contains(Entry.RESOURCE)){
			resourceArray = gson.fromJson(json, Entry[].class);
		}else if(resource.contains(Invoice.RESOURCE)){
			resourceArray = gson.fromJson(json, Invoice[].class);
		}else if(resource.contains(Item.RESOURCE)){
			resourceArray = gson.fromJson(json, Item[].class);
		}else if(resource.contains(Line.RESOURCE)){
			resourceArray = gson.fromJson(json, Line[].class);
		}else if(resource.contains(Location.RESOURCE)){
			resourceArray = gson.fromJson(json, Location[].class);
		}else if(resource.contains(Person.RESOURCE)){
			resourceArray = gson.fromJson(json, Person[].class);
		}else if(resource.contains(Shift.RESOURCE)){
			resourceArray = gson.fromJson(json, Shift[].class);
		}else if(resource.contains(Store.RESOURCE)){
			resourceArray = gson.fromJson(json, Store[].class);
		}else if(resource.contains(Ticket.RESOURCE)){
			resourceArray = gson.fromJson(json, Ticket[].class);
		}else if(resource.contains(Till.RESOURCE)){
			resourceArray = gson.fromJson(json, Till[].class);
		}else if(resource.contains(Timecard.RESOURCE)){
			resourceArray = gson.fromJson(json, Timecard[].class);
		}else if(resource.contains(Transaction.RESOURCE)){
			resourceArray = gson.fromJson(json, Transaction[].class);
		}else if(resource.contains(Unit.RESOURCE)){
			resourceArray = gson.fromJson(json, Unit[].class);
		}else if(resource.contains(User.RESOURCE)){
			resourceArray = gson.fromJson(json, User[].class);
		}
		
		if(resourceArray != null){
			for(int resourceIndex = 0; resourceIndex< resourceArray.length; resourceIndex++){
				resources.add(resourceArray[resourceIndex]);
			}
		}
		
		return resources;
		
	}
	
	/**
	 * <p>
	 * Takes a singular java Cashier API Resource data access object and maps it to 
	 * an equivalent JSON valid string.
	 * </p>
	 * <p>
	 * Precondition: None<br />
	 * Postcondition: A singular JSON valid string will be returned equivalent
	 * to the parameter provided Cashier API Resource data access object.
	 * </p>
	 * @param resource Cashier API Resource data access object.
	 *
	 * @return resource the resource to be converted to JSON.
	 *
	 */
	public static <T> String toJSON(T resource){
		GsonBuilder gsonBuilder = new GsonBuilder();
		
		String res = ((Resource)resource).getResource();
		
		
		if(res.contains(Customer.RESOURCE)){
			return gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().toJson((Customer)resource);
		}else if(res.contains(Entry.RESOURCE)){
			return gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().toJson((Entry)resource);
		}else if(res.contains(Invoice.RESOURCE)){
			return gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().toJson((Invoice)resource);
		}else if(res.contains(Item.RESOURCE)){
			return gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().toJson((Item)resource);
		}else if(res.contains(Line.RESOURCE)){
			return gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().toJson((Line)resource);
		}else if(res.contains(Location.RESOURCE)){
			return gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().toJson((Location)resource);
		}else if(res.contains(Person.RESOURCE)){
			return gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().toJson((Person)resource);
		}else if(res.contains(Shift.RESOURCE)){
			return gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().toJson((Shift)resource);
		}else if(res.contains(Store.RESOURCE)){
			return gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().toJson((Store)resource);
		}else if(res.contains(Ticket.RESOURCE)){
			return gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().toJson((Ticket)resource);
		}else if(res.contains(Till.RESOURCE)){
			return gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().toJson((Till)resource);
		}else if(res.contains(Timecard.RESOURCE)){
			return gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().toJson((Timecard)resource);
		}else if(res.contains(Transaction.RESOURCE)){
			return gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().toJson((Transaction)resource);
		}else if(res.contains(Unit.RESOURCE)){
			return gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().toJson((Unit)resource);
		}else if(res.contains(User.RESOURCE)){
			return gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().toJson((User)resource);
		}
		
		return "";
	}
	
	public static ArrayList<Resource> fromXML(String xml){
		// TODO implement
		return null;
	}
	
	public static String toXML(Resource resource){
		// TODO implement 
		return null;
	}
	
	/**
	 * <p>
	 * Returns the last insert or create id .  If an object was created and the HTTP response status
	 * code was "201" then a nonzero number representing the id of the object will be returned.
	 * If an object was not created or the most recent request was not a insert or create then 
	 * 0 will be returned.  This must be called immediately following a creation request in order
	 * to obtain the insert id.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: The unique id representing the newly created object will be returned if the
	 * most recent request was a creation or insertion request.
	 *</p>
	 * @return int id number of the most recently created object.
	 *
	 */
	public static int getLastInsertId(){
		
		System.out.println(Connection.lastResponse.getStatusCode());
		if(Connection.lastResponse.getStatusCode().equals("201")){
			String json = Connection.lastResponse.getBody();
			Resource resource = Resource.fromJSON(json).get(0);
			String resourceClass = resource.getClass().toString();
			
			if(resourceClass.contains("Customer")){
				return ((Customer) resource).getId();
			}else if(resourceClass.contains("Entry")){
				return ((Entry) resource).getId();
			}else if(resourceClass.contains("Invoice")){
				return ((Invoice) resource).getId();
			}else if(resourceClass.contains("Item")){
				return ((Item) resource).getId();
			}else if(resourceClass.contains("Line")){
				return ((Line) resource).getId();
			}else if(resourceClass.contains("Location")){
				return ((Location) resource).getId();
			}else if(resourceClass.contains("Person")){
				return ((Person) resource).getId();
			}else if(resourceClass.contains("Shift")){
				return ((Shift) resource).getId();
			}else if(resourceClass.contains("Store")){
				return ((Store) resource).getId();
			}else if(resourceClass.contains("Ticket")){
				return ((Ticket) resource).getId();
			}else if(resourceClass.contains("Till")){
				return ((Till) resource).getId();
			}else if(resourceClass.contains("Timecard")){
				return ((Timecard) resource).getId();
			}else if(resourceClass.contains("Transaction")){
				return ((Transaction) resource).getId();
			}else if(resourceClass.contains("Unit")){
				return ((Unit) resource).getId();
			}else if(resourceClass.contains("User")){
				return ((User) resource).getId();
			}
			return 0;
		}else{
			return 0;
		}
	}
	
    ///////////////////////
	//Getters and Setters//
	///////////////////////
	/**
	 * <p>
	 * accessor/getter returns resource table as a String.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: none
	 *</p>
	 * @return String the resource table.
	 *
	 */
	protected String getTable() {
		return this.table;
	}
	/**
	 * <p>
	 * accessor/getter returns resource database resource name as a String.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: none
	 *</p>
	 * @return String the resource table.
	 *
	 */
	protected String getResource() {
		return this.resource;
	}
	/**
	 * <p>
	 * setter/mutator for the the resource table.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: table is set to the argument value provided
	 *</p>
	 * @param table table name of the resource.
	 *
	 */
	public void setTable(String table) {
		this.table = table;
	}
	/**
	 * <p>
	 * setter/mutator for the the resource database resource name.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: resource is set to the argument value provided
	 *</p>
	 * @param resource database resource name.
	 *
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}

}
