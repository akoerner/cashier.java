package cashier.tools;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import cashier.exceptions.connection.AuthenticationFailureException;
import cashier.exceptions.connection.ConnectionDisabledException;
import cashier.net.Connection;
import cashier.net.Query;
import cashier.net.Resource;
import cashier.resource.Customer;
import cashier.resource.Entry;
import cashier.resource.Invoice;
import cashier.resource.Item;
import cashier.resource.Line;
import cashier.resource.Location;
import cashier.resource.Person;
import cashier.resource.Shift;
import cashier.resource.Store;
import cashier.resource.Ticket;
import cashier.resource.Till;
import cashier.resource.Timecard;
import cashier.resource.Transaction;
import cashier.resource.Unit;
import cashier.resource.User;

public class DataSlammer implements Runnable {
	
	//resources
		public static final String CUSTOMER = "Customer";
		public static final String ENTRY = "Entry";
		public static final String INVOICE = "Invoice";
		public static final String ITEM = "Item";
		public static final String LINE = "Line";
		public static final String LOCATION = "Location";
		public static final String PERSON = "Person";
		public static final String SHIFT = "Shift";
		public static final String STORE = "Store";
		public static final String TICKET = "Ticket";
		public static final String TILL = "Till";
		public static final String TIMECARD = "Timecard";
		public static final String TRANSACTION = "Transaciton";
		public static final String UNIT = "Unit";
	    public static final String USER = "User";
		
		//methods
		public static final String ALL = "all";
		public static final String FIND = "find";
		public static final String DESTORY = "destroy";
		public static final String QUERY = "query";
	
	private ResourceLoader tempResourceLoader;
	private Thread runner;
	
	private ArrayList<ResourceLoader> resourceLoaders;
	
	public static final int ITEM_HASH = "Item".hashCode();
	public DataSlammer() throws AuthenticationFailureException, ConnectionDisabledException {
		this.resourceLoaders = new ArrayList<ResourceLoader>();
		if(!Connection.connected())throw new AuthenticationFailureException("");
		runner = new Thread(this);
		runner.start();
	}

