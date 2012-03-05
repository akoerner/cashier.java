package cashier.resource;

import java.util.ArrayList;
import java.util.HashMap;
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
public class Transaction extends Resource {

	@Expose(serialize = false, deserialize = false) public static final String TABLE = "transactions";
	@Expose(serialize = false, deserialize = false) public static final String RESOURCE = "transaction";
	@SerializedName(Transaction.RESOURCE)
	@Expose(serialize = true, deserialize = true) private Data data;
	
	/*-Resource Constants-*/
	@Expose(serialize = false, deserialize = false) public static final String ID = "id";
	@Expose(serialize = false, deserialize = false) public static final String STORE_ID = "store_id";
	@Expose(serialize = false, deserialize = false) public static final String TILL_ID = "till_id";
	@Expose(serialize = false, deserialize = false) public static final String CUSTOMER_ID = "customer_id";
	@Expose(serialize = false, deserialize = false) public static final String USER_ID = "user_id";
	@Expose(serialize = false, deserialize = false) public static final String ADJUSTMENTS = "adjustments";
	@Expose(serialize = false, deserialize = false) public static final String PAYMENTS = "payments";
	@Expose(serialize = false, deserialize = false) public static final String TAX_RATE = "tax_rate";
	@Expose(serialize = false, deserialize = false) public static final String COMPLETE = "completed";
	@Expose(serialize = false, deserialize = false) public static final String LOCKED = "locked";

	private class Data {
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Transaction.ID) public int id;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Transaction.STORE_ID) public int storeId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Transaction.TILL_ID) public int tillId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Transaction.CUSTOMER_ID) public int customerId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Transaction.USER_ID) public int userId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Transaction.ADJUSTMENTS) public HashMap<String, String>[] adjustments;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Transaction.PAYMENTS) public HashMap<String, String>[] payments;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Transaction.TAX_RATE) public double taxRate;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Transaction.COMPLETE) public boolean complete;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Transaction.LOCKED) public boolean locked;
	}

	public Transaction() {
		super();
		this.data = new Data();
		this.setTable(Transaction.TABLE);
		this.setResource(Transaction.RESOURCE);
	}

	@Override
	public String toString() {
		return "Transaction [id=" + this.data.id + ", storeId="
				+ this.data.storeId + ", tillId=" + this.data.tillId + ", customerId="
				+ this.data.customerId + ", userId=" + this.data.userId
				+ ", adjustments=" + this.data.adjustments + ", payments="
				+ this.data.payments + ", taxRate=" + this.data.taxRate
				+ ", complete=" + this.data.complete + ", locked="
				+ this.data.locked + "]";
	}

	public static ArrayList<Resource> find(int id) throws ConnectionDisabledException {
		return Resource.find(id, Transaction.RESOURCE, Transaction.TABLE);
	}

	public static ArrayList<Resource> all() throws ConnectionDisabledException {
		return Resource.all(Transaction.RESOURCE, Transaction.TABLE);
	}

	public static ArrayList<Resource> all(int limit, int offset) throws ConnectionDisabledException {
		return Resource.all(limit, offset, Transaction.RESOURCE, Transaction.TABLE);
	}

	public static Response destroy(int id) throws ConnectionDisabledException {
		return Resource.destroy(id, Transaction.RESOURCE, Transaction.TABLE);
	}

	public static ArrayList<Resource> query(Query query) throws ConnectionDisabledException {
		return Resource.query(query, Transaction.RESOURCE, Transaction.TABLE);
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

	public int getTillId() {
		return this.data.tillId;
	}

	public int getCustomerId() {
		return this.data.customerId;
	}

	public int getUserId() {
		return this.data.userId;
	}

	public HashMap<String, String>[] getAdjustments() {
		return this.data.adjustments;
	}

	public HashMap<String, String>[] getPayments() {
		return this.data.payments;
	}

	public double getTaxRate() {
		return this.data.taxRate;
	}
	
	public Transaction getThis(){
		return this;
	}

	public boolean isComplete() {
		return this.data.complete;
	}

	public boolean isLocked() {
		return this.data.locked;
	}

	public void setId(int id) {
		this.data.id = id;
	}

	public void setStoreId(int storeId) {
		this.data.storeId = storeId;
	}

	public void setTillId(int tillId) {
		this.data.tillId = tillId;
	}

	public void setCustomerId(int customerId) {
		this.data.customerId = customerId;
	}

	public void setUserId(int userId) {
		this.data.userId = userId;
	}

	public void setAdjustments(HashMap<String, String>[] adjustments) {
		this.data.adjustments = adjustments;
	}

	public void setPayments(HashMap<String, String>[] payments) {
		this.data.payments = payments;
	}

	public void setTaxRate(double taxRate) {
		this.data.taxRate = taxRate;
	}

	public void setComplete(boolean complete) {
		this.data.complete = complete;
	}

	public void setLocked(boolean locked) {
		this.data.locked = locked;
	}

}