package com.discountService;

import discount.model.Customer;
import discount.model.DataHandler;

public class CustomerService {

	private DataHandler dh = new DataHandler();
	
	public Customer getCustomer (int id) {
		return dh.getCustomer(id);
	}
	
	public boolean checkDiscount(Customer c) {
		return dh.checkDiscount(c);
	}

}
