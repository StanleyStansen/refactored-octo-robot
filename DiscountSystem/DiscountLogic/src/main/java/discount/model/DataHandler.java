package discount.model;

import org.hibernate.Session;

public class DataHandler implements DataHandlerAPI {

	@Override
	public Customer getCustomer(String forename, String lastname) {
		// TBD: retrieve Customer from Database		
		return null;
	}

	@Override
	public boolean isDiscountable(double turnover) {
		// TODO Auto-generated method stub
		return false;
	}

}
