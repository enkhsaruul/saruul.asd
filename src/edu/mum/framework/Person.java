package edu.mum.framework;

public class Person extends Customer implements IPerson {
	
	private String birthdate;
	
	public Person(Address address, String name, String email, String birthdate) {
		super(address, name, email);
		this.birthdate = birthdate;
	}

	@Override
	public String getCustomerType() {
		return "P";
	}
	
	public String[] toArray() {
		String[] s = {"Person", name, email, address.getCity(), address.getState(), address.getStreet(), address.getZip(), birthdate};
		return s;
	}
	
}
