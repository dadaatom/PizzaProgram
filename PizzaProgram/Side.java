package PizzaProgram;

public class Side {
	private double price;
	private String name;
	public Side(String name, String type, double cost){
		this.name = name;
		price = cost;
	}
	public double getCost(){
		return price;
	}
}
