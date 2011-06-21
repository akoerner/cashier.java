package cashier.resource;

import java.util.ArrayList;
import cashier.exceptions.connection.ConnectionDisabledException;
import cashier.net.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Cashier data access object containing getters/accessors and mutators/setters and a toString method that formats add class data members in a string
 * for easy output.  The object contains Google Gson annotations and structure for JSON serialization and deserialization of the data.  Class resource 
 * constants are provided for easy Cashier Query object formation.
 * 
 * @author <a href="mailto:andrewkoerner.b@gmail.com"> Andrew Koerner</a>
 * @version 1.0
 * 
 */
public class Store extends Resource {

	@Expose(serialize = false, deserialize = false) public static final String TABLE = "stores";
	@Expose(serialize = false, deserialize = false) public static final String RESOURCE = "stores";
	@SerializedName(Shift.RESOURCE)
	@Expose(serialize = true, deserialize = true) private Data data;
	
	/*-Resource Constants-*/
	@Expose(serialize = false, deserialize = false) public static final String ID = "id";
	@Expose(serialize = false, deserialize = false) public static final String TITLE = "title";
	@Expose(serialize = false, deserialize = false) public static final String DESCRIPTION = "description";
	@Expose(serialize = false, deserialize = false) public static final String URL = "url";
	@Expose(serialize = false, deserialize = false) public static final String ACTIVE = "active";

	private class Data {
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Store.ID) private int id;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Store.TITLE) private String title;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Store.DESCRIPTION) private String description;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Store.URL) private String url;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Store.ACTIVE) private boolean active;
	}

	public Store() {
		super();
		this.data = new Data();
		this.setTable(Store.TABLE);
		this.setResource(Store.RESOURCE);
	}
	
	@Override
	public String toString() {
		return "Store [id=" + this.data.id + ", title=" + this.data.title
				+ ", description=" + this.data.description + ", url=" + this.data.url
				+ ", active=" + this.data.active + "]";
	}

	public static ArrayList<Resource> find(int id) throws ConnectionDisabledException {
		return Resource.find(id, Store.RESOURCE, Store.TABLE);
	}

	public static ArrayList<Resource> all() throws ConnectionDisabledException {
		return Resource.all(Store.RESOURCE, Store.TABLE);
	}

	public static ArrayList<Resource> all(int limit, int offset) throws ConnectionDisabledException {
		return Resource.all(limit, offset, Store.RESOURCE, Store.TABLE);
	}

	public static Response destroy(Integer id) throws ConnectionDisabledException {
		return Resource.destroy(id, Store.RESOURCE, Store.TABLE);
	}

	public static ArrayList<Resource> query(Query query) throws ConnectionDisabledException {
		return Resource.query(query, Store.RESOURCE, Store.TABLE);
	}
    ///////////////////////
	//Getters and Setters//
	///////////////////////
	public int getId() {
		return this.data.id;
	}

	public String getTitle() {
		return this.data.title;
	}

	public String getDescription() {
		return this.data.description;
	}

	public String getUrl() {
		return this.data.url;
	}
	
	public Store getThis(){
		return this;
	}

	public boolean isActive() {
		return this.data.active;
	}

	public void setId(int id) {
		this.data.id = id;
	}

	public void setTitle(String title) {
		this.data.title = title;
	}

	public void setDescription(String description) {
		this.data.description = description;
	}

	public void setUrl(String url) {
		this.data.url = url;
	}

	public void setActive(boolean active) {
		this.data.active = active;
	}

}