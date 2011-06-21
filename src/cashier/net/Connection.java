package cashier.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import sun.misc.BASE64Encoder;
//exception imports
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import cashier.exceptions.connection.ConnectionDisabledException;
import cashier.exceptions.connection.NullConnectionConstantProvidedException;
import cashier.exceptions.request.InvalidRequestException;

/**
 * <p>
 * This class uses java.net.HttpURLConnection to submit and receive restful data from the Cashier System.  To 
 * use the connection must first be enabled by calling and providing constants to the connect method.  The connection
 * can be disabled and subsequent submissions of Cashier Request objects will cause a ConnectionDisabledException to be thrown.
 * The connection can be re-enabled by once again calling connect with the proper arguments provided.  With a valid Cashier Request object
 * submitted using the Connection.submitRequest(Request request) method a Cashier Response object will be formed and returned.  The connection
 * api key and secret can be verified by calling the connected method.
 * </p>
 *
 * @author Andrew Koerner
 * @version 1.0
 * 
 */
public class Connection {

	/**
	 * HttpURLConneciton object responsible for handling HTTP data transmission and receival. 
	 */
	private static HttpURLConnection connection;
	/**
	 * Cashier API token
	 */
	private static String token;
	/**
	 * Root url of the Cashier server e.g., localhost:3000 or cashierapp.com
	 */
	private static String rootUrl;
	/**
	 * Cashier API key
	 */
	private static String apiKey;
	/**
	 * Cashier API secret
	 */
	private static String apiSecret;
	/**
	 * resource url e.g., customers/all, transactions/1 ect.
	 */
	protected static URL resourceUrl;
	/**
	 * current state of the connection if it is disabled and calls to the Cashier Conneciton will cause Exceptions to be thrown
	 */
	protected static boolean enabled = false;
	/**
	 * The most recent Cashier Request object submitted to the Cashier Connection.
	 */
	public static Request lastRequest;
	/**
	 * The most recent Cashier Response object received from the cashier server.
	 */
	public static Response lastResponse;

	protected Connection() {}

	/**
	 * <p>
	 * Sets the connection constants and enables the connection.
	 * </p>
	 * <p>
	 * Precondition: The connection must first be enabled by providing not null
	 * connection constants.<br /> 
	 * Postcondition: Connection constants set and the
	 * connection is enabled.
	 * </p>
	 * @param rootUrl
	 *            server address. Accepted format examples:
	 *            http://localhost:3000, http://localhost:3000/, localhost:3000,
	 *            localhost:3000/
	 * @param apiKey Cashier API key
	 * @param apiSecret Cashier API secret
	 * @param token Cashier API token
	 * 
	 * @throws NullConnectionConstantProvidedException
	 *             The connection must be enabled by calling
	 *             Connection.setConnectionConstants.
	 */
	public static void connect(String rootUrl, String apiKey,
			String apiSecret, String token)
			throws NullConnectionConstantProvidedException {
		if (rootUrl != null && apiKey != null && apiSecret != null
				&& token != null) {
			if (rootUrl.charAt(rootUrl.length() - 1) != "/".charAt(0)){
				rootUrl += "/";
			}

			if (rootUrl.toLowerCase().indexOf("http://") == -1) {
				rootUrl = "http://" + rootUrl;
			}
			Connection.rootUrl = rootUrl;
			Connection.apiKey = apiKey;
			Connection.apiSecret = apiSecret;
			Connection.token = token;
			Connection.enabled = true;
		} else {
			Connection.enabled = false;
			throw new NullConnectionConstantProvidedException(rootUrl, apiKey,
					apiSecret, token);
		}
	}
	
	/**
	 * <p>
	 * Submits a Cashier Request object to the provided Cashier server.
	 * </p>
	 * <p>
	 * Precondition: The request method must be set to a valid HTTP/1.1 method
	 * and Connection constants must be set and the connection must be enabled.<br />
	 * Postcondition: provides a response object.
	 * </p>
	 * @param request A Cashier API Request object containing a body and method
	 * 
	 * @throws ConnectionDisabled
	 *             The connection must be enabled by calling
	 *             Connection.setConnectionConstants.
	 */
	public static Response submitRequest(Request request) throws ConnectionDisabledException {
		Response response = null;
		Connection.lastRequest = request;
		if (Connection.isEnabled()) {
			if (request.getMethod().equals(Request.POST) || request.getMethod().equals(Request.PUT)) {
				response = Connection.putPost(request);
			} else if (request.getMethod().equals(Request.GET)) {
				response = Connection.get(request);
			} else if (request.getMethod().equals(Request.DELETE)) {
				response = Connection.delete(request);
			}
			
		} else {
			throw new ConnectionDisabledException("");
		}
		if(response != null){
			Connection.lastResponse = response;
			return response;
		}else{
			response = new Response();
			Connection.lastResponse = response;
			return response;
		}

	}

