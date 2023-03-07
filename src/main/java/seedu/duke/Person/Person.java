package seedu.duke.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Person {
	protected String name;
	protected String email;
	protected String contactNumber;
	protected static Map<String, ArrayList<String>> patientsList = new HashMap<String, ArrayList<String>>();

	/**
	 * To create a new person object (i.e. register a new person in the system)
	 * @param newDetails The new details of the new patient
	 */
	public Person(ArrayList<String> newDetails) {
		this.setName(newDetails.get(0));
		this.setAddress(newDetails.get(1));
		this.setContactNumber(newDetails.get(2));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setAddress(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void loadHistory(String key, String toAdd) {
		patientsList.get(key).add(toAdd);
	}
}
