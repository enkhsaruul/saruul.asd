package edu.mum.framework;

public class Address {

	private String state;
	private String zip;
	private String street;
	private String city;

	public Address(String state, String zip, String street, String city) {
		super();
		this.state = state;
		this.zip = zip;
		this.street = street;
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}
	
	public String toString() {
		return street + ", " + city + ", " + state + ", " + zip; 
	}
}
