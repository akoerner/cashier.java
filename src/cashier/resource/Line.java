package cashier.resource;

import java.util.ArrayList;
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
public class Line extends Resource {

	@Expose(serialize = false, deserialize = false) public static final String TABLE = "lines";
	@Expose(serialize = false, deserialize = false) public static final String RESOURCE = "line";
	@SerializedName(Line.RESOURCE)
	@Expose(serialize = true, deserialize = true) private Data data;
	
	/*-Resource Constants-*/
	@Expose(serialize = false, deserialize = false) public static final String ID = "id";
	@Expose(serialize = false, deserialize = false) public static final String TRANSACTION_ID = "transaction_id";
	@Expose(serialize = false, deserialize = false) public static final String ITEM_ID = "item_id";
	@Expose(serialize = false, deserialize = false) public static final String UNIT_ID = "unit_id";
	@Expose(serialize = false, deserialize = false) public static final String INVOICE_ID  = "invoice_id";
	@Expose(serialize = false, deserialize = false) public static final String TITLE = "title";
	@Expose(serialize = false, deserialize = false) public static final String QUANTITY = "quantity";
	@Expose(serialize = false, deserialize = false) public static final String CONDITION = "condition";
	@Expose(serialize = false, deserialize = false) public static final String DISCOUNT = "discount";
	@Expose(serialize = false, deserialize = false) public static final String AMOUNT = "amount";
	@Expose(serialize = false, deserialize = false) public static final String TRADE_VALUE = "trade_value";
	@Expose(serialize = false, deserialize = false) public static final String CASH_VALUE = "cash_value";
	@Expose(serialize = false, deserialize = false) public static final String TRADED = "traded";
	@Expose(serialize = false, deserialize = false) public static final String TAXABLE = "taxable";
	@Expose(serialize = false, deserialize = false) public static final String LOCKED = "locked";
	
	private class Data {
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Line.ID) public int id;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Line.TRANSACTION_ID) public int transactionId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Line.ITEM_ID) public int itemId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Line.UNIT_ID) public int unitId; 
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Line.INVOICE_ID) public int invoiceId;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Line.TITLE) public String title;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Line.QUANTITY) public int quantity;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Line.CONDITION) public double condition;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Line.DISCOUNT) public double discount;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Line.AMOUNT) public int amount;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Line.TRADE_VALUE) public int tradeValue;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Line.CASH_VALUE) public int cashValue;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Line.TRADED) public boolean traded;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Line.TAXABLE) public boolean taxable;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Line.LOCKED) public boolean locked;
	}

	public Line() {
		super();
		this.data = new Data();
		this.setTable(Line.TABLE);
		this.setResource(Line.RESOURCE);
	}

	@Override
	public String toString() {
		return "Line [id=" + this.data.id + ", transactionId="
				+ this.data.transactionId + ", itemId=" + this.data.itemId
				+ ", unitId=" + this.data.unitId + ", invoiceId=" + this.data.invoiceId
				+ ", title=" + this.data.title + ", quantity="
				+ this.data.quantity + ", condition=" + this.data.condition
				+ ", discount=" + this.data.discount + ", amount="
				+ this.data.amount + ", tradeValue=" + this.data.tradeValue
				+ ", cashValue=" + this.data.cashValue + ", traded="
				+ this.data.traded + ", taxable=" + this.data.taxable
				+ ", locked=" + this.data.locked + "]";
	}

	public static ArrayList<Resource> find(int id) throws ConnectionDisabledException {
		return Resource.find(id, Line.RESOURCE, Line.TABLE);
	}

	public static ArrayList<Resource> all() throws ConnectionDisabledException {
		System.out.println("here");
		return Resource.all(Line.RESOURCE, Line.TABLE);
	}

	public static ArrayList<Resource> all(int limit, int offset) throws ConnectionDisabledException {
		return Resource.all(limit, offset, Line.RESOURCE, Line.TABLE);
	}

	public static Response destroy(Integer id) throws ConnectionDisabledException {
		return Resource.destroy(id, Line.RESOURCE, Line.TABLE);
	}

	public static ArrayList<Resource> query(Query query) throws ConnectionDisabledException {
		return Resource.query(query , Line.RESOURCE, Line.TABLE);
	}
    ///////////////////////
	//Getters and Setters//
	///////////////////////	
	public int getId() {
		return this.data.id;
	}

	public int getTransactionId() {
		return this.data.transactionId;
	}

	public int getItemId() {
		return this.data.itemId;
	}

	public int getUnitId() {
		return this.data.unitId;
	}

	public int getInvoiceId() {
		return this.data.invoiceId;
	}

	public String getTitle() {
		return this.data.title;
	}

	public int getQuantity() {
		return this.data.quantity;
	}

	public double getCondition() {
		return this.data.condition;
	}

	public double getDiscount() {
		return this.data.discount;
	}

	public int getAmount() {
		return this.data.amount;
	}

	public int getTradeValue() {
		return this.data.tradeValue;
	}

	public int getCashValue() {
		return this.data.cashValue;
	}
	
	public Line getThis(){
		return this;
	}

	public boolean isTraded() {
		return this.data.traded;
	}

	public boolean isTaxable() {
		return this.data.taxable;
	}

	public boolean isLocked() {
		return this.data.locked;
	}

	public void setId(int id) {
		this.data.id = id;
	}

	public void setTransactionId(int transactionId) {
		this.data.transactionId = transactionId;
	}

	public void setItemId(int itemId) {
		this.data.itemId = itemId;
	}

	public void setUnitId(int unitId) {
		this.data.unitId = unitId;
	}

	public void setInvoiceId(int invoiceId) {
		this.data.invoiceId = invoiceId;
	}

	public void setTitle(String title) {
		this.data.title = title;
	}

	public void setQuantity(int quantity) {
		this.data.quantity = quantity;
	}

	public void setCondition(double condition) {
		this.data.condition = condition;
	}

	public void setDiscount(double discount) {
		this.data.discount = discount;
	}

	public void setAmount(int amount) {
		this.data.amount = amount;
	}

	public void setTradeValue(int tradeValue) {
		this.data.tradeValue = tradeValue;
	}

	public void setCashValue(int cashValue) {
		this.data.cashValue = cashValue;
	}

	public void setTraded(boolean traded) {
		this.data.traded = traded;
	}

	public void setTaxable(boolean taxable) {
		this.data.taxable = taxable;
	}

	public void setLocked(boolean locked) {
		this.data.locked = locked;
	}

}