	/**<p>
	 * Connection enabled check.
	 * </p>
	 * <p>
	 * Precondition: None<br /> 
	 * Postcondition: None
	 * </p>
	 * @return boolean if the Connection is enabled then boolean true is
	 *         returned else false.
	 */
	public static boolean isEnabled() {
		return enabled;
	}

	/**
	 * <p>
	 * Disables the connection until new connection constants are provided.
	 * </p>
	 * <p>
	 * Precondition: None <br />
	 * Postcondition: The connection is disabled and
	 * subsequent calls to Connection.connect() or Connection.submitRequest() will throw exceptions.
	 * </p>
	 * @return Boolean if the API key and Secret are valid for the provided
	 *         server then true is returned else false is returned
	 * @throws ConnectionDisabled
	 *             The connection must be enabled by calling
	 *             Connection.setConnectionConstants.
	 */
	public static void disable() {
		Connection.enabled = false;
	}

	/**
	 * <p>
	 * Validates API key secret and token.  If any of the three are incorrect or invalid false will 
	 * be returned.
	 * </p>
	 * <p>
	 * Precondition: The connection must first be enabled by providing not null
	 * connection constants calling Conncection.connect().<br /> 
	 * Postcondition: If the Cashier API key and secret
	 * provided are valid then a boolean true is returned else for invalid api
	 * key and/or secret false is returned. If no connection constants are
	 * provided enabling the connection or the connection was manually disabled
	 * by calling Connection.disable() then ConnectionDisabledExcepiton is
	 * thrown.
	 * </p>
	 * 
	 * @return Boolean if the API key and Secret are valid for the provided
	 *         server then true is returned else false is returned
	 * @throws ConnectionDisabled
	 *             The connection must be enabled by calling
	 *             Connection.setConnectionConstants().
	 */
	public static boolean connected() throws ConnectionDisabledException {
		Request request = new Request("accounts", "account");
		try {
			request.setMethod(Request.GET);
			request.setBody("");
			request.setResourceAddress(request.getTable());
			Response response;
			try {
				response = request.submit();
				if (response.getStatusCode().equals("200")) {
					return true;
				}
			} catch (InvalidRequestException e) {
				return false;
			}
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	/**
	 * Sets up the connection before each request
	 * 
	 * <p>
	 * Precondition: The connection must first be enabled by providing not null
	 * connection constants.<br /> 
	 * Postcondition: A HttpURLConnection is initialized.
	 * </p>
	 * 
	 * @param request Cashier Request object to submit
	 */
	protected static void initialize(Request request) {
		try {
			BASE64Encoder enc = new sun.misc.BASE64Encoder();
			Connection.resourceUrl = new URL(Connection.rootUrl + "~"
					+ Connection.token + "/" + request.getResourceAddress());
			Connection.connection = (HttpURLConnection) Connection.resourceUrl
					.openConnection();
			Connection.connection.setRequestProperty("Accept",
					"application/json");
			Connection.connection.setRequestProperty("Content-Type",
					"application/json");
			Connection.connection.setRequestProperty("charset", "utf-8");
			Connection.connection.setRequestMethod(request.getMethod());
			Connection.connection
					.setRequestProperty(
							"Authorization",
							"Basic "
									+ enc.encode(
											(Connection.apiKey + ":" + Connection.apiSecret)
													.getBytes()).replace("\n",
											""));
			Connection.connection.setDoInput(true);
			Connection.connection.setDoOutput(true);
		} catch (java.net.MalformedURLException mue) {
			System.out.println("Malformed URL");
		} catch (java.io.IOException ioe) {
			System.out.println();
			System.out.println("Error Message:");
			System.out.println(ioe.getMessage().split("for")[0]);
			System.out.println(ioe.getMessage().split("for")[1]);
		}

	}
	
	private static Response get(Request request) {
	
		Response response = new Response();
		Connection.initialize(request);
		try {
			response.setStatusCode(Integer.toString(Connection.connection.getResponseCode()));
			BufferedReader rd = new BufferedReader(new InputStreamReader(Connection.connection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();

			Connection.connection.disconnect();
			response.setBody(sb.toString());
			return response;
		} catch (UnsupportedEncodingException e) {
			try {
				response.setStatusCode(Integer.toString(Connection.connection.getResponseCode()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} catch (IOException e) {
			try {
				response.setStatusCode(Integer.toString(Connection.connection.getResponseCode()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private static Response putPost(Request request) {
		Response response = new Response();
		Connection.initialize(request);
	
		try {
			OutputStream out = Connection.connection.getOutputStream();
			Writer writer;
			writer = new OutputStreamWriter(out, "UTF-8");
			writer.write(request.getBody());
			writer.close();
			out.close();

			response.setStatusCode(Integer.toString(Connection.connection
					.getResponseCode()));
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					Connection.connection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();

			Connection.connection.disconnect();
			response.setBody(sb.toString());
			System.out.println(response.getBody());
			return response;
		} catch (UnsupportedEncodingException e) {
			try {
				response.setStatusCode(Integer.toString(Connection.connection.getResponseCode()));
				Connection.lastResponse = response;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (IOException e) {
				try {
					response.setStatusCode(Integer.toString(Connection.connection.getResponseCode()));
					Connection.lastResponse = response;
				} catch (IOException e1) {}
		}
	
		return response;
	}

	private static Response delete(Request request) {
		Response response = new Response();

		Connection.initialize(request);
		try {
			response.setStatusCode(Integer.toString(Connection.connection
					.getResponseCode()));
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					Connection.connection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();

			Connection.connection.disconnect();
			response.setBody(sb.toString());
		} catch (UnsupportedEncodingException e) {
			try {
				response.setStatusCode(Integer.toString(Connection.connection.getResponseCode()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			try {
				response.setStatusCode(Integer.toString(Connection.connection.getResponseCode()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return response;
	}
	
	
    ///////////////////////
	//Getters and Setters//
	///////////////////////
	/**
	 * <p>
	 * accessor/getter Returns the api token
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: none
	 *</p>
	 * @return String token
	 *
	 */
	public static String getToken() {
		return Connection.token;
	}

	/**
	 * <p>
	 * accessor/getter Returns the rootUrl
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: none
	 *</p>
	 * @return String rootUrl
	 *
	 */
	public static String getRootUrl() {
		return Connection.rootUrl;
	}

	/**
	 * <p>
	 * accessor/getter Returns the apiKey
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: none
	 *</p>
	 * @return String apiKey
	 *
	 */
	public static String getApiKey() {
		return Connection.apiKey;
	}

	/**
	 * <p>
	 * accessor/getter Returns the apiSecret
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: none
	 *</p>
	 * @return String apiSecret
	 *
	 */
	public static String getApiSecret() {
		return Connection.apiSecret;
	}

	/**
	 * <p>
	 * setter/mutator for the the token.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: token is set to the argument value provided
	 *</p>
	 * @param token
	 *
	 */
	public static void setToken(String token) {
		Connection.token = token;
	}

	/**
	 * <p>
	 * setter/mutator for the the rootUrl.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: rootUrl is set to the argument value provided
	 *</p>
	 * @param rootUrl
	 *
	 */
	public static void setRootUrl(String rootUrl) {
		Connection.rootUrl = rootUrl;
	}

	/**
	 * <p>
	 * setter/mutator for the the apiKey.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: apiKey is set to the argument value provided
	 *</p>
	 * @param apiKey
	 *
	 */
	public static void setApiKey(String apiKey) {
		Connection.apiKey = apiKey;
	}

	/**
	 * <p>
	 * setter/mutator for the the apiSecret.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: apiSecret is set to the argument value provided
	 *</p>
	 * @param apiSecret
	 *
	 */
	public static void setApiSecret(String apiSecret) {
		Connection.apiSecret = apiSecret;
	}
	
}
