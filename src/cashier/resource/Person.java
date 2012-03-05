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
public class Person extends Resource {
	
	@Expose(serialize = false, deserialize = false) public static final String TABLE = "people";
	@Expose(serialize = false, deserialize = false) public static final String RESOURCE = "person";
	@SerializedName(Person.RESOURCE)
	@Expose(serialize = true, deserialize = true) private Data data;
	
	/*-Resource Constants-*/
	@Expose(serialize = false, deserialize = false) public static final String ID = "id";
	@Expose(serialize = false, deserialize = false) public static final String FIRST_NAME = "first_name";
	@Expose(serialize = false, deserialize = false) public static final String MIDDLE_NAME = "middle_name";
	@Expose(serialize = false, deserialize = false) public static final String LAST_NAME = "last_name";
	@Expose(serialize = false, deserialize = false) public static final String DATE_OF_BIRTH = "date_of_birth";
	@Expose(serialize = false, deserialize = false) public static final String PROPERTIES = "properties";
	@Expose(serialize = false, deserialize = false) public static final String EMAILS = "emails";
	@Expose(serialize = false, deserialize = false) public static final String PHONES = "phones";
	@Expose(serialize = false, deserialize = false) public static final String ADDRESSES = "addresses";

	private class Data {
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Person.ID) public int id;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Person.FIRST_NAME) public String firstName;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Person.MIDDLE_NAME) public String middleName;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Person.LAST_NAME) public String lastName;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Person.DATE_OF_BIRTH) public String dateOfBirth;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Person.PROPERTIES) public HashMap<String, String> properties;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Person.EMAILS) public String[] emails;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Person.PHONES) public String[] phones;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Person.ADDRESSES) public String[] addresses;
	}

	public Person() {
		super();
		this.data = new Data();
		this.setTable(Person.TABLE);
		this.setResource(Person.RESOURCE);
	}

	@Override
	public String toString() {
		return "Person [id=" + this.data.id + ", firstName="
				+ this.data.firstName + ", middleName=" + this.data.middleName
				+ ", lastName=" + this.data.lastName + ", dateOfBirth="
				+ this.data.dateOfBirth + ", properties="
				+ this.data.properties + ", emails=" + this.data.emails
				+ ", phones=" + this.data.phones + ", addresses=" + this.data.addresses
				+ "]";
	}

	public static ArrayList<Resource> find(int id) throws ConnectionDisabledException {
		return Resource.find(id, Person.RESOURCE, Person.TABLE);
	}

	public static ArrayList<Resource> all() throws ConnectionDisabledException {
		return Resource.all(Person.RESOURCE, Person.TABLE);
	}

	public static ArrayList<Resource> all(int limit, int offset) throws ConnectionDisabledException {
		return Resource.all(limit, offset, Person.RESOURCE, Person.TABLE);
	}

	public static Response destroy(int id) throws ConnectionDisabledException {
		return Resource.destroy(id, Person.RESOURCE, Person.TABLE);
	}

	public static ArrayList<Resource> query(Query query) throws ConnectionDisabledException {
		return Resource.query(query, Person.RESOURCE, Person.TABLE);
	}	
	
    ///////////////////////
	//Getters and Setters//
	///////////////////////
	public int getId() {
		return this.data.id;
	}

	public String getFirstName() {
		return this.data.firstName;
	}

	public String getMiddleName() {
		return this.data.middleName;
	}

	public String getLastName() {
		return this.data.lastName;
	}

	public String getDateOfBirth() {
		return this.data.dateOfBirth;
	}

	public HashMap<String, String> getProperties() {
		return this.data.properties;
	}

	public String[] getEmails() {
		return this.data.emails;
	}

	public String[] getPhones() {
		return this.data.phones;
	}

	public String[] getAddresses() {
		return this.data.addresses;
	}
	
	public Person getThis(){
		return this;
	}

	public void setId(int id) {
		this.data.id = id;
	}

	public void setFirstName(String firstName) {
		this.data.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.data.middleName = middleName;
	}

	public void setLastName(String lastName) {
		this.data.lastName = lastName;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.data.dateOfBirth = dateOfBirth;
	}

	public void setProperties(HashMap<String, String> properties) {
		this.data.properties = properties;
	}

	public void setEmails(String[] emails) {
		this.data.emails = emails;
	}

	public void setPhones(String[] phones) {
		this.data.phones = phones;
	}

	public void setAddresses(String[] addresses) {
		this.data.addresses = addresses;
	}

}