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
public class Invoice extends Resource {
	
	@Expose(serialize = false, deserialize = false) public static final String TABLE = "invoices";
	@Expose(serialize = false, deserialize = false) public static final String RESOURCE = "invoice";
	@SerializedName(Invoice.RESOURCE)
	@Expose(serialize = true, deserialize = true) private Data data;
	
	/*-Resource Constants-*/
	@Expose(serialize = false, deserialize = false) public static final String ID = "id";
	@Expose(serialize = false, deserialize = false) public static final String CUSTOMER_ID = "customer_id";
	@Expose(serialize = false, deserialize = false) public static final String TICKET_ID = "ticket_id";
	@Expose(serialize = false, deserialize = false) public static final String TITLE = "title";
	@Expose(serialize = false, deserialize = false) public static final String DESCRIPTION = "description";
	@Expose(serialize = false, deserialize = false) public static final String ISSUED = "invoice";
	@Expose(serialize = false, deserialize = false) public static final String DUE = "due";
	@Expose(serialize = false, deserialize = false) public static final String PAID = "paid";
	@Expose(serialize = false, deserialize = false) public static final String ACTIVE = "active";
	
	

	private class Data {
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Invoice.ID) public int id;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Invoice.CUSTOMER_ID) public int customerId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Invoice.TICKET_ID) public int ticketId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Invoice.TITLE) public String title;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Invoice.DESCRIPTION) public String description;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Invoice.ISSUED) public String issued;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Invoice.DUE) public String due;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Invoice.PAID) public String paid;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Invoice.ACTIVE) public boolean active;
	}

	public Invoice() {
		super();
		this.data = new Data();
		this.setTable(Invoice.TABLE);
		this.setResource(Invoice.RESOURCE);
	}

	@Override
	public String toString() {
		return "Invoice [id=" + this.data.id + ", customerId="
				+ this.data.customerId + ", ticketId=" + this.data.ticketId
				+ ", title=" + this.data.title + ", description="
				+ this.data.description + ", issued=" + this.data.issued
				+ ", due=" + this.data.due + ", paid=" + this.data.paid
				+ ", active=" + this.data.active + "]";
	}

	public static ArrayList<Resource> find(int id) throws ConnectionDisabledException {
		return Resource.find(id, Invoice.RESOURCE, Invoice.TABLE);
	}

	public static ArrayList<Resource> all() throws ConnectionDisabledException {
		return Resource.all(Invoice.RESOURCE, Invoice.TABLE);
	}

	public static ArrayList<Resource> all(int limit, int offset) throws ConnectionDisabledException {
		return Resource.all(limit, offset, Invoice.RESOURCE, Invoice.TABLE);
	}

	public static Response destroy(Integer id) throws ConnectionDisabledException {
		return Resource.destroy(id, Invoice.RESOURCE, Invoice.TABLE);
	}

	public static ArrayList<Resource> query(Query query) throws ConnectionDisabledException {
		return Resource.query(query , Invoice.RESOURCE, Invoice.TABLE);
	}

    ///////////////////////
	//Getters and Setters//
	///////////////////////	
	public int getId() {
		return this.data.id;
	}

	public int getCustomerId() {
		return this.data.customerId;
	}

	public int getTicketId() {
		return this.data.ticketId;
	}

	public String getTitle() {
		return this.data.title;
	}

	public String getDescription() {
		return this.data.description;
	}

	public String getIssued() {
		return this.data.issued;
	}

	public String getDue() {
		return this.data.due;
	}

	public String getPaid() {
		return this.data.paid;
	}
	
	public Invoice getThis(){
		return this;
	}

	public boolean isActive() {
		return this.data.active;
	}

	public void setId(int id) {
		this.data.id = id;
	}

	public void setCustomerId(int customerId) {
		this.data.customerId = customerId;
	}

	public void setTicketId(int ticketId) {
		this.data.ticketId = ticketId;
	}

	public void setTitle(String title) {
		this.data.title = title;
	}

	public void setDescription(String description) {
		this.data.description = description;
	}

	public void setIssued(String issued) {
		this.data.issued = issued;
	}

	public void setDue(String due) {
		this.data.due = due;
	}

	public void setPaid(String paid) {
		this.data.paid = paid;
	}

	public void setActive(boolean active) {
		this.data.active = active;
	}

}