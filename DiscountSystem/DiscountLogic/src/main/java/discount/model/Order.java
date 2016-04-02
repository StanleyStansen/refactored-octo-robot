package discount.model;

public class Order {
	private int id;
	private float turnover;
	private int customerId;
	
	// Getters und Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getTurnover() {
		return turnover;
	}
	public void setTurnover(float turnover) {
		this.turnover = turnover;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
}
