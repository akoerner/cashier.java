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
public class Entry extends Resource {
	
	@Expose(serialize = false, deserialize = false) public static final String TABLE = "entries";
	@Expose(serialize = false, deserialize = false) public static final String RESOURCE = "entry";
	@SerializedName(Entry.RESOURCE)
	@Expose(serialize = true, deserialize = true) private Data data;
	
	/*-Resource Constants-*/
	@Expose(serialize = false, deserialize = false) public static final String ID = "id";
	@Expose(serialize = false, deserialize = false) public static final String TILL_ID = "till_id";
	@Expose(serialize = false, deserialize = false) public static final String USER_ID = "user_id";
	@Expose(serialize = false, deserialize = false) public static final String TITLE = "title";
	@Expose(serialize = false, deserialize = false) public static final String DESCRIPTION = "description";
	@Expose(serialize = false, deserialize = false) public static final String TIME = "time";
	@Expose(serialize = false, deserialize = false) public static final String AMOUNT = "amount";

	private class Data {
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Entry.ID) public int id;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Entry.TILL_ID) public int tillId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Entry.USER_ID) public int userId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Entry.TITLE) public String title;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Entry.DESCRIPTION) public String description;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Entry.TIME) public String time;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Entry.AMOUNT) public int amount;
	}

	public Entry() {
		super();
		this.data = new Data();
		this.setTable(Entry.TABLE);
		this.setResource(Entry.RESOURCE);
	}

	@Override
	public String toString() {
		return "Entry [id=" + this.data.id + ", tillId=" + this.data.tillId
				+ ", userId=" + this.data.userId + ", title=" + this.data.title
				+ ", description=" + this.data.description + ", time="
				+ this.data.time + ", amount=" + this.data.amount + "]";
	}

	public static ArrayList<Resource> find(int id) throws ConnectionDisabledException {
		return Resource.find(id, Entry.RESOURCE, Entry.TABLE);
	}

	public static ArrayList<Resource> all() throws ConnectionDisabledException {
		return Resource.all(Entry.RESOURCE, Entry.TABLE);
	}

	public static ArrayList<Resource> all(int limit, int offset) throws ConnectionDisabledException {
		return Resource.all(limit, offset, Entry.RESOURCE, Entry.TABLE);
	}

	public static Response destroy(Integer id) throws ConnectionDisabledException {
		return Resource.destroy(id, Entry.RESOURCE, Entry.TABLE);
	}

	public static ArrayList<Resource> query(Query query) throws ConnectionDisabledException {
		return Resource.query(query , Entry.RESOURCE, Entry.TABLE);
	}

    ///////////////////////
	//Getters and Setters//
	///////////////////////	
	public int getId() {
		return this.data.id;
	}

	public int getTillId() {
		return this.data.tillId;
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

	public String getTime() {
		return this.data.time;
	}

	public int getAmount() {
		return this.data.amount;
	}
	
	public Entry getThis(){
		return this;
	}

	public void setId(int id) {
		this.data.id = id;
	}

	public void setTillId(int tillId) {
		this.data.tillId = tillId;
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

	public void setTime(String time) {
		this.data.time = time;
	}

	public void setAmount(int amount) {
		this.data.amount = amount;
	}

}