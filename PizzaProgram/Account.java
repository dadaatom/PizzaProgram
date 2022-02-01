package PizzaProgram;

public class Account {
	private String creditCardNumber;
	private String customerName;
	private Address address;
	
	public Account(String name, String cardNumber, Address customerAddress){
		customerName = name;
		creditCardNumber = cardNumber;
		address = customerAddress;
	}
	public Account(){}
	
	public String getCardNumber(){
		return creditCardNumber;
	}
	public String getName(){
		return customerName;
	}
	public Address getAdress(){
		return address;
	}
}
