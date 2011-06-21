
package cashier.net;

/**
 * <p>
 * This class is a wrapper to submit sql prepared statements against the Cashier system
 * </p>
 * <p>
 * This class uses a linked list implementation to store each argument for the prepared statement.
 * </p>
 * <p>
 * Example:<br />
 * sql prepared statement : "id = ? AND name = ?"<br />
 * set the limit to 1 and the offset to 0 by providing them in the constructor of calling the
 * appropriate setter methods.<br />
 * if the desired values for id and name were 10 and Joe Blow respectively then
 * first call *.addPreparedValue(10) then *.addPreparedValue(Joe Blow)
 * </p>
 * <p>
 * once converted to json the result would be:<br />
 * {"query":{"statement":"id = ? AND name = ?","params":["10", "Joe Blow"],"limit":1, "offset":0}}
 * </p>
 * Example Code:
 * <code>
 * <br />
 *   ArrayList<Customer> customers;<br />
 *   Query query = new Query(Integer.toString(5), Integer.toString(5), Customer.NOTES+"= ? AND "+Customer.CREDIT+" = ?");<br />
 *   query.addPreparedValue("Hello, World!");<br />
 *   query.addPreparedValue("3000");<br />
 *  try {<br />
 *   customers = Customer.query(query);<br />
 * } catch (ConnectionDisabledException e) {<br />
 * 		System.out.println("The connection is disabled");<br />
 * }
 * 
 * 
 * </code>
 * @author Andrew Koerner
 * @version 1.0
 * 
 */
public class Query {
	
	/** 
	 * Upper limit of Cashier resource objects to return in the sql prepared query.
	 **/
	private String limit = "0";
	/** 
	 * The offset in witch to start returning Cashier resource objects in reference to the resource id.
	 **/
	private String offset = "0";
	/** 
	 * The sql prepared statement.
	 **/
	private String sqlPreparedStatement = "";
	/** 
	 * Head node of PreparedValue in the linked list  head.next() contains the next PreparedValue in the list.  
	 **/
	private PreparedValue head = null;
	/** 
	 * The current node that has been iterated to.
	 **/
	protected PreparedValue pointer = null;
	/** 
	 * The number of prepared values currently being stored.
	 **/
	public int length = 0;
	
	/* -- Constructors -- */
	 
	/**
	  * default, Creates a new <code>Query</code> instance.
	  **/
	public Query(){}
	
	/**
	  * Creates a new <code>Query</code> instance.
      * @param limit Upper limit of Cashier resource objects to return in the sql prepared query.
	  * @param offset The offest in witch to start returning Cashier resource objects in reference to the resource id. 
	  * @param sqlPreparedStatement the sql prepared statement to submit in the query.
	  **/
	public Query(String limit, String offset,String sqlPreparedStatement){
		this.limit = limit;
		this.offset = offset;
		this.sqlPreparedStatement = sqlPreparedStatement;
	}
	
	/**
	 * <p>
	 * Adds a PreparedValue to the linked list given a value as a string a PreparedValue is created and added to the list.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: The length in incremented one and a PreparedValue is added to the linked list.
	 * </p>
	 * 
	 * @param value the string value to be added to the PreparedValue linked list.
	 *
	 */
	public void addPreparedValue(String value){
		if(this.head != null){
			PreparedValue pointer = this.head;
			while(pointer.getNext() != null){
				pointer = pointer.getNext();
			}
			if(pointer.getNext() == null){
				this.length++;
				pointer.setNext(new PreparedValue(value));
			}
		}else{
			this.length++;
			head = new PreparedValue(value);
		}
	}
	
	/**
	 * <p>
	 * Simple check to determine if the PreparedValue linked list pointer has reached the end of the list or not.  
	 * If PreparedValues still remain in the list then true is returned else false is returned.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: none.
	 * </p>
	 *
	 * @return boolean if there are elements still in the list true is returned
	 * 
	 */
	public boolean hasNextPreparedValue(){
		
		if(head == null){
			return false;
		}else{
			if(this.pointer == null){
				return true;
			}
			if(this.pointer.getNext() == null){
				return false;
			}else{
				return true;
			}
		}
	}
	
	/**
	 * <p>
	 * Returns the next PreparedValue in the linked list and advances the pointer.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: The PreparedValue pointer is advanced.
	 * </p>
	 *
	 * @return PreparedValue the next PreparedValue in the list returns null if no items exist.
	 * 
	 */
	public PreparedValue getNextPreparedValue(){
		if(this.pointer == null){
			this.pointer = this.head;
			return this.pointer;
		}else{
			this.pointer = this.pointer.getNext();
			return this.pointer;
		}
	}
	
