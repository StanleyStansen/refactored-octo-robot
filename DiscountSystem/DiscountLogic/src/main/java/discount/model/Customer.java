package discount.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.*;

/**
 * This class implements the entity "Customer".
 * @author Richard
 * @version 01.04.2016
 *
 */
public class Customer {

	private int id;
	private String forename;
	private String lastname;
	private Date birthday;
	private CustomerAddress address;
	
	/**
	 * The constructor of a customer needs only three attributes due to the assumption a customer does not neccessarely need to give his or her adress.
	 * @param lastname
	 * @param birthday
	 */
	public Customer () {
		super();
	}
	public Customer (String forename, String lastname, Date birthday, CustomerAddress address) {
		this.forename = forename;
		this.lastname = lastname;
		this.birthday = birthday;
		this.address = address;
	}
	
	// Getters / Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public CustomerAddress getAddress() {
		return address;
	}
	public void setAddress(CustomerAddress address) {
		this.address = address;
	}
	
	public String toString() {
		return "User-ID " +  this.getId() + ": \n Vorname: " + this.getForename() + "\n Nachname: " + this.getLastname() + "\n Geburtsdatum: " + this.getBirthday() + "\n Straße: " + this.address.getStreet() + "\n PLZ: " + this.address.getPostcode() + "\n Stadt: " + this.address.getCity() + ".";
		//return "User-ID " + this.getCustomerId() + ": " + this.getForename() + " " + this.getLastname();
	}
	
	
	
}


