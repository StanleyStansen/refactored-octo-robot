package discount.model;

public class CustomerAddress {
	private int id;
	private Customer customer;
	private String street;
	private String postcode;
	private String city;
	
	/* Constructor */
	
	
	public CustomerAddress () {
		super();
	}
	
	public CustomerAddress(int customerId, String street, String postcode, String city) {
		this.id = customerId;
		this.street = street;
		this.postcode = postcode;
		this.city = city;
	}
	
	/* Getter und Setter */

	public int getId() {
		return id;
	}

	public void setId(int customerId) {
		this.id = customerId;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
