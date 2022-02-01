package PizzaProgram;

import java.util.ArrayList;

public class CustomerAccount extends Account implements Comparable<CustomerAccount>{
	private ArrayList<Order> orderHistory;
	private String userName;
	private String password;
	public CustomerAccount(String uName, String password, String creditCardNumber, String name, Address address){
		super(name,creditCardNumber,address);
		this.password = password;
		userName = uName;
		orderHistory = new ArrayList<Order>();
	}
	public CustomerAccount(String uName, String password){
		super();
		userName = uName;
		password = password;
		orderHistory = new ArrayList<Order>();
	}
	
	public String getUsername(){
		return userName;
	}
	public String getPassword(){
		return password;
	}
	
	@Override
	public int compareTo(CustomerAccount o) {
		return getUsername().compareTo(o.getUsername());
	}
	public String toString(){
		return userName;
	}
	
	public void setHistory(ArrayList<Order> history){
		orderHistory = history;
	}
	public void addToHistory(Order o){
		orderHistory.add(o);
	}
	public ArrayList<Order> getHistory(){
		return orderHistory;
	}
	
	public void setPassword(String newPassword){
		password = newPassword;
	}
}
