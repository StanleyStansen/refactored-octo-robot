package discount.model;

public interface DataHandlerAPI {
	
	public Customer getCustomer(String forename, String lastname);
	
	public boolean isDiscountable(double turnover);

}
