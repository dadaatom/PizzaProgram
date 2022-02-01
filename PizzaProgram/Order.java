package PizzaProgram;

public class Order {
	private Account buyer;
	private Pizza pizza;
	private Side[] sides;
	private boolean hasSides;
	private double cost;
	
	String[] displayableList = new String[6];
	public Order(String[] myList){
		displayableList = myList;
	}
	
	public Order(Account buyer,Pizza pizza){
		this.buyer = buyer;
		this.pizza = pizza;
		hasSides = false;
	}
	public Order(Account buyer,Pizza pizza, Side[] sides){
		this.buyer = buyer;
		this.pizza = pizza;
		this.sides = sides;
		hasSides = true;
	}
	public Pizza getPizza(){
		return pizza;
	}
	public Side[] getSides(){
		return sides;
	}
	public boolean hasSide(){
		return hasSides;
	}
	public double getCost(){
		cost = pizza.getCost();
		if(hasSides){
			for(int i = 0; i < sides.length; i++){
				cost += sides[i].getCost();
			}
		}
		return (double)((int)(cost*100))/100;
	}
	public double getSidesCost(){
		double sideCost = 0;
		for(int i = 0; i < sides.length; i++){
			sideCost += sides[i].getCost();
		}
		return sideCost;
	}
	public Account getBuyer(){
		return buyer;
	}
	public String[] getList(){
		return displayableList;
	}
}
