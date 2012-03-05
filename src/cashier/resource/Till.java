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
public class Till extends Resource {

	@Expose(serialize = false, deserialize = false) public static final String TABLE = "tills";
	@Expose(serialize = false, deserialize = false) public static final String RESOURCE = "till";
	@SerializedName(Till.RESOURCE)
	@Expose(serialize = true, deserialize = true) private Data data;
	
	/*-Resource Constants-*/
	@Expose(serialize = false, deserialize = false) public static final String ID = "id";
	@Expose(serialize = false, deserialize = false) public static final String STORE_ID = "store_id";
	@Expose(serialize = false, deserialize = false) public static final String TITLE = "title";
	@Expose(serialize = false, deserialize = false) public static final String DESCRIPTION = "description";
	@Expose(serialize = false, deserialize = false) public static final String MINIMUM_TRANSFER = "minimum_transfer";
	@Expose(serialize = false, deserialize = false) public static final String MINIMUM_BALANCE = "minimum_balance";
	@Expose(serialize = false, deserialize = false) public static final String RETAINABLE = "retainable";
	@Expose(serialize = false, deserialize = false) public static final String ACTIVE = "active";

	private class Data {
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Till.ID) public int id;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Till.STORE_ID) public int storeId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Till.TITLE) public String title;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Till.DESCRIPTION) public String description;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Till.MINIMUM_TRANSFER) public int minimumTransfer;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Till.MINIMUM_BALANCE) public int minimumBalance;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Till.RETAINABLE) public boolean retainable;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Till.ACTIVE) public boolean active;
	}

	public static ArrayList<Resource> find(int id) throws ConnectionDisabledException {
		return Resource.find(id, Till.RESOURCE, Till.TABLE);
	}

	public static ArrayList<Resource> all() throws ConnectionDisabledException {
		return Resource.all(Till.RESOURCE, Till.TABLE);
	}

	public static ArrayList<Resource> all(int limit, int offset) throws ConnectionDisabledException {
		return Resource.all(limit, offset, Till.RESOURCE, Till.TABLE);
	}

	public static Response destroy(int id) throws ConnectionDisabledException {
		return Resource.destroy(id, Till.RESOURCE, Till.TABLE);
	}

	public static ArrayList<Resource> query(Query query) throws ConnectionDisabledException {
		return Resource.query(query, Till.RESOURCE, Till.TABLE);
	}
	public Till() {
		super();
		this.data = new Data();
		this.setTable(Till.TABLE);
		this.setResource(Till.RESOURCE);
	}

	@Override
	public String toString() {
		return "Till [id=" + this.data.id + ", storeId=" + this.data.storeId
				+ ", title=" + this.data.title + ", description="
				+ this.data.description + ", minimumTransfer="
				+ this.data.minimumTransfer + ", minimumBalance="
				+ this.data.minimumBalance + ", retainable="
				+ this.data.retainable + ", active=" + this.data.active + "]";
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

	public String getTitle() {
		return this.data.title;
	}

	public String getDescription() {
		return this.data.description;
	}

	public int getMinimumTransfer() {
		return this.data.minimumTransfer;
	}

	public int getMinimumBalance() {
		return this.data.minimumBalance;
	}

	public boolean isRetainable() {
		return this.data.retainable;
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

	public void setTitle(String title) {
		this.data.title = title;
	}

	public void setDescription(String description) {
		this.data.description = description;
	}

	public void setMinimumTransfer(int minimumTransfer) {
		this.data.minimumTransfer = minimumTransfer;
	}

	public void setMinimumBalance(int minimumBalance) {
		this.data.minimumBalance = minimumBalance;
	}

	public void setRetainable(boolean retainable) {
		this.data.retainable = retainable;
	}

	public void setActive(boolean active) {
		this.data.active = active;
	}

}