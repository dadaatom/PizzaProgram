package PizzaProgram;

public class Pizza {
	private String size;
	private Topping[] pizzaToppings;
	private double totalCost;
	private boolean hasToppings;
	
	public Pizza(String pizzaSize, double price,Topping[] toppingList){
		pizzaToppings = toppingList;
		totalCost = price;
		size = pizzaSize;
		hasToppings = true;
	}
	public Pizza(String pizzaSize, double price){
		pizzaToppings = new Topping[0];
		size = pizzaSize;
		totalCost = price;
		hasToppings = false;
	}
	public int toppingCount(){
		return pizzaToppings.length;
	}
	public double getCost(){
		return totalCost;
	}
	public boolean hasToppings(){
		return hasToppings;
	}
	public String getSize(){
		return size;
	}
	public double toppingCost(){
		double total = 0;
		for(int i = 0; i < pizzaToppings.length; i++){
			total = pizzaToppings[i].getCost();
		}
		return total;
	}
}
