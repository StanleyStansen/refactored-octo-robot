package service;

import discount.model.Customer;
import java.sql.Date;

public class CustomerService {
	
	public void setCustomer(String forename, String lastname, Date birthday) {
		Customer c = new Customer(forename, lastname, birthday);
		// TBD: saving Customer to database
		return;
	}
	
	public Customer getCustomer (int id) {
		// TBD: retrieving a specific customer from database in Project DiscountSystem, currently dummy implementation
		Customer c = new Customer("Erhard", "Pech", new Date(31536000000l));
		return c;
	}

}