	public void run(){
		while(true){
			for(int numberOfResourceLoaders = 0; numberOfResourceLoaders<this.resourceLoaders.size();numberOfResourceLoaders++){
				tempResourceLoader = this.resourceLoaders.get(numberOfResourceLoaders);
				if(tempResourceLoader != null){	
					if(!tempResourceLoader.loaded){
						try {
							this.resourceLoaders.set(numberOfResourceLoaders, this.loadResource(tempResourceLoader));
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ConnectionDisabledException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			try {
				runner.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int addResourceLoader(String objectName, String methodName,
			String arg1, String arg2, Query query) {
		ResourceLoader tempResourceLoader = new ResourceLoader(objectName, methodName, arg1, arg2, query);
		this.resourceLoaders.add(tempResourceLoader);
		return this.resourceLoaders.indexOf(tempResourceLoader);
	}

	public ArrayList<Resource> getResultAtIndex(int index) {
		return this.resourceLoaders.get(index).result;
	}

	public boolean isResultAtIndexReady(int index) {
		return this.resourceLoaders.get(index).loaded;
	}
	
	public int size(){
		return this.resourceLoaders.size();
	}

	public boolean areAllResultsReady() {
		boolean allReady = true;
		for (int resourceLoaderNumber = 0; resourceLoaderNumber < this.resourceLoaders
				.size(); resourceLoaderNumber++) {
			allReady = allReady
					&& this.resourceLoaders.get(resourceLoaderNumber).loaded;
		}
		return allReady;
	}

	private ResourceLoader loadResource(ResourceLoader resourceLoader) throws NumberFormatException, ConnectionDisabledException {

		String objectName = resourceLoader.objectName;
		String methodName = resourceLoader.methodName;
		String arg1 = resourceLoader.arg1;
		String arg2 = resourceLoader.arg2;
		Query query = resourceLoader.query;
	
		
	
		
		if(objectName.equalsIgnoreCase("Item")){
			if (methodName.equalsIgnoreCase("destroy")) {
				Item.destroy(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("find")) {
				resourceLoader.result = Item.find(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("all") && (arg1 != null) && (arg2 != null)) {
				resourceLoader.result = Item.all(Integer.parseInt(arg1), Integer.parseInt(arg2));
			} else if (methodName.equalsIgnoreCase("all")) {
				resourceLoader.result = Item.all();
			} else if (methodName.equalsIgnoreCase("query")) {
				resourceLoader.result = Item.query(query);
			}
		
		}else if(objectName.equalsIgnoreCase("Line")){
			if (methodName.equalsIgnoreCase("destroy")) {
				Line.destroy(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("find")) {
				resourceLoader.result = Line.find(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("all") && arg1 != null && arg2 != null) {
				resourceLoader.result = Line.all(Integer.parseInt(arg1), Integer.parseInt(arg2));
			} else if (methodName.equalsIgnoreCase("all")) {
				resourceLoader.result = Line.all();
			} else if (methodName.equalsIgnoreCase("query")) {
				resourceLoader.result = Line.query(query);
			}

		}else if(objectName.equalsIgnoreCase("Location")){
			if (methodName.equalsIgnoreCase("destroy")) {
				Location.destroy(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("find")) {
				resourceLoader.result = Location.find(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("all") && arg1 != null&& arg2 != null) {
				resourceLoader.result = Location.all(Integer.parseInt(arg1),
						Integer.parseInt(arg2));
			} else if (methodName.equalsIgnoreCase("all")) {
				resourceLoader.result = Location.all();
			} else if (methodName.equalsIgnoreCase("query")) {
				resourceLoader.result = Location.query(query);
			}
			

		}else if(objectName.equalsIgnoreCase("Person")){
			if (methodName.equalsIgnoreCase("destroy")) {
				Person.destroy(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("find")) {
				resourceLoader.result = Person.find(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("all") && arg1 != null
					&& arg2 != null) {
				resourceLoader.result = Person.all(Integer.parseInt(arg1),
						Integer.parseInt(arg2));
			} else if (methodName.equalsIgnoreCase("all")) {
				resourceLoader.result = Person.all();
			} else if (methodName.equalsIgnoreCase("query")) {
				resourceLoader.result = Person.query(query);
			}
			

		}else if(objectName.equalsIgnoreCase("Shift")){
			if (methodName.equalsIgnoreCase("destroy")) {
				Shift.destroy(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("find")) {
				resourceLoader.result = Shift.find(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("all") && arg1 != null
					&& arg2 != null) {
				resourceLoader.result = Shift.all(Integer.parseInt(arg1),
						Integer.parseInt(arg2));
			} else if (methodName.equalsIgnoreCase("all")) {
				resourceLoader.result = Shift.all();
			} else if (methodName.equalsIgnoreCase("query")) {
				resourceLoader.result = Shift.query(query);
			}
		

		}else if(objectName.equalsIgnoreCase("Store")){
			if (methodName.equalsIgnoreCase("destroy")) {
				Store.destroy(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("find")) {
				resourceLoader.result = Store.find(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("all") && arg1 != null
					&& arg2 != null) {
				resourceLoader.result = Store.all(Integer.parseInt(arg1),
						Integer.parseInt(arg2));
			} else if (methodName.equalsIgnoreCase("all")) {
				resourceLoader.result = Store.all();
			} else if (methodName.equalsIgnoreCase("query")) {
				resourceLoader.result = Store.query(query);
			}
			

		}else if(objectName.equalsIgnoreCase("Ticket")){
			if (methodName.equalsIgnoreCase("destroy")) {
				Ticket.destroy(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("find")) {
				resourceLoader.result = Ticket.find(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("all") && arg1 != null
					&& arg2 != null) {
				resourceLoader.result = Ticket.all(Integer.parseInt(arg1),
						Integer.parseInt(arg2));
			} else if (methodName.equalsIgnoreCase("all")) {
				resourceLoader.result = Ticket.all();
			} else if (methodName.equalsIgnoreCase("query")) {
				resourceLoader.result = Ticket.query(query);
			}


		}else if(objectName.equalsIgnoreCase("Till")){
			if (methodName.equalsIgnoreCase("destroy")) {
				Till.destroy(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("find")) {
				resourceLoader.result = Till.find(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("all") && arg1 != null
					&& arg2 != null) {
				resourceLoader.result = Till.all(Integer.parseInt(arg1),
						Integer.parseInt(arg2));
			} else if (methodName.equalsIgnoreCase("all")) {
				resourceLoader.result = Till.all();
			} else if (methodName.equalsIgnoreCase("query")) {
				resourceLoader.result = Till.query(query);
			}

		}else if(objectName.equalsIgnoreCase("Timecard")){
			if (methodName.equalsIgnoreCase("destroy")) {
				Timecard.destroy(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("find")) {
				resourceLoader.result = Timecard.find(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("all") && arg1 != null
					&& arg2 != null) {
				resourceLoader.result = Timecard.all(Integer.parseInt(arg1),
						Integer.parseInt(arg2));
			} else if (methodName.equalsIgnoreCase("all")) {
				resourceLoader.result = Timecard.all();
			} else if (methodName.equalsIgnoreCase("query")) {
				resourceLoader.result = Timecard.query(query);
			}


		}else if(objectName.equalsIgnoreCase("Transaction")){
			if (methodName.equalsIgnoreCase("destroy")) {
				Transaction.destroy(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("find")) {
				resourceLoader.result = Transaction.find(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("all") && arg1 != null
					&& arg2 != null) {
				resourceLoader.result = Transaction.all(Integer.parseInt(arg1),
						Integer.parseInt(arg2));
			} else if (methodName.equalsIgnoreCase("all")) {
				resourceLoader.result = Transaction.all();
			} else if (methodName.equalsIgnoreCase("query")) {
				resourceLoader.result = Transaction.query(query);
			}

		}else if(objectName.equalsIgnoreCase("Unit")){
			if (methodName.equalsIgnoreCase("destroy")) {
				Unit.destroy(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("find")) {
				resourceLoader.result = Unit.find(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("all") && arg1 != null
					&& arg2 != null) {
				resourceLoader.result = Unit.all(Integer.parseInt(arg1),
						Integer.parseInt(arg2));
			} else if (methodName.equalsIgnoreCase("all")) {
				resourceLoader.result = Unit.all();
			} else if (methodName.equalsIgnoreCase("query")) {
				resourceLoader.result = Unit.query(query);
			}

		}else if(objectName.equalsIgnoreCase("User")){
			if (methodName.equalsIgnoreCase("destroy")) {
				User.destroy(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("find")) {
				resourceLoader.result = User.find(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("all") && arg1 != null && arg2 != null) {
				resourceLoader.result = User.all(Integer.parseInt(arg1),
						Integer.parseInt(arg2));
			} else if (methodName.equalsIgnoreCase("all")) {
				resourceLoader.result = User.all();
			} else if (methodName.equalsIgnoreCase("query")) {
				resourceLoader.result = User.query(query);
			}

		}else if(objectName.equalsIgnoreCase("Customer")){
			if (methodName.equalsIgnoreCase("destroy")) {
				Customer.destroy(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("find")) {
				resourceLoader.result = Customer.find(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("all") && arg1 != null
					&& arg2 != null) {
				resourceLoader.result = Customer.all(Integer.parseInt(arg1),
						Integer.parseInt(arg2));
			} else if (methodName.equalsIgnoreCase("all")) {
				resourceLoader.result = Customer.all();
			} else if (methodName.equalsIgnoreCase("query")) {
				resourceLoader.result = Customer.query(query);
			}

		}else if(objectName.equalsIgnoreCase("Entry")){
			if (methodName.equalsIgnoreCase("destroy")) {
				Entry.destroy(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("find")) {
				resourceLoader.result = Entry.find(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("all") && arg1 != null
					&& arg2 != null) {
				resourceLoader.result = Entry.all(Integer.parseInt(arg1),
						Integer.parseInt(arg2));
			} else if (methodName.equalsIgnoreCase("all")) {
				resourceLoader.result = Entry.all();
			} else if (methodName.equalsIgnoreCase("query")) {
				resourceLoader.result = Entry.query(query);
			}

		}else if(objectName.equalsIgnoreCase("Invoice")){
			if (methodName.equalsIgnoreCase("destroy")) {
				Invoice.destroy(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("find")) {
				resourceLoader.result = Invoice.find(Integer.parseInt(arg1));
			} else if (methodName.equalsIgnoreCase("all") && arg1 != null
					&& arg2 != null) {
				resourceLoader.result = Invoice.all(Integer.parseInt(arg1),
						Integer.parseInt(arg2));
			} else if (methodName.equalsIgnoreCase("all")) {
				resourceLoader.result = Invoice.all();
			} else if (methodName.equalsIgnoreCase("query")) {
				resourceLoader.result = Invoice.query(query);
			}

		}
		
		
		
		
		
		
		resourceLoader.loaded = true;
		return resourceLoader;

	}

	public class ResourceLoader{

		/**
		 * argument value
		 **/
		protected ArrayList<Resource> result;
		protected String objectName;
		protected String methodName;
		protected String arg1;
		protected String arg2;
		protected Query query;
		protected boolean loaded = false;

		public ResourceLoader(String objectName, String methodName,
				String arg1, String arg2, Query query) {
			this.objectName = objectName;
			this.methodName = methodName;
			this.arg1 = arg1;
			this.arg2 = arg2;
			this.query = query;
		}
	}

}
