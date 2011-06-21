package cashier.resource;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import cashier.net.*;
import cashier.exceptions.connection.ConnectionDisabledException;

/**
 * Cashier data access object containing getters/accessors and mutators/setters and a toString method that formats add class data members in a string
 * for easy output.  The object contains Google Gson annotations and structure for JSON serialization and deserialization of the data.  Class resource 
 * constants are provided for easy Cashier Query object formation.
 * 
 * @author <a href="mailto:andrewkoerner.b@gmail.com"> Andrew Koerner</a>
 * @version 1.0
 * 
 */
public class Customer extends Resource{

	@Expose(serialize = false, deserialize = false) public static final String TABLE = "customers";
	@Expose(serialize = false, deserialize = false) public static final String RESOURCE = "customer";
	
	/*-Resource Constants-*/
	@Expose(serialize = false, deserialize = false) public static final String ID = "id";
	@Expose(serialize = false, deserialize = false) public static final String  PERSON_ID = "person_id";
	@Expose(serialize = false, deserialize = false) public static final String  CREDIT = "credit";
	@Expose(serialize = false, deserialize = false) public static final String  NOTES = "notes";
	@Expose(serialize = false, deserialize = false) public static final String  ACTIVE = "active";
	@Expose(serialize = false, deserialize = false) public static final String  UPDATED_AT = "updated_at";
	@Expose(serialize = false, deserialize = false) public static final String  CREATED_AT = "created_at";
	
	@SerializedName(Customer.RESOURCE)
	@Expose(serialize = true, deserialize = true) private Data data;

	private class Data {
		@SerializedName(ID)
		@Expose(serialize = true, deserialize = true) private int id;
		@SerializedName(PERSON_ID)
		@Expose(serialize = true, deserialize = true) private int personId;
		@SerializedName(CREDIT)
		@Expose(serialize = true, deserialize = true) private int credit;
		@SerializedName(NOTES)
		@Expose(serialize = true, deserialize = true) private String notes;
		@SerializedName(ACTIVE)
		@Expose(serialize = true, deserialize = true) private boolean active;
		@SerializedName(UPDATED_AT)
		@Expose(serialize = true, deserialize = true) private String updatedAt;
		@SerializedName(CREATED_AT)
		@Expose(serialize = true, deserialize = true) private String createdAt;
	}

	public Customer() {
		super();
		this.data = new Data();
		this.setTable(Customer.TABLE);
		this.setResource(Customer.RESOURCE);
	}

	@Override
	public String toString() {
		return "Customer [id=" + this.data.id + ", personId="
				+ this.data.personId + ", credit=" + this.data.credit
				+ ", notes=" + this.data.notes + ", active=" + this.data.active
				+ ", updatedAt=" + this.data.updatedAt + ", createdAt="
				+ this.data.createdAt + "]";
	}
	
	public void save() throws ConnectionDisabledException{
		Resource.save(this);
		this.setId(Resource.getLastInsertId());
	}
	
	public static ArrayList<Resource> find(int id) throws ConnectionDisabledException {
		return Resource.find(id, Customer.RESOURCE, Customer.TABLE);
	}

	public static ArrayList<Resource> all() throws ConnectionDisabledException {
		return Resource.all(Customer.RESOURCE, Customer.TABLE);
	}

	public static ArrayList<Resource> all(int limit, int offset) throws ConnectionDisabledException {
		return Resource.all(limit, offset, Customer.RESOURCE, Customer.TABLE);
	}

	public static Response destroy(Integer id) throws ConnectionDisabledException {
		return Resource.destroy(id, Customer.RESOURCE, Customer.TABLE);
	}
	
	public static ArrayList<Resource> query(Query query) throws ConnectionDisabledException {
		return Resource.query(query, Customer.RESOURCE, Customer.TABLE);
	}

    ///////////////////////
	//Getters and Setters//
	///////////////////////
	public int getId() {
		return this.data.id;
	}

	public int getPersonId() {
		return this.data.personId;
	}

	public int getCredit() {
		return this.data.credit;
	}

	public String getNotes() {
		return this.data.notes;
	}

	public boolean isActive() {
		return this.data.active;
	}

	public String getUpdatedAt() {
		return this.data.updatedAt;
	}

	public String getCreatedAt() {
		return this.data.createdAt;
	}
	
	public Customer getThis(){
		return this;
	}

	public void setId(int id) {
		this.data.id = id;
	}

	public void setPersonId(int personId) {
		this.data.personId = personId;
	}

	public void setCredit(int credit) {
		this.data.credit = credit;
	}

	public void setNotes(String notes) {
		this.data.notes = notes;
	}

	public void setActive(boolean active) {
		this.data.active = active;
	}

	public void setUpdatedAt(String updatedAt) {
		this.data.updatedAt = updatedAt;
	}

	public void setCreatedAt(String createdAt) {
		this.data.createdAt = createdAt;
	}
}