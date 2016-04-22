package discount.model;

public interface DataHandlerAPI {
	
	public Customer getCustomer(int id);
	
	public boolean checkDiscount(Customer c);

}