	/**
	 * <p>
	 * Resets the PreparedValue pointer to the linked list head.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: The PreparedValue pointer is reset.
	 * </p>
	 * 
	 */
	public void resetPreparedValuePointer(){
		this.pointer = null;
	}
	
	/**
	 * <p>
	 * Returns the limit.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: none
	 *</p>
	 * @return String limit.
	 *
	 */
	public String getLimit(){
		return this.limit;
	}
	
	/**
	 * <p>
	 * accessor/getterReturns the offset.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: none
	 *</p>
	 * @return String offset
	 *
	 */
	public String getOffset(){
		return this.offset;
	}
	
	/**
	 * <p>
	 * accessor/getter Returns the sql prepared statement
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: none
	 *</p>
	 * @return String sqlPreparedStatement
	 *
	 */
	public String getSqlPreparedStatement(){
		return this.sqlPreparedStatement;
	}
	
	/**
	 * <p>
	 * setter/mutator for the the Query limit.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: limit is set to the argument value provided
	 *</p>
	 * @param limit the upper limit of objects to return or the max objects to return.
	 *
	 */
	public void setLimit(String limit){
		this.limit = limit;
	}
	
	/**
	 * <p>
	 * setter/mutator for the the Query offset.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: offset is set to the argument value provided
	 *</p>
	 * @param offset the number of objects to offset before continuing to return.
	 *
	 */
	public void setOffset(String offset){
		this.offset = offset;
	}
	
	/**
	 * <p>
	 * setter/mutator for the the Query sqlPreparedStatement.
	 * </p>
	 * <p>
	 * Precondition: none<br />
	 * Postcondition: sqlPreparedStatement is set to the argument value provided
	 *</p>
	 * @param sqlPreparedStatement A prepared sql statement to submit with the query.
	 *
	 */
	public void setSqlPreparedStatement(String sqlPreparedStatement){
		this.sqlPreparedStatement = sqlPreparedStatement;
	}
	
	/**
	 * <p>
	 * This is a linked list data structure to hold argument values for the Query sql prepared statement.
	 * </p>
	 * <p>
	 * A PrepardValue stores a single String and a reference to the next PreparedValue.  Reading a prepared statement from 
	 * left to right, the argument PreparedValues will be applied in the order that they are inserted into the link list first in first out.
	 * Add elements to the link list in the order that they appear in the prepared statement.
	 * </p>
	 *
	 * @author Andrew Koerner
	 * @version 1.0
	 * 
	 */
	public class PreparedValue {
		
		/** 
		 * argument value
		 **/
		protected String value;
		/** 
		 * Reference to the next PreparedValue
		 **/
		protected PreparedValue next;


		/* -- Constructors -- */
		 
		/**
		  * default, Creates a new <code>PreparedValue</code> instance.
		  **/
		private PreparedValue(){}
		
		/**
		  * Creates a new <code>PreparedValue</code> instance.
	      * @param value argument value to store.
		  **/
		private PreparedValue(String value){
			this.value = value;
		}
		
		/**
		 * <p>
		 * accessor/getter Returns the value
		 * </p>
		 * <p>
		 * Precondition: none<br />
		 * Postcondition: none
		 *</p>
		 * @return String value
		 *
		 */
		public String getValue() {
			return value;
		}

		/**
		 * <p>
		 * accessor/getter Returns the reference to the next PreparedValue in the list
		 * </p>
		 * <p>
		 * Precondition: none<br />
		 * Postcondition: none
		 *</p>
		 * @return PreparedValue next PreparedValue in the list
		 *
		 */
		public PreparedValue getNext() {
			return next;
		}

		/**
		 * <p>
		 * setter/mutator for the the PreparedValue value.
		 * </p>
		 * <p>
		 * Precondition: none<br />
		 * Postcondition: value is set to the argument value provided
		 *</p>
		 * @param value The argument value.
		 *
		 */
		public void setValue(String value) {
			this.value = value;
		}

		/**
		 * <p>
		 * setter/mutator for the the PreparedValue reference to the next PreparedValue.
		 * </p>
		 * <p>
		 * Precondition: none<br />
		 * Postcondition: next is set to the argument value provided
		 *</p>
		 * @param next A PreparedValue object to add to the list.
		 *
		 */
		public void setNext(PreparedValue next) {
			this.next = next;
		}
	}
}


