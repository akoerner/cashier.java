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
public class Unit extends Resource {
	
	@Expose(serialize = false, deserialize = false) public static final String TABLE = "units";
	@Expose(serialize = false, deserialize = false) public static final String RESOURCE = "unit";
	@SerializedName(Unit.RESOURCE)
	@Expose(serialize = true, deserialize = true) private Data data;
	
	/*-Resource Constants-*/
	@Expose(serialize = false, deserialize = false) public static final String ID = "id";
	@Expose(serialize = false, deserialize = false) public static final String ITEM_ID = "item_id";
	@Expose(serialize = false, deserialize = false) public static final String LOCATION_ID = "location_id";
	@Expose(serialize = false, deserialize = false) public static final String CONDITION = "condition";
	@Expose(serialize = false, deserialize = false) public static final String ACTIVE = "active";

	private class Data {
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Unit.ID) public int id;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Unit.ITEM_ID) public int itemId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Unit.LOCATION_ID) public int locationId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Unit.CONDITION) public double condition;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Unit.ACTIVE) public boolean active;
	}

	public Unit() {
		super();
		this.data = new Data();
		this.setTable(Unit.TABLE);
		this.setResource(Unit.RESOURCE);
	}

	@Override
	public String toString() {
		return "Unit [id=" + this.data.id + ", itemId=" + this.data.itemId
				+ ", locationId=" + this.data.locationId + ", condition="
				+ this.data.condition + ", active=" + this.data.active + "]";
	}

	public static ArrayList<Resource> find(int id) throws ConnectionDisabledException {
		return Resource.find(id, Unit.RESOURCE, Unit.TABLE);
	}

	public static ArrayList<Resource> all() throws ConnectionDisabledException {
		return Resource.all(Unit.RESOURCE, Unit.TABLE);
	}

	public static ArrayList<Resource> all(int limit, int offset) throws ConnectionDisabledException {
		return Resource.all(limit, offset, Unit.RESOURCE, Unit.TABLE);
	}

	public static Response destroy(Integer id) throws ConnectionDisabledException {
		return Resource.destroy(id, Unit.RESOURCE, Unit.TABLE);
	}

	public static ArrayList<Resource> query(Query query) throws ConnectionDisabledException {
		return Resource.query(query, Unit.RESOURCE, Unit.TABLE);
	}
    ///////////////////////
	//Getters and Setters//
	///////////////////////
	public int getId() {
		return this.data.id;
	}

	public int getItemId() {
		return this.data.itemId;
	}

	public int getLocationId() {
		return this.data.locationId;
	}

	public double getCondition() {
		return this.data.condition;
	}
	
	public Unit getThis(){
		return this;
	}

	public boolean isActive() {
		return this.data.active;
	}

	public void setId(int id) {
		this.data.id = id;
	}

	public void setItemId(int itemId) {
		this.data.itemId = itemId;
	}

	public void setLocationId(int locationId) {
		this.data.locationId = locationId;
	}

	public void setCondition(double condition) {
		this.data.condition = condition;
	}

	public void setActive(boolean active) {
		this.data.active = active;
	}

}