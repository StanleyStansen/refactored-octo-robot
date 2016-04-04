package com.discountService;

import discount.model.Customer;
import discount.model.DataHandler;

public class CustomerService {

	public Customer getCustomer (String forename, String lastname) {
		DataHandler h = new DataHandler();
		return h.getCustomer(forename, lastname);
	}

}
