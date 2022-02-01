package PizzaProgram;

public class Address {
	private String streetAddress;
	private String city;
	private int zipCode;
	public Address(String street, String city, int zip){
		streetAddress = street;
		this.city = city;
		zipCode = zip;
	}
	public String toString(){
		return getStreetAddress() + ", " + getCity() + ". " + getZip() + ".";
	}
	public String getStreetAddress(){
		return streetAddress;
	}
	public String getCity(){
		return city;
	}
	public int getZip(){
		return zipCode;
	}

}
