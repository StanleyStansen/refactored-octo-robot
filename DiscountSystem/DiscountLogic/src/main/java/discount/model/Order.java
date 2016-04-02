package discount.model;

public class Order {
	private int id;
	private double turnover;
	private int customerId;
		
	public Order() {
		super();
	}
	
	public Order(double d, int customerId) {
		super();
		this.turnover = d;
		this.customerId = customerId;
	}
	
	// Getters und Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getTurnover() {
		return turnover;
	}
	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
	
}
