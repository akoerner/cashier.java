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
public class Location extends Resource {

	@Expose(serialize = false, deserialize = false) public static final String TABLE = "locations";
	@Expose(serialize = false, deserialize = false) public static final String RESOURCE = "location";
	@SerializedName(Location.RESOURCE)
	@Expose(serialize = true, deserialize = true) private Data data;
	
	/*-Resource Constants-*/
	@Expose(serialize = false, deserialize = false) public static final String ID = "id";
	@Expose(serialize = false, deserialize = false) public static final String STORE_ID = "store_id";
	@Expose(serialize = false, deserialize = false) public static final String PARENT_ID = "parent_id";
	@Expose(serialize = false, deserialize = false) public static final String TITLE = "title";
	@Expose(serialize = false, deserialize = false) public static final String DESCRIPTION = "description";
	@Expose(serialize = false, deserialize = false) public static final String ACTIVE = "active";
	
	private class Data {
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Location.ID) public int id;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Location.STORE_ID) public int storeId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Location.PARENT_ID) public int parentId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Location.TITLE) public String title;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Location.DESCRIPTION) public String description;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Location.ACTIVE) public boolean active;
	}

	public Location() {
		super();
		this.data = new Data();
		this.setTable(Location.TABLE);
		this.setResource(Location.RESOURCE);
	}

	public static ArrayList<Resource> find(int id) throws ConnectionDisabledException {
		return Resource.find(id, Location.RESOURCE, Location.TABLE);
	}

	public static ArrayList<Resource> all() throws ConnectionDisabledException {
		return Resource.all(Location.RESOURCE, Location.TABLE);
	}

	public static ArrayList<Resource> all(int limit, int offset) throws ConnectionDisabledException {
		return Resource.all(limit, offset, Location.RESOURCE, Location.TABLE);
	}

	public static Response destroy(int id) throws ConnectionDisabledException {
		return Resource.destroy(id, Location.RESOURCE, Location.TABLE);
	}

	public static ArrayList<Resource> query(Query query) throws ConnectionDisabledException {
		return Resource.query(query, Location.RESOURCE, Location.TABLE);
	}
    ///////////////////////
	//Getters and Setters//
	///////////////////////	
	public int getId() {
		return this.data.id;
	}

	public int getStoreId() {
		return this.data.storeId;
	}

	public int getParentId() {
		return this.data.parentId;
	}

	public String getTitle() {
		return this.data.title;
	}

	public String getDescription() {
		return this.data.description;
	}
	
	public Location getThis(){
		return this;
	}

	public boolean isActive() {
		return this.data.active;
	}

	public void setId(int id) {
		this.data.id = id;
	}

	public void setStoreId(int storeId) {
		this.data.storeId = storeId;
	}

	public void setParentId(int parentId) {
		this.data.parentId = parentId;
	}

	public void setTitle(String title) {
		this.data.title = title;
	}

	public void setDescription(String description) {
		this.data.description = description;
	}

	public void setActive(boolean active) {
		this.data.active = active;
	}

	@Override
	public String toString() {
		return "Location [id=" + this.data.id + ", storeId="
				+ this.data.storeId + ", parentId=" + this.data.parentId + ", title="
				+ this.data.title + ", description=" + this.data.description
				+ ", active=" + this.data.active + "]";
	}

}