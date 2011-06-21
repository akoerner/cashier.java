package cashier.resource;

import java.net.ProtocolException;
import java.util.ArrayList;
import cashier.exceptions.connection.ConnectionDisabledException;
import cashier.exceptions.request.InvalidRequestException;
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
public class User extends Resource {

	@Expose(serialize = false, deserialize = false) public static final String TABLE = "users";
	@Expose(serialize = false, deserialize = false) public static final String RESOURCE = "user";
	@SerializedName(User.RESOURCE)
	@Expose(serialize = true, deserialize = true) private Data data;
	
	/*-Resource Constants-*/
	@Expose(serialize = false, deserialize = false) public static final String ID = "id";
	@Expose(serialize = false, deserialize = false) public static final String PERSON_ID = "person_id";
	@Expose(serialize = false, deserialize = false) public static final String TOKEN = "token";
	@Expose(serialize = false, deserialize = false) public static final String PASSWORD_HASH = "password_hash";
	@Expose(serialize = false, deserialize = false) public static final String PASSWORD_SALT = "password_salt";
	@Expose(serialize = false, deserialize = false) public static final String ADMINISTRATOR = "administrator";
	@Expose(serialize = false, deserialize = false) public static final String ACTIVE = "active";

	private class Data{
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(User.ID) public int id;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(User.PERSON_ID) public int personId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(User.TOKEN) public String token;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(User.PASSWORD_HASH) public String passwordHash;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(User.PASSWORD_SALT) public String passwordSalt;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(User.ADMINISTRATOR) public boolean administrator;
		@Expose(serialize = true, deserialize = true)
		@SerializedName(User.ACTIVE) public boolean active;
	}

	public User() {
		super();
		this.data = new Data();
		this.setTable(User.TABLE);
		this.setResource(User.RESOURCE);
	}

	@Override
	public String toString() {
		return "User [id=" + this.data.id + ", personId=" + this.data.personId
				+ ", token=" + this.data.token + ", passwordHash="
				+ this.data.passwordHash + ", passwordSalt=" + this.data.passwordSalt
				+ ", administrator=" + this.data.administrator + ", active="
				+ this.data.active + "]";
	}
	
	/**
	 * Authenticates user based on username and password provided as arguments.
	 * 
	 * Precondition: The connection must first be enabled by providing not null
	 * connection constants. 
	 * Postcondition: If the username and password false is returned. If no connection constants are
	 * provided enabling the connection or the connection was manually disabled
	 * by calling Connection.disable() then ConnectionDisabledExcepiton is
	 * thrown.
	 * @param username The username of the user to be authenticated.
	 * @param password The password of the user to be authenticated.
	 * @return Boolean username and password combination provided are valid
	 *          then true is returned else false is returned
	 * @throws ConnectionDisabled
	 *             The connection must be enabled by calling
	 *             Connection.setConnectionConstants().
	 */
	public static boolean authenticate(String username, String password) throws ConnectionDisabledException {
		Request request = new Request(User.RESOURCE, User.TABLE);
		try {
			request.setMethod(Request.GET);
			request.setBody("{\"user\":{\"username\":\""+username+"\",\"password\":\""+password+"\"}}");
			request.setResourceAddress(request.getTable());
			Response response;
			try {
				response = request.submit();
				System.out.println(response.getStatusCode());
				if (response.getStatusCode().equals("200")) {
					return true;
				}
			} catch (InvalidRequestException e) {
				System.out.println(e.getMessage());
			}
		} catch (ProtocolException e) {
		}
		return false;

	}

	public static ArrayList<Resource> find(int id) throws ConnectionDisabledException {
		return Resource.find(id, User.RESOURCE, User.TABLE);
	}

	public static ArrayList<Resource> all() throws ConnectionDisabledException {
		return Resource.all(User.RESOURCE, User.TABLE);
	}

	public static ArrayList<Resource> all(int limit, int offset) throws ConnectionDisabledException {
		return Resource.all(limit, offset, User.RESOURCE, User.TABLE);
	}

	public static Response destroy(Integer id) throws ConnectionDisabledException {
		return Resource.destroy(id, User.RESOURCE, User.TABLE);
	}

	public static ArrayList<Resource> query(Query query) throws ConnectionDisabledException {
		return Resource.query(query, User.RESOURCE, User.TABLE);
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

	public String getToken() {
		return this.data.token;
	}

	public String getPasswordHash() {
		return this.data.passwordHash;
	}

	public String getPasswordSalt() {
		return this.data.passwordSalt;
	}
	
	public User getThis(){
		return this;
	}

	public boolean isAdministrator() {
		return this.data.administrator;
	}

	public boolean isActive() {
		return this.data.active;
	}

	public void setId(int id) {
		this.data.id = id;
	}

	public void setPersonId(int personId) {
		this.data.personId = personId;
	}

	public void setToken(String token) {
		this.data.token = token;
	}

	public void setPasswordHash(String passwordHash) {
		this.data.passwordHash = passwordHash;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.data.passwordSalt = passwordSalt;
	}

	public void setAdministrator(boolean administrator) {
		this.data.administrator = administrator;
	}

	public void setActive(boolean active) {
		this.data.active = active;
	}

}