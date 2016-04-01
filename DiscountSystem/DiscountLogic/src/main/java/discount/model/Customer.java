package discount.model;

import java.sql.Date;

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
	private String street;
	private String houseNumber;
	private String postcode;
	private String city;
	private Date birthday;
	
	/**
	 * The constructor of a customer needs only three attributes due to the assumption a customer does not neccessarely need to give his or her adress.
	 * @param lastname
	 * @param birthday
	 */
	public Customer (String forename, String lastname, Date birthday) {
		this.forename = forename;
		this.lastname = lastname;
		this.birthday = birthday;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
	
}


