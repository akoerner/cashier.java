package cashier.resource;

import cashier.exceptions.connection.ConnectionDisabledException;
import cashier.net.*;
import java.util.ArrayList;
import java.util.HashMap;
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
public class Ticket extends Resource {

	@Expose(serialize = false, deserialize = false) public static final String TABLE = "tickets";
	@Expose(serialize = false, deserialize = false) public static final String RESOURCE = "ticket";
	@SerializedName(Ticket.RESOURCE)
	@Expose(serialize = true, deserialize = true) private Data data;
	
	/*-Resource Constants-*/
	@Expose(serialize = false, deserialize = false) public static final String ID = "id";
	@Expose(serialize = false, deserialize = false) public static final String ASSIGNER_ID = "assigner_id";
	@Expose(serialize = false, deserialize = false) public static final String ASSIGNEE_ID = "assignee_id";
	@Expose(serialize = false, deserialize = false) public static final String TITLE = "title";
	@Expose(serialize = false, deserialize = false) public static final String DESCRIPTION = "description";
	@Expose(serialize = false, deserialize = false) public static final String NOTES = "notes";
	@Expose(serialize = false, deserialize = false) public static final String STARTED = "started";
	@Expose(serialize = false, deserialize = false) public static final String FINISHED = "finished";
	@Expose(serialize = false, deserialize = false) public static final String EXPIRATION = "expiration";
	@Expose(serialize = false, deserialize = false) public static final String ASSIGNED = "assigned";
	@Expose(serialize = false, deserialize = false) public static final String PRIORITY = "priority";
	@Expose(serialize = false, deserialize = false) public static final String STATUS = "status";
	@Expose(serialize = false, deserialize = false) public static final String LOGS = "logs";
	@Expose(serialize = false, deserialize = false) public static final String PROPERTIES = "properties";
	@Expose(serialize = false, deserialize = false) public static final String ACTIVE = "active";
	@Expose(serialize = false, deserialize = false) public static final String CREATED_AT = "created_at";
	@Expose(serialize = false, deserialize = false) public static final String UPDATED_AT = "updated_at";

	private class Data {
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Ticket.ID) public int id;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Ticket.ASSIGNER_ID) public int assignerId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Ticket.ASSIGNEE_ID) public int assigneeId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Ticket.TITLE) public String title;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Ticket.DESCRIPTION) public String description;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Ticket.NOTES) public String notes;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Ticket.STARTED) public String started;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Ticket.FINISHED) public String finished;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Ticket.EXPIRATION) public String expiration;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Ticket.ASSIGNED) public String assigned;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Ticket.PRIORITY) public String priority;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Ticket.STATUS) public String status;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Ticket.LOGS) public HashMap<String, String>[] logs;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Ticket.PROPERTIES) public HashMap<String, String> properties;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Ticket.ACTIVE) public boolean active;
		@Expose(serialize = false, deserialize = true) 
		@SerializedName(Ticket.CREATED_AT) public String createdAt;
		@Expose(serialize = false, deserialize = true) 
		@SerializedName(Ticket.UPDATED_AT) public String updated_at;
	}

	public static ArrayList<Resource> find(int id) throws ConnectionDisabledException {
		return Resource.find(id, Ticket.RESOURCE, Ticket.TABLE);
	}

	public static ArrayList<Resource> all() throws ConnectionDisabledException {
		return Resource.all(Ticket.RESOURCE, Ticket.TABLE);
	}

	public static ArrayList<Resource> all(int limit, int offset) throws ConnectionDisabledException {
		return Resource.all(limit, offset, Ticket.RESOURCE, Ticket.TABLE);
	}

	public static Response destroy(Integer id) throws ConnectionDisabledException {
		return Resource.destroy(id, Ticket.RESOURCE, Ticket.TABLE);
	}

	public static ArrayList<Resource> query(Query query) throws ConnectionDisabledException {
		return Resource.query(query, Ticket.RESOURCE, Ticket.TABLE);
	}
	public Ticket() {
		super();
		this.data = new Data();
		this.setTable(Ticket.TABLE);
		this.setResource(Ticket.RESOURCE);
	}

	@Override
	public String toString() {
		return "Ticket [id=" + this.data.id + ", assignerId="
				+ this.data.assignerId + ", assigneeId=" + this.data.assigneeId
				+ ", title=" + this.data.title + ", description="
				+ this.data.description + ", notes=" + this.data.notes
				+ ", started=" + this.data.started + ", finished="
				+ this.data.finished + ", expiration=" + this.data.expiration
				+ ", assigned=" + this.data.assigned + ", priority="
				+ this.data.priority + ", status=" + this.data.status
				+ ", log=" + this.data.logs + ", properties=" + this.data.properties
				+ ", active=" + this.data.active + "]";
	}

    ///////////////////////
	//Getters and Setters//
	///////////////////////
	public int getId() {
		return this.data.id;
	}

	public int getAssignerId() {
		return this.data.assignerId;
	}

	public int getAssigneeId() {
		return this.data.assigneeId;
	}

	public String getTitle() {
		return this.data.title;
	}

	public String getDescription() {
		return this.data.description;
	}

	public String getNotes() {
		return this.data.notes;
	}

	public String getStarted() {
		return this.data.started;
	}

	public String getFinished() {
		return this.data.finished;
	}

	public String getExpiration() {
		return this.data.expiration;
	}

	public String getAssigned() {
		return this.data.assigned;
	}

	public String getPriority() {
		return this.data.priority;
	}

	public String getStatus() {
		return this.data.status;
	}

	public HashMap<String, String>[] getLogs() {
		return this.data.logs;
	}

	public HashMap<String, String> getProperties() {
		return this.data.properties;
	}
	
	public String getCreatedAt(){
		return this.data.createdAt;
	}
	
	public String getUpdatedAt(){
		return this.data.updated_at;
	}
	
	public Ticket getThis(){
		return this;
	}

	public boolean isActive() {
		return this.data.active;
	}

	public void setId(int id) {
		this.data.id = id;
	}

	public void setAssignerId(int assignerId) {
		this.data.assignerId = assignerId;
	}

	public void setAssigneeId(int assigneeId) {
		this.data.assigneeId = assigneeId;
	}

	public void setTitle(String title) {
		this.data.title = title;
	}

	public void setDescription(String description) {
		this.data.description = description;
	}

	public void setNotes(String notes) {
		this.data.notes = notes;
	}

	public void setStarted(String started) {
		this.data.started = started;
	}

	public void setFinished(String finished) {
		this.data.finished = finished;
	}

	public void setExpiration(String expiration) {
		this.data.expiration = expiration;
	}

	public void setAssigned(String assigned) {
		this.data.assigned = assigned;
	}

	public void setPriority(String priority) {
		this.data.priority = priority;
	}

	public void setStatus(String status) {
		this.data.status = status;
	}

	public void setLogs(HashMap<String, String>[] logs) {
		this.data.logs = logs;
	}

	public void setProperties(HashMap<String, String> properties) {
		this.data.properties = properties;
	}

	public void setActive(boolean active) {
		this.data.active = active;
	}

}