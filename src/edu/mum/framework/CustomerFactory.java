package edu.mum.framework;

import java.util.List;

import edu.mum.framework.gui.GUIDialog;

public class CustomerFactory {
	public static ICustomer createCustomerFromDialog(String type, GUIDialog dialog) {
		if (type.equals("Person")) {
			List<String> fields = dialog.getValues();
			return new Person(
					new Address(fields.get(3), fields.get(4), fields.get(1), fields.get(2)),
					fields.get(0),
					fields.get(6),
					fields.get(5)
					);
		}
		else {
			List<String> fields = dialog.getValues();
			return new Company(
					new Address(fields.get(3), fields.get(4), fields.get(1), fields.get(2)),
					fields.get(0),
					fields.get(6),
					Integer.parseInt(fields.get(5))
					);
		}
	}
}
