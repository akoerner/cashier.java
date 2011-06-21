import java.net.ProtocolException;
import java.util.ArrayList;

import cashier.*;
import cashier.exceptions.connection.ConnectionDisabledException;
import cashier.exceptions.connection.NullConnectionConstantProvidedException;
import cashier.exceptions.request.InvalidRequestException;
import cashier.net.Connection;
import cashier.net.Query;
import cashier.net.Query.*;
import cashier.net.Request;
import cashier.net.Resource;
import cashier.net.Response;
import cashier.resource.*;
public class Tester {


	public static void main(String[] args){
		String apiKey = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b"; 
		String apiSecret = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
		String url = "cashier.local";
		String token = "example";
		try {
			Connection.connect(url, apiKey, apiSecret, token);
		} catch (NullConnectionConstantProvidedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}

}
