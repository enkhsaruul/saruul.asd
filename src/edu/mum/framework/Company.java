package edu.mum.framework;

import java.util.List;

public class Company extends Customer implements ICompany {

	private int numOfEmployee;

	public Company(Address address, String name, String email, int numOfEmployee) {
		super(address, name, email);
		this.numOfEmployee = numOfEmployee;
	}

	@Override
	public String getCustomerType() {
		return "C";
	}
	
	public String[] toArray() {
		String[] s = {"Company", name, email, address.getCity(), address.getState(), address.getStreet(), address.getZip(), numOfEmployee + ""};
		return s;
	}
}
