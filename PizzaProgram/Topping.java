package PizzaProgram;

public class Topping {
	private double cost;
	private String toppingName;
	private String type;
	
	public Topping(String name, String type, double cost){
		toppingName = name;
		this.type = type;
		this.cost = cost;
		
	}
	
	public String getType(){
		return toppingName;
	}
	public double getCost(){
		return cost;
	}
}
