package cashier.resource;

import java.util.ArrayList;
import java.util.HashMap;
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
public class Item extends Resource {

	@Expose(serialize = false, deserialize = false) public static final String TABLE = "items";
	@Expose(serialize = false, deserialize = false) public static final String RESOURCE = "item";
	@SerializedName(Item.RESOURCE)
	@Expose(serialize = true, deserialize = true) private Data data;
	
	/*-Resource Constants-*/
	@Expose(serialize = false, deserialize = false) public static final String ID = "id";
	@Expose(serialize = false, deserialize = false) public static final String TITLE = "title";
	@Expose(serialize = false, deserialize = false) public static final String DESCRIPTION = "description";
	@Expose(serialize = false, deserialize = false) public static final String IMAGE = "image";
	@Expose(serialize = false, deserialize = false) public static final String TAGS = "tags";
	@Expose(serialize = false, deserialize = false) public static final String PROPERTIES = "properties";
	@Expose(serialize = false, deserialize = false) public static final String SKU = "sku";
	@Expose(serialize = false, deserialize = false) public static final String PRICE = "price";
	@Expose(serialize = false, deserialize = false) public static final String TRADE_VALUE = "trade_value";
	@Expose(serialize = false, deserialize = false) public static final String CASH_VALUE = "cash_value";
	@Expose(serialize = false, deserialize = false) public static final String TAXABLE = "taxable";
	@Expose(serialize = false, deserialize = false) public static final String DISCOUNTABLE = "discountable";
	@Expose(serialize = false, deserialize = false) public static final String LOCKED = "locked";
	@Expose(serialize = false, deserialize = false) public static final String ACTIVE = "active";
	
	private class Data {
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Item.ID) public int id;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Item.TITLE) public String title;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Item.DESCRIPTION) public String description;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Item.IMAGE) public String image;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Item.TAGS) public String[] tags;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Item.PROPERTIES) public HashMap<String, String>[] properties;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Item.SKU) public String sku;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Item.PRICE) public int price;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Item.TRADE_VALUE) public int tradeValue;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Item.CASH_VALUE) public int cashValue;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Item.TAXABLE) public boolean taxable;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Item.DISCOUNTABLE) public boolean discountable;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Item.LOCKED) public boolean locked;
		@Expose(serialize = true, deserialize = true) 
		@SerializedName(Item.ACTIVE) public boolean active;
	}

	public Item() {
		super();
		this.data = new Data();
		this.setTable(Item.TABLE);
		this.setResource(Item.RESOURCE);
	}
	
	@Override
	public String toString() {
		return "Item [id=" + this.data.id + ", title=" + this.data.title
				+ ", description=" + this.data.description + ", image=" + this.data.image
				+ ", tags=" + this.data.tags + ", properties="
				+ this.data.properties + ", sku=" + this.data.sku + ", price="
				+ this.data.price + ", tradeValue=" + this.data.tradeValue
				+ ", cashValue=" + this.data.cashValue + ", taxable=" + this.data.taxable
				+ ", discountable=" + this.data.discountable + ", locked="
				+ this.data.locked + ", active=" + this.data.active + "]";
	}

	public static ArrayList<Resource> find(int id) throws ConnectionDisabledException {
		return Resource.find(id, Item.RESOURCE, Item.TABLE);
	}

	public static ArrayList<Resource> all() throws ConnectionDisabledException {
		return Resource.all(Item.RESOURCE, Item.TABLE);
	}

	public static ArrayList<Resource> all(int limit, int offset) throws ConnectionDisabledException {
		return Resource.all(limit, offset, Item.RESOURCE, Item.TABLE);
	}

	public static Response destroy(Integer id) throws ConnectionDisabledException {
		return Resource.destroy(id, Item.RESOURCE, Item.TABLE);
	}

	public static ArrayList<Resource> query(Query query) throws ConnectionDisabledException {
		return Resource.query(query, Item.RESOURCE, Item.TABLE);
	}
	
    ///////////////////////
	//Getters and Setters//
	///////////////////////	
	public int getId() {
		return this.data.id;
	}

	public String getTitle() {
		return this.data.title;
	}

	public String getDescription() {
		return this.data.description;
	}

	public String getImage() {
		return this.data.image;
	}

	public String[] getTags() {
		return this.data.tags;
	}

	public HashMap<String, String>[] getProperties() {
		return this.data.properties;
	}

	public String getSku() {
		return this.data.sku;
	}

	public int getPrice() {
		return this.data.price;
	}

	public int getTradeValue() {
		return this.data.tradeValue;
	}

	public int getCashValue() {
		return this.data.cashValue;
	}
	
	public Item getThis(){
		return this;
	}

	public boolean isTaxable() {
		return this.data.taxable;
	}

	public boolean isDiscountable() {
		return this.data.discountable;
	}

	public boolean isLocked() {
		return this.data.locked;
	}

	public boolean isActive() {
		return this.data.active;
	}

	public void setId(int id) {
		this.data.id = id;
	}

	public void setTitle(String title) {
		this.data.title = title;
	}

	public void setDescription(String description) {
		this.data.description = description;
	}

	public void setImage(String image) {
		this.data.image = image;
	}

	public void setTags(String[] tags) {
		this.data.tags = tags;
	}

	public void setProperties(HashMap<String, String>[] properties) {
		this.data.properties = properties;
	}

	public void setSku(String sku) {
		this.data.sku = sku;
	}

	public void setPrice(int price) {
		this.data.price = price;
	}

	public void setTradeValue(int tradeValue) {
		this.data.tradeValue = tradeValue;
	}

	public void setCashValue(int cashValue) {
		this.data.cashValue = cashValue;
	}

	public void setTaxable(boolean taxable) {
		this.data.taxable = taxable;
	}

	public void setDiscountable(boolean discountable) {
		this.data.discountable = discountable;
	}

	public void setLocked(boolean locked) {
		this.data.locked = locked;
	}

	public void setActive(boolean active) {
		this.data.active = active;
	}

}