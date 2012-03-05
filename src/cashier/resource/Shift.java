package cashier.resource;

import cashier.exceptions.connection.ConnectionDisabledException;
import cashier.net.*;
import java.util.ArrayList;
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
public class Shift extends Resource {

	@Expose(serialize = false, deserialize = false) public static final String TABLE = "shifts";
	@Expose(serialize = false, deserialize = false) public static final String RESOURCE = "shift";
	@SerializedName(Shift.RESOURCE)
	@Expose(serialize = true, deserialize = true) private Data data;

	/*-Resource Constants-*/
	@Expose(serialize = false, deserialize = false) public static final String ID = "id";
	@Expose(serialize = false, deserialize = false) public static final String USER_ID = "user_id";
	@Expose(serialize = false, deserialize = false) public static final String TITLE = "title";
	@Expose(serialize = false, deserialize = false) public static final String DESCRIPTION = "description";
	@Expose(serialize = false, deserialize = false) public static final String BEGIN = "begin";
	@Expose(serialize = false, deserialize = false) public static final String END = "end";
	@Expose(serialize = false, deserialize = false) public static final String ACTIVE = "active";
	
	private class Data {
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Shift.ID) public int id;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Shift.USER_ID) public int userId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Shift.TITLE) public String title;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Shift.DESCRIPTION) public String description;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Shift.BEGIN) public String begin;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Shift.END) public String end;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Shift.ACTIVE) public boolean active;
	}

	public Shift() {
		super();
		this.data = new Data();
		this.setTable(Shift.TABLE);
		this.setResource(Shift.RESOURCE);
	}

	public static ArrayList<Resource> find(int id) throws ConnectionDisabledException {
		return Resource.find(id, Shift.RESOURCE, Shift.TABLE);
	}

	public static ArrayList<Resource> all() throws ConnectionDisabledException {
		return Resource.all(Shift.RESOURCE, Shift.TABLE);
	}

	public static ArrayList<Resource> all(int limit, int offset) throws ConnectionDisabledException {
		return Resource.all(limit, offset, Shift.RESOURCE, Shift.TABLE);
	}

	public static Response destroy(int id) throws ConnectionDisabledException {
		return Resource.destroy(id, Shift.RESOURCE, Shift.TABLE);
	}

	public static ArrayList<Resource> query(Query query) throws ConnectionDisabledException {
		return Resource.query(query, Shift.RESOURCE, Shift.TABLE);
	}
    ///////////////////////
	//Getters and Setters//
	///////////////////////
	public int getId() {
		return this.data.id;
	}

	public int getUserId() {
		return this.data.userId;
	}

	public String getTitle() {
		return this.data.title;
	}

	public String getDescription() {
		return this.data.description;
	}

	public String getBegin() {
		return this.data.begin;
	}

	public String getEnd() {
		return this.data.end;
	}
	
	public Shift getThis(){
		return this;
	}

	public boolean isActive() {
		return this.data.active;
	}

	public void setId(int id) {
		this.data.id = id;
	}

	public void setUserId(int userId) {
		this.data.userId = userId;
	}

	public void setTitle(String title) {
		this.data.title = title;
	}

	public void setDescription(String description) {
		this.data.description = description;
	}

	public void setBegin(String begin) {
		this.data.begin = begin;
	}

	public void setEnd(String end) {
		this.data.end = end;
	}

	public void setActive(boolean active) {
		this.data.active = active;
	}

	@Override
	public String toString() {
		return "Shift [id=" + this.data.id + ", userId=" + this.data.userId
				+ ", title=" + this.data.title + ", description="
				+ this.data.description + ", begin=" + this.data.begin
				+ ", end=" + this.data.end + ", active=" + this.data.active
				+ "]";
	}

}