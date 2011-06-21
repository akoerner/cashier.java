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
public class Timecard extends Resource {

	@Expose(serialize = false, deserialize = false) public static final String TABLE = "timecards";
	@Expose(serialize = false, deserialize = false) public static final String RESOURCE = "timecard";
	@SerializedName(Timecard.RESOURCE)
	@Expose(serialize = true, deserialize = true) private Data data;
	
	/*-Resource Constants-*/
	@Expose(serialize = false, deserialize = false) public static final String ID = "id";
	@Expose(serialize = false, deserialize = false) public static final String USER_ID = "user_id";
	@Expose(serialize = false, deserialize = false) public static final String BEGIN = "begin";
	@Expose(serialize = false, deserialize = false) public static final String END = "end";

	private class Data {
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Timecard.ID) public int id;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Timecard.USER_ID) public int userId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Timecard.BEGIN) public String begin;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Timecard.END) public String end;
	}

	public Timecard() {
		super();
		this.data = new Data();
		this.setTable(Timecard.TABLE);
		this.setResource(Timecard.RESOURCE);
	}
	
	@Override
	public String toString() {
		return "Timecard [id=" + this.data.id + ", userId=" + this.data.userId
				+ ", begin=" + this.data.begin + ", end=" + this.data.end + "]";
	}

	public static ArrayList<Resource> find(int id) throws ConnectionDisabledException {
		return Resource.find(id, Timecard.RESOURCE, Timecard.TABLE);
	}

	public static ArrayList<Resource> all() throws ConnectionDisabledException {
		return Resource.all(Timecard.RESOURCE, Timecard.TABLE);
	}

	public static ArrayList<Resource> all(int limit, int offset) throws ConnectionDisabledException {
		return Resource.all(limit, offset, Timecard.RESOURCE, Timecard.TABLE);
	}

	public static Response destroy(Integer id) throws ConnectionDisabledException {
		return Resource.destroy(id, Timecard.RESOURCE, Timecard.TABLE);
	}

	public static ArrayList<Resource> query(Query query) throws ConnectionDisabledException {
		return Resource.query(query, Timecard.RESOURCE, Timecard.TABLE);
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

	public String getBegin() {
		return this.data.begin;
	}

	public String getEnd() {
		return this.data.end;
	}
	
	public Timecard getThis(){
		return this;
	}
	
	public void setId(int id) {
		this.data.id = id;
	}

	public void setUserId(int userId) {
		this.data.userId = userId;
	}

	public void setBegin(String begin) {
		this.data.begin = begin;
	}

	public void setEnd(String end) {
		this.data.end = end;
	}

